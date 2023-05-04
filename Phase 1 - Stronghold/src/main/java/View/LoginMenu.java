package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Controller.SignUpMenuController;
import Enums.Commands.LoginMenuCommands;
import Model.User;

import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenu {
    private final LoginMenuController loginMenuController;
    private final MainMenu mainMenu;
    private final SignUpMenuController signUpMenuController = new SignUpMenuController();
    private final SignupMenu signupMenu = new SignupMenu(signUpMenuController);

    public LoginMenu(LoginMenuController loginMenuController) {
        this.mainMenu= new MainMenu();
        this.loginMenuController = loginMenuController;
    }

    public void run() throws Exception {

        System.out.println("Don't have an account? Whenever you want, type \" create a new account \" to enter Signup Menu.");

        Matcher matcher;
        String command, result;

        while (true) {
            command = Menu.getScanner().nextLine();

            if (command.matches("^create a new account$")) {
                System.out.println("Entered Sign up menu!");
                signupMenu.run();
            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGGING_IN)) != null) {
                result = loginMenuController.login(matcher);
                System.out.println(result);
                if (Objects.equals(result, "user logged in successfully!"))
                    mainMenu.run();

            } else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD)) != null) {
                result = loginMenuController.forgetPassword(matcher);
                System.out.println(result);

                if (result.charAt(0) == 'P') {
                    String expectedAnswer = User.getUserByUsername(matcher.group("username")).getAnswerToSecurityQuestion();

                    while (true) {
                        command = Menu.getScanner().nextLine();
                        if (Objects.equals(command, expectedAnswer)) {
                            System.out.println("You answered the security question correctly. Please enter your new password:");

                            while (true) {
                                command = Menu.getScanner().nextLine();
                                result = loginMenuController.setANewPassword(matcher, command);
                                System.out.println(result);

                                if (result.charAt(0) == 'Y')
                                    break;
                            }
                        } else
                            System.out.println("Wrong answer to security question. Try again!");
                    }
                }
            } else if (command.matches("^exit$"))
                return;

            else
                System.out.println("invalid command!");
        }

    }
}