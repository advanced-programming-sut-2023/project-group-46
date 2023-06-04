package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Controller.SignUpMenuController;
import Enums.Commands.LoginMenuCommands;
import Model.Captcha;
import Model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenu extends Application{
    private LoginMenuController loginMenuController;
    public static Stage stage;
    private MainMenu mainMenu;
    static int delayTime = 0;
    @FXML
    private Label error;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private CheckBox visibility;
    @FXML
    private CheckBox stayLogin;
    @FXML
    private TextField recoveryPassword;
    @FXML
    private CheckBox forgetPassword;
    private Pane pane;

    public LoginMenu() {
        this.loginMenuController = new LoginMenuController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage= stage;
        stage.setMaximized(true);
        pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/LoginMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        if(stage.getScene() == null){
            Scene scene= new Scene(pane);
            stage.setScene(scene);
        }else stage.getScene().setRoot(pane);
        stage.show();
    }

    @FXML
    public void initialize() throws Exception {
        recoveryPassword.disableProperty().bind(forgetPassword.selectedProperty().not());
        forgetPassword.selectedProperty().addListener((observable, oldText, newText)->{
            try {
                recoveryPassword();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        stayLogin.selectedProperty().addListener((observable, oldText, newText)->{
            try {
                loginMenuController.handleStayLoggedIn();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
    }

    static void delayForWrongPassword() {
        if(delayTime < 15000)
            delayTime += 5000;

        System.out.println("The system is locked for " + delayTime/1000 + " seconds. please don't type anything until the sleep time ends.");

        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

//    public void run() throws Exception {
//
//        //System.out.println("Don't have an account? Whenever you want, type \" create a new account \" to enter Signup Menu.");
//
//        Matcher matcher;
//        String command , result;
//
//        while (true)
//        {
//            command = Menu.getScanner().nextLine();
//
//            if(command.matches("^show current menu$"))
//                System.out.println("Login Menu");
//
//            else if(command.matches("^create a new account$"))
//            {
//                System.out.println("Entered Sign up menu!");
//                //signupMenu.run();
//            }
//
//            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGGING_IN)) != null)
//            {
//                result = loginMenuController.login();
//                if(!Objects.equals(result, "Information were correct!"))
//                    System.out.println(result);
//
//                if(Objects.equals(result, "Information were correct!"))
//                {
//                    while (true)
//                    {
//                        if (Captcha.verifyCaptcha(false)) {
//                            System.out.println("Logged in successfully!");
//                            break;
//                        }
//                    }
//                    mainMenu.run();
//                    delayTime = 0;
//                }
//
//                else if(Objects.equals(result, "Username and password did not match!"))
//                    delayForWrongPassword();
//
//            }
//            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD)) != null)
//            {
//                result = loginMenuController.forgetPassword(matcher);
//                System.out.println(result);
//
//                if(result.charAt(0) == 'P')
//                {
//                    String expectedAnswer = User.getUserByUsername( matcher.group("username")).getAnswerToSecurityQuestion();
//
//                    while (true)
//                    {
//                        command = Menu.getScanner().nextLine();
//                        if(Objects.equals(command, expectedAnswer))
//                        {
//                            System.out.println("You answered the security question correctly. Please enter your new password:");
//
//                            while (true)
//                            {
//                                command = Menu.getScanner().nextLine();
//                                result = loginMenuController.setANewPassword(matcher , command);
//                                System.out.println(result);
//
//                                if(result.charAt(0) == 'Y')
//                                    break;
//                            }
//                        }
//                        else
//                            System.out.println("Wrong answer to security question. Try again!");
//                    }
//                }
//            }
//
//            else if(command.matches("^exit$"))
//                return;
//
//            else
//                System.out.println("invalid command!");
//        }
//
//    }

    public void signup() throws Exception {
        loginMenuController.signup();
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public CheckBox getStayLogin() {
        return stayLogin;
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String login= loginMenuController.login();
        if(login.equals("Information were correct!")){
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
                try {
                    new ProfileMenu().start(LoginMenu.stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else error.setText(login);
    }

    public void recoveryPassword() throws Exception {
//        TextField passwordRecovery= new TextField();
//        passwordRecovery.setTranslateX(1323);
//        passwordRecovery.setTranslateY(450);
//        pane.getChildren().add(passwordRecovery);
        error.setText(loginMenuController.forgetPassword());
    }
}