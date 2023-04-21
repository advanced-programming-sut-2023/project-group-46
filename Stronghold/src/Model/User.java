package Model;

import java.util.ArrayList;
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
    private static ArrayList<User> users = new ArrayList<>();

    public User(String username, String password, String nickname, String email ) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = null;
        this.numberOfSecurityQuestion =  0;
        this.answerToSecurityQuestion = null;
        this.isStayedLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setUsername(String username){this.username = username;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setNumberOfSecurityQuestion(int numberOfSecurityQuestion) {
        this.numberOfSecurityQuestion = numberOfSecurityQuestion;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    public void setStayedLoggedIn(boolean stayedLoggedIn) {
        isStayedLoggedIn = stayedLoggedIn;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public int getNumberOfSecurityQuestion() {
        return numberOfSecurityQuestion;
    }

    public String getAnswerToSecurityQuestion() {
        return answerToSecurityQuestion;
    }

    public boolean isStayedLoggedIn() {
        return isStayedLoggedIn;
    }

    public static User getUserByUsername(String username)
    {
        for(User user : users)
        {
            if(Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

    public boolean isPasswordCorrect(String password) {
        return this.getPassword().equals(password);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

}
