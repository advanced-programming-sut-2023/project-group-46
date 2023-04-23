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
            if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX1)) != null) {
                System.out.println(editMapMenuController.setTexture(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX2)) != null) {
                System.out.println(editMapMenuController.setTextureRectangle(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX3)) != null) {
                System.out.println(editMapMenuController.clear(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX4)) != null) {
                System.out.println(editMapMenuController.dropRock(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX5)) != null) {
                System.out.println(editMapMenuController.dropTree(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX6)) != null) {
                System.out.println(editMapMenuController.dropBuilding(matcher));
            } else if ((matcher = EditMapMenuCommands.getMatcher(command, EditMapMenuCommands.REGEX7)) != null) {
                System.out.println(editMapMenuController.dropUnit(matcher));
            } else System.out.println("Invalid command!");
            command = scanner.nextLine();
        }
    }
}