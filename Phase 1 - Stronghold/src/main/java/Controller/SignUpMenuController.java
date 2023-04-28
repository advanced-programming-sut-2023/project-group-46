package Controller;

import Enums.PreBuiltSecurityQuestions;
import Enums.PreBuiltSlogans;
import Model.User;
import View.SignupMenu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;


public class SignUpMenuController {
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

    public static int checkStringForDoubleQuote(String input) {
        //"Only one double quote"   1
        //"Has two double quotes correctly which should be removed"    2
        //"Is not between double quotes and has whitespace"    3
        //"doesn't have whitespace and double quote, acceptable"     4
        //Others(unreachable)    5
        if (input.contains("\"")) {
            int firstQuote = input.indexOf("\"");
            int lastQuote = input.lastIndexOf("\"");
            if (firstQuote == lastQuote)
                return 1;   //"Only one double quote"

            else if (firstQuote == 0 && lastQuote == input.length() - 1)
                return 2;   //"Has two double quotes correctly which should be removed"
        } else if (input.matches(".*\\s.*"))
            return 3;  //"Is not between double quotes and has whitespace"

        else
            return 4;  //"doesn't have whitespace and double quote, acceptable"

        return 5;
    }

    public String register(Matcher matcher) throws Exception {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickName = matcher.group("nickname");

        if (username.matches("^ *$") || password.matches("^ *$") || passwordConfirmation.matches("^ *$") || email.matches("^ *$") || nickName.matches("^ *$"))
            return "Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!";

        username = cleanUsername(username);
        password = cleanPassword(password);
        passwordConfirmation = cleanPasswordConfirmation(passwordConfirmation);
        email = cleanEmail(email);
        nickName = cleanNickname(nickName);

        switch (checkStringForDoubleQuote(nickName)) {
            case 1:
                return "The nickname you entered has only 1 double quote!";
            case 2:
                nickName = nickName.substring(1, nickName.length() - 1);
                break;
            case 3:
                return "The nickname you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        if (username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username should contain only English letters, digits and underline character.";

        if (User.getUserByUsername(username) != null)
            return suggestUsername(username);

        if (password.length() < 6 || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[^a-zA-Z0-9|_].*") || !password.equals(passwordConfirmation))
            return handlePasswordErrors(password, passwordConfirmation);

        email = email.toLowerCase();

        for (User user : User.getUsersFromJsonFile()) {
            if (Objects.equals(user.getEmail(), email))
                return "This email is already taken!";
        }

        if (!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        String slogan = matcher.group("slogan");

        if (slogan != null) {
            if (slogan.matches("^-s\\s*$"))
                return "Invalid command! Slogan flag and slogan come with together!.";

            slogan = slogan.substring(3);
            while (slogan.matches("^\\s.*$")) {
                slogan = slogan.substring(1);
            }

            switch (checkStringForDoubleQuote(slogan)) {
                case 1:
                    return "The slogan you entered has only 1 double quote!";
                case 2:
                    slogan = slogan.substring(1, slogan.length() - 1);
                    break;
                case 3:
                    return "The slogan you entered has some whitespaces and is not between double quotes";
                case 4:
                    break;
            }
        }

        writeInJsonFile(username, password, email, nickName, slogan);

        return showSecurityQuestion();
    }

    public String registerWithRandomPassword(Matcher matcher) throws Exception {
        String username = matcher.group("username");
        String email = matcher.group("email");
        String nickName = matcher.group("nickname");

        if (username.matches("^ *$") || email.matches("^ *$") || nickName.matches("^ *$"))
            return "Invalid command! Please enter your username, email and nickname correctly!";

        username = cleanUsername(username);
        email = cleanEmail(email);
        nickName = cleanNickname(nickName);

        switch (checkStringForDoubleQuote(nickName)) {
            case 1:
                return "The nickname you entered has only 1 double quote!";
            case 2:
                nickName = nickName.substring(1, nickName.length() - 1);
                break;
            case 3:
                return "The nickname you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        if (username.matches(".*[^a-zA-Z0-9|_].*"))
            return "Invalid username format! Username is only consisted of English letters, numbers and underline character.";

        if (User.getUserByUsername(username) != null)
            return suggestUsername(username);

        email = email.toLowerCase();

        for (User user : User.getUsersFromJsonFile()) {
            if (Objects.equals(user.getEmail(), email))
                return "This email is already taken!";
        }

        if (!email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"))
            return "Invalid email format!";

        String slogan = matcher.group("slogan");

        if (slogan != null) {
            if (slogan.matches("^-s\\s*$"))
                return "Invalid command! Slogan flag and slogan come with together!";

            slogan = slogan.substring(3);
            while (slogan.matches("^\\s.*$")) {
                slogan = slogan.substring(1);
            }

            switch (checkStringForDoubleQuote(slogan)) {
                case 1:
                    return "The slogan you entered has only 1 double quote!";
                case 2:
                    slogan = slogan.substring(1, slogan.length() - 1);
                    break;
                case 3:
                    return "The slogan you entered has some whitespaces and is not between double quotes";
                case 4:
                    break;
            }
        }

        String password = generateRandomPassword();

        writeInJsonFile(username, password, email, nickName, slogan);

        return "Please re-enter you password:  \"   " + password + "   \"";

    }

    public String chooseSecurityQuestion(Matcher findUser, Matcher questionNumberAndAnswer) throws Exception {
        if (Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) > 3 || Integer.parseInt(questionNumberAndAnswer.group("questionNumber")) == 0)
            return "Please choose a question number between 1 and 3!";

        String answer = questionNumberAndAnswer.group("answer").trim();
        String answerConfirmation = questionNumberAndAnswer.group("answerConfirmation").trim();

        if (!answer.equals(answerConfirmation))
            return "Answer and answer confirmation didn't match!";

        switch (checkStringForDoubleQuote(answer)) {
            case 1:
                return "The answer you entered has only 1 double quote!";
            case 2:
                answer = answer.substring(1, answer.length() - 1);
                break;
            case 3:
                return "The answer you entered has some whitespaces and is not between double quotes";
            case 4:
                break;
        }

        String username = findUser.group("username");
        username = username.trim();
        if (username.matches("^-u\\s.*$"))    // if the last input in username
        {
            username = username.substring(3);
            while (username.matches("^\\s.*$")) {
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

        return "You registered successfully!";
    }

    public String handlePasswordErrors(String password, String passwordConfirmation) {
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

        if (!password.equals(passwordConfirmation))
            return "Password and Password Confirmation are not the same!";

        else return "";
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

    public String cleanPasswordConfirmation(String passwordConfirmation) {
        passwordConfirmation = passwordConfirmation.trim();
        if (passwordConfirmation.matches("^-c\\s.*$")) {
            passwordConfirmation = passwordConfirmation.substring(3);
            while (passwordConfirmation.matches("^\\s.*$")) {
                passwordConfirmation = passwordConfirmation.substring(1);
            }
        }
        return passwordConfirmation;
    }

    public String cleanEmail(String email) {
        email = email.trim();
        if (email.matches("^-e\\s.*$")) {
            email = email.substring(3);
            while (email.matches("^\\s.*$")) {
                email = email.substring(1);
            }
        }
        return email;
    }

    public String cleanNickname(String nickName) {
        nickName = nickName.trim();
        if (nickName.matches("^-n\\s.*$")) {
            nickName = nickName.substring(3);
            while (nickName.matches("^\\s.*$")) {
                nickName = nickName.substring(1);
            }
        }
        return nickName;
    }

    public String suggestUsername(String username) throws Exception {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000) + 1;

        while (User.getUserByUsername(username + randomNumber) != null) {
            randomNumber = rand.nextInt(1000) + 1;
        }
        return "This username is already taken! You can use this username instead: " + username + randomNumber;
    }

    public void writeInJsonFile(String username, String password, String email, String nickName, String slogan) throws IOException {
        File file = new File("users.json");

        Map<String, Object> newUserMap = new LinkedHashMap<>();
        newUserMap.put("username", username);
        newUserMap.put("password", password);
        newUserMap.put("email", email);
        newUserMap.put("nickname", nickName);
        if (slogan != null) {
            if (!slogan.equals("random"))
                newUserMap.put("slogan", slogan);

            else {
                Random random = new Random();
                int randomSloganNumber = random.nextInt(5) + 1;

                newUserMap.put("slogan", PreBuiltSlogans.getSloganByNumber(randomSloganNumber));
            }
        } else newUserMap.put("slogan", "");
        newUserMap.put("number of security question", 0);
        newUserMap.put("answer to security question", "");
        newUserMap.put("is stayed logged in", false);

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

    public String showSecurityQuestion() {
        String output = "Pick your security question:\n";
        for (int i = 1; i < 4; i++) {
            output += i + ". " + PreBuiltSecurityQuestions.getSecurityQuestionByNumber(i);
            if (i != 3)
                output += "\n";
        }
        return output;
    }
}

