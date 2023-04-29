package Enums;

public enum UnitType {
    ARCHER("Archer", 4, 20, "high", 12, 10),
    CROSSBOWMEN("Crossbowman", 12, 60, "low", 20, 9),
    SPEARMAN("Spearman", 3, 15, "medium", 8, 0),
    PIKE_MAN("Pikeman", 30, 150, "low", 5, 0),
    MACE_MAN("Maceman", 30, 100, "medium", 20, 0),
    SWORDSMAN("Swordsman", 60, 300, "very low", 40, 0),
    KNIGHT("Knight", 60, 300, "very high", 40, 0),
    TUNNELER("Tunneler", 10, 10, "high", 30, 0),
    LADDER_MAN("Ladderman", 0, 10, "high", 4, 0),
    ENGINEER("Engineer", 0, 10, "medium", 30, 0),
    BLACK_MONK("BlackMonk", 30, 150, "low", 10, 0),
    ARCHER_BOW("ArcherBow", 4, 20, "high", 75, 10),
    SLAVES("Slaves", 10, 10, "high", 5, 0),
    SLINGER("Slinger", 3, 10, "high", 12, 5),
    ASSASSIN("Assassin", 20, 20, "medium", 60, 0),
    HORSE_ARCHER("HorseArcher", 20, 30, "very high", 80, 13),
    ARABIAN_SWORDSMAN("ArabianSwordsman", 40, 100, "very high", 80, 0),
    FIRE_THROWER("FireThrower", 70, 20, "very high", 100, 3),
    PORTABLE_SHIELD("PortableShield", 0, 100, "high", 5, 0),
    BATTERING_RAM("BatteringRam", 500, 2000, "low", 150, 0),
    TREBUCHET("Trebuchet", 2000, 400, null, 150, 40),
    CATAPULT("Catapult", 650, 150, "medium", 150, 30),
    FIRE_BALLISTA("FireBallista", 100, 150, "medium", 150, 30),
    SIEGE_TOWER("SiegeTower", 0, 3300, "medium", 150, 0);
    private final int cost;
    private final String name;
    private final String speed;
    private final int attackPower;
    private final int defencePower;
    private final int attackRange;

    UnitType(String name, int attackPower, int defencePower, String speed, int cost, int attackRange) {
        this.name = name;
        this.speed = speed;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.cost = cost;
        this.attackRange = attackRange;
    }

    public static UnitType getUnitByName(String name) {
        for (UnitType unit : UnitType.values()) {
            if (name.equalsIgnoreCase(unit.name))
                return unit;
        }
        return null;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getCost() {
        return cost;
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
}
