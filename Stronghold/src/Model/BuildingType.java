package Model;

import java.util.ArrayList;

public enum BuildingType {
    SMALL_STONE_GATEHOUSE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BIG_STONE_GATEHOUSE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    DRAW_BRIDGE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    LOOKOUT_TOWER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    PERIMETER_TOWER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    DEFENCIVE_TURRET(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    SQUARE_TOWER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    CIRCLE_TOWER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    ARMOURY(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BARRACK(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    MERCENARY_POST(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    ENGINEER_GUILD(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    KILLING_PIT(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    INN(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    MILL(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    IRON_MINE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    MARKET(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    OW_TETHER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    PITCH_RIG(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    QUARRY(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    STOCKPILE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    WOODCUTTER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    HOVEL(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    CHURCH(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    CATHEDRAL(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    ARMOURER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BLACKSMITH(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    FLETCHER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    POLETURNER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    OIL_SMELTER(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    PITCH_DITCH(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    CAGED_WAR_DOGS(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    SIEGE_TENT(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    STABLE(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    APPLE_GARDEN(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    DAIRY_PRODUCTS(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BARLEY_FIELD(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    HUNTING_POST(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    WHEAT_FIELD(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BAKERY(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    BREWING(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0),
    FOOD_STOCK(10, "Mill", 1, 0, 0, 0, 0, 10, "defend", 0, 0, 0, 0);


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

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public static BuildingType getBuildingByName(String name) {
        for (BuildingType building : BuildingType.values()) {
            if (name.equalsIgnoreCase(building.name))
                return building;
        }
        return null;
    }
}
