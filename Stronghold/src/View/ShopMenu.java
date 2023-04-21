package View;

import Controller.ShopMenuController;

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
            } else if ((matcher = Menu.getMatcher(command, "(?=.* -i (?<name>\\S+))(?=.* -a (?<amount>\\S+))^buy( *\\-[ai] \\S+){2}$")) != null) {
                System.out.println(shopMenuController.buy(matcher));
            } else if ((matcher = Menu.getMatcher(command, "^(?=.* -i (?<name>\\S+))(?=.* -a (?<amount>\\S+))^sell( *\\-[ai] \\S+){2}$")) != null) {
                System.out.println(shopMenuController.sell(matcher));
            } else if (command.matches("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
