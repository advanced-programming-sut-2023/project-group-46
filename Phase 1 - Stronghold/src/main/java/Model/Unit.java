package Model;

import Controller.MoveController;
import Enums.UnitType;

import java.util.ArrayList;

public class Unit {
    private ArrayList<MoveController.Pair<Integer, Integer>> path;
    private ArrayList<MoveController.Pair<Integer, Integer>> secondPath;
    private int currentCell;
    private UnitType unitType;
    private Empire owner;
    private int hp;
    private String mode;
    private int attackPower;
    private boolean isPatrol;

    public Unit(UnitType unitType, Empire owner) {
        this.owner = owner;
        this.hp = unitType.getDefencePower();
        this.unitType = unitType;
        this.mode = "standing";
        this.attackPower = (int) (unitType.getAttackPower() * (1 + (owner.getFearRate() * 0.1)));
        isPatrol= false;
        currentCell = -1;
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

    public ArrayList<MoveController.Pair<Integer, Integer>> getPath() {
        return path;
    }

    public void setPath(ArrayList<MoveController.Pair<Integer, Integer>> path) {
        this.path = path;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

    public boolean isPatrol() {
        return isPatrol;
    }

    public void setPatrol(boolean patrol) {
        isPatrol = patrol;
    }

    public ArrayList<MoveController.Pair<Integer, Integer>> getSecondPath() {
        return secondPath;
    }

    public void setSecondPath(ArrayList<MoveController.Pair<Integer, Integer>> secondPath) {
        this.secondPath = secondPath;
    }
}