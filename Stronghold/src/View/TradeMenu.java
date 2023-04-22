package View;

import Controller.TradeMenuController;
import Enums.ShopMenuCommands;
import Enums.TradeMenuCommands;

import java.util.regex.Matcher;

public class TradeMenu {
    private final TradeMenuController tradeMenuController;

    public TradeMenu(TradeMenuController tradeMenuController) {
        this.tradeMenuController = tradeMenuController;
    }

    public void run() {
        Matcher matcher;
        String command;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^trade list$")) {
                System.out.println(tradeMenuController.tradeList());
            } else if (command.matches("^trade history$")) {
                System.out.println(tradeMenuController.tradeHistory());
            } else if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_ACCEPT_REGEX)) != null) {
                System.out.println(tradeMenuController.tradeAccept(matcher));
            } else if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE_REGEX)) != null) {
                System.out.println(tradeMenuController.trade(matcher));
            } else if (command.matches("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}