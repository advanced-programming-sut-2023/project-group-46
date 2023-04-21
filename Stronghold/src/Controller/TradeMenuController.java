package Controller;

import Model.Trade;
import View.TradeMenu;

import java.util.regex.Matcher;

public class TradeMenuController {
    private final TradeMenu tradeMenu;
    public TradeMenuController(){
        tradeMenu = new TradeMenu(this);
    }

    public void run(){

    }

    private String trade(Matcher matcher){
        GameMenuController.getCurrentEmpire().getAvailableTrades().add(new Trade("A",0,0,"a",GameMenuController.getGame().getEmpireById(0),GameMenuController.getGame().getEmpireById(0)));
        return null;
    }

    private String tradeList(){
        return null;
    }

    private String tradeAccept(Matcher matcher){
        return null;
    }

    private String tradeHistory(){
        return null;
    }
}