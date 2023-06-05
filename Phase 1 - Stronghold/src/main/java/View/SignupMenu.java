package View;

import Controller.SignUpMenuController;
import Enums.PreBuiltSecurityQuestions;
import Enums.PreBuiltSlogans;
import Model.Captcha;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;

public class SignupMenu extends Application {
    private SignUpMenuController signUpMenuController;
    @FXML
    private TextField username;
    @FXML
    private TextField nickname;
    @FXML
    private TextField email;
    @FXML
    private TextField passwordRecovery;
    @FXML
    private PasswordField password;
    @FXML
    private Label error;
    @FXML
    private TextField slogan;
    @FXML
    private CheckBox randomPassword;
    @FXML
    private CheckBox chooseSlogan;
    @FXML
    private CheckBox randomSlogan;
    @FXML
    private CheckBox visibility;
    private String answerUsername;
    private String answerNickname;
    private String answerEmail;
    private String answerSlogan;
    private String answerPassword;
    private String answerPasswordRecovery;
    private int questionNumber;
    @FXML
    private ChoiceBox choiceBox;
    private int sloganNumber;
    @FXML
    private ChoiceBox slogans;
    @FXML
    private TextField captcha;
    private int rand;

    public SignupMenu() {
        this.signUpMenuController = new SignUpMenuController(this);
        answerUsername = "";
        answerNickname = "";
        answerEmail = "";
        answerSlogan = "";
        answerPassword = "";
        answerPasswordRecovery = "";
        rand= Captcha.getNum();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/SignupMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        ImageView imageView= new ImageView();
        imageView.setX(150);
        imageView.setY(700);
        Image imageCaptcha= new Image(SignupMenu.class.getResource(Captcha.getCaptcha().get(rand)).toExternalForm());
        imageView.setImage(imageCaptcha);
        pane.getChildren().add(imageView);
        stage.getScene().setRoot(pane);
        stage.show();
    }

    @FXML
    public void initialize() {
        String question[] = {PreBuiltSecurityQuestions.getSecurityQuestionByNumber(1),
                PreBuiltSecurityQuestions.getSecurityQuestionByNumber(2), PreBuiltSecurityQuestions.getSecurityQuestionByNumber(3)};
        choiceBox.setItems(FXCollections.observableArrayList(question));
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                questionNumber = new_value.intValue() + 1;
            }
        });
        String slogansStrings[] = {PreBuiltSlogans.getSloganByNumber(1), PreBuiltSlogans.getSloganByNumber(2), PreBuiltSlogans.getSloganByNumber(3),
                PreBuiltSlogans.getSloganByNumber(4), PreBuiltSlogans.getSloganByNumber(5)};
        slogans.setItems(FXCollections.observableArrayList(slogansStrings));
        slogans.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue observableValue, Number number, Number t1) {
                sloganNumber= t1.intValue() + 1;
            }
        });
        slogan.disableProperty().bind(chooseSlogan.selectedProperty().not());
        randomSlogan.disableProperty().bind(chooseSlogan.selectedProperty().not());
        slogans.disableProperty().bind(chooseSlogan.selectedProperty().not());
        visibility.setOnAction(event -> {
            if (visibility.isSelected()) {
                password.setPromptText(password.getText());
                password.setText("");
                password.setDisable(true);
            } else {
                password.setText(password.getPromptText());
                password.setPromptText("password");
                password.setDisable(false);
            }
        });
        username.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerUsername = signUpMenuController.username();
                error.setText(answerUsername);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerNickname = signUpMenuController.nickname();
                error.setText(answerNickname);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerEmail = signUpMenuController.email();
                error.setText(answerEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerSlogan = signUpMenuController.slogan(randomSlogan.isSelected());
                if (randomSlogan.isSelected()) {
                    slogan.setText(answerSlogan);
                    error.setText("Success");
                }else if(sloganNumber != 0){
                    slogan.setText(PreBuiltSlogans.getSloganByNumber(sloganNumber));
                    error.setText("Success");
                } else error.setText(answerSlogan);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        password.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerPassword = signUpMenuController.password(randomPassword.isSelected());
                error.setText(answerPassword);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        passwordRecovery.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerPasswordRecovery = signUpMenuController.passwordRecovery();
                error.setText(answerPasswordRecovery);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getNickname() {
        return nickname;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getPasswordRecovery() {
        return passwordRecovery;
    }

    public PasswordField getPassword() {
        return password;
    }

    public TextField getSlogan() {
        return slogan;
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

    public String getAnswerPassword() {
        return answerPassword;
    }

    public String getAnswerPasswordRecovery() {
        return answerPasswordRecovery;
    }

    public CheckBox getChooseSlogan() {
        return chooseSlogan;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void back() throws Exception {
        signUpMenuController.back();
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

    public void signup(MouseEvent mouseEvent) throws Exception {
        String signup = signUpMenuController.signup();
        if(signup.equals("Please enter your captcha correctly!")) start(LoginMenu.stage);
        if (signup.equals("Success")) {
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
                try {
                    new LoginMenu().start(LoginMenu.stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }));
            timeline.setCycleCount(1);
            timeline.play();
        } else error.setText(signup);
    }
}
