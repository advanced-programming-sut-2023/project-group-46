import Controller.LoginMenuController;
import View.LoginMenu;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        LoginMenuController loginMenuController = new LoginMenuController();
        LoginMenu loginMenu = new LoginMenu(loginMenuController);
        loginMenu.run();
    }
}