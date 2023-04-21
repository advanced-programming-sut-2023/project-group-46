package Controller;

import Model.Game;
import View.ShopMenu;

import java.util.regex.Matcher;

public class ShopMenuController {
    private final ShopMenu shopMenu;
    public ShopMenuController(){
        shopMenu = new ShopMenu(this);
    }

    public void run(){

    }

    private String showPriceList(){
        Game.getShopItems().get("a");
        return null;
    }

    private String buy(Matcher matcher){
        return null;
    }

    private String sell(Matcher matcher){
        return null;
    }
}
