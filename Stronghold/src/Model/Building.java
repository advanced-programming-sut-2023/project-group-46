package Model;

import Enums.BuildingType;

public class Building {
    private int hp;
    private String name;
    private int rate;
    private int freeCapacity;
    private final BuildingType buildingType;
    private final Empire owner;

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
}