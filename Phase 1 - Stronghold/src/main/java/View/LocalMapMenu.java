package View;

import Client.Connection;
import Client.Database;
import Client.Reaction;
import Client.ReactionType;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;

public class LocalMapMenu extends Application {
    ImageView map1;
    ImageView map2;
    ImageView map3;
    ImageView map4;
    ImageView map5;
    ImageView map6;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/LocalMapMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        map1= new ImageView();
        map1.setFitHeight(150);
        map1.setFitWidth(200);
        map1.setTranslateX(475);
        map1.setTranslateY(400);
        map2= new ImageView();
        map2.setFitHeight(150);
        map2.setFitWidth(200);
        map2.setTranslateX(475);
        map2.setTranslateY(195);
        map3= new ImageView();
        map3.setFitHeight(150);
        map3.setFitWidth(200);
        map3.setTranslateX(1161);
        map3.setTranslateY(190);
        map4= new ImageView();
        map4.setFitHeight(150);
        map4.setFitWidth(200);
        map4.setTranslateX(817);
        map4.setTranslateY(190);
        map5= new ImageView();
        map5.setFitHeight(150);
        map5.setFitWidth(200);
        map5.setTranslateX(817);
        map5.setTranslateY(400);
        map6= new ImageView();
        map6.setFitHeight(150);
        map6.setFitWidth(200);
        map6.setTranslateX(1161);
        map6.setTranslateY(400);
        map6= new ImageView();
        map6.setFitHeight(150);
        map6.setFitWidth(200);
        map6.setTranslateX(475);
        map6.setTranslateY(400);
        pane.getChildren().addAll(map1, map2, map3, map4, map5, map6);
        map1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map1();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map2();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map3();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map4();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map5();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        map6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    map6();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        if(stage.getScene() == null){
            Scene scene= new Scene(pane);
            stage.setScene(scene);
        }else stage.getScene().setRoot(pane);
        setMaps();
        stage.show();
    }

    public void back() throws Exception {
        new AddMapMenu().start(LoginMenu.stage);
    }

    private void setMaps(){
        if(LoginMenuController.getLoggedInUser().getMaps().size() == 1){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
        }else if(LoginMenuController.getLoggedInUser().getMaps().size() == 2){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
            map2.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(1)).toExternalForm()));
        }else if(LoginMenuController.getLoggedInUser().getMaps().size() == 3){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
            map2.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(1)).toExternalForm()));
            map3.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(2)).toExternalForm()));
        }else if(LoginMenuController.getLoggedInUser().getMaps().size() == 4){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
            map2.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(1)).toExternalForm()));
            map3.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(2)).toExternalForm()));
            map4.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(3)).toExternalForm()));
        }else if(LoginMenuController.getLoggedInUser().getMaps().size() == 5){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
            map2.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(1)).toExternalForm()));
            map3.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(2)).toExternalForm()));
            map4.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(3)).toExternalForm()));
            map5.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(4)).toExternalForm()));
        }else if(LoginMenuController.getLoggedInUser().getMaps().size() == 6){
            map1.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(0)).toExternalForm()));
            map2.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(1)).toExternalForm()));
            map3.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(2)).toExternalForm()));
            map4.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(3)).toExternalForm()));
            map5.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(4)).toExternalForm()));
            map6.setImage(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getMaps().get(5)).toExternalForm()));
        }
    }

    public void map1() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map1.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/1.png");
        Connection.handelClient("submit users");

        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map2() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map2.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/2.png");
        Connection.handelClient("submit users");

        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map3() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map3.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/3.png");
        Connection.handelClient("submit users");

        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map4() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map4.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/4.png");
        Connection.handelClient("submit users");


        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map5() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map5.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/5.png");
        Connection.handelClient("submit users");

        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map6() throws Exception {
        LoginMenuController.getLoggedInUser().getMaps().remove(map6.getImage().getUrl());
        ProfileMenuController.removeMapFromJsonFile("/Image/Map/6.png");
        Connection.handelClient("submit users");


        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void shareMap() throws Exception {
        new ShareMapMenu().start(LoginMenu.stage);
    }
}
