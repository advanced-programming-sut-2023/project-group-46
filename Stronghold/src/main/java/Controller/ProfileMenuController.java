package Controller;

import Model.User;
import View.ProfileMenu;

import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController {
    private final ProfileMenu profileMenu;
    public ProfileMenuController(){
        profileMenu = new ProfileMenu(this);
    }

    public void run(){

    }

    public String changeUsername(Matcher matcher)
    {
        String username = matcher.group("username");

        if(username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        LoginMenuController.getLoggenInUser().setUsername(username);
        return "Username was changed successfully.";
    }

    public String changeNickname(Matcher matcher)
    {
        String nickname = matcher.group("nickname");

        LoginMenuController.getLoggenInUser().setNickname(nickname);
        return "Nickname was changed successfully.";
    }

    public String changePassword(Matcher matcher)
    {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if(!LoginMenuController.getLoggenInUser().isPasswordCorrect(oldPassword))
            return "Current password is incorrect!";

        if(Objects.equals(oldPassword, newPassword))
            return "Please enter a new password!";

        if(newPassword.length() < 6)
            return "Weak password! Password length should be more than 5.";

        if(!newPassword.matches(".*[a-z].*"))
            return "Weak password! Password should have at least one small English letter.";

        if(!newPassword.matches(".*[A-Z].*"))
            return "Weak password! Password should have at least one capital English letter.";

        if(!newPassword.matches(".*[0-9].*"))
            return "Weak password! Password should have at least one digit.";

        if(!newPassword.matches(".*[^a-zA-Z0-9|_].*"))
            return "Weak password! Password should have at least one character except english letters and digits.";

        LoginMenuController.getLoggenInUser().setPassword(newPassword);
        return "Password was changed successfully.";

    }

    public String changeEmail(Matcher matcher)
    {
        String newEmail = matcher.group("email");

        for(User user : User.getUsers())
        {
            if(Objects.equals(user.getEmail(), newEmail))
                return "This email is already taken!";
        }

        if(!newEmail.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        LoginMenuController.getLoggenInUser().setEmail(newEmail);
        return "Email was changed successfully.";
    }

    public String changeSlogan()
    {
        return null;
    }

    public String removeSlogan()
    {
        return null;
    }

    public String showUserHighScore()
    {
        return "Highscore: " + LoginMenuController.getLoggenInUser().getHighScore();
    }

    public String showUserRank()
    {
        return null;
    }

    public String showUserSlogan()
    {
        return null;
    }

    public String showProfileInfo()
    {
        return null;
    }

}
