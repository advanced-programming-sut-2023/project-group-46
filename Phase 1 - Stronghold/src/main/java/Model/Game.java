package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Empire> empires;
    private Cell[][] map;

    private static HashMap<String, Integer> shopItems = new HashMap<>();

    static {//TODO double check
        shopItems.put("Food", 5);
    }

    public Game(Cell[][] map) {
        this.map = map;
    }

    public void setEmpires(ArrayList<Empire> empires) {
        this.empires = empires;
    }

    public ArrayList<Empire> getEmpires() {
        return empires;
    }

    public Cell[][] getMapGame() {
        return map;
    }

    public static HashMap<String, Integer> getShopItems() {
        return shopItems;
    }

    public static int getPriceOfItem(String item) {
        if (shopItems.containsKey(item)) {
            return shopItems.get(item);
        }
        return 0;
    }

    public Empire getEmpireById(int id) {
        for (Empire empire : empires) {
            if (empire.getEmpireId() == id)
                return empire;
        }
        return null;
    }
}
