package Controller;

import Model.*;

import java.util.regex.Matcher;

public class GameMenuController {
    private static Game game;
    private static Empire currentEmpire;
    private Building selectedBuilding;
    private Unit selectedUnit;
    private Machine selectedMachine;

    public static Empire getCurrentEmpire() {
        return currentEmpire;
    }

    public static Game getGame() {
        return game;
    }

    public String startANewGame(String command) {
        return null;
    }

    public void dropBuilding(Matcher matcher) {
    }

    public void selectBuilding(Matcher matcher) {
    }

    public void repair() {
    }

    public void selectUnit(Matcher matcher) {
    }

    public void moveUnit(Matcher matcher) {
    }

    public void patrolUnit(Matcher matcher) {
    }

    public void setUnitMode(Matcher matcher) {
    }

    public void attackEnemy(Matcher matcher) {
    }

    public void attackLocation(Matcher matcher) {
    }

    public void pourOil(Matcher matcher) {
    }

    public void burnOil(Matcher matcher) {
    }

    public void digTunnel(Matcher matcher) {
    }

    public void buildEquipment(Matcher matcher) {
    }

    public void disbandUnit() {
    }

    public void nextTurn() {
    }

    private void checkFightUnits() {
    }

    private void checkFightMachines() {
    }

    public void checkFoodProductiveBuildings() {// each building produces if there is enough free space in the foodStock
        for (int i = 0; i < currentEmpire.getBuildings().size(); i++) {
            String name = currentEmpire.getBuildings().get(i).getName();
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
            switch (currentEmpire.getBuildings().get(i).getName()) {
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
            switch (currentEmpire.getBuildings().get(i).getName()) {
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