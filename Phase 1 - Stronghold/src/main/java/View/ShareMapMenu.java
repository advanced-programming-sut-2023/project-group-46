package View;

import Client.Connection;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class ShareMapMenu extends Application {
    ImageView map1;
    ImageView map2;
    ImageView map3;
    ImageView map4;
    ImageView map5;
    ImageView map6;
    @FXML
    Label error;
    @FXML
    TextField enterUsername;
    Stage stage;
    static String selectedMap;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage= stage;
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/ShareMapMenu.fxml").toExternalForm()));
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

    public void back() throws Exception {
        new LocalMapMenu().start(LoginMenu.stage);
    }

    public void map1() throws Exception {
        selectedMap="/Image/Map/1.png";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void map2() throws Exception {
        selectedMap="/Image/Map/2.png";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void map3() throws Exception {
        selectedMap="/Image/Map/3.png";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void map4() throws Exception {
        selectedMap="/Image/Map/4.png";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void map5() throws Exception {
        selectedMap="/Image/Map/5.png";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void map6() throws Exception {
        selectedMap="/Image/Map/6.png";
//        System.out.println(selectedMap);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation");
        alert.setContentText("map selected");
        alert.show();
    }

    public void enter() throws Exception {
        if(User.getUserByUsername(enterUsername.getText()) == null)
            error.setText("username not found");

        else {
            User receiverUser = User.getUserByUsername(enterUsername.getText());
            if (receiverUser.getMaps().contains(selectedMap) || receiverUser.getMapRequests().contains(selectedMap))
                error.setText("this user already has the map");

            else {
                System.out.println(selectedMap);
                ProfileMenuController.sendMap(enterUsername.getText() , selectedMap);
                //ProfileMenuController.updateUserInJsonFile(LoginMenuController.getLoggedInUser().getUsername() , "friendRequests" , "users.json" , receiverUser);
                Connection.handelClient("submit users");
//                stage.close();
            }
        }

    }

    private ObservableList<String> requests = FXCollections.observableArrayList();
    private ListView<String> requestListView;

    public void mapRequests() throws Exception {
        Stage primaryStage= new Stage();
        requests.setAll(User.getUserByUsername(LoginMenuController.getLoggedInUser().getUsername()).getMapRequests());
        requestListView = new ListView<>(requests);
        requestListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> {
            try {
                acceptRequest();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Button denyButton = new Button("Deny");
        denyButton.setOnAction(e -> {
            try {
                denyRequest();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(acceptButton, denyButton);
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(requestListView, buttonsBox);
        BorderPane layout = new BorderPane();
        layout.setCenter(mainBox);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void acceptRequest() throws Exception {
        String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            if(LoginMenuController.getLoggedInUser().getMaps().size() == 6) return;
            requests.remove(selectedRequest);
            ProfileMenuController.removeMapRequest(selectedRequest);
            Connection.handelClient("submit users");
            ProfileMenuController.addMap(selectedRequest);  // TODO: Something goes wrong with the formmat of map name, maybe Amirhossein can do sth
            Connection.handelClient("submit users");
//            LoginMenuController.getLoggedInUser().getMaps().add(selectedRequest);
        }
    }

    private void denyRequest() throws Exception {
        String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            requests.remove(selectedRequest);
            ProfileMenuController.removeMapRequest(selectedRequest);
            Connection.handelClient("submit users");
        }
    }
}
