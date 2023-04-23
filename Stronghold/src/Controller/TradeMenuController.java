package Controller;

import Model.Trade;

import java.util.regex.Matcher;

public class TradeMenuController {

    public String trade(Matcher matcher) {
        String message = matcher.group("message");
        String wantedResource = matcher.group("wantedResource");
        String givenResource = matcher.group("givenResource");
        int wantedAmount = Integer.parseInt(matcher.group("wantedAmount"));
        int givenAmount = Integer.parseInt(matcher.group("givenAmount"));
        boolean wantedResourceType = GameMenuController.getCurrentEmpire().getResources().isResourceType(wantedResource);
        boolean wantedArmouryType = GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(wantedResource);
        boolean wantedFoodType = GameMenuController.getCurrentEmpire().getFoodStock().isFoodType(wantedResource);
        boolean givenResourceType = GameMenuController.getCurrentEmpire().getResources().isResourceType(givenResource);
        boolean givenArmouryType = GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(givenResource);
        boolean givenFoodType = GameMenuController.getCurrentEmpire().getFoodStock().isFoodType(givenResource);
        if (!wantedResourceType && !wantedArmouryType && !wantedFoodType && !givenResourceType && !givenArmouryType && !givenFoodType) {
            return "invalid item";
        }
        if (givenResourceType && GameMenuController.getCurrentEmpire().getResources().getResourceAmount(givenResource) < givenAmount) {
            return "you don't have enough amount of this item";
        } else if (givenArmouryType && GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount(givenResource) < givenAmount) {
            return "you don't have enough amount of this item";
        } else if (givenFoodType && GameMenuController.getCurrentEmpire().getFoodStock().getFoodAmount(givenResource) < givenAmount) {
            return "you don't have enough amount of this item";
        }
        if (givenResourceType) {
            GameMenuController.getCurrentEmpire().getResources().addFreeCapacityStockpile(givenAmount);
            GameMenuController.getCurrentEmpire().getResources().addResource(givenResource, -1 * givenAmount);
        } else if (givenArmouryType) {
            GameMenuController.getCurrentEmpire().getArmoury().addArmoury(givenResource, -1 * givenAmount);
            GameMenuController.getCurrentEmpire().getArmoury().addFreeCapacityArmoury(givenAmount);
        } else if (givenFoodType) {
            GameMenuController.getCurrentEmpire().getFoodStock().addFood(givenResource, -1 * givenAmount);
            GameMenuController.getCurrentEmpire().getFoodStock().addFreeCapacityFoodStock(givenAmount);
        }
        GameMenuController.getGame().getAvailableTrades().add(new Trade(wantedResource, wantedAmount, givenResource, givenAmount, message, GameMenuController.getCurrentEmpire()));
        return "success";
    }

