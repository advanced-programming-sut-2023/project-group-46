package View;

import Client.Connection;
import Client.RefreshLobby;
import Client.RefreshMessenger;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Enums.PreBuiltSecurityQuestions;
import Model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;

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
        LoginMenuController.getLoggedInUser().setOnline("false");
        String min = String.format("%02d", LocalTime.now().getMinute());
        String hor = String.format("%02d", LocalTime.now().getHour());
        String time = hor + ":" + min;
        ProfileMenuController.updateLastSeen(time);
        Connection.handelClient("submit users");
        new LoginMenu().start(LoginMenu.stage);
    }

    public void newGame() throws Exception {
        new NewGameMenu().start(LoginMenu.stage);
    }

    public void profileMenu() throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(LoginMenu.stage);
    }

    public void map() throws Exception {
        AddMapMenu addMapMenu = new AddMapMenu();
        addMapMenu.start(LoginMenu.stage);
    }

    public void friendship() throws Exception {
        Pane pane = new Pane();
        Stage stage = new Stage();
        TextField username = new TextField();
        username.setPromptText("enter username");
        username.setTranslateX(140);
        username.setTranslateY(50);
        Button button = new Button("send");
        button.setTranslateX(190);
        button.setTranslateY(100);
        Label label = new Label();
        label.setTextFill(Color.RED);
        label.setTranslateX(100);
        label.setTranslateY(150);
        pane.getChildren().addAll(username, button, label);
        username.textProperty().addListener((observable, oldText, newText) -> {
            User user = null;
            try {
                user = User.getUserByUsername(username.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (user == null) label.setText("user name dose not exist");
            else {
                String info = "Username: ";
                info += user.getUsername() + "\n";
                info += "Nickname: " + user.getNickname() + "\n";
                info += "Email: " + user.getEmail() + "\n";
                info += "Score: " + user.getScore() + "\n";
                info += "Security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(user.getNumberOfSecurityQuestion()) + "\n";
                info += "Answer to security question: " + user.getAnswerToSecurityQuestion();
                if (Objects.equals(user.getSlogan(), ""))
                    info += "\nSlogan is ,empty";
                else
                    info += "\nSlogan: " + user.getSlogan();
                label.setText(info);
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (User.getUserByUsername(username.getText()) != null) {
                        User receiverUser = User.getUserByUsername(username.getText());
                        if (receiverUser.friendRequests.contains(LoginMenuController.getLoggedInUser().getUsername()) || receiverUser.friendsOfUser.contains(LoginMenuController.getLoggedInUser().getUsername()))
                            stage.close();

                        else {
                            ProfileMenuController.sendReq(receiverUser.getUsername());
                            //ProfileMenuController.updateUserInJsonFile(LoginMenuController.getLoggedInUser().getUsername() , "friendRequests" , "users.json" , receiverUser);
                            Connection.handelClient("submit users");
                            stage.close();
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Scene scene = new Scene(pane, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void send(Stage stage) {
        //TODO
        stage.close();
    }

    public void chat() {
        ChatMenu chatMenu = new ChatMenu();
        chatMenu.start(new Stage());
        RefreshMessenger refreshMessenger = new RefreshMessenger();
        refreshMessenger.start();
    }

    public void Lobby(MouseEvent mouseEvent) {
        RefreshLobby refreshLobby = new RefreshLobby();
        refreshLobby.start();
        RefreshMessenger refreshMessenger = new RefreshMessenger();
        refreshMessenger.start();
        LobbyMenu lobbyMenu= new LobbyMenu(FXCollections.observableArrayList(), FXCollections.observableArrayList());
        lobbyMenu.start(LoginMenu.stage);
    }
}