package Enums;

public enum MachineType {
    PORTABLE_SHIELD("high", 0, 100, "PortableShield", 1, 5, 0),
    BATTERING_RAM("low", 500, 2000, "BatteringRam", 4, 150, 0),
    TREBUCHET(null, 2000, 400, "Trebuchet", 3, 150, 40),//منجنیق با وزنه تعادل
    SIEGE_TOWER("medium", 0, 3300, "SiegeTower", 4, 150, 0),
    CATAPULT("medium", 650, 150, "Catapult", 2, 150, 30),
    FIRE_BALLISTA("medium", 100, 150, "FireBallista", 2, 150, 30);
    MachineType(String speed, int attackPower, int defencePower, String name, int engineerNumber, int gold, int attackRange) {
        this.speed = speed;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.name = name;
        this.engineerNumber = engineerNumber;
        this.gold = gold;
        this.attackRange = attackRange;
    }

    private final String speed;
    private final int attackPower;
    private final int defencePower;
    private final String name;
    private final int engineerNumber;
    private final int gold;
    private final int attackRange;

    public int getAttackRange() {
        return attackRange;
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

    public String getName() {
        return name;
    }

    public int getEngineerNumber() {
        return engineerNumber;
    }

    public int getGold() {
        return gold;
    }

    public static MachineType getMachineByName(String name) {
        for (MachineType machine : MachineType.values()) {
            if (name.equalsIgnoreCase(machine.name))
                return machine;
        }
        return null;
    }
}
