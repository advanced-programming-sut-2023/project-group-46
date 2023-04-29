package View;

import Controller.EditMapMenuController;
import Enums.Commands.EditMapMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class EditMapMenu {
    private final EditMapMenuController editMapMenuController;

    public EditMapMenu(EditMapMenuController editMapMenuController) {
        this.editMapMenuController = editMapMenuController;
    }

    public void run() {
        Matcher matcher;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (true) {
            if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.SET_TEXTURE)) != null) {
                System.out.println(editMapMenuController.setTexture(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.SET_TEXTURE_RECTANGLE)) != null) {
                System.out.println(editMapMenuController.setTextureRectangle(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.CLEAR)) != null) {
                System.out.println(editMapMenuController.clear(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.DROP_ROCK)) != null) {
                System.out.println(editMapMenuController.dropRock(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.DROP_TREE)) != null) {
                System.out.println(editMapMenuController.dropTree(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.DROP_BUILDING)) != null) {
                System.out.println(editMapMenuController.dropBuilding(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.DROP_UNIT)) != null) {
                System.out.println(editMapMenuController.dropUnit(matcher));
            } else if (command.equals("back")) {
                String checkBack = editMapMenuController.checkCountEmpires();
                if (checkBack.equals("Need more keepBuilding"))
                    System.out.println(checkBack);
                else break;
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
    }
}
