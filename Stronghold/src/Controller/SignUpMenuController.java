package Controller;

import Model.User;
import View.SignupMenu;

import java.util.regex.Matcher;

public class SignUpMenuController {
    private final SignupMenu signupMenu;
    public SignUpMenuController(){
        signupMenu = new SignupMenu(this);
    }

    public void run(){
    }

    public String register(Matcher matcher)
    {
        return null;
    }

    public String chooseSecurityQuestion()
    {

        return null;
    }

    public String generateRandomPassword()
    {
        return null;
    }


}