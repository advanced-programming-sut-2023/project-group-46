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
        while (!command.equals("back")) {
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
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
    }
}
