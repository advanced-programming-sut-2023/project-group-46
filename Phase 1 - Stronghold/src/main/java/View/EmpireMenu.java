package View;

import Controller.EmpireMenuController;
import Enums.Commands.EmpireMenuCommands;

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
                System.out.print(empireMenuController.showPopularityFactors());
            } else if (command.matches("^show popularity$")) {
                System.out.println(empireMenuController.showPopularity());
            } else if (command.matches("^show food list$")) {
                System.out.print(empireMenuController.showFoodList());
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
            } else if (command.equals("back")) {
                System.out.println("Back to GameMenu");
                return;
            } else if (command.equals("show armoury status")) {
                System.out.println(empireMenuController.showArmoury());
            } else if (command.equals("show stockpile status")) {
                System.out.println(empireMenuController.showStockpile());
            }
            else if (command.equals("show current menu")) {
                System.out.println("EmpireMenu");
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}