package Controller;

import Model.PreBuiltSecurityQuestions;
import Model.User;
import View.LoginMenu;

import java.util.regex.Matcher;

public class LoginMenuController {

    private static User loggenInUser;
    private final LoginMenu loginMenu;
    public LoginMenuController(){
        loginMenu = new LoginMenu(this);
    }

    public void run(){
    }
    public String login(Matcher matcher)
    {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);
        if(!user.isPasswordCorrect(password))
            return "Username and password didn’t match!";

        loggenInUser = user;
        return "user logged in successfully!";
    }

    public String forgetPassword(Matcher matcher)
    {
        String username = matcher.group("username");

        if(User.getUserByUsername(username) == null)
            return "No user with this username found!";

        return null;
    }

    public String setANewPassword(Matcher matcher)
    {
        return null;
    }

    public boolean isAnswerToSecurityQuestionCorrect()
    {
        return false;
    }

    public String ShowSecurityQuestion(User user)
    {
        String s = "Your security question: \"" + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(user.getNumberOfSecurityQuestion());
        s += "\"\nInput Your answer: ";
        return s;
    }

    public boolean isAnswerToSecurityQuestionCorrect(String username , String answer)
    {
        return false;
    }

    public static User getLoggenInUser(){
        return loggenInUser;
    }
}
