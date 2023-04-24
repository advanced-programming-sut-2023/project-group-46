package View;

import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Controller.SignUpMenuController;
import Enums.Commands.LoginMenuCommands;
import Model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenu {
    private LoginMenuController loginMenuController;
    private MainMenu mainMenu;
    private SignUpMenuController signUpMenuController = new SignUpMenuController();
    private SignupMenu signupMenu = new SignupMenu(signUpMenuController);

    public LoginMenu(LoginMenuController empireMenuController) {
        this.loginMenuController = empireMenuController;
    }

    public void run() throws Exception {

        System.out.println("Don't have an account? Whenever you want, type \" create a new account \" to enter Signup Menu.");

        Matcher matcher;
        String command , result;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if(command.matches("^create a new account$"))
            {
                System.out.println("Entered Sign up menu!");
                signupMenu.run();
            }

            else if ((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGGING_IN)) != null)
            {
                result = loginMenuController.login(matcher);
                System.out.println(result);

                //TODO
                //change this part when main menu is completed
                ProfileMenu profileMenu = new ProfileMenu(new ProfileMenuController());
                if(Objects.equals(result, "user logged in successfully!"))
                    profileMenu.run();

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
                        if(command == expectedAnswer)
                        {
                            System.out.println("You answered the security question correctly. Please enter your new password:");
                            command = Menu.getScanner().nextLine();
                            //TODO
                            //change password action should be completed
                            break;
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