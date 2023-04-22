package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private ArrayList<Empire> empires;
    private final Cell[][] map;
    private final ArrayList<Trade> availableTrades;
    private final ArrayList<Trade> historyTrades;
    private int turnsCounter;

    private static HashMap<String, Integer> shopItems = new HashMap<>();

    public Game(Cell[][] map) {
        this.map = map;
        availableTrades = new ArrayList<>();
        historyTrades = new ArrayList<>();
        shopItems = new HashMap<>();
        shopItems.put("meat", 8);
        shopItems.put("bread", 8);
        shopItems.put("apple", 8);
        shopItems.put("cheese", 8);
        shopItems.put("wheat", 23);
        shopItems.put("flour", 32);
        shopItems.put("hop", 15);
        shopItems.put("ale", 20);
        shopItems.put("wood", 4);
        shopItems.put("stone", 14);
        shopItems.put("iron", 45);
        shopItems.put("pitch", 20);
        shopItems.put("spear", 20);
        shopItems.put("bow", 31);
        shopItems.put("mace", 58);
        shopItems.put("crossbow", 58);
        shopItems.put("pike", 36);
        shopItems.put("sword", 58);
        shopItems.put("leatherArmor", 25);
        shopItems.put("metalArmor", 58);
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

    public void setTurnsCounter(int turnsCounter) {
        this.turnsCounter = turnsCounter;
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getTurnsCounter() {
        return turnsCounter;
    }

    public ArrayList<Trade> getAvailableTrades() {
        return availableTrades;
    }

    public ArrayList<Trade> getHistoryTrades() {
        return historyTrades;
    }
}
