package Controller;

import Model.PreBuiltSecurityQuestions;
import Model.PreBuiltSlogans;
import Model.User;
import View.SignupMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.tools.javac.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import org.json.JSONArray;
import org.json.JSONObject;


public class SignUpMenuController {
    private final SignupMenu signupMenu;
    public SignUpMenuController(){
        signupMenu = new SignupMenu(this);
    }

    public String register(Matcher matcher) throws Exception {
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

        for(User user : User.getUsersFromJsonFile())
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

        File file = new File("users.json");

        Map<String, Object> newUserMap = new LinkedHashMap<>();
        newUserMap.put("username", username);
        newUserMap.put("password", password);
        newUserMap.put("email", email);
        newUserMap.put("nickname", nickName);
        if(slogan!=null)
            newUserMap.put("slogan" , slogan);
        else newUserMap.put("slogan" , "");
        newUserMap.put("number of security question" , 0);
        newUserMap.put("answer to security question" , "");
        newUserMap.put("is stayed logged in" , false);

        JSONObject newUser = new JSONObject(newUserMap);

        // read the existing contents of the users.json file into a string
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        // manually add an opening square bracket if the string is empty
        if (sb.length() == 0) {
            sb.append("[]");
        }

        // parse the contents of the file as a JSONArray
        JSONArray usersArray = new JSONArray(sb.toString());

        // add the new user JSONObject to the JSONArray
        usersArray.put(newUser);

        // write the updated contents of the JSONArray back to the users.json file
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(usersArray.toString());
        bw.close();

        String output = "Pick your security question:\n";
        for(int i = 1 ; i < 4 ; i++)
        {
            output += i +". "+PreBuiltSecurityQuestions.getSecurityQuestionByNumber(i) ;
            if(i != 3)
                output += "\n";
        }
        return output;
    }

    public String registerWithRandomPassword(Matcher matcher) throws Exception {
        String username = matcher.group("username");
        String email = matcher.group("userEmail");
        String nickName = matcher.group("userNickName");

        if(username == null || email == null || nickName == null)
            return "Invalid command! Please enter your username, email and nickname correctly!";

        if(username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        if(User.getUserByUsername(username) != null)
            return "This username is already taken!";

        email = email.toLowerCase();

        for(User user : User.getUsersFromJsonFile())
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

        String password = generateRandomPassword();

        File file = new File("users.json");

        Map<String, Object> newUserMap = new LinkedHashMap<>();
        newUserMap.put("username", username);
        newUserMap.put("password", password);
        newUserMap.put("email", email);
        newUserMap.put("nickname", nickName);
        if(slogan!=null)
            newUserMap.put("slogan" , slogan);
        else newUserMap.put("slogan" , "");
        newUserMap.put("number of security question" , 0);
        newUserMap.put("answer to security question" , "");
        newUserMap.put("is stayed logged in" , false);

        JSONObject newUser = new JSONObject(newUserMap);

        // read the existing contents of the users.json file into a string
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        // manually add an opening square bracket if the string is empty
        if (sb.length() == 0) {
            sb.append("[");
        }

        // parse the contents of the file as a JSONArray
        JSONArray usersArray = new JSONArray(sb.toString());

        // add the new user JSONObject to the JSONArray
        usersArray.put(newUser);

        // write the updated contents of the JSONArray back to the users.json file
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(usersArray.toString());
        bw.close();

        return "Please re-enter you password:  \"   " + password + "   \"";

    }

    public String chooseSecurityQuestion(Matcher findUser , Matcher questionNumberAndAnswer) throws Exception
    {
        if(Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) > 3 || Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) == 0 )
            return "Please choose a question number between 1 and 3!";

        if(!Objects.equals(questionNumberAndAnswer.group("answer"), questionNumberAndAnswer.group("answerConfirmation")))
            return "Answer and answer confirmation didn't match!";

        User user = User.getUserByUsername(findUser.group("username"));
        user.setNumberOfSecurityQuestion(Integer.parseInt(questionNumberAndAnswer.group("questionNumber")));
        user.setAnswerToSecurityQuestion(questionNumberAndAnswer.group("answer"));

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
            JSONObject updatedUser = new JSONObject(user);
            usersArray.put(userIndex, updatedUser);
        }

        // write the updated contents of the JSONArray back to the users.json file
        Files.write(Paths.get("users.json"), usersArray.toString().getBytes());


        return "You registered successfully!";
    }

    private static String generateRandomPassword()
    {
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


