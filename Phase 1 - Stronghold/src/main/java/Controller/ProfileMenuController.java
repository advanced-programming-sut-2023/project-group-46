package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController {

    public String changeUsername(Matcher matcher) throws IOException {
        String newUsername = matcher.group("username");

        if (newUsername.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        changeUsernameAction(newUsername);

        return "Username was changed successfully.";
    }

    public void changeUsernameAction(String newUsername) throws IOException {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();


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
    }

    public String changeNickname(Matcher matcher) throws IOException {
        String newNickname = matcher.group("nickname");

        changeNicknameAction(newNickname);
        return "Nickname was changed successfully.";
    }

    public void changeNicknameAction(String newNickname) throws IOException {

        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();


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

            loggedInUser.setNickname(newNickname);

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());

    }

    public String changePassword(Matcher matcher) throws IOException {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        newPassword = newPassword.trim();
        if (newPassword.matches("^-n\\s.*$")) {
            newPassword = newPassword.substring(3);
            while (newPassword.matches("^\\s.*$")) {
                newPassword = newPassword.substring(1);
            }
        }

        oldPassword = oldPassword.trim();
        if (oldPassword.matches("^-o\\s.*$")) {
            oldPassword = oldPassword.substring(3);
            while (oldPassword.matches("^\\s.*$")) {
                oldPassword = oldPassword.substring(1);
            }
        }

        if (!LoginMenuController.getLoggedInUser().isPasswordCorrect(oldPassword))
            return "Current password is incorrect!";

        if (Objects.equals(oldPassword, newPassword))
            return "Please enter a new password!";

        if (newPassword.length() < 6)
            return "Weak password! Password length should be more than 5.";

        if (!newPassword.matches(".*[a-z].*"))
            return "Weak password! Password should have at least one small English letter.";

        if (!newPassword.matches(".*[A-Z].*"))
            return "Weak password! Password should have at least one capital English letter.";

        if (!newPassword.matches(".*[0-9].*"))
            return "Weak password! Password should have at least one digit.";

        if (!newPassword.matches(".*[^a-zA-Z0-9|_].*"))
            return "Weak password! Password should have at least one character except english letters and digits.";

        changePasswordAction(newPassword);

        return "Password was changed successfully.";
    }

    public void changePasswordAction(String newPassword) throws IOException {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();


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

            loggedInUser.setPassword(newPassword);

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }

    public String changeEmail(Matcher matcher) throws Exception {
        String newEmail = matcher.group("email");

        for (User user : User.getUsersFromJsonFile()) {
            if (Objects.equals(user.getEmail(), newEmail))
                return "This email is already taken!";
        }

        if (!newEmail.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        changeEmailAction(newEmail);
        return "Email was changed successfully.";
    }

    public void changeEmailAction(String newEmail) throws IOException {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();


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

            loggedInUser.setEmail(newEmail);

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }

    public String changeSlogan(Matcher matcher) throws IOException {
        String newSlogan = matcher.group("slogan");
        changeSloganAction(newSlogan);

        return "Slogan was changed successfully.";
    }

    public void changeSloganAction(String newSlogan) throws IOException {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();


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

            loggedInUser.setEmail(newSlogan);

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());
    }

    public String removeSlogan() throws IOException {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        if (Objects.equals(loggedInUser.getSlogan(), ""))
            return "You don't have a slogan to remove it!";

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
            loggedInUser.setSlogan("");

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());

        return "Removed slogan successfully!";
    }

    public String showUserHighScore() {
        return "Highscore: " + LoginMenuController.getLoggedInUser().getHighScore();
    }

    public String showUserRank() throws Exception {

        ArrayList<User> sortedUsers = new ArrayList<>();
        sortedUsers.addAll(User.getUsersFromJsonFile());

        Comparator<User> comparator = Comparator
                .comparing(User::getHighScore)
                .thenComparing(User::getUsername);

        sortedUsers.sort(comparator);

        int rank = 1;

        for (int i = 1; i <= sortedUsers.size(); i++) {
            if (sortedUsers.get(i - 1) == LoginMenuController.getLoggedInUser()) {
                rank = i;
                break;
            }
        }

        return "Rank: " + rank;
    }

    public String showUserSlogan() {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        if (Objects.equals(loggedInUser.getSlogan(), ""))
            return "Slogan is empty!";

        else return "Slogan: " + loggedInUser.getSlogan();
    }

    public String showProfileInfo() {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        String info = "Username: ";
        info += loggedInUser.getUsername() + "\n";

        info += "Password: " + loggedInUser.getPassword() + "\n";

        info += "Nickname: " + loggedInUser.getNickname() + "\n";

        info += "Email: " + loggedInUser.getEmail() + "\n";

        info += "Security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(loggedInUser.getNumberOfSecurityQuestion()) + "\n";
        info += "Answer to security question: " + loggedInUser.getAnswerToSecurityQuestion();

        if (Objects.equals(loggedInUser.getSlogan(), ""))
            return info;

        else
            info += "\nSlogan: " + loggedInUser.getSlogan();

        return info;
    }

}
