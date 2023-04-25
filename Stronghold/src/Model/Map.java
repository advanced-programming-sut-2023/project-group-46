package Model;

import java.util.ArrayList;

public class Map {

    private ArrayList<int[]> empireCoordinates= new ArrayList<>();
    private int size;
    private String name;
    private int numberOfPlayers;
    private Cell[][] map;

    public Map(int size, String name, int numberOfPlayers) {
        this.size = size;
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        map= new Cell[size][size];
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

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Cell[][] getMap() {
        return map;
    }
}
