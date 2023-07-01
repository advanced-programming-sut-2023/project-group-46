package View;

import Client.Connection;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class AddMapMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/AddMapMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        if(stage.getScene() == null){
            Scene scene= new Scene(pane);
            stage.setScene(scene);
        }else stage.getScene().setRoot(pane);
        stage.show();
    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void localMap() throws Exception {
        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void addMapToJsonFile(String mapName) throws Exception {
        ProfileMenuController.addMap(mapName);
        Connection.handelClient("submit users");
    }

    public void map1() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains("/Image/Map/1.png")) {
            //LoginMenuController.getLoggedInUser().getMaps().add("/Image/Map/1.png");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();


            addMapToJsonFile("/Image/Map/1.png");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }

    public void map2() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains("/Image/Map/2.png")) {
            //LoginMenuController.getLoggedInUser().getMaps().add("/Image/Map/2.png");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();

            addMapToJsonFile("/Image/Map/2.png");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }

    public void map3() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains("/Image/Map/3.png")) {
            //LoginMenuController.getLoggedInUser().getMaps().add("/Image/Map/3.png");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();

            addMapToJsonFile("/Image/Map/3.png");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }

    public void map4() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains("/Image/Map/4.png")) {
            //LoginMenuController.getLoggedInUser().getMaps().add("/Image/Map/4.png");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();

            addMapToJsonFile("/Image/Map/4.png");


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }

    public void map5() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains("/Image/Map/5.png")) {
            //LoginMenuController.getLoggedInUser().getMaps().add("/Image/Map/5.png");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();

            addMapToJsonFile("/Image/Map/5.png");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }

    public void map6() throws Exception {
        if(!LoginMenuController.getLoggedInUser().getMaps().contains(LoginMenu.class.getResource("/Image/Map/6.png").toExternalForm())) {
            //LoginMenuController.getLoggedInUser().getMaps().add(LoginMenu.class.getResource("/Image/Map/6.png").toExternalForm());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation");
            alert.setContentText("map add to local maps");
            alert.show();

            addMapToJsonFile("/Image/Map/6.png");

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error duplicated");
            alert.setContentText("this map is already is in your local maps");
            alert.show();
        }
    }
}
