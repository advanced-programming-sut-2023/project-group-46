package Controller;

import java.util.regex.Matcher;

import Model.User;
import View.EmpireMenu;
import View.LoginMenu;

public class LoginMenuController {

    private static User loggenInUser;

    public String login(Matcher matcher) {
        return null;
    }

    public String forgetPassword(Matcher matcher) {
        return null;
    }

    public String setANewPassword(Matcher matcher) {
        return null;
    }

    public boolean isAnswerToSecurityQuestionCorrect() {
        return false;
    }

    public String ShowSecurityQuestion(User user) {
        return null;
    }

    public boolean isAnswerToSecurityQuestionCorrect(String username, String answer) {
        return false;
    }

    public static User getLoggenInUser() {
        return loggenInUser;
    }
}