    public String tradeList() {// id-> 1 | wanted : food->5 | given : food->6 | sender'sID-> (int)EmpireID
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < GameMenuController.getGame().getAvailableTrades().size(); i++) {
            String givenResource = GameMenuController.getGame().getAvailableTrades().get(i).getGivenResource();
            String wantedResource = GameMenuController.getGame().getAvailableTrades().get(i).getWantedResource();
            int wantedResourceAmount = GameMenuController.getGame().getAvailableTrades().get(i).getWantedResourceAmount();
            int givenResourceAmount = GameMenuController.getGame().getAvailableTrades().get(i).getGivenResourceAmount();
            int senderId = GameMenuController.getGame().getAvailableTrades().get(i).getSenderEmpire().getEmpireId();
            output.append("id-> ").append(i).append(" | wanted : ").append(wantedResource).append("->").append(wantedResourceAmount).append(" | given : ").append(givenResource).append("->").append(givenResourceAmount).append(" | sender'sID->").append(senderId).append('\n');
        }
        return output.toString();
    }

    public String tradeAccept(Matcher matcher) {
        int tradeId = Integer.parseInt(matcher.group("id"));
        if (tradeId < 0 || tradeId >= GameMenuController.getGame().getAvailableTrades().size()) {
            return "invalid trade ID";
        }
        String givenResource = GameMenuController.getGame().getAvailableTrades().get(tradeId).getGivenResource();
        String wantedResource = GameMenuController.getGame().getAvailableTrades().get(tradeId).getWantedResource();
        String message = GameMenuController.getGame().getAvailableTrades().get(tradeId).getMessage();
        int wantedResourceAmount = GameMenuController.getGame().getAvailableTrades().get(tradeId).getWantedResourceAmount();
        int givenResourceAmount = GameMenuController.getGame().getAvailableTrades().get(tradeId).getGivenResourceAmount();
        int senderId = GameMenuController.getGame().getAvailableTrades().get(tradeId).getSenderEmpire().getEmpireId();
        boolean wantedResourceType = GameMenuController.getCurrentEmpire().getResources().isResourceType(wantedResource);
        boolean wantedArmouryType = GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(wantedResource);
        boolean wantedFoodType = GameMenuController.getCurrentEmpire().getFoodStock().isFoodType(wantedResource);
        boolean givenResourceType = GameMenuController.getCurrentEmpire().getResources().isResourceType(givenResource);
        boolean givenArmouryType = GameMenuController.getCurrentEmpire().getArmoury().isArmouryType(givenResource);
        boolean givenFoodType = GameMenuController.getCurrentEmpire().getFoodStock().isFoodType(givenResource);
        if (wantedResourceType) {
            if (GameMenuController.getCurrentEmpire().getResources().getResourceAmount(wantedResource) < wantedResourceAmount) {
                return "you don't have enough resource to complete the trade";
            }
            GameMenuController.getCurrentEmpire().getResources().addResource(wantedResource, -1 * wantedResourceAmount);
            GameMenuController.getCurrentEmpire().getResources().addFreeCapacityStockpile(wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getResources().addResource(wantedResource, wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getResources().addFreeCapacityStockpile(-1 * wantedResourceAmount);
        } else if (wantedArmouryType) {
            if (GameMenuController.getCurrentEmpire().getArmoury().getArmouryAmount(wantedResource) < wantedResourceAmount) {
                return "you don't have enough armour to complete the trade";
            }
            GameMenuController.getCurrentEmpire().getArmoury().addArmoury(wantedResource, -1 * wantedResourceAmount);
            GameMenuController.getCurrentEmpire().getArmoury().addFreeCapacityArmoury(wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getArmoury().addArmoury(wantedResource, wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getArmoury().addFreeCapacityArmoury(-1 * wantedResourceAmount);
        } else if (wantedFoodType) {
            if (GameMenuController.getCurrentEmpire().getFoodStock().getFoodAmount(wantedResource) < wantedResourceAmount) {
                return "you don't have enough food to complete the trade";
            }
            GameMenuController.getCurrentEmpire().getFoodStock().addFood(wantedResource, -1 * wantedResourceAmount);
            GameMenuController.getCurrentEmpire().getFoodStock().addFreeCapacityFoodStock(wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getFoodStock().addFood(wantedResource, wantedResourceAmount);
            GameMenuController.getGame().getEmpires().get(senderId).getFoodStock().addFreeCapacityFoodStock(-1 * wantedResourceAmount);
        }
        if (givenResourceType) {
            GameMenuController.getCurrentEmpire().getResources().addResource(givenResource, givenResourceAmount);
            GameMenuController.getCurrentEmpire().getResources().addFreeCapacityStockpile(-1 * givenResourceAmount);
        } else if (givenArmouryType) {
            GameMenuController.getCurrentEmpire().getArmoury().addArmoury(givenResource, givenResourceAmount);
            GameMenuController.getCurrentEmpire().getArmoury().addFreeCapacityArmoury(-1 * givenResourceAmount);
        } else if (givenFoodType) {
            GameMenuController.getCurrentEmpire().getFoodStock().addFood(givenResource, givenResourceAmount);
            GameMenuController.getCurrentEmpire().getFoodStock().addFreeCapacityFoodStock(-1 * givenResourceAmount);
        }
        GameMenuController.getGame().getAvailableTrades().remove(tradeId);
        GameMenuController.getGame().getHistoryTrades().add(new Trade(wantedResource, wantedResourceAmount, givenResource, givenResourceAmount, message, GameMenuController.getGame().getEmpires().get(senderId)));
        GameMenuController.getGame().getHistoryTrades().get(GameMenuController.getGame().getHistoryTrades().size() - 1).setGetterEmpire(GameMenuController.getCurrentEmpire());
        return "success";
    }

    public String tradeHistory() {// id-> 1 | wanted : food->5 | given : food->6 | sender'sID->1 | getter'sID->2
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < GameMenuController.getGame().getHistoryTrades().size(); i++) {
            String givenResource = GameMenuController.getGame().getHistoryTrades().get(i).getGivenResource();
            String wantedResource = GameMenuController.getGame().getHistoryTrades().get(i).getWantedResource();
            int wantedResourceAmount = GameMenuController.getGame().getHistoryTrades().get(i).getWantedResourceAmount();
            int givenResourceAmount = GameMenuController.getGame().getHistoryTrades().get(i).getGivenResourceAmount();
            int senderId = GameMenuController.getGame().getHistoryTrades().get(i).getSenderEmpire().getEmpireId();
            int getterId = GameMenuController.getGame().getHistoryTrades().get(i).getGetterEmpire().getEmpireId();
            output.append("id-> ").append(i).append(" | wanted : ").append(wantedResource).append("->").append(wantedResourceAmount).append(" | given : ").append(givenResource).append("->").append(givenResourceAmount).append(" | sender'sID->").append(senderId).append(" | getter'sID->").append(getterId).append('\n');
        }
        return output.toString();
    }
}