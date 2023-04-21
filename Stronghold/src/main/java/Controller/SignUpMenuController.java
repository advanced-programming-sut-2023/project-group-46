package Controller;

import Model.PreBuiltSecurityQuestions;
import Model.PreBuiltSlogans;
import Model.User;
import View.SignupMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.Main;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class SignUpMenuController {
    private final SignupMenu signupMenu;
    public SignUpMenuController(){
        signupMenu = new SignupMenu(this);
    }

    public void run(){
    }

    public String register(Matcher matcher) throws IOException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("userEmail");
        String nickName = matcher.group("userNickName");

        if(username == null || password == null || passwordConfirmation == null || email == null || nickName == null)
            return "Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!";

        if(username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        if(User.getUserByUsername(username) != null)
            return "This username is already taken!";

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

        if(!password.equals(passwordConfirmation))
            return "Password and Password Confirmation are not the same!";

        email = email.toLowerCase();

        for(User user : User.getUsers())
        {
            if(Objects.equals(user.getEmail(), email))
                return "This email is already taken!";
        }

        if(!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        String sloganFlag = matcher.group("sloganFlag");
        String slogan = matcher.group("userSlogan");

        if(sloganFlag != null && slogan == null)
            return "Invalid command! If you type slogan flag, you must enter your slogan.";

        User user = new User( username , password , nickName , email);
        ArrayList<User> userList = User.getUsersFromJsonFile();
        userList.add(user);
        Gson gson = new GsonBuilder().create();



        String info = gson.toJson(userList);
        saveToFile("users.json", info);

        return "registered successfully!";

    }


    private static String loadFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);
        String text = new String(inputStream.readAllBytes());
        inputStream.close();
        return text;
    }

    private static void saveToFile(String fileName, String text) throws IOException {
        String contentsFromBefore = loadFromFile(fileName);

        File file = new File(fileName);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write("");
        printWriter.close();

        FileWriter fileWriter = new FileWriter(fileName, true);
        printWriter = new PrintWriter(fileWriter);

        printWriter.print(contentsFromBefore + "\n" +  text);
        printWriter.close();
    }

    public String chooseSecurityQuestion()
    {
        return null;
    }

    private static String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        // add at least one character from each type
        sb.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        sb.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        sb.append(digits.charAt(random.nextInt(digits.length())));
        sb.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // fill remaining length with characters from all types
        String allCharacters = uppercaseLetters + lowercaseLetters + digits + specialCharacters;
        for (int i = 4; i < 8; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            char character = allCharacters.charAt(randomIndex);
            sb.append(character);
        }

        return sb.toString();
    }
}


