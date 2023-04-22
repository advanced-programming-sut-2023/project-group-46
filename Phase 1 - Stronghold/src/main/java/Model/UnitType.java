package Model;

public enum UnitType {
    ARCHER("Bow", null, "archer", "low", "low", "low", "defensive", 5, 5, 0, 0);

    UnitType(String weapon, String armor, String name, String speed, String attackPower, String defencePower, String mode, int cost, int hp, int defendRange, int attackRange) {
        this.weapon = weapon;
        this.armor = armor;
        this.name = name;
        this.speed = speed;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.mode = mode;
        this.cost = cost;
        this.hp = hp;
        this.attackRange = attackRange;
        this.defendRange = defendRange;
    }

    private int hp;
    private int cost;
    private String weapon;
    private String armor;
    private String name;
    private String speed;
    private String attackPower;
    private String defencePower;
    private String mode;
    private int attackRange;
    private int defendRange;

    public int getAttackRange() {
        return attackRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getHp() {
        return hp;
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

    public String getAttackPower() {
        return attackPower;
    }

    public String getDefencePower() {
        return defencePower;
    }

    public String getMode() {
        return mode;
    }

    public static UnitType getUnitByName(String name) {
        for (UnitType unit : UnitType.values()) {
            if (name.equalsIgnoreCase(unit.name))
                return unit;
        }
        return null;
    }
}
