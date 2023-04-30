package Enums;

public enum UnitType {
    ARCHER("Archer", 4, 20, 4, 12, 10),
    CROSSBOWMEN("Crossbowman", 12, 60, 2, 20, 9),
    SPEARMAN("Spearman", 3, 15, 3, 8, 0),
    PIKE_MAN("Pikeman", 30, 150, 2, 5, 0),
    MACE_MAN("Maceman", 30, 100, 3, 20, 0),
    SWORDSMAN("Swordsman", 60, 300, 1, 40, 0),
    KNIGHT("Knight", 60, 300, 5, 40, 0),
    TUNNELER("Tunneler", 10, 10, 4, 30, 0),
    LADDER_MAN("Ladderman", 0, 10, 4, 4, 0),
    ENGINEER("Engineer", 0, 10, 3, 30, 0),
    BLACK_MONK("BlackMonk", 30, 150, 2, 10, 0),
    ARCHER_BOW("ArcherBow", 4, 20, 4, 75, 10),
    SLAVES("Slaves", 10, 10, 4, 5, 0),
    SLINGER("Slinger", 3, 10, 4, 12, 5),
    ASSASSIN("Assassin", 20, 20, 3, 60, 0),
    HORSE_ARCHER("HorseArcher", 20, 30, 5, 80, 13),
    ARABIAN_SWORDSMAN("ArabianSwordsman", 40, 100, 5, 80, 0),
    FIRE_THROWER("FireThrower", 70, 20, 5, 100, 3),
    PORTABLE_SHIELD("PortableShield", 0, 100, 4, 5, 0),
    BATTERING_RAM("BatteringRam", 500, 2000, 2, 150, 0),
    TREBUCHET("Trebuchet", 2000, 400, 0, 150, 40),
    CATAPULT("Catapult", 650, 150, 3, 150, 30),
    FIRE_BALLISTA("FireBallista", 100, 150, 3, 150, 30),
    SIEGE_TOWER("SiegeTower", 0, 3300, 3, 150, 0);

    private final int cost;
    private final String name;
    private final int speed;
    private final int attackPower;
    private final int defencePower;
    private final int attackRange;
    UnitType(String name, int attackPower, int defencePower, int speed, int cost, int attackRange) {
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

    public int getSpeed() {
        return speed;
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

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }
}
