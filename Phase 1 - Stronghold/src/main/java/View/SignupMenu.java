package View;

import Controller.SignUpMenuController;
import Model.PreBuiltSecurityQuestions;

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

        while (true)
        {
            command = Menu.getScanner().nextLine();

            if ((matcher = Menu.getMatcher(command, "^user create -u (?<username>.+) -p (?<password>.+) (?<passwordConfirmation>.+) -email (?<userEmail>.+) -n (?<userNickName>.+)(?<sloganFlag> \\-s)?(?<userSlogan> .+)?$")) != null)
            {
                //TODO
                //slogan is not separated from nickname, so is not handled yet(Random or not random)

                String result = signUpMenuController.register(matcher);
                System.out.println(result);

                if(result.charAt(0) == 'P' && result.charAt(1) == 'i')
                {
                    while (true)
                    {
                        command = Menu.getScanner().nextLine();
                        Matcher matcher1;
                        if ((matcher1 = Menu.getMatcher(command, "^question pick -q (?<questionNumber>\\d+) -a (?<answer>.+) -c (?<answerConfirmation>.+)$")) != null)
                        {
                            result = signUpMenuController.chooseSecurityQuestion(matcher , matcher1);
                            System.out.println(result);
                            if(result.charAt(0) == 'Y')
                                break;
                        }
                        else
                            System.out.println("Please choose your security question and answer it!");
                    }
                }
            }

            else if ((matcher = Menu.getMatcher(command, "^user create -u (?<username>.+) -p random -email (?<userEmail>.+) -n (?<userNickName>.+)(?<sloganFlag> \\-s)?(?<userSlogan> .+)?$")) != null)
            {
                String result = signUpMenuController.registerWithRandomPassword(matcher);
                System.out.println(result);

                if(result.charAt(0) == 'P')
                {
                    int start_index = result.indexOf('"') + 1;

                    String expectedInput = result.substring(start_index , start_index + 12).trim();
                    while (true)
                    {
                        command = Menu.getScanner().nextLine();

                        if(Objects.equals(command, expectedInput))
                        {
                            System.out.println("You registered successfully!");
                            break;
                        }
                        else
                            System.out.println("You didn't input the generated password correctly. please try again!");
                    }

                    String askingSecurityQuestion = "Pick your security question:\n";
                    for(int i = 1 ; i < 4 ; i++)
                    {
                        askingSecurityQuestion += i +". "+ PreBuiltSecurityQuestions.getSecurityQuestionByNumber(i) ;
                        if(i != 3)
                            askingSecurityQuestion += "\n";
                    }
                    System.out.println(askingSecurityQuestion);

                    while (true)
                    {
                        command = Menu.getScanner().nextLine();
                        Matcher matcher1;
                        if ((matcher1 = Menu.getMatcher(command, "^question pick -q (?<questionNumber>\\d+) -a (?<answer>.+) -c (?<answerConfirmation>.+)$")) != null)
                        {
                            result = signUpMenuController.chooseSecurityQuestion(matcher , matcher1);
                            System.out.println(result);
                            if(result.charAt(0) == 'Y')
                                break;
                        }
                        else
                            System.out.println("Please choose your security question and answer it!");
                    }

                }
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
