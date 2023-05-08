package Model;

public class Map {

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
