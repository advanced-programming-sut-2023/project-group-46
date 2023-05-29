package View;

import Controller.EditMapMenuController;
import Controller.GameMenuController;
import Controller.ProfileMenuController;

public class MainMenu {
    public void run() throws Exception {
        String command;

        while (true) {
            System.out.println("choose number of one of these menus: 1)EditMapMenu 2)GameMenu 3)ProfileMenu");
            command = Menu.getScanner().nextLine();
            if (command.equals("3")) {
                //ProfileMenu profileMenu = new ProfileMenu(new ProfileMenuController());
//                System.out.println("Entered ProfileMenu");
//                profileMenu.run();
            } else if (command.equals("1")) {
                EditMapMenu editMapMenu = new EditMapMenu(new EditMapMenuController());
                System.out.println("Entered EditMapMenu");
                editMapMenu.run();
            } else if (command.equals("2")) {
                GameMenu gameMenu = new GameMenu(new GameMenuController());
                System.out.println("Entered GameMenu");
                gameMenu.run();
            } else if (command.matches("log out")) {
                System.out.println("logged out");
                return;
            }  else if (command.matches("show current menu")) {
                System.out.println("MainMenu");
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}