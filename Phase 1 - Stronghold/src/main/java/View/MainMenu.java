package View;

import Controller.EditMapMenuController;
import Controller.GameMenuController;
import Controller.MapMenuController;
import Controller.ProfileMenuController;

public class MainMenu {

    public void run() throws Exception {
        String command;

        while (true) {
            System.out.println("choose one of menus: 1)EditMapMenu 2)GameMenu 3)ProfileMenu");
            command = Menu.getScanner().nextLine();
            if (command.matches("ProfileMenu")) {
                ProfileMenu profileMenu = new ProfileMenu(new ProfileMenuController());
                profileMenu.run();
            } else if (command.matches("EditMapMenu")) {
                EditMapMenu editMapMenu = new EditMapMenu(new EditMapMenuController());
                editMapMenu.run();
            } else if (command.matches("GameMenu")) {
                GameMenu gameMenu = new GameMenu(new GameMenuController());
                gameMenu.run();
            } else if (command.matches("log out")) {
                //TODO kiarash should check this part in loginMenu
                return;
            }else if (command.matches("MapMenu")) {
                MapMenu mapMenu = new MapMenu(new MapMenuController());
                mapMenu.run();
            }  else {
                System.out.println("invalid command!");
            }
        }
    }
}