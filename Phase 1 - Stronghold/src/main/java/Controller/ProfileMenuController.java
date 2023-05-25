package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import View.ProfileMenu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenuController {
    private final ProfileMenu profileMenu;
    public ProfileMenuController(){
        profileMenu = new ProfileMenu(this);
    }

    public void updateUserInJsonFile(String newField, String fieldType, String filename) throws IOException {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();

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

        // update the specified user field
        if (userIndex != -1) {
            switch(fieldType) {
                case "username":
                    loggedInUser.setUsername(newField);
                    break;
                case "email":
                    loggedInUser.setEmail(newField);
                    break;
                case "password":
                    loggedInUser.setPassword(newField);
                    break;
                case "nickname":
                    loggedInUser.setNickname(newField);
                    break;
                case "slogan":
                    loggedInUser.setSlogan(newField);
                    break;
                default:
                    System.out.println("Invalid field type.");
                    return;
            }

            JSONObject updatedUser = new JSONObject(loggedInUser);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get(filename), usersArray.toString().getBytes());
    }

    public String changeUsername(Matcher matcher) throws Exception {
        String newUsername = matcher.group("username");

        if(newUsername.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        if(User.getUserByUsername(newUsername) != null)
            return "This username is already taken!";

        updateUserInJsonFile(newUsername ,"username", "users.json");

        return "Username was changed successfully.";
    }

    public String changeNickname(Matcher matcher) throws IOException {
        String nickName = matcher.group("nickname");

        switch (checkStringForDoubleQuote(nickName))
        {
            case 1:
                return "The nickName you entered has only 1 double quote!";
            case 2:
                nickName = nickName.substring(1 , nickName.length() - 1);
                break;
            case 3:
                return "The nickName you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        updateUserInJsonFile(nickName ,"nickname", "users.json");
        return "Nickname was changed successfully.";
    }


    public static int checkStringForDoubleQuote(String input)
    {
        //"Only one double quote"   1
        //"Has two double quotes correctly which should be removed"    2
        //"Is not between double quotes and has whitespace"    3
        //"doesn't have whitespace, acceptable"     4

        if (input.contains("\""))
        {
            int firstQuote = input.indexOf("\"");
            int lastQuote = input.lastIndexOf("\"");
            if (firstQuote == lastQuote)
                return 1;   //"Only one double quote"

            else if (firstQuote == 0 && lastQuote == input.length() - 1)
                return 2;   //"Has two double quotes correctly which should be removed"

            else
            {
                if (input.matches(".*\\s.*"))
                    return 3;   //"Is not between double quotes and has whitespace"

                else
                    return 4;   //"doesn't have whitespace, acceptable"
            }

        }
        else if (input.matches(".*\\s.*"))
            return 3;  //"Is not between double quotes and has whitespace"

        else
            return 4;  //"doesn't have whitespace, acceptable"

    }

    public String changePassword(Matcher matcher) throws IOException {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        newPassword = newPassword.trim();
        if(newPassword.matches("^-n\\s.*$"))
        {
            newPassword = newPassword.substring(3);
            while (newPassword.matches("^\\s.*$"))
            {
                newPassword = newPassword.substring(1);
            }
        }

        oldPassword = oldPassword.trim();
        if(oldPassword.matches("^-o\\s.*$"))
        {
            oldPassword = oldPassword.substring(3);
            while (oldPassword.matches("^\\s.*$"))
            {
                oldPassword = oldPassword.substring(1);
            }
        }

        if(!LoginMenuController.getLoggedInUser().isPasswordCorrect(oldPassword))
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

        updateUserInJsonFile(encryptedPassword ,"password" ,"users.json");

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

        updateUserInJsonFile(newEmail , "email","users.json");
        return "Email was changed successfully.";
    }

    public String changeSlogan(Matcher matcher) throws IOException {
        String newSlogan = matcher.group("slogan");

        switch (checkStringForDoubleQuote(newSlogan))
        {
            case 1:
                return "The slogan you entered has only 1 double quote!";
            case 2:
                newSlogan = newSlogan.substring(1 , newSlogan.length() - 1);
                break;
            case 3:
                return "The slogan you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        updateUserInJsonFile(newSlogan , "slogan" ,"users.json");

        return "Slogan was changed successfully.";
    }

    public String removeSlogan() throws IOException {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        if(Objects.equals(loggedInUser.getSlogan(), ""))
            return "You don't have a slogan to remove it!";

        updateUserInJsonFile("" , "slogan" , "users.json");
        return "Removed slogan successfully!";
    }

    public String showUserHighScore()
    {
        return "Score: " + LoginMenuController.getLoggedInUser().getScore();
    }

    public String showUserRank() throws Exception {

        ArrayList<User> sortedUsers = new ArrayList<>();
        sortedUsers.addAll(User.getUsersFromJsonFile());

        Comparator<User> comparator = Comparator
                .comparing(User::getScore)
                .thenComparing(User::getUsername);

        sortedUsers.sort(comparator);

        int rank = 1;

        for(int i = 1 ; i <= sortedUsers.size() ; i++)
        {
            if(sortedUsers.get(i-1) == LoginMenuController.getLoggedInUser())
            {
                rank = i;
                break;
            }
        }

        return "Rank: " + rank;
    }

    public String showUserSlogan()
    {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        if(Objects.equals(loggedInUser.getSlogan(), ""))
            return "Slogan is empty!";

        else return "Slogan: " + loggedInUser.getSlogan();
    }

    public String showProfileInfo()
    {
        User loggedInUser = LoginMenuController.getLoggedInUser();

        String info = "Username: ";
        info += loggedInUser.getUsername() + "\n";


        info += "Nickname: " + loggedInUser.getNickname() + "\n";

        info += "Email: " + loggedInUser.getEmail() + "\n";

        info += "Score: " + loggedInUser.getScore() + "\n";

        info += "Security question: " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber( loggedInUser.getNumberOfSecurityQuestion()) + "\n";
        info += "Answer to security question: " + loggedInUser.getAnswerToSecurityQuestion();

        if(Objects.equals(loggedInUser.getSlogan(), ""))
            return info;

        else
            info += "\nSlogan: " + loggedInUser.getSlogan();

        return info;
    }

}
