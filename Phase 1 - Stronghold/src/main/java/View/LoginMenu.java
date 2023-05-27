package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Controller.SignUpMenuController;
import Enums.Commands.LoginMenuCommands;
import Model.Captcha;
import Model.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenu extends Application{
    private LoginMenuController loginMenuController;
    private MainMenu mainMenu;
    private SignUpMenuController signUpMenuController = new SignUpMenuController();
    private SignupMenu signupMenu = new SignupMenu(signUpMenuController);
    static int delayTime = 0;
    public static Stage stage;
    private TextField username;
    private TextField nickname;
    private TextField email;
    private TextField passwordRecovery;
    private PasswordField password;
    private Label error;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        Pane pane= new Pane();
        Paint paint= new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        Label loginMenu= new Label("Login Menu");
        loginMenu.setFont(new Font(50));
        loginMenu.setTextFill(Color.YELLOW);
        loginMenu.setTranslateX(600); loginMenu.setTranslateY(50);
        username=new TextField();
        Label labelUsername= new Label("username");
        labelUsername.setFont(new Font(30));
        username.setTranslateX(1200);username.setTranslateY(150);
        labelUsername.setTranslateX(900); labelUsername.setTranslateY(140);
        password= new PasswordField();
        Label labelPassword= new Label("password");
        labelPassword.setFont(new Font(30));
        password.setTranslateX(1200); password.setTranslateY(250);
        labelPassword.setTranslateX(900); labelPassword.setTranslateY(240);
        nickname= new TextField();
        Label labelNickname= new Label("nickname");
        labelNickname.setFont(new Font(30));
        nickname.setTranslateX(1200); nickname.setTranslateY(350);
        labelNickname.setTranslateX(900); labelNickname.setTranslateY(340);
        email= new TextField();
        Label labelEmail= new Label("email");
        labelEmail.setFont(new Font(30));
        email.setTranslateX(1200); email.setTranslateY(450);
        labelEmail.setTranslateX(900); labelEmail.setTranslateY(440);
        passwordRecovery = new TextField();
        Label labelPasswordRecovery= new Label("password recovery");
        labelPasswordRecovery.setFont(new Font(30));
        passwordRecovery.setTranslateX(1200); passwordRecovery.setTranslateY(550);
        labelPasswordRecovery.setTranslateX(900); labelPasswordRecovery.setTranslateY(540);
        error = new Label("");
        error.setTranslateX(900); error.setTranslateY(650);
        error.setFont(new Font(20));error.setTextFill(Color.RED);
        Scene scene = new Scene(pane);
        pane.getChildren().addAll(username, nickname, email, passwordRecovery, password, error, loginMenu);
        pane.getChildren().addAll(labelUsername,labelNickname, labelEmail, labelPasswordRecovery, labelPassword);
        stage.setScene(scene);
        stage.show();
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

    public LoginMenu(LoginMenuController loginMenuController) {
        this.loginMenuController = loginMenuController;
        this.mainMenu = new MainMenu();
    }

    public void run() throws Exception {

        System.out.println("Don't have an account? Whenever you want, type \" create a new account \" to enter Signup Menu.");

        Matcher matcher;
        String command , result;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if(command.matches("^show current menu$"))
                System.out.println("Login Menu");

            else if(command.matches("^create a new account$"))
            {
                System.out.println("Entered Sign up menu!");
                signupMenu.run();
            }

            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGGING_IN)) != null)
            {
                result = loginMenuController.login(matcher);
                if(!Objects.equals(result, "Information were correct!"))
                    System.out.println(result);

                if(Objects.equals(result, "Information were correct!"))
                {
                    while (true)
                    {
                        if (Captcha.verifyCaptcha(false)) {
                            System.out.println("Logged in successfully!");
                            break;
                        }
                    }
                    mainMenu.run();
                    delayTime = 0;
                }

                else if(Objects.equals(result, "Username and password did not match!"))
                    delayForWrongPassword();

            }
            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD)) != null)
            {
                result = loginMenuController.forgetPassword(matcher);
                System.out.println(result);

                if(result.charAt(0) == 'P')
                {
                    String expectedAnswer = User.getUserByUsername( matcher.group("username")).getAnswerToSecurityQuestion();

                    while (true)
                    {
                        command = Menu.getScanner().nextLine();
                        if(Objects.equals(command, expectedAnswer))
                        {
                            System.out.println("You answered the security question correctly. Please enter your new password:");

                            while (true)
                            {
                                command = Menu.getScanner().nextLine();
                                result = loginMenuController.setANewPassword(matcher , command);
                                System.out.println(result);

                                if(result.charAt(0) == 'Y')
                                    break;
                            }
                        }
                        else
                            System.out.println("Wrong answer to security question. Try again!");
                    }
                }
            }

            else if(command.matches("^exit$"))
                return;

            else
                System.out.println("invalid command!");
        }

    }
}