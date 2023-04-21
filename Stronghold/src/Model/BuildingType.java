package Model;

import java.util.ArrayList;

public enum BuildingType {
    MILL(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0,0,0);

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    BuildingType(int hp, String name, int workers, int engineers, int gold, int wood, int iron, int stone, String type, int capacity, int rate, int fireRange, int defendRange) {
        this.hp = hp;
        this.name = name;
        this.Workers = workers;
        this.engineers = engineers;
        this.gold = gold;
        this.wood = wood;
        this.Iron = iron;
        this.stone = stone;
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
    }

    public int getRate() {
        return rate;
    }

    private final int hp;
    private final String name;
    private final int Workers;
    private final int engineers;
    private final int gold;
    private final int wood;
    private final int Iron;
    private final int stone;
    private final String type;
    private final int rate;
    private final int capacity;
    private int fireRange;
    private int defendRange;

    public int getCapacity() {
        return capacity;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getWorkers() {
        return Workers;
    }

    public int getEngineers() {
        return engineers;
    }

    public int getGold() {
        return gold;
    }

    public int getWood() {
        return wood;
    }

    public int getIron() {
        return Iron;
    }

    public int getStone() {
        return stone;
    }

    public String getType() {
        return type;
    }

    public static BuildingType getBuildingByName(String name) {
        for (BuildingType building : BuildingType.values()) {
            if (name.equalsIgnoreCase(building.name))
                return building;
        }
        return null;
    }
}
