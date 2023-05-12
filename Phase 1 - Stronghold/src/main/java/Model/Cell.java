package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    @JsonProperty
    private Building building;
    @JsonProperty
    private ArrayList<Unit> units;
    @JsonProperty
    private String type;
    @JsonProperty
    private String environmentName;

    public Cell() {
        this.building = null;
        this.units = new ArrayList<>();
        this.type = "earth";
        this.environmentName = null;
    }

    @JsonProperty
    public Building getBuilding() {
        return building;
    }

    @JsonProperty
    public void setBuilding(Building building) {
        this.building = building;
    }

    @JsonProperty
    public ArrayList<Unit> getUnits() {
        return units;
    }

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getEnvironmentName() {
        return environmentName;
    }

    @JsonProperty
    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    @JsonProperty
    public void addUnit(Unit unit) {
        units.add(unit);
    }

}