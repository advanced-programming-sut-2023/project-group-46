import Controller.LoginMenuController;
import Model.User;
import View.LoginMenu;
import View.MainMenu;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean loggedInUserExists = false;
        for (User user : User.getUsersFromJsonFile()) {
            if (user.isStayedLoggedIn()) {
                LoginMenuController.setLoggedInUser(user);
                loggedInUserExists = true;
                MainMenu mainMenu = new MainMenu();
                mainMenu.run();
                break;
            }
        }

        if (!loggedInUserExists) {
            LoginMenuController loginMenuController = new LoginMenuController();
            LoginMenu loginMenu = new LoginMenu(loginMenuController);
            loginMenu.run();
        }
    }
}