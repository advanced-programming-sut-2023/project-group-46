package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import View.LoginMenu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenuController {

    private static User loggedInUser;
    private final LoginMenu loginMenu;
    public LoginMenuController(){
        loginMenu = new LoginMenu(this);
    }

    public String login(Matcher matcher) throws Exception {
        String username = matcher.group("username").trim();
        String password = matcher.group("password").trim();

        username = cleanUsername(username);
        password = cleanPassword(password);

        if(User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);
        if(!user.isPasswordCorrect(password))
            return "Username and password did not match!";

        loggedInUser = user;
        if(matcher.group("stayLoggedInFlag") != null)
            handleStayLoggedIn("users.json");

        return "Information were correct!";
    }

    public String forgetPassword(Matcher matcher) throws Exception {
        String username = matcher.group("username");

        if(User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);

        return "Please answer your security question to reset password.\nYour security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(user.getNumberOfSecurityQuestion());
    }

    public String setANewPassword(Matcher matcher , String newPassword) throws Exception {
        newPassword = newPassword.trim();
        User user = User.getUserByUsername(matcher.group("username"));

        if(user.isPasswordCorrect(newPassword))     // repeated the last password
            return "Please enter a new password!";

        if(newPassword.length() < 6 || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*") || !newPassword.matches(".*[^a-zA-Z0-9|_].*"))
            return handlePasswordErrors(newPassword);

        String encryptedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(newPassword.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            encryptedPassword = no.toString(16);
            while (encryptedPassword.length() < 32) {
                encryptedPassword = "0" + encryptedPassword;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        changePasswordAction(user , encryptedPassword , "users.json");

        return "Your password was changed successfully.";
    }

    public String handlePasswordErrors(String password )
    {
        if(password.length() < 6)
            return "Weak password! Password length should be more than 5.";

        if(!password.matches(".*[a-z].*"))
            return "Weak password! Password should have at least one small English letter.";

        if(!password.matches(".*[A-Z].*"))
            return "Weak password! Password should have at least one capital English letter.";

        if(!password.matches(".*[0-9].*"))
            return "Weak password! Password should have at least one digit.";

        if(!password.matches(".*[^a-zA-Z0-9|_].*"))
            return "Weak password! Password should have at least one character except english letters and digits.";

        else return "";
    }

    public void changePasswordAction(User user , String newPassword , String fileName) throws IOException {

        // read the existing contents of the users.json file into a JSONArray
        String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
        JSONArray usersArray = new JSONArray(jsonString);

        // find the index of the JSONObject for the updated User
        int userIndex = -1;
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject jsonObject = usersArray.getJSONObject(i);
            if (jsonObject.getString("username").equals(user.getUsername())) {
                userIndex = i;
                break;
            }
        }

        // replace the old JSONObject with the updated User's JSONObject
        if (userIndex != -1) {

            user.setPassword(newPassword);

            JSONObject updatedUser = new JSONObject(user);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get(fileName), usersArray.toString().getBytes());
    }
    public String cleanStringWithAFlag(String string , String flag)
    {
        string = string.trim();
        if (string.matches("^" + flag + "\\s.*$"))
        {
            string = string.substring(3);
            while (string.matches("^\\s.*$"))
                string = string.substring(1);
        }
        return string;
    }

    public String cleanUsername(String username)
    {
        return cleanStringWithAFlag(username , "-u");
    }

    public String cleanPassword(String password)
    {
        return cleanStringWithAFlag(password , "-p");
    }

    public void handleStayLoggedIn(String filename) throws Exception {
        // read the existing contents of the users.json file into a JSONArray
        String jsonString = new String(Files.readAllBytes(Paths.get(filename)));
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
            int counter = 0;
            for(User user1 : User.getUsersFromJsonFile())
            {
                if(Objects.equals(user1.getUsername(), loggedInUser.getUsername()))
                {
                    user1.setStayedLoggedIn(true);

                    JSONObject updatedUser = new JSONObject(user1);
                    usersArray.put(counter, updatedUser);
                    counter++;
                }

                else
                {
                    user1.setStayedLoggedIn(false);

                    JSONObject updatedUser = new JSONObject(user1);
                    usersArray.put(counter, updatedUser);
                    counter++;
                }
            }

        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get(filename), usersArray.toString().getBytes());
    }


    public static User getLoggedInUser(){
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggenInUser) {
        LoginMenuController.loggedInUser = loggenInUser;
    }
}
