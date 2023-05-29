package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import View.LoginMenu;
import View.SignupMenu;
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
    private LoginMenu loginMenu;

    public LoginMenuController(LoginMenu loginMenu) {
        this.loginMenu = loginMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggenInUser) {
        LoginMenuController.loggedInUser = loggenInUser;
    }

    public String login() throws Exception {
        String username = loginMenu.getUsername().getText();
        String password = loginMenu.getPassword().getText();

        if (User.getUserByUsername(username) == null)
            return "No user with this username found!";

        User user = User.getUserByUsername(username);
        if (!user.isPasswordCorrect(password))
            return "Username and password did not match!";

        loggedInUser = user;
//        if (loginMenu.getStayLogin().isSelected()) handleStayLoggedIn();

        return "Information were correct!";
    }

    public String forgetPassword() throws Exception {
        System.out.println(User.getUserByUsername(loginMenu.getUsername().getText()).getUsername());
        if (User.getUserByUsername(loginMenu.getUsername().getText()) == null)
            return "No user with this username found!";
        User user = User.getUserByUsername(loginMenu.getUsername().getText());
        return "Please answer your security question to reset password.\nYour security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(user.getNumberOfSecurityQuestion());
    }

    public String setANewPassword(Matcher matcher, String newPassword) throws Exception {
        newPassword = newPassword.trim();
        User user = User.getUserByUsername(matcher.group("username"));

        if (user.isPasswordCorrect(newPassword))     // repeated the last password
            return "Please enter a new password!";

        if (newPassword.length() < 6 || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*") || !newPassword.matches(".*[^a-zA-Z0-9|_].*"))
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

        changePasswordAction(user, encryptedPassword, "users.json");

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

    public void changePasswordAction(User user, String newPassword, String fileName) throws IOException {

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
            int counter = 0;
            for (User user1 : User.getUsersFromJsonFile()) {
                if (Objects.equals(user1.getUsername(), loggedInUser.getUsername())) {
                    user1.setStayedLoggedIn(true);

                    JSONObject updatedUser = new JSONObject(user1);
                    usersArray.put(counter, updatedUser);
                    counter++;
                } else {
                    user1.setStayedLoggedIn(false);

                    JSONObject updatedUser = new JSONObject(user1);
                    usersArray.put(counter, updatedUser);
                    counter++;
                }
            }

        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }

    public void signup() throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }
}
