package Controller;

import Model.Building;
import Model.Game;
import View.ShopMenu;

import java.util.Map;
import java.util.regex.Matcher;

public class ShopMenuController {

    public String showPriceList() {//item's name: name |buy: price |sell: price |in stockpile:
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Integer> entry : Game.getShopItems().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Integer amount = GameMenuController.getCurrentEmpire().getResources().getResourceAmount(key);
            output.append("item's name: ").append(key).append(" |buy: ").append(value).append(" |sell: ").append((int) (value * 0.8)).append(" |in stockpile: ").append(amount).append('\n');
        }
        return output.toString();
    }

    public String buy(Matcher matcher) {
        String name = matcher.group("name");
        int amount = Integer.parseInt(matcher.group("amount"));
        if (GameMenuController.getCurrentEmpire().getResources().getGold() < Game.getShopItems().get(name) * amount) {
            return "Not enough gold!";
        }
        if (!GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(name) && GameMenuController.getCurrentEmpire().getBuildingByName("stockpile").getFreeCapacity() < amount) {
            return "not enough space in the stockpile";
        }
        if (!GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(name) && GameMenuController.getCurrentEmpire().getBuildingByName("armoury").getFreeCapacity() < amount) {
            return "not enough space in the armoury";
        }
        GameMenuController.getCurrentEmpire().getResources().addResource("gold", -1 * Game.getShopItems().get(name) * amount);
        GameMenuController.getCurrentEmpire().getResources().addResource(name, amount);
        GameMenuController.getCurrentEmpire().getArmoury().addArmoury(name, amount);
        return "success";
    }

    public String sell(Matcher matcher) {
        String name = matcher.group("name");
        int amount = Integer.parseInt(matcher.group("amount"));
        if (!GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(name) && GameMenuController.getCurrentEmpire().getResources().getResourceAmount(name) < amount)
            return "not enough resource in the stockpile";
        if (GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(name) && GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount(name) < amount)
            return "not enough resource in the armoury";

        GameMenuController.getCurrentEmpire().getResources().addResource(name, -1 * amount);
        GameMenuController.getCurrentEmpire().getArmoury().addArmoury(name, -1 * amount);
        GameMenuController.getCurrentEmpire().getResources().addResource("gold", (int) (amount * Game.getShopItems().get(name) * 0.8));
        return "success";
    }
}
