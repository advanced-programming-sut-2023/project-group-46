package Enums;

public enum UnitType {
    ARCHER("Archer",  "Bow", null, 40, 200, "high", 12, 10),
    CROSSBOWMEN("Crossbowmen",  "Crossbow", "LeatherArmor", 120, 600, "low", 20, 9),
    SPEAR_MEN("spearmen",  "Spear", null, 30, 150, "medium", 8,  0),
    PIKE_MEN("pikmen",  "Pike", "LeatherArmor", 300, 1500, "low", 5,  0),
    MACE_MEN("macemen",  "Mace", "LeatherArmor", 300, 1000, "medium", 20, 0),
    SWORDSMEN("swordsmen",  "Swords", "metalArmor", 600, 3000, "very low", 40, 0),
    KNIGHT("knight",  "Swords", "metalArmor", 600, 3000, "very high", 40, 0),
    TUNNELER("tunneler",  null, null, 100, 100, "high", 30, 0),
    LADDER_MEN("laddermen",  null, null, 0, 100, "high", 4, 0),
    ENGINEER("engineer",  null, null, 0, 100, "medium", 30, 0),
    BLACK_MONK("blackMonk",  null, null, 300, 1500, "low", 10, 0),
    ARCHER_BOW("archerBow",  null, null, 40, 200, "high", 75, 10),
    SLAVE("slave",  null, null, 100, 100, "high", 5, 0),
    SLINGERS("slingers",  null, null, 30, 100, "high", 12, 5),
    ASSASSINS("assassins",  null, null, 200, 200, "medium", 60, 0),
    HORSE_ARCHER("horseArcher",  null, null, 200, 300, "very high", 80, 13),
    ARABIAN_SWORDSMEN("arabianSwordsmen",  null, null, 400, 1000, "very high", 80, 0),
    FIRE_THROWERS("fireThrowers",  null, null, 700, 200, "very high", 100, 3);

    UnitType(String name, String weapon, String armor, int attackPower, int defencePower, String speed, int cost, int attackRange) {
        this.weapon = weapon;
        this.armor = armor;
        this.name = name;
        this.speed = speed;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.cost = cost;
        this.attackRange = attackRange;
    }

    private final int cost;
    private final String weapon;
    private final String armor;
    private final String name;
    private final String speed;
    private final int attackPower;
    private final int defencePower;
    private final int attackRange;

    public int getAttackRange() {
        return attackRange;
    }

    public int getCost() {
        return cost;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getArmor() {
        return armor;
    }

    public String getName() {
        return name;
    }

    public String getSpeed() {
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }


    public static UnitType getUnitByName(String name) {
        for (UnitType unit : UnitType.values()) {
            if (name.equalsIgnoreCase(unit.name))
                return unit;
        }
        return null;
    }
}