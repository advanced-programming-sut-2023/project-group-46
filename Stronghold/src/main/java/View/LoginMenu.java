package View;

import Controller.LoginMenuController;
import Controller.SignUpMenuController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu {
    private LoginMenuController loginMenuController;
    private SignUpMenuController signUpMenuController = new SignUpMenuController();
    private SignupMenu signupMenu = new SignupMenu(signUpMenuController);

    public LoginMenu(LoginMenuController empireMenuController) {
        this.loginMenuController = empireMenuController;
    }

    public void run() throws IOException {

        System.out.println("Don't have an account? Type \"Create a new account\" to enter Signup Menu.");

        Matcher matcher;
        String command;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if(command.matches("^\\s*Create\\s+a\\s+new\\s+account\\s*$"))
            {
                System.out.println("Entered Sign up menu!");
                signupMenu.run();
            }

            else if ((matcher = Menu.getMatcher(command, "^\\s*user\\s+login\\s+\\-u\\s+(?<username>.+)\\s+\\-p\\s+(?<password>.+)\\s+(?<stayLoggedInFlag>\\-\\-stay\\-logged\\-in)\\s*$")) != null)
                System.out.println(loginMenuController.login(matcher));

            else if ((matcher = Menu.getMatcher(command, "^\\s*forgot\\s+my\\s+password\\s+\\-u\\s+(?<username>.+)\\s*$")) != null)
                System.out.println(loginMenuController.forgetPassword(matcher));



        }

    }
}