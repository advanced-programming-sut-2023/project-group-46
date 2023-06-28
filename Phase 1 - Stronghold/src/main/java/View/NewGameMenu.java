package View;

import Controller.LoginMenuController;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class NewGameMenu extends Application {
    ArrayList<String> usernames;
    @FXML
    private TextField username1;
    @FXML
    private TextField username2;
    @FXML
    private TextField username3;
    @FXML
    private TextField turnsNumber;
    @FXML
    private Label error;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/NewGameMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        if (stage.getScene() == null) {
            Scene scene = new Scene(pane);
            stage.setScene(scene);
        } else stage.getScene().setRoot(pane);
        stage.show();
    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void newGame() throws Exception {
        checkErrors();
        if (error.equals("Success")) {
            //TODO new game
        }
    }

    public void checkErrors() throws Exception {
        usernames = new ArrayList<>();
        if (!username1.getText().equals("") || User.getUserByUsername(username1.getText()) != null)
            usernames.add(username1.getText());
        if (!username2.getText().equals("") || User.getUserByUsername(username2.getText()) != null)
            usernames.add(username2.getText());
        if (!username3.getText().equals("") || User.getUserByUsername(username3.getText()) != null)
            usernames.add(username3.getText());
        if (turnsNumber.getText().equals("")) error.setText("Enter turns number");
        else if (errorHandle(usernames).equals("success")) {
            save();
            error.setText("success");
        } else {
            error.setText(errorHandle(usernames));
        }
    }

    private String errorHandle(ArrayList<String> usernames) throws Exception {
        ArrayList<String> usernames2 = new ArrayList<>();
        usernames2.addAll(usernames);
        usernames2.add(LoginMenuController.getLoggedInUser().getUsername());
        int players = 0, errorFinder = 0;
        for (String username : usernames2) {
            for (String name : usernames2) {
                if (name.equals(username)) {
                    errorFinder++;
                }
            }
            if (errorFinder >= 2) {
                return "duplicate usernames";
            }
            if (User.getUserByUsername(username) == null) {
                return username + " doesn't exist";
            }
            players++;
            errorFinder = 0;
        }
        if (players < 2) {
            return "choose more players";
        }
        return "success";
    }

    private void save() throws IOException {
        usernames.add(LoginMenuController.getLoggedInUser().getUsername());
        String text = "";
        for (String username : usernames) {
            text = text + username + "\n";
        }
        text += turnsNumber.getText();
        Path fileName = Path.of("game.txt");
        Files.writeString(fileName, text);
    }
}
