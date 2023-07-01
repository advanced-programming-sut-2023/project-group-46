package View;

import Client.Connection;
import Client.Database;
import Client.RefreshUsers;
import Controller.LoginMenuController;
import Controller.SignUpMenuController;
import Model.User;
import View.LoginMenu;
import View.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        for(User user : User.getUsersFromJsonFile()) {
            if (user.isStayedLoggedIn()) {
                LoginMenuController.setLoggedInUser(user);
                break;
            }
        }
        Connection connection = new Connection("localhost", 8080);
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
        Database.refreshUsers.start();
    }
}