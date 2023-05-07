package Model;

import Controller.GameMenuController;
import Enums.BuildingType;

public class Building {
    private int hp;
    private String name;
    private int rate;
    private int freeCapacity;
    private final BuildingType buildingType;
    private final Empire owner;
    private String mode;//this is for the buildings like armourers that can produce different things

    public Building(BuildingType buildingType, Empire owner) {
        this.owner = owner;
        this.name = buildingType.getName();
        this.hp = buildingType.getHp();
        this.freeCapacity = buildingType.getCapacity();
        this.buildingType = buildingType;
        this.rate = buildingType.getRate() + (int) (-0.5 * owner.getFearRate());
        if (buildingType.getName().equals("Church") || buildingType.getName().equals("Cathedral")) {
            owner.addReligionPopularity(2);
        }
        if (buildingType.getName().equals("Hovel")) {
            owner.addUnemployedPeople(8);
        }
        if (buildingType.getName().equals("Fletcher")) {
            this.mode = "bow";
        }
        if (buildingType.getName().equals("BlackSmith")) {
            this.mode = "sword";
        }
        if (buildingType.getName().equals("PoleTurner")) {
            this.mode = "spear";
        }
        if (buildingType.getName().equals("Armoury")) {
            GameMenuController.getCurrentEmpire().getArmoury().addFreeCapacityArmoury(50);
        }
        if (buildingType.getName().equals("FoodStock")) {
            GameMenuController.getCurrentEmpire().getFoodStock().addFreeCapacityFoodStock(250);
        }
        if (buildingType.getName().equals("Stockpile")) {
            GameMenuController.getCurrentEmpire().getResources().addFreeCapacityStockpile(190);
        }
        if (buildingType.getName().equals("Inn")) {
            //TODO check for the change of popularity
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public int getFreeCapacity() {
        return freeCapacity;
    }

    public void addFreeCapacity(int amount) {
        this.freeCapacity += amount;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}