package Model;

public class Machine {
    private int hp;
    private String name;
    private Empire owner;
    private int engineerNumber;
    private MachineType machineType;

    public Machine(MachineType machineType, Empire owner) {
        this.owner = owner;
        this.machineType = machineType;
    }

    public int getHp() {
        return hp;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Empire getOwner() {
        return owner;
    }

    public int getEngineerNumber() {
        return engineerNumber;
    }
}
