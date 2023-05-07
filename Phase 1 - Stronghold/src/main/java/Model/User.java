package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int highScore;
    private int numberOfSecurityQuestion;
    private String answerToSecurityQuestion;
    private boolean isStayedLoggedIn;

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = null;
        this.numberOfSecurityQuestion = 0;
        this.answerToSecurityQuestion = null;
        this.isStayedLoggedIn = false;
    }

    public User() {
    }

    public static User getUserByUsername(String username) throws Exception {
        for (User user : getUsersFromJsonFile()) {
            if (Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

    public static List<User> getUsersFromJsonFile() throws Exception {
        List<User> users = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("users.json"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();


        JSONArray jsonArray = new JSONArray(sb.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            User loadedUser = gson.fromJson(String.valueOf(jsonObject), User.class);
            users.add(loadedUser);
        }
        return users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getNumberOfSecurityQuestion() {
        return numberOfSecurityQuestion;
    }

    public void setNumberOfSecurityQuestion(int numberOfSecurityQuestion) {
        this.numberOfSecurityQuestion = numberOfSecurityQuestion;
    }

    public String getAnswerToSecurityQuestion() {
        return answerToSecurityQuestion;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    public boolean isStayedLoggedIn() {
        return isStayedLoggedIn;
    }

    public void setStayedLoggedIn(boolean stayedLoggedIn) {
        isStayedLoggedIn = stayedLoggedIn;
    }

    public boolean isPasswordCorrect(String password) {
        return this.getPassword().equals(password);
    }

}

