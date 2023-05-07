package View;

import Controller.ShopMenuController;
import Enums.Commands.ShopMenuCommands;

import java.util.regex.Matcher;

public class ShopMenu {
    private final ShopMenuController shopMenuController;

    public ShopMenu(ShopMenuController shopMenuController) {
        this.shopMenuController = shopMenuController;
    }

    public void run() {
        Matcher matcher;
        String command;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^show price list$")) {
                System.out.println(shopMenuController.showPriceList());
            } else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.BUY_REGEX)) != null) {
                System.out.println(shopMenuController.buy(matcher));
            } else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.SELL_REGEX)) != null) {
                System.out.println(shopMenuController.sell(matcher));
            } else if (command.matches("back")) {
                System.out.println("Back to GameMenu");
                return;
            }  else {
                System.out.println("Invalid command!");
            }
        }
    }
}