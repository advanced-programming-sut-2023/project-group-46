package Controller;

import Enums.PreBuiltSecurityQuestions;
import Enums.PreBuiltSlogans;
import Model.User;
import View.SignupMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignUpMenuController {
    private final SignupMenu signupMenu;
    public SignUpMenuController(){
        signupMenu = new SignupMenu(this);
    }

    public String registerOrRegisterWithRandomPassword(Matcher matcher, boolean randomPassword) throws Exception {
        String username = matcher.group("username");
        String email = matcher.group("email");
        String nickName = matcher.group("nickname");

        if(username.matches("^ *$") || email.matches("^ *$") || nickName.matches("^ *$"))
            return "Invalid command! Please enter your username, email and nickname correctly!";

        username = cleanUsername(username);
        email = cleanEmail(email);
        nickName = cleanNickname(nickName);

        switch (checkStringForDoubleQuote(nickName))
        {
            case 1:
                return "The nickname you entered has only 1 double quote!";
            case 2:
                nickName = nickName.substring(1 , nickName.length() - 1);
                break;
            case 3:
                return "The nickname you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        if(username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username should contain only English letters, digits and underline character.";

        if(User.getUserByUsername(username) != null)
            return suggestUsername(username);

        email = email.toLowerCase();

        for(User user : User.getUsersFromJsonFile())
        {
            if(Objects.equals(user.getEmail(), email))
                return "This email is already taken!";
        }

        if(!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        String slogan = matcher.group("slogan");

        if(slogan != null)
        {
            if(slogan.matches("^-s\\s*$"))
                return "Invalid command! Slogan flag and slogan come with together!";

            slogan = slogan.substring(3);
            while (slogan.matches("^\\s.*$"))
            {
                slogan = slogan.substring(1);
            }

            switch (checkStringForDoubleQuote(slogan))
            {
                case 1:
                    return "The slogan you entered has only 1 double quote!";
                case 2:
                    slogan = slogan.substring(1 , slogan.length() - 1);
                    break;
                case 3:
                    return "The slogan you entered has some whitespaces and is not between double quotes";
                case 4:
                    break;
            }
        }

        String password;
        if(randomPassword) {
            password = generateRandomPassword();

            String encryptedPassword = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                encryptedPassword = no.toString(16);
                while (encryptedPassword.length() < 32) {
                    encryptedPassword = "0" + encryptedPassword;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            writeInJsonFile(username , encryptedPassword , email , nickName , slogan , "users.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            try {
                objectMapper.writeValue(new File( username + ".json"), new Model.Map(100));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "Please re-enter you password:  \"   " + password + "   \"";

        } else {
            String userPassword = matcher.group("password");
            String passwordConfirmation = matcher.group("passwordConfirmation");

            if(userPassword.matches("^ *$") || passwordConfirmation.matches("^ *$"))
                return "Invalid command! Please enter your password and password confirmation correctly!";

            userPassword = cleanPassword(userPassword);
            passwordConfirmation = cleanPasswordConfirmation(passwordConfirmation);

            if(userPassword.length() < 6 || !userPassword.matches(".*[a-z].*") || !userPassword.matches(".*[A-Z].*") || !userPassword.matches(".*[0-9].*") || !userPassword.matches(".*[^a-zA-Z0-9|_].*") || !userPassword.equals(passwordConfirmation))
                return handlePasswordErrors(userPassword , passwordConfirmation);

            password = userPassword;


            String encryptedPassword = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                encryptedPassword = no.toString(16);
                while (encryptedPassword.length() < 32) {
                    encryptedPassword = "0" + encryptedPassword;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            writeInJsonFile(username , encryptedPassword , email , nickName , slogan , "users.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            try {
                objectMapper.writeValue(new File( username + ".json"), new Model.Map(100));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



            return showSecurityQuestion();
        }
    }

    public String chooseSecurityQuestion(Matcher findUser , Matcher questionNumberAndAnswer) throws Exception
    {
        if(Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) > 3 || Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) == 0 )
            return "Please choose a question number between 1 and 3!";

        String answer = questionNumberAndAnswer.group("answer").trim();
        String answerConfirmation = questionNumberAndAnswer.group("answerConfirmation").trim();

        if(!answer.equals(answerConfirmation))
            return "Answer and answer confirmation didn't match!";

        switch (checkStringForDoubleQuote(answer))
        {
            case 1:
                return "The answer you entered has only 1 double quote!";
            case 2:
                answer = answer.substring(1 , answer.length() - 1);
                break;
            case 3:
                return "The answer you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        String username = findUser.group("username");
        username = username.trim();
        if(username.matches("^-u\\s.*$"))    // if the last input in username
        {
            username = username.substring(3);
            while (username.matches("^\\s.*$"))
            {
                username = username.substring(1);
            }
        }

        User user = User.getUserByUsername(username);
        user.setNumberOfSecurityQuestion(Integer.parseInt(questionNumberAndAnswer.group("questionNumber")));
        user.setAnswerToSecurityQuestion(answer);

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

        return "You chose question successfully!";
    }

    public String handlePasswordErrors(String password , String passwordConfirmation)
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

        if(!password.equals(passwordConfirmation))
            return "Password and Password Confirmation are not the same!";

        else return "";
    }

    public static String generateRandomPassword()
    {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_+=[]{}|;:,.<>?";

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

    public String cleanPasswordConfirmation(String passwordConfirmation)
    {
        return cleanStringWithAFlag(passwordConfirmation , "-c");
    }

    public String cleanEmail(String email)
    {
        return cleanStringWithAFlag(email , "-e");
    }

    public String cleanNickname(String nickName)
    {
        return cleanStringWithAFlag(nickName , "-n");
    }

    public String suggestUsername(String username) throws Exception {
        Random rand = new Random();
        int randomNumber  = rand.nextInt(1000) + 1 ;

        while (User.getUserByUsername(username + randomNumber) != null)
        {
            randomNumber = rand.nextInt(1000) + 1 ;
        }
        return "This username is already taken! You can use this username instead: " + username + randomNumber;
    }

    public void writeInJsonFile(String username , String password , String email , String nickName , String slogan , String fileName) throws IOException {
        File file = new File(fileName);

        Map<String, Object> newUserMap = new LinkedHashMap<>();
        newUserMap.put("username", username);
        newUserMap.put("password", password);
        newUserMap.put("email", email);
        newUserMap.put("nickname", nickName);
        if(slogan!=null)
        {
            if(!slogan.equals("random"))
                newUserMap.put("slogan" , slogan);

            else
            {
                Random random = new Random();
                int randomSloganNumber = random.nextInt(5) + 1;

                newUserMap.put("slogan" , PreBuiltSlogans.getSloganByNumber(randomSloganNumber));
            }
        }
        else newUserMap.put("slogan" , "");
        newUserMap.put("number of security question" , 0);
        newUserMap.put("answer to security question" , "");
        newUserMap.put("isStayedLoggedIn" , false);
        newUserMap.put("score" , 0);

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

    }

    public String showSecurityQuestion()
    {
        String output = "Pick your security question:\n";
        for(int i = 1 ; i < 4 ; i++)
        {
            output += i +". "+PreBuiltSecurityQuestions.getSecurityQuestionByNumber(i) ;
            if(i != 3)
                output += "\n";
        }
        return output;
    }
}


