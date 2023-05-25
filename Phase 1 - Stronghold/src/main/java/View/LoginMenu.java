package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Controller.SignUpMenuController;
import Enums.Commands.LoginMenuCommands;
import Model.Captcha;
import Model.User;

import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenu {
    private LoginMenuController loginMenuController;
    private MainMenu mainMenu;
    private SignUpMenuController signUpMenuController = new SignUpMenuController();
    private SignupMenu signupMenu = new SignupMenu(signUpMenuController);
    static int delayTime = 0;

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