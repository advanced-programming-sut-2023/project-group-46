package View;

import Controller.EmpireMenuController;
import Enums.EmpireMenuCommands;

import java.util.regex.Matcher;

public class EmpireMenu {
    private final EmpireMenuController empireMenuController;

    public EmpireMenu(EmpireMenuController empireMenuController) {
        this.empireMenuController = empireMenuController;
    }

    public void run() {
        Matcher matcher;
        String command;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^show popularity factors$")) {
                System.out.println(empireMenuController.showPopularityFactors());
            } else if (command.matches("^show popularity$")) {
                System.out.println(empireMenuController.showPopularity());
            } else if (command.matches("^show food list$")) {
                System.out.println(empireMenuController.showFoodList());
            } else if ((matcher = EmpireMenuCommands.getMatcher(command, EmpireMenuCommands.FOOD_RATE_REGEX)) != null) {
                empireMenuController.changeFoodRate(matcher);
            } else if (command.matches("^food rate show$")) {
                System.out.println(empireMenuController.foodRateShow());
            } else if ((matcher = EmpireMenuCommands.getMatcher(command, EmpireMenuCommands.TAX_RATE_REGEX)) != null) {
                empireMenuController.changeTaxRate(matcher);
            } else if (command.matches("^tax rate show$")) {
                System.out.println(empireMenuController.taxRateShow());
            } else if ((matcher = EmpireMenuCommands.getMatcher(command, EmpireMenuCommands.FEAR_RATE_REGEX)) != null) {
                empireMenuController.changeFearRate(matcher);
            } else if (command.matches("^fear rate show$")) {
                System.out.println(empireMenuController.fearRateShow());
            } else if (command.matches("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
