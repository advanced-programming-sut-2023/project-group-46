package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import View.LoginMenu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;

public class LoginMenuController {

    private static User loggedInUser;
    private final LoginMenu loginMenu;

    public LoginMenuController() {
        loginMenu = new LoginMenu(this);
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggenInUser) {
        LoginMenuController.loggedInUser = loggenInUser;
    }

    public String login(Matcher matcher) throws Exception {
        String username = matcher.group("username").trim();
        String password = matcher.group("password").trim();

        username = cleanUsername(username);
        password = cleanPassword(password);

        if (User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);
        if (!user.isPasswordCorrect(password))
            return "Username and password did not match!";

        loggedInUser = user;
        if (matcher.group("stayLoggedInFlag") != null)
            handleStayLoggedIn();

        return "user logged in successfully!";
    }

    public String forgetPassword(Matcher matcher) throws Exception {
        String username = matcher.group("username");

        if (User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);

        return "Please answer your security question to reset password.\nYour security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(user.getNumberOfSecurityQuestion());
    }

    public String setANewPassword(Matcher matcher, String newPassword) throws Exception {
        newPassword = newPassword.trim();
        User user = User.getUserByUsername(matcher.group("username"));

        if (Objects.equals(user.getPassword(), newPassword))
            return "Please enter a new password!";

        if (newPassword.length() < 6 || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*") || !newPassword.matches(".*[^a-zA-Z0-9|_].*"))
            return handlePasswordErrors(newPassword);

        changePasswordAction(user, newPassword);

        return "Your password was changed successfully.";
    }

    public String handlePasswordErrors(String password) {
        if (password.length() < 6)
            return "Weak password! Password length should be more than 5.";

        if (!password.matches(".*[a-z].*"))
            return "Weak password! Password should have at least one small English letter.";

        if (!password.matches(".*[A-Z].*"))
            return "Weak password! Password should have at least one capital English letter.";

        if (!password.matches(".*[0-9].*"))
            return "Weak password! Password should have at least one digit.";

        if (!password.matches(".*[^a-zA-Z0-9|_].*"))
            return "Weak password! Password should have at least one character except english letters and digits.";

        else return "";
    }

    public void changePasswordAction(User user, String newPassword) throws IOException {
        // read the existing contents of the users.json file into a JSONArray
        String jsonString = new String(Files.readAllBytes(Paths.get("users.json")));
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
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }

    public String cleanUsername(String username) {
        username = username.trim();
        if (username.matches("^-u\\s.*$"))    // if the last input in username
        {
            username = username.substring(3);
            while (username.matches("^\\s.*$")) {
                username = username.substring(1);
            }
        }
        return username;
    }

    public String cleanPassword(String password) {
        password = password.trim();
        if (password.matches("^-p\\s.*$")) {
            password = password.substring(3);
            while (password.matches("^\\s.*$")) {
                password = password.substring(1);
            }
        }
        return password;
    }

    public void handleStayLoggedIn() throws Exception {
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

            for (User user1 : User.getUsersFromJsonFile()) {
                if (user1 == loggedInUser) {
                    loggedInUser.setStayedLoggedIn(true);

                    JSONObject updatedUser = new JSONObject(loggedInUser);
                    usersArray.put(userIndex, updatedUser);
                } else if (!user1.isStayedLoggedIn())
                    continue;

                else {
                    user1.setStayedLoggedIn(false);

                    JSONObject updatedUser = new JSONObject(user1);
                    usersArray.put(userIndex, updatedUser);
                }
            }

        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }
}
