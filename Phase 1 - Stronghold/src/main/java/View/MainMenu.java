package View;

import Controller.EditMapMenuController;
import Controller.GameMenuController;
import Controller.ProfileMenuController;

public class MainMenu {
    public void run() throws Exception {
        String command;

        while (true) {
            System.out.println("choose one of menus: 1)EditMapMenu 2)GameMenu 3)ProfileMenu");
            command = Menu.getScanner().nextLine();
            if (command.matches("ProfileMenu")) {
                ProfileMenu profileMenu = new ProfileMenu(new ProfileMenuController());
                System.out.println("Entered ProfileMenu");
                profileMenu.run();
            } else if (command.matches("EditMapMenu")) {
                EditMapMenu editMapMenu = new EditMapMenu(new EditMapMenuController());
                System.out.println("Entered EditMapMenu");
                editMapMenu.run();
            } else if (command.matches("GameMenu")) {
                GameMenu gameMenu = new GameMenu(new GameMenuController());
                System.out.println("Entered GameMenu");
                gameMenu.run();
            } else if (command.matches("log out")) {
                System.out.println("logged out");
                return;
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}