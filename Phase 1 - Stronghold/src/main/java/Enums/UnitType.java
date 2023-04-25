package Enums;

public enum UnitType {
    ARCHER("Archer", "bow", null, 4, 20, "high", 12, 10),
    CROSSBOWMEN("Crossbowman", "crossbow", "leatherArmor", 12, 60, "low", 20, 9),
    SPEARMAN("Spearman", "spear", null, 3, 15, "medium", 8, 0),
    PIKE_MAN("Pikeman", "pike", "leatherArmor", 30, 150, "low", 5, 0),
    MACE_MAN("Maceman", "mace", "leatherArmor", 30, 100, "medium", 20, 0),
    SWORDSMAN("Swordsman", "swords", "metalArmor", 60, 300, "very low", 40, 0),
    KNIGHT("Knight", "swords", "metalArmor", 60, 300, "very high", 40, 0),
    TUNNELER("Tunneler", null, null, 10, 10, "high", 30, 0),
    LADDER_MAN("Ladderman", null, null, 0, 10, "high", 4, 0),
    ENGINEER("Engineer", null, null, 0, 10, "medium", 30, 0),
    BLACK_MONK("BlackMonk", null, null, 30, 150, "low", 10, 0),
    ARCHER_BOW("ArcherBow", null, null, 4, 20, "high", 75, 10),
    SLAVES("Slaves", null, null, 10, 10, "high", 5, 0),
    SLINGER("Slinger", null, null, 3, 10, "high", 12, 5),
    ASSASSIN("Assassin", null, null, 20, 20, "medium", 60, 0),
    HORSE_ARCHER("HorseArcher", null, null, 20, 30, "very high", 80, 13),
    ARABIAN_SWORDSMEN("ArabianSwordsmen", null, null, 40, 100, "very high", 80, 0),
    FIRE_THROWERS("FireThrowers", null, null, 70, 20, "very high", 100, 3);

    private final int cost;
    private final String weapon;
    private final String armor;
    private final String name;
    private final String speed;
    private final int attackPower;
    private final int defencePower;
    private final int attackRange;
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
}
