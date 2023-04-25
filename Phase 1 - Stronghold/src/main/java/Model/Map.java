package Model;

import java.util.ArrayList;

public class Map {

    private static Cell[][] map;
    private final ArrayList<int[]> empireCoordinates = new ArrayList<>();
    private final int size;
    private final String name;

    public Map(int size, String name) {
        this.size = size;
        this.name = name;
        map = new Cell[size][size];
    }

    public ArrayList<int[]> getEmpireCoordinates() {
        return empireCoordinates;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Cell[][] getMap() {
        return map;
    }
}
