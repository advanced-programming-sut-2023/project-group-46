package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    public ArrayList<String> friendRequests;
    public ArrayList<String> friendsOfUser;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int score;
    private int numberOfSecurityQuestion;
    private String answerToSecurityQuestion;
    private boolean stayedLoggedIn;
    private String image;
    private ArrayList<String> maps;
    private String online;
    private String lastSeenTime;
    private ArrayList<String> mapRequests;

    public User(String username, String password, String nickname, String email, String answerToSecurityQuestion) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = "";
        maps = new ArrayList<>();
        friendRequests = new ArrayList<>();
        this.numberOfSecurityQuestion = 0;
        this.answerToSecurityQuestion = answerToSecurityQuestion;
        this.stayedLoggedIn = false;
        image = "/Image/Avatar/1.png";
        this.online = "false";
        friendsOfUser = new ArrayList<>();
        String min = String.format("%02d", LocalTime.now().getMinute());
        String hor = String.format("%02d", LocalTime.now().getHour());
        String time = hor + ":" + min;
        this.lastSeenTime = time;
        mapRequests = new ArrayList<>();
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

    public static ArrayList<String> getAllUsers() throws Exception {
        ArrayList<String> usernames = new ArrayList<>();
        for (int i = 0; i < getUsersFromJsonFile().size(); i++) {
            usernames.add(getUsersFromJsonFile().get(i).username);
        }
        return usernames;
    }

    public static ArrayList<User> getAllUsersMain() throws Exception {
        ArrayList<User> usernames = new ArrayList<>();
        usernames.addAll(getUsersFromJsonFile());
        return usernames;
    }

    public ArrayList<String> getFriendsOfUser() {
        return friendsOfUser;
    }

    public void setFriendsOfUser(ArrayList<String> friendsOfUser) {
        this.friendsOfUser = friendsOfUser;
    }

    public ArrayList<String> getMapRequests() {
        return mapRequests;
    }

    public void setMapRequests(ArrayList<String> mapRequests) {
        this.mapRequests = mapRequests;
    }

    public ArrayList<String> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<String> maps) {
        this.maps = maps;
    }

    public ArrayList<String> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(ArrayList<String> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(String lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public void addScore(int score) {
        this.score += score;
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
        return stayedLoggedIn;
    }

    public void setStayedLoggedIn(boolean stayedLoggedIn) {
        this.stayedLoggedIn = stayedLoggedIn;
    }

    /**
     * Check if the given password matches the user's stored password.
     *
     * @param password The password to check
     * @return true if the password is correct, false otherwise
     */
    public boolean isPasswordCorrect(String password) {
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
        return encryptedPassword.equals(this.password);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
