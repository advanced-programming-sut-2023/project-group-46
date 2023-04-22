package Model;

import Enums.UnitType;

public class Unit {
    private int hp;
    private String name;
    private String mode;
    private final UnitType unitType;
    private final Empire owner;

    public Unit(UnitType unitType, Empire owner) {
        this.owner = owner;
        this.name = unitType.getName();
        this.hp = unitType.getDefencePower();
        this.unitType = unitType;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Empire getOwner() {
        return owner;
    }
}