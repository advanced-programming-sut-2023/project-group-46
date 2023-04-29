package Model;

import java.util.ArrayList;

public class Cell {
    private Building building;
    private ArrayList<Unit> units;
    private String type;
    private String environmentName;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

}
