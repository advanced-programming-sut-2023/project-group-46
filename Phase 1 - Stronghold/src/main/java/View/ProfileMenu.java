package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ProfileMenu extends Application {
    public static ImageView avatar;
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
    private int numberInScoreboard;

    public ProfileMenu() {
        this.profileMenuController = new ProfileMenuController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/ProfileMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        stage.getScene().setRoot(pane);
        stage.show();
    }

    @FXML
    public void initialize() throws Exception {
        username.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerUsername = profileMenuController.username();
                error.setText(answerUsername);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerNickname = profileMenuController.nickname();
                error.setText(answerNickname);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerEmail = profileMenuController.email();
                error.setText(answerEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerSlogan = profileMenuController.slogan();
                error.setText(answerSlogan);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        removeSlogan.selectedProperty().addListener((observable, oldText, newText) -> {
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

//    public void scoreboard() throws Exception {
//        error.setText(profileMenuController.scoreboard());
//    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

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
        String username = profileMenuController.changeUsername();
        if (username.equals("Success")) {
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
        } else error.setText(username);
    }

    public void changeNickname(MouseEvent mouseEvent) throws IOException {
        String nickname = profileMenuController.changeNickname();
        if (nickname.equals("Success")) {
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
        } else error.setText(nickname);
    }

    public void changeEmail(MouseEvent mouseEvent) throws IOException {
        String email = profileMenuController.changeEmail();
        if (email.equals("Success")) {
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
        } else error.setText(email);
    }

    public void changeSlogan(MouseEvent mouseEvent) throws IOException {
        String slogan = profileMenuController.changeSlogan();
        if (slogan.equals("Success")) {
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
        } else error.setText(slogan);
    }

    public void changePassword() throws Exception {
        ChangePassword changePassword = new ChangePassword();
        changePassword.start(new Stage());
    }

    public void scoreboard() throws Exception {
        Pane pane = new Pane();
        Stage stage = new Stage();
        ListView<String> listView = new ListView<>();
        VBox vbox = new VBox(listView);
        ObservableList<String> items = FXCollections.observableArrayList(profileMenuController.scoreboard());
        listView.setItems(items);
        Scene scene = new Scene(vbox, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    public void showUsername() {
        Label label = new Label(profileMenuController.showProfileInfo());
        Pane pane = new Pane(label);
        pane.setPrefHeight(150);
        pane.setPrefWidth(400);
        avatar = new ImageView();
        avatar.setX(350);
        avatar.setY(10);
        avatar.setFitHeight(44);
        avatar.setFitWidth(47);
        System.out.println(LoginMenuController.getLoggedInUser().getImage());
        try {
            System.out.println(LoginMenuController.getLoggedInUser().getImage());
            URI uri = new URI(LoginMenuController.getLoggedInUser().getImage());
            URL url = uri.toURL();
            avatar.setImage(new javafx.scene.image.Image(url.toExternalForm()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        pane.getChildren().add(avatar);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void changeAvatar() throws Exception {
        new AvatarMenu().start(new Stage());
    }
}