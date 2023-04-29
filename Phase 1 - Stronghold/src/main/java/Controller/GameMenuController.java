package Controller;

import Enums.BuildingType;
import Enums.UnitType;
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameMenuController {
    private static Game game;
    private static Empire currentEmpire;
    private final ArrayList<Unit> selectedUnits;
    private final HashMap<String, int[]> selectedCoordinates;//keys are building , unit
    private Building selectedBuilding;
    private Map map;//zero base

    public GameMenuController() {
        selectedUnits = new ArrayList<>();
        selectedCoordinates = new HashMap<>();
    }

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static Game getGame() {
        return game;
    }

    private ArrayList<Cell> neighbors(int x, int y) {
        ArrayList<Cell> cells = new ArrayList<>();
        if (x > 0) {
            cells.add(map.getMap()[x - 1][y]);
        }
        if (x < map.getSize() - 1) {
            cells.add(map.getMap()[x + 1][y]);
        }
        if (y > 0) {
            cells.add(map.getMap()[x][y - 1]);
        }
        if (y < map.getSize() - 1) {
            cells.add(map.getMap()[x][y + 1]);
        }
        return cells;
    }

    private boolean enemyInThisCell(Cell cell) {
        for (int i = 0; i < cell.getUnits().size(); i++) {
            if (!cell.getUnits().get(i).getOwner().equals(currentEmpire)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyEnemyNear(int x, int y) {//3*3 map that x,y is in the center
        if (enemyInThisCell(map.getMap()[x][y])) return true;
        if (x > 0 && enemyInThisCell(map.getMap()[x - 1][y])) return true;
        if (y > 0 && enemyInThisCell(map.getMap()[x][y - 1])) return true;
        if (x < map.getSize() - 1 && enemyInThisCell(map.getMap()[x + 1][y])) return true;
        if (y < map.getSize() - 1 && enemyInThisCell(map.getMap()[x][y + 1])) return true;
        if (x > 0 && y > 0 && enemyInThisCell(map.getMap()[x - 1][y - 1])) return true;
        if (x > 0 && y < map.getSize() - 1 && enemyInThisCell(map.getMap()[x - 1][y + 1])) return true;
        if (y > 0 && x < map.getSize() - 1 && enemyInThisCell(map.getMap()[x + 1][y - 1])) return true;
        if (y < map.getSize() - 1 && x < map.getSize() - 1 && enemyInThisCell(map.getMap()[x + 1][y + 1])) return true;

        return false;
    }

    public String startANewGame(String command) throws Exception {
        String[] usernames = command.split("/");
        int players = 1;
        for (String username : usernames) {
            if (User.getUserByUsername(username) == null) {
                return username + " doesn't exist";
            }
            players++;
        }
        if (players < 2) {
            return "choose more players";
        } else if (players > 8) {
            return "choose fewer players";
        } else if (map.getEmpireCoordinates().size() < players) {
            return "this map doesn't have enough capacity for this players";
        } else {
            game = new Game(map.getMap());
            for (int i = 0; i < usernames.length; i++) {
                game.getEmpires().add(new Empire(User.getUserByUsername(usernames[i]), i, map.getEmpireCoordinates().get(i)[0], map.getEmpireCoordinates().get(i)[1]));
            }
            currentEmpire = game.getEmpires().get(0);
            return "Game Started";
        }
        //TODO check for drop keep building and also first stockpiles
    }

    public String dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String buildingName = matcher.group("type");
        if (BuildingType.getBuildingByName(buildingName) == null) {
            return "invalid building type";
        }
        if (x > map.getSize() || x < 0 || y > map.getSize() || y < 0) {
            return "invalid coordinate";
        }
        if (map.getMap()[x][y].getBuilding() != null || map.getMap()[x][y].getUnits() != null || map.getMap()[x][y].getEnvironmentName() != null) {
            return "there are some other things in this place";
        }
        String cellType = map.getMap()[x][y].getEnvironmentName();
        if (BuildingType.getBuildingByName(buildingName).getType().equals("FarmBuildings")) {
            if (!cellType.equals("thickGrass") && !cellType.equals("oasisGrass")) {
                return "can't drop farm buildings in this place";
            }
        } else if (buildingName.equals("IronMine")) {
            if (!cellType.equals("ironTexture")) {
                return "IronMine must be built on IronTexture";
            }
        } else if (buildingName.equals("Quarry")) {
            if (!cellType.equals("boulder")) {
                return "Quarry must be built on Boulder";
            }
        } else if (buildingName.equals("PitchRig")) {
            if (!cellType.equals("oil")) {//TODO check for environment type
                return "PitchRig must be built on Oil";
            }
        } else {//regular buildings
            if (!cellType.equals("earth") && !cellType.equals("earthAndStone") && !cellType.equals("scrub") && !cellType.equals("thickGrass") && !cellType.equals("oasisGrass") && !cellType.equals("beach")) {
                return "can't drop this building in this place";
            }
            if (buildingName.equals("DrawBridge")) {
                boolean bool = false;
                for (int i = 0; i < neighbors(x, y).size(); i++) {
                    if (neighbors(x, y).get(i).getBuilding() != null && (neighbors(x, y).get(i).getBuilding().getBuildingType().getName().equals("SmallStoneGatehouse") || neighbors(x, y).get(i).getBuilding().getBuildingType().getName().equals("BigStoneGatehouse"))) {
                        bool = true;
                    }
                }
                if (!bool) {
                    return "DrawBridge must be built near Gates";
                }
            }
            if (buildingName.equals("Armoury") && currentEmpire.haveThisBuilding(buildingName)) {
                boolean bool = false;
                for (int i = 0; i < neighbors(x, y).size(); i++) {
                    if (neighbors(x, y).get(i).getBuilding() != null && neighbors(x, y).get(i).getBuilding().getBuildingType().getName().equals("Armoury")) {
                        bool = true;
                    }
                }
                if (!bool) {
                    return "Armoury must be built near other Armouries";
                }
            }
            if (buildingName.equals("FoodStock") && currentEmpire.haveThisBuilding(buildingName)) {
                boolean bool = false;
                for (int i = 0; i < neighbors(x, y).size(); i++) {
                    if (neighbors(x, y).get(i).getBuilding() != null && neighbors(x, y).get(i).getBuilding().getBuildingType().getName().equals("FoodStock")) {
                        bool = true;
                    }
                }
                if (!bool) {
                    return "FoodStock must be built near other FoodStocks";
                }
            }
            if (buildingName.equals("Stockpile") && currentEmpire.haveThisBuilding(buildingName)) {
                boolean bool = false;
                for (int i = 0; i < neighbors(x, y).size(); i++) {
                    if (neighbors(x, y).get(i).getBuilding() != null && neighbors(x, y).get(i).getBuilding().getBuildingType().getName().equals("Stockpile")) {
                        bool = true;
                    }
                }
                if (!bool) {
                    return "Stockpile must be built near other Stockpiles";
                }
            }
        }
        map.getMap()[x][y].setBuilding(new Building(BuildingType.getBuildingByName(buildingName), currentEmpire));
        currentEmpire.getBuildings().add(map.getMap()[x][y].getBuilding());
        return "success";
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (x > map.getSize() || x < 0 || y > map.getSize() || y < 0) {
            return "invalid coordinate";
        }
        if (map.getMap()[x][y].getBuilding() == null) {
            selectedBuilding = null;
            return "there is no building in this place";
        }
        if (!map.getMap()[x][y].getBuilding().getOwner().equals(currentEmpire)) {
            return "this building isn't yours";
        }
        selectedBuilding = map.getMap()[x][y].getBuilding();
        selectedCoordinates.put("building", new int[]{x, y});
        String name = selectedBuilding.getBuildingType().getName();
        if (name.equals("SmallStoneGatehouse") || name.equals("BigStoneGatehouse") || name.equals("LookoutTower") || name.equals("PerimeterTower") || name.equals("DefenciveTurret") || name.equals("SquareTower") || name.equals("RoundTower")) {
            return "hp of this building is " + selectedBuilding.getHp();
        }
        return map.getMap()[x][y].getBuilding().getBuildingType().getName() + " selected";
    }

    public String repair() {
        if (selectedBuilding == null) {
            return "you should select a building first";
        }
        String name = selectedBuilding.getBuildingType().getName();
        if (!(name.equals("SmallStoneGatehouse") || name.equals("BigStoneGatehouse") || name.equals("LookoutTower") || name.equals("PerimeterTower") || name.equals("DefenciveTurret") || name.equals("SquareTower") || name.equals("RoundTower"))) {
            return "this building won't repair";
        }
        if (anyEnemyNear(selectedCoordinates.get("building")[0], selectedCoordinates.get("building")[1])) {
            return "you can't repair your building while enemies are here";
        }
        if (currentEmpire.getResources().getStone() < (selectedBuilding.getBuildingType().getHp() - selectedBuilding.getHp()) / 500) {//TODO saghf function
            return "not enough stone to repair this building";
        }
        currentEmpire.getResources().addResource("stone", (selectedBuilding.getBuildingType().getHp() - selectedBuilding.getHp()) / 500);
        selectedBuilding.setHp(selectedBuilding.getBuildingType().getHp());
        return "success";
    }

    public void selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        selectedUnits.clear();
        selectedCoordinates.put("unit", new int[]{x, y});
        for (int i = 0; i < map.getMap()[x][y].getUnits().size(); i++) {
            if (map.getMap()[x][y].getUnits().get(i).getOwner().equals(currentEmpire)) {
                selectedUnits.add(map.getMap()[x][y].getUnits().get(i));
            }
        }
    }

    public void moveUnit(Matcher matcher) {
    }

    public void patrolUnit(Matcher matcher) {
    }

    public void setUnitMode(Matcher matcher) {
        String mode = matcher.group("mode");
        if (!mode.equals("standing") && !mode.equals("defensive") && !mode.equals("offensive")) {
            return;
        }
        for (Unit selectedUnit : selectedUnits) {
            selectedUnit.setMode(mode);
        }
    }

    public void attackEnemy(Matcher matcher) {
    }

    public void attackLocation(Matcher matcher) {
    }

    public void pourOil(Matcher matcher) {
    }

    public String createUnit(Matcher matcher) {
        String type = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        int cost = UnitType.getUnitByName(type).getCost() * count;
        if (!selectedBuilding.getBuildingType().getName().equals("MercenaryPost") && !selectedBuilding.getBuildingType().getName().equals("Barrack") && !selectedBuilding.getBuildingType().getName().equals("EngineerGuild")) {
            return "select proper building";
        }
        if (UnitType.getUnitByName(type) == null) {
            return "invalid name for unit";
        }
        if (selectedBuilding.getBuildingType().getName().equals("Barrack")) {
            if (!type.equals("Archer") && !type.equals("Crossbowman") && !type.equals("Spearman") && !type.equals("Pikeman") && !type.equals("Maceman") && !type.equals("Swordsman") && !type.equals("Knight")) {
                return "Barrack can't create " + type;
            }
            if (currentEmpire.getResources().getGold() < cost) {
                return "not enough gold";
            }
            if (type.equals("Archer")) {
                if (currentEmpire.getArmoury().getBow() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("bow", -1 * count);
            } else if (type.equals("Crossbowman")) {
                if (currentEmpire.getArmoury().getLeatherArmor() < count || currentEmpire.getArmoury().getCrossbow() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("crossbow", -1 * count);
                currentEmpire.getArmoury().addArmoury("leatherArmor", -1 * count);
            } else if (type.equals("Spearman")) {
                if (currentEmpire.getArmoury().getSpear() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("spear", -1 * count);
            } else if (type.equals("Pikeman")) {
                if (currentEmpire.getArmoury().getPike() < count || currentEmpire.getArmoury().getMetalArmor() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("pike", -1 * count);
                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * count);
            } else if (type.equals("Maceman")) {
                if (currentEmpire.getArmoury().getMace() < count || currentEmpire.getArmoury().getLeatherArmor() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("mace", -1 * count);
                currentEmpire.getArmoury().addArmoury("leatherArmor", -1 * count);
            } else if (type.equals("Swordsman")) {
                if (currentEmpire.getArmoury().getSword() < count || currentEmpire.getArmoury().getMetalArmor() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("sword", -1 * count);
                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * count);
            } else if (type.equals("Knight")) {
                if (currentEmpire.getArmoury().getSword() < count || currentEmpire.getArmoury().getMetalArmor() < count || currentEmpire.getArmoury().getHorse() < count) {
                    return "weapons needed";
                }
                currentEmpire.getArmoury().addArmoury("sword", -1 * count);
                currentEmpire.getArmoury().addArmoury("metalArmor", -1 * count);
                currentEmpire.getArmoury().addHorse(-1 * count);
            }
        } else if (selectedBuilding.getBuildingType().getName().equals("MercenaryPost")) {
            if (!type.equals("FireThrower") && !type.equals("ArcherBow") && !type.equals("Slaves") && !type.equals("Slinger") && !type.equals("Assassin") && !type.equals("HorseArcher") && !type.equals("ArabianSwordsmen")) {
                return "MercenaryPost can't create " + type;
            }
            if (currentEmpire.getResources().getGold() < cost) {
                return "not enough gold";
            }

        } else if (selectedBuilding.getBuildingType().getName().equals("EngineerGuild")) {
            if (!type.equals("Ladderman") && !type.equals("Engineer")) {
                return "EngineerGuild can't create " + type;
            }
            if (currentEmpire.getResources().getGold() < cost) {
                return "not enough gold";
            }
        }
        currentEmpire.getResources().addResource("gold", -1 * cost);
        int size = map.getMap()[currentEmpire.getKeepCoordinates()[0]][currentEmpire.getKeepCoordinates()[1]].getUnits().size();
        for (int i = 0; i < count; i++) {
            map.getMap()[currentEmpire.getKeepCoordinates()[0]][currentEmpire.getKeepCoordinates()[1]].getUnits().add(new Unit(UnitType.getUnitByName(type), currentEmpire));
            currentEmpire.getUnits().add(map.getMap()[currentEmpire.getKeepCoordinates()[0]][currentEmpire.getKeepCoordinates()[1]].getUnits().get(size + i));
        }
        return "success";
    }

    public void digTunnel(Matcher matcher) {
    }

    public void changeArmourProdoucerMode() {

    }

    public String buildEquipment(Matcher matcher) {
        String type = matcher.group("equipment");
        if (!type.equals("PortableShield") && !type.equals("BatteringRam") && !type.equals("Trebuchet") && !type.equals("Catapult") && !type.equals("FireBallista") && !type.equals("SiegeTower")) {
            return "invalid type for equipment";
        }
    }

    public void disbandUnit() {
    }

    public void nextTurn() {//TODO make selecteds null
    }

    private void checkFightUnits() {
    }

    private void checkFightMachines() {
    }

    public void checkFoodProductiveBuildings() {// each building produces if there is enough free space in the foodStock
        for (int i = 0; i < currentEmpire.getBuildings().size(); i++) {
            String name = currentEmpire.getBuildings().get(i).getBuildingType().getName();
            if (!name.equals("AppleOrchard") && !name.equals("HuntingPost") && !name.equals("Bakery") && !name.equals("DairyFarm")) {
                continue;
            }
            int rate = currentEmpire.getBuildings().get(i).getRate();
            int productEachRate = currentEmpire.getBuildings().get(i).getBuildingType().getCapacity();
            if (currentEmpire.getFoodStock().getFreeCapacityFoodStock() < productEachRate * rate) {
                continue;//Not Enough space in the FoodStock
            }
            switch (name) {
                case "AppleOrchard" -> {
                    currentEmpire.getFoodStock().addFood("apple", productEachRate * rate);
                }
                case "HuntingPost" -> {
                    currentEmpire.getFoodStock().addFood("meat", productEachRate * rate);
                }
                case "Bakery" -> {
                    if (currentEmpire.getResources().getFlour() >= rate) {
                        currentEmpire.getResources().addResource("flour", -1 * rate);
                        currentEmpire.getFoodStock().addFood("bread", productEachRate * rate);
                    }
                }
                case "DairyFarm" -> {
                    currentEmpire.getFoodStock().addFood("cheese", productEachRate * rate);
                }
            }
        }
    }

    public void checkArmourProductiveBuildings() {// each armour producer , produce 1 item in 1 turn
        for (int i = 0; i < currentEmpire.getBuildings().size(); i++) {
            if (!currentEmpire.getBuildings().get(i).getBuildingType().getType().equals("Weapon")) {
                continue;
            }
            if (currentEmpire.getArmoury().getFreeCapacityArmoury() < 1) {
                continue;
            }
            switch (currentEmpire.getBuildings().get(i).getBuildingType().getName()) {
                case "Fletcher" -> {
                    if (currentEmpire.getResources().getWood() >= 1) {
                        currentEmpire.getResources().addResource("wood", -1);
                        if (currentEmpire.getBuildings().get(i).getMode().equals("bow")) {
                            currentEmpire.getArmoury().addArmoury("bow", 1);
                        } else {
                            currentEmpire.getArmoury().addArmoury("crossbow", 1);
                        }
                    }
                }
                case "DairyFarm" -> {
                    currentEmpire.getArmoury().addArmoury("leatherArmor", 1);
                }
                case "BlackSmith" -> {
                    if (currentEmpire.getResources().getIron() >= 1) {
                        currentEmpire.getResources().addResource("iron", -1);
                        if (currentEmpire.getBuildings().get(i).getMode().equals("sword")) {
                            currentEmpire.getArmoury().addArmoury("sword", 1);
                        } else {
                            currentEmpire.getArmoury().addArmoury("mace", 1);
                        }
                    }
                }
                case "PoleTurner" -> {
                    if (currentEmpire.getResources().getWood() >= 1) {
                        currentEmpire.getResources().addResource("wood", -1);
                        if (currentEmpire.getBuildings().get(i).getMode().equals("spear")) {
                            currentEmpire.getArmoury().addArmoury("spear", 1);
                        } else {
                            currentEmpire.getArmoury().addArmoury("pike", 1);
                        }
                    }
                }
                case "Armourer" -> {
                    currentEmpire.getArmoury().addArmoury("metalArmor", 1);
                }
            }
        }
    }

    public void checkResourceProductiveBuildings() {
        for (int i = 0; i < currentEmpire.getBuildings().size(); i++) {
            int rate = currentEmpire.getBuildings().get(i).getRate();
            int productEachRate = currentEmpire.getBuildings().get(i).getBuildingType().getCapacity();
            switch (currentEmpire.getBuildings().get(i).getBuildingType().getName()) {
                case "Mill" -> {
                    if (currentEmpire.getResources().getWheat() < rate) {
                        continue;
                    }
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * (rate - 1)) {//(rate-1) because we use rate amount of resources from stockpile
                        continue;
                    }
                    currentEmpire.getResources().addResource("flour", productEachRate * rate);
                    currentEmpire.getResources().addResource("wheat", -1 * rate);
                }
                case "IronMine" -> {
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * rate) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("iron", productEachRate * rate);
                }
                case "PitchRig" -> {
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * rate) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("pitch", productEachRate * rate);
                }
                case "Woodcutter" -> {
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * rate) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("wood", productEachRate * rate);
                }
                case "HopsFarm" -> {
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * rate) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("hop", productEachRate * rate);
                }
                case "WheatFarm" -> {
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * rate) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("wheat", productEachRate * rate);
                }
                case "Brewery" -> {
                    if (currentEmpire.getResources().getHop() < rate) {
                        continue;
                    }
                    if (currentEmpire.getResources().getFreeCapacityStockpile() < productEachRate * (rate - 1)) {
                        continue;
                    }
                    currentEmpire.getResources().addResource("ale", productEachRate * rate);
                    currentEmpire.getResources().addResource("hop", -1 * rate);
                }
            }//TODO add quarry and OxTether
        }
    }

    private void removeDestroyedThings() {
    }

    private void findDirectionForMovements() {
    }

    private void checkEndOfTheGame() {
    }
}