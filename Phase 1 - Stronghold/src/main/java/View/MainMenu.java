package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/MainMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        if (stage.getScene() == null) {
            Scene scene = new Scene(pane);
            stage.setScene(scene);
        } else stage.getScene().setRoot(pane);
        stage.show();
    }

    public void logout() throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }

    public void newGame() throws Exception {
        new NewGameMenu().start(LoginMenu.stage);
    }

    public void profileMenu() throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(LoginMenu.stage);
    }
}