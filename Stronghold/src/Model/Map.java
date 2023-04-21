package Model;

import java.util.ArrayList;

public class Map {
    private static Cell[][] map1;
    private static Cell[][] map2;
    private static Cell[][] map3;
    private static Cell[][] map4;


    public Map() {
        map1 = new Cell[200][200];
        map2 = new Cell[100][100];
        map3 = new Cell[300][300];
    }

    public Map(int width, int height) {
        map4 = new Cell[width][height];
    }

    {
        //TODO define the maps
    }

    public static Cell[][] getMap1() {
        return map1;
    }

    public static Cell[][] getMap2() {
        return map2;
    }

    public static Cell[][] getMap3() {
        return map3;
    }

    public static Cell[][] getMap4() {
        return map4;
    }
}
