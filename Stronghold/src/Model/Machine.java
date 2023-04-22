package Model;


import Enums.MachineType;

public class Machine {
    private int hp;
    private String name;
    private final Empire owner;
    private final MachineType machineType;

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

    public Empire getOwner() {
        return owner;
    }

}
