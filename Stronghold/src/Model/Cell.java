package Model;

import java.util.ArrayList;

public class Cell {
    private Building building;
    private ArrayList<Unit> units;
    private ArrayList<Machine> machines;
    private String type;
    private String environmentName;

    public Building getBuilding() {
        return building;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public String getType() {
        return type;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }
}
