package View;

import Controller.ProfileMenuController;
import Model.Captcha;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ChangePassword extends Application {
    private ProfileMenuController profileMenuController;
    private PasswordField oldPassword;
    private PasswordField newPassword;
    private String answerPassword;
    private Button button;
    private Label error;
    private Stage stage;
    private TextField captcha;
    private int rand;

    public ChangePassword() throws Exception {
        this.profileMenuController = new ProfileMenuController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage= stage;
        stage.setResizable(false);
        error= new Label();
        error.setTextFill(Color.RED);
        error.setLayoutX(75);
        error.setLayoutY(270);
        oldPassword= new PasswordField();
        oldPassword.setPromptText("enter old password");
        oldPassword.setLayoutX(175);
        oldPassword.setLayoutY(50);
        newPassword= new PasswordField();
        newPassword.setPromptText("enter new password");
        newPassword.setLayoutX(175);
        newPassword.setLayoutY(100);
        button= new Button("change");
        button.setLayoutX(400);
        button.setLayoutY(350);
        captcha= new TextField();
        captcha.setPromptText("enter captcha");
        captcha.setTranslateX(175);
        captcha.setTranslateY(150);
        ImageView imageView= new ImageView();
        imageView.setX(172);
        imageView.setY(200);
        Image imageCaptcha= new Image(SignupMenu.class.getResource(Captcha.getCaptcha().get(rand)).toExternalForm());
        imageView.setImage(imageCaptcha);
        Pane pane= new Pane(oldPassword, newPassword, button, error, captcha, imageView);
        pane.setPrefWidth(500);
        pane.setPrefHeight(400);
        Scene scene= new Scene(pane);
        stage.setScene(scene);
        stage.show();
        initialize();
    }

    @FXML
    public void initialize() throws Exception {
        newPassword.textProperty().addListener((observable, oldText, newText)->{
            try {
                answerPassword= profileMenuController.newPassword(newPassword.getText());
                error.setText(answerPassword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    error.setText(profileMenuController.changePassword());
                    if(error.getText().equals("Please enter your captcha correctly!")) {
                        stage.close();
                        start(new Stage());
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), a -> {
                    if(error.getText().equals("Password was changed successfully.")) stage.close();
                }));
                timeline.setCycleCount(1);
                timeline.play();

            }
        };
        button.setOnAction(event);
    }

    public PasswordField getOldPassword() {
        return oldPassword;
    }

    public PasswordField getNewPassword() {
        return newPassword;
    }

    public String getAnswerPassword() {
        return answerPassword;
    }

    public TextField getCaptcha() {
        return captcha;
    }

    public int getRand() {
        return rand;
    }

    public void changeRand(){
        rand ++;
        Captcha.setNum(rand);
    }
}
