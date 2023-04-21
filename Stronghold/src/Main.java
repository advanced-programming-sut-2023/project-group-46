import Controller.GameMenuController;
import Controller.LoginMenuController;
import Model.Game;

import java.util.HashMap;
import java.util.Map;

public class Main {
    /*test
    public static String showPriceList(HashMap<String,Integer> hashMap) {//item's name: name |buy: price |sell: price |in stockpile:
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            output.append("item's name: ").append(key).append(" |buy: ").append(value).append(" |sell: ").append((int)(value * 0.8)).append(" |in stockpile: ").append(51).append('\n');
        }
        return output.toString();
    }

    public static void main(String[] args) {
        HashMap<String, Integer> shopItems = new HashMap<>();
        shopItems.put("ale",53);
        shopItems.put("man",50);
        shopItems.put("oil",10);
        System.out.println(showPriceList(shopItems));
    }*/
}