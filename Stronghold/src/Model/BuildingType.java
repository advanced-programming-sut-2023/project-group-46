package Model;

public enum BuildingType {
    SMALL_STONE_GATEHOUSE(10, "SmallStoneGatehouse", 0, 0, 0, 0, 20, "CastleBuildings", 25, 0, 0),
    BIG_STONE_GATEHOUSE(10, "BigStoneGatehouse", 0, 0, 0, 0, 10, "CastleBuildings", 50, 0, 0),
    DRAW_BRIDGE(10, "DrawBridge", 0, 0, 10, 0, 0, "CastleBuildings", 0, 0, 0),
    LOOKOUT_TOWER(10, "LookoutTower", 0, 0, 0, 0, 10, "CastleBuildings", 5, 0, 0),
    PERIMETER_TOWER(10, "PerimeterTower", 0, 0, 0, 0, 10, "CastleBuildings", 15, 0, 0),
    DEFENCIVE_TURRET(10, "DefenciveTurret", 0, 0, 0, 0, 15, "CastleBuildings", 20, 0, 0),
    SQUARE_TOWER(10, "SquareTower", 0, 0, 0, 0, 35, "CastleBuildings", 35, 0, 0),
    ROUND_TOWER(10, "RoundTower", 0, 0, 0, 0, 40, "CastleBuildings", 40, 0, 0),
    ARMOURY(10, "Armoury", 0, 0, 5, 0, 0, "CastleBuildings", 50, 0, 0),
    BARRACK(10, "Barrack", 0, 0, 0, 0, 15, "CastleBuildings", 0, 0, 0),
    MERCENARY_POST(10, "MercenaryPost", 0, 0, 10, 0, 0, "CastleBuildings", 0, 0, 0),
    ENGINEER_GUILD(10, "EngineerGuild", 0, 100, 10, 0, 0, "CastleBuildings", 0, 0, 0),
    KILLING_PIT(10, "KillingPit", 0, 0, 6, 0, 0, "CastleBuildings", 0, 0, 0),
    INN(10, "Inn", 1, 100, 20, 0, 0, "FoodProcessingBuildings", 0, 0, 0),
    MILL(10, "Mill", 3, 0, 20, 0, 0, "FoodProcessingBuildings", 3, 0, 0),
    IRON_MINE(10, "IronMine", 2, 0, 20, 0, 0, "Industry", 5, 0, 0),
    MARKET(10, "Market", 1, 0, 5, 0, 0, "Industry", 0, 0, 0),
    OW_TETHER(10, "OwTether", 1, 0, 5, 0, 0, "Industry", 4, 0, 0),
    PITCH_RIG(10, "PitchRig", 1, 0, 20, 0, 0, "Industry", 0, 0, 0),
    QUARRY(10, "Quarry", 3, 0, 20, 0, 0, "Industry", 5, 0, 0),
    STOCKPILE(10, "Stockpile", 0, 0, 0, 0, 0, "Industry", 40, 0, 0),
    WOODCUTTER(10, "Woodcutter", 1, 0, 3, 0, 0, "Industry", 18, 0, 0),
    HOVEL(10, "Hovel", 0, 0, 6, 0, 0, "TownBuildings", 0, 0, 0),
    CHURCH(10, "Church", 0, 250, 0, 0, 0, "TownBuildings", 0, 0, 0),
    CATHEDRAL(10, "Cathedral", 0, 1000, 0, 0, 0, "TownBuildings", 0, 0, 0),
    ARMOURER(10, "Armourer", 1, 100, 20, 0, 0, "Weapon", 1, 0, 0),
    BLACKSMITH(10, "BlackSmith", 1, 100, 20, 0, 0, "Weapon", 1, 0, 0),
    FLETCHER(10, "Fletcher", 1, 100, 20, 0, 0, "Weapon", 1, 0, 0),
    POLE_TURNER(10, "PoleTurner", 1, 100, 10, 0, 0, "Weapon", 1, 0, 0),
    OIL_SMELTER(10, "OilSmelter", 0, 100, 0, 10, 0, "CastleBuildings", 0, 0, 0),
    PITCH_DITCH(10, "PitchDitch", 0, 0, 0, 0, 0, "CastleBuildings", 0, 0, 0),//1 pitch for 4 pitchditch
    CAGED_WAR_DOGS(10, "CagedWarDogs", 0, 100, 10, 0, 0, "CastleBuildings", 0, 0, 0),
    SIEGE_TENT(10, "SiegeTent", 0, 0, 0, 0, 0, "CastleBuildings", 0, 0, 0),
    STABLE(10, "Stable", 0, 400, 20, 0, 0, "CastleBuildings", 6, 0, 0),
    APPLE_ORCHARD(10, "AppleOrchard", 1, 0, 5, 0, 0, "FarmBuildings", 4, 0, 0),
    DAIRY_ّFARM(10, "DairyFarm", 1, 0, 10, 0, 0, "FarmBuildings", 3, 0, 0),
    HOPS_FARM(10, "HopsFarm", 1, 0, 15, 0, 0, "FarmBuildings", 4, 0, 0),
    HUNTING_POST(10, "HuntingPost", 1, 0, 5, 0, 0, "FarmBuildings", 4, 0, 0),
    WHEAT_FARM(10, "WheatFarm", 1, 0, 15, 0, 0, "FarmBuildings", 2, 0, 0),
    BAKERY(10, "Bakery", 1, 0, 10, 0, 0, "FoodProcessingBuildings", 8, 0, 0),
    BREWERY(10, "Brewery", 1, 0, 10, 0, 0, "FoodProcessingBuildings", 1, 0, 0),
    FOOD_STOCK(10, "FoodStock", 0, 0, 5, 0, 0, "FoodProcessingBuildings", 200, 0, 0);


    BuildingType(int hp, String name, int workers, int gold, int wood, int iron, int stone, String type, int capacity, int rate, int fireRange) {
        this.hp = hp;
        this.name = name;
        this.Workers = workers;
        this.gold = gold;
        this.wood = wood;
        this.Iron = iron;
        this.stone = stone;
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
        this.fireRange = fireRange;
    }

    public int getRate() {
        return rate;
    }

    private final int hp;
    private final String name;
    private final int Workers;
    private final int gold;
    private final int wood;
    private final int Iron;
    private final int stone;
    private final String type;
    private final int rate;
    private final int capacity;
    private int fireRange;

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

    public static BuildingType getBuildingByName(String name) {
        for (BuildingType building : BuildingType.values()) {
            if (name.equalsIgnoreCase(building.name))
                return building;
        }
        return null;
    }
}
