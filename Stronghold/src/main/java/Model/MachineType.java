package Model;

public enum MachineType {
    PORTABLE_SHIELDS(null, null, null, "portable shields", 1, 0, 0, 0);

    MachineType(String speed, String attackPower, String defencePower, String name, int engineerNumber, int gold, int rate, int attackRange) {
        this.speed = speed;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.name = name;
        this.engineerNumber = engineerNumber;
        this.gold = gold;
        this.rate = rate;
        this.attackRange = attackRange;
    }

    private String speed;
    private String attackPower;
    private String defencePower;
    private String name;
    private int engineerNumber;
    private int gold;
    private int rate;
    private int attackRange;

    public int getAttackRange() {
        return attackRange;
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

    public String getName() {
        return name;
    }

    public int getEngineerNumber() {
        return engineerNumber;
    }

    public int getGold() {
        return gold;
    }

    public int getRate() {
        return rate;
    }

    public static MachineType getMachineByName(String name) {
        for (MachineType machine : MachineType.values()) {
            if (name.equalsIgnoreCase(machine.name))
                return machine;
        }
        return null;
    }
}
