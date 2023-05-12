package Model;

import Enums.UnitType;

public class Unit {
    private UnitType unitType;
    private Empire owner;
    private int hp;
    private String mode;
    private int attackPower;

    public Unit(UnitType unitType, Empire owner) {
        this.owner = owner;
        this.hp = unitType.getDefencePower();
        this.unitType = unitType;
        this.mode = "standing";
        this.attackPower = (int) (unitType.getAttackPower() * (1 + (owner.getFearRate() * 0.1)));
    }

    public Unit(UnitType unitType) {
        this.unitType = unitType;
    }

    public Unit() {
    }

    public int getHp() {
        return hp;
    }

    public void getDamage(int hp) {
        this.hp -= hp;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Empire getOwner() {
        return owner;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}