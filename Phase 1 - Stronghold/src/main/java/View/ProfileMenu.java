package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Enums.Commands.ProfileMenuCommands;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.regex.Matcher;

public class ProfileMenu extends Application {
    private ProfileMenuController profileMenuController;
    @FXML
    private TextField username;
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private TextField email;
    @FXML
    private TextField slogan;
    @FXML
    private Label error;
    @FXML
    private CheckBox removeSlogan;

    public ProfileMenu() {
        this.profileMenuController = new ProfileMenuController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/ProfileMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        stage.getScene().setRoot(pane);
        stage.show();
    }

    @FXML
    public void initialize() throws Exception {
        username.textProperty().addListener((observable, oldText, newText)->{
            try {
                error.setText(profileMenuController.changeUsername());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText)->{
            try {
                error.setText(profileMenuController.changeNickname());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        email.textProperty().addListener((observable, oldText, newText)->{
            try {
                error.setText(profileMenuController.changeEmail());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText)->{
            try {
                error.setText(profileMenuController.changeSlogan());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        removeSlogan.selectedProperty().addListener((observable, oldText, newText)->{
            try {
                error.setText(profileMenuController.removeSlogan());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

//    public void run() throws Exception {
//
//        Matcher matcher;
//        String command;
//
//        while (true) {
//            command = Menu.getScanner().nextLine();
//
//            if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_USERNAME)) != null)
//                System.out.println(profileMenuController.changeUsername(matcher));
//
//            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME)) != null)
//                System.out.println(profileMenuController.changeNickname(matcher));
//
//            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_PASSWORD)) != null)
//                System.out.println(profileMenuController.changePassword(matcher));
//
//            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_EMAIL)) != null)
//                System.out.println(profileMenuController.changeEmail(matcher));
//
//            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_SLOGAN)) != null)
//                System.out.println(profileMenuController.changeSlogan(matcher));
//
//            else if (command.matches("^profile display slogan$"))
//                System.out.println(profileMenuController.showUserSlogan());
//
//            else if (command.matches("^profile display rank$"))
//                System.out.println(profileMenuController.showUserRank());
//
//            else if (command.matches("^profile remove slogan$"))
//                System.out.println(profileMenuController.removeSlogan());
//
//            else if (command.matches("^profile display highscore$"))
//                System.out.println(profileMenuController.showUserHighScore());
//
//            else if (command.matches("^profile display$"))
//                System.out.println(profileMenuController.showProfileInfo());
//
//            else if (command.matches("^back$"))
//                return;
//
//            else
//                System.out.println("invalid command!");
//
//        }
//
//    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public TextField getNickname() {
        return nickname;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getSlogan() {
        return slogan;
    }

    public void back() throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }

    public void scoreboard() throws Exception {
        error.setText(profileMenuController.scoreboard());
    }
}