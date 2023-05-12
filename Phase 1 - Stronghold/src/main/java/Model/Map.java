package Model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Map {

    @JsonProperty
    private Cell[][] map;
    @JsonProperty
    private ArrayList<int[]> empireCoordinates;
    @JsonIgnore
    private int size;

    public Map(int size) {
        this.size = size;
        this.empireCoordinates = new ArrayList<>();
        map = new Cell[size][];
        for (int i = 0; i < size; i++) {
            map[i] = new Cell[size];
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = new Cell();
            }
        }
    }

    public Map() {

    }

    public void setEmpireCoordinates(ArrayList<int[]> empireCoordinates) {
        this.empireCoordinates = empireCoordinates;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JsonProperty
    public ArrayList<int[]> getEmpireCoordinates() {
        return empireCoordinates;
    }

    @JsonProperty
    public int getSize() {
        return size;
    }

    @JsonProperty
    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        this.map = map;
    }
}