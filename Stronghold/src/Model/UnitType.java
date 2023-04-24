package Model;

public enum UnitType {
    ARCHER("Archer",  "bow", null, 4, 20, "high", 12, 10),
    CROSSBOWMEN("Crossbowmen",  "crossbow", "leatherArmor", 12, 60, "low", 20, 9),
    SPEAR_MEN("Spearmen",  "spear", null, 3, 15, "medium", 8,  0),
    PIKE_MEN("Pikmen",  "pike", "leatherArmor", 30, 150, "low", 5,  0),
    MACE_MEN("Macemen",  "mace", "leatherArmor", 30, 100, "medium", 20, 0),
    SWORDSMEN("Swordsmen",  "swords", "metalArmor", 60, 300, "very low", 40, 0),
    KNIGHT("Knight",  "swords", "metalArmor", 60, 300, "very high", 40, 0),
    TUNNELER("Tunneler",  null, null, 10, 10, "high", 30, 0),
    LADDER_MEN("Laddermen",  null, null, 0, 10, "high", 4, 0),
    ENGINEER("Engineer",  null, null, 0, 10, "medium", 30, 0),
    BLACK_MONK("BlackMonk",  null, null, 30, 150, "low", 10, 0),
    ARCHER_BOW("ArcherBow",  null, null, 4, 20, "high", 75, 10),
    SLAVES("Slaves",  null, null, 10, 10, "high", 5, 0),
    SLINGERS("Slingers",  null, null, 3, 10, "high", 12, 5),
    ASSASSINS("Assassins",  null, null, 20, 20, "medium", 60, 0),
    HORSE_ARCHER("HorseArcher",  null, null, 20, 30, "very high", 80, 13),
    ARABIAN_SWORDSMEN("ArabianSwordsmen",  null, null, 40, 100, "very high", 80, 0),
    FIRE_THROWERS("FireThrowers",  null, null, 70, 20, "very high", 100, 3);

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

    private int cost;
    private String weapon;
    private String armor;
    private String name;
    private String speed;
    private int attackPower;
    private int defencePower;
    private int attackRange;

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
