package Model;

public class Unit {
    private int hp;
    private String name;
    private String mode;
    private UnitType unitType;
    private Empire owner;
    private Cell initialCell;
    private Cell goalCell;

    public Unit(UnitType unitType, Empire owner) {
        this.owner = owner;
        this.name = unitType.getName();
        this.hp = unitType.getHp();
        this.mode = unitType.getMode();
        this.unitType = unitType;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setGoalCell(Cell goalCell) {
        this.goalCell = goalCell;
    }

    public void setInitialCell(Cell initialCell) {
        this.initialCell = initialCell;
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

    public Cell getInitialCell() {
        return initialCell;
    }

    public Cell getGoalCell() {
        return goalCell;
    }
}
