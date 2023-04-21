package View;

import Controller.SignUpMenuController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;

public class SignupMenu {

    private final SignUpMenuController signUpMenuController;

    public SignupMenu(SignUpMenuController signUpMenuController) {
        this.signUpMenuController = signUpMenuController;
    }
    public void run() throws IOException {
        String command;
        Matcher matcher;

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if ((matcher = Menu.getMatcher(command, "^\\s*user\\s+create\\s+\\-u\\s+(?<username>.+)\\s+\\-p\\s+(?<password>.+)\\s+(?<passwordConfirmation>.+)\\s+\\-email\\s+(?<userEmail>.+)\\s+\\-n(?<userNickName>.+)(?<sloganFlag>\\s+\\-s)?(?<userSlogan>\\s+.+)?\\s*$")) != null)
            {
                System.out.println("Y");
                System.out.println(signUpMenuController.register(matcher));
            }

            else if(command.matches("^\\s*back\\s*$"))
            {
                System.out.println("Back to login menu!");
                return;
            }

            else System.out.println("Invalid command!");
        }
    }

}
