package Controller;

import Model.User;
import View.ProfileMenu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController {
    private final ProfileMenu profileMenu;
    public ProfileMenuController(){
        profileMenu = new ProfileMenu(this);
    }

    public void run(){

    }

    public String changeUsername(Matcher matcher) throws IOException {
        String newUsername = matcher.group("username");

        if(newUsername.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggenInUser();


        // read the existing contents of the users.json file into a JSONArray
        String jsonString = new String(Files.readAllBytes(Paths.get("users.json")));
        JSONArray usersArray = new JSONArray(jsonString);

        // find the index of the JSONObject for the updated User
        int userIndex = -1;
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject jsonObject = usersArray.getJSONObject(i);
            if (jsonObject.getString("username").equals(loggedInUser.getUsername())) {
                userIndex = i;
                break;
            }
        }

        // replace the old JSONObject with the updated User's JSONObject
        if (userIndex != -1) {
            // update the username of the logged in user
            loggedInUser.setUsername(newUsername);

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());

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

    public String changeEmail(Matcher matcher) throws Exception {
        String newEmail = matcher.group("email");

        for(User user : User.getUsersFromJsonFile())
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
