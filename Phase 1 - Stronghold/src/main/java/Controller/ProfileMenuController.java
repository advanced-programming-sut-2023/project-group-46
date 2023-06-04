package Controller;

import Enums.PreBuiltSecurityQuestions;
import Model.User;
import View.ChangePassword;
import View.LoginMenu;
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
    private ProfileMenu profileMenu;

    private ChangePassword changePassword;

    public ProfileMenuController(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
    }

    public ProfileMenuController(ChangePassword changePassword) {
        this.changePassword = changePassword;
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

    public String username() throws Exception {
        String newUsername = profileMenu.getUsername().getText();

        if(newUsername.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        if(User.getUserByUsername(newUsername) != null)
            return "This username is already taken!";

        return "Success";
    }

    public String changeUsername() throws IOException {
        if (profileMenu.getUsername().getText().matches("^ *$")) return "Please enter your username!";
        if(!profileMenu.getAnswerUsername().equals("Success")) return "Please enter your username correctly!";
        String newUsername = profileMenu.getUsername().getText();
        updateUserInJsonFile(newUsername ,"username", "users.json");
        return "Username was changed successfully.";
    }

    public String nickname() throws IOException {
        String nickName = profileMenu.getNickname().getText();

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


        return "Success";
    }

    public String changeNickname() throws IOException {
        if (profileMenu.getNickname().getText().matches("^ *$")) return "Please enter your nickname!";
        if(!profileMenu.getAnswerNickname().equals("Success")) return "Please enter your nickname correctly!";
        String nickName = profileMenu.getNickname().getText();
        updateUserInJsonFile(nickName ,"nickname", "users.json");
        return "Nickname was changed successfully.";
    }


    public static int checkStringForDoubleQuote(String input) {
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

    public String changePassword() throws IOException {
        String oldPassword = changePassword.getOldPassword().getText();
        String newPassword = changePassword.getNewPassword().getText();

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

        if(!changePassword.getAnswerPassword().equals("Success")) return "Please enter new password correctly!";

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


    public String email() throws Exception {
        String newEmail = profileMenu.getEmail().getText();

        for(User user : User.getUsersFromJsonFile())
        {
            if(Objects.equals(user.getEmail(), newEmail))
                return "This email is already taken!";
        }

        if(!newEmail.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        return "Success";
    }

    public String changeEmail() throws IOException {
        if (profileMenu.getEmail().getText().matches("^ *$")) return "Please enter your email!";
        if(!profileMenu.getAnswerEmail().equals("Success")) return "Please enter your email correctly!";
        String newEmail = profileMenu.getEmail().getText();
        updateUserInJsonFile(newEmail , "email","users.json");
        return "Email was changed successfully.";
    }

    public String slogan() throws IOException {
        String newSlogan = profileMenu.getSlogan().getText();

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

        return "Success";
    }

    public String changeSlogan() throws IOException {
        if (profileMenu.getSlogan().getText().matches("^ *$")) return "Please enter your slogan!";
        if(!profileMenu.getAnswerSlogan().equals("Success")) return "Please enter your slogan correctly!";
        String newSlogan = profileMenu.getSlogan().getText();
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

    public ArrayList<String> scoreboard() throws Exception {
        ArrayList<User> sortedUsers = new ArrayList<>();
        sortedUsers.addAll(User.getUsersFromJsonFile());

        Comparator<User> comparator = Comparator
                .comparing(User::getScore)
                .thenComparing(User::getUsername);

        sortedUsers.sort(comparator);
        ArrayList<String> result= new ArrayList<>();
        for(int i = 1 ; i <= sortedUsers.size() ; i++) {
           result.add("Rank : " + i + "   Score : " + LoginMenuController.getLoggedInUser().getScore());
        }
        return result;
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
            info += "\nSlogan is ,empty";

        else
            info += "\nSlogan: " + loggedInUser.getSlogan();

        return info;
    }

    public String newPassword(String newPassword){
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
        return "Success";
    }
}
