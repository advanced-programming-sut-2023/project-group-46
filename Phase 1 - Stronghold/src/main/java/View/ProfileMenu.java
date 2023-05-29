package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Enums.BuildingType;
import Enums.Commands.ProfileMenuCommands;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private String answerUsername;
    private String answerNickname;
    private String answerEmail;
    private String answerSlogan;

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
                answerUsername= profileMenuController.username();
                error.setText(answerUsername);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText)->{
            try {
                answerNickname= profileMenuController.nickname();
                error.setText(answerNickname);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        email.textProperty().addListener((observable, oldText, newText)->{
            try {
                answerEmail= profileMenuController.email();
                error.setText(answerEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText)->{
            try {
                answerSlogan= profileMenuController.slogan();
                error.setText(answerSlogan);
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

//    public void scoreboard() throws Exception {
//        error.setText(profileMenuController.scoreboard());
//    }

    public String getAnswerUsername() {
        return answerUsername;
    }

    public String getAnswerNickname() {
        return answerNickname;
    }

    public String getAnswerEmail() {
        return answerEmail;
    }

    public String getAnswerSlogan() {
        return answerSlogan;
    }

    public void changeUsername(MouseEvent mouseEvent) throws IOException {
        String username= profileMenuController.changeUsername();
        if(username.equals("Success")){
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else error.setText(username);
    }

    public void changeNickname(MouseEvent mouseEvent) throws IOException {
        String nickname= profileMenuController.changeNickname();
        if(nickname.equals("Success")){
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else error.setText(nickname);
    }

    public void changeEmail(MouseEvent mouseEvent) throws IOException {
        String email= profileMenuController.changeEmail();
        if(email.equals("Success")){
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else error.setText(email);
    }

    public void changeSlogan(MouseEvent mouseEvent) throws IOException {
        String slogan= profileMenuController.changeSlogan();
        if(slogan.equals("Success")){
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else error.setText(slogan);
    }

    public void changePassword(){
        Dialog dialog= new Dialog();
        PasswordField oldPassword= new PasswordField();
        oldPassword.setPromptText("enter old password");
        PasswordField newPassword= new PasswordField();
        newPassword.setPromptText("enter new password");
        //Button button= new Button("change");
        ButtonType button= new ButtonType("change");
        dialog.getDialogPane().getButtonTypes().addAll(button);
        dialog.show();
    }

    public void scoreboard() throws Exception {
        ListView<String> listView= new ListView<>();
        ObservableList<String> items= FXCollections.observableArrayList(profileMenuController.scoreboard());
        listView.setItems(items);
        listView.setPrefHeight(10 * (listView.getFixedCellSize() + .5));
        listView.setOnScroll((event) ->{
            int offset= listView.getItems().size() - 10;
//            if(listView.getOnScrollTo() >= offset)
        });
    }
}