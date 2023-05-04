package Model;

import Controller.GameMenuController;
import Enums.BuildingType;

public class Building {
    private BuildingType buildingType;
    private Empire owner;
    private int hp;
    private int rate;
    private int freeCapacity;
    private String mode;//this is for the buildings like armourers that can produce different things
    public Building(BuildingType buildingType, Empire owner) {
        this.owner = owner;
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
        if (buildingType.getName().equals("Stable")) {
            GameMenuController.getCurrentEmpire().getArmoury().addHorse(4);
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
    public Building(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public Building() {
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public Empire getOwner() {
        return owner;
    }

    public void setOwner(Empire owner) {
        this.owner = owner;
    }

    public int getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(int freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public void addFreeCapacity(int amount) {
        this.freeCapacity += amount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}