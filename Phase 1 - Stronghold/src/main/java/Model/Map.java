package Model;

public class Map {
    private static Cell[][] map1;
    private static Cell[][] map2;
    private static Cell[][] map3;

    public Map() {
        map1 = new Cell[200][200];
        map2 = new Cell[100][100];
        map3 = new Cell[300][300];
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
}
