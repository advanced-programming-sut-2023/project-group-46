package View;

import Controller.SignUpMenuController;
import Enums.Commands.SignupMenuCommands;
import Enums.PreBuiltSecurityQuestions;

import java.util.Objects;
import java.util.regex.Matcher;

public class SignupMenu {

    private final SignUpMenuController signUpMenuController;

    public SignupMenu(SignUpMenuController signUpMenuController) {
        this.signUpMenuController = signUpMenuController;
    }

    public void run() throws Exception {
        String command;
        Matcher matcher;

        while (true) {
            command = Menu.getScanner().nextLine();

            if ((matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER)) != null) {
                String result = signUpMenuController.register(matcher);
                System.out.println(result);

                if (result.charAt(0) == 'P' && result.charAt(1) == 'i') {
                    while (true) {
                        command = Menu.getScanner().nextLine();
                        Matcher matcher1;
                        if ((matcher1 = SignupMenuCommands.getMatcher(command, SignupMenuCommands.PICK_A_SECURITY_QUESTION)) != null) {
                            result = signUpMenuController.chooseSecurityQuestion(matcher, matcher1);
                            System.out.println(result);
                            if (result.charAt(0) == 'Y')
                                break;
                        } else
                            System.out.println("Please choose your security question and answer it!");
                    }
                }
            } else if ((matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD)) != null) {
                String result = signUpMenuController.registerWithRandomPassword(matcher);
                System.out.println(result);

                if (result.charAt(0) == 'P') {
                    int start_index = result.indexOf('"') + 1;

                    String expectedInput = result.substring(start_index, start_index + 12).trim();
                    while (true) {
                        command = Menu.getScanner().nextLine();

                        if (Objects.equals(command, expectedInput)) {
                            break;
                        } else
                            System.out.println("You didn't input the generated password correctly. please try again!");
                    }

                    String askingSecurityQuestion = "Pick your security question:\n";
                    for (int i = 1; i < 4; i++) {
                        askingSecurityQuestion += i + ". " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(i);
                        if (i != 3)
                            askingSecurityQuestion += "\n";
                    }
                    System.out.println(askingSecurityQuestion);

                    while (true) {
                        command = Menu.getScanner().nextLine();
                        Matcher matcher1;
                        if ((matcher1 = SignupMenuCommands.getMatcher(command, SignupMenuCommands.PICK_A_SECURITY_QUESTION)) != null) {
                            result = signUpMenuController.chooseSecurityQuestion(matcher, matcher1);
                            System.out.println(result);
                            if (result.charAt(0) == 'Y')
                                break;
                        } else
                            System.out.println("Please choose your security question and answer it!");
                    }

                }
            } else if (command.matches("^back$")) {
                System.out.println("Back to login menu!");
                return;
            } else System.out.println("Invalid command!");
        }
    }

}
