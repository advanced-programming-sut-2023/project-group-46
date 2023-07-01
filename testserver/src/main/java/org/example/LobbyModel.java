package org.example;

import java.util.ArrayList;

public class LobbyModel {
    private String lobbyName;
    private int capacity;
    private ArrayList<String> usernames;
    private String adminUsername;
    private boolean isStarted;
    private boolean isPrivate;
    private String password;

    public LobbyModel(String lobbyName, int capacity, ArrayList<String> usernames, String adminUsername, boolean isPrivate, String password) {
        this.lobbyName = lobbyName;
        this.capacity = capacity;
        this.usernames = usernames;
        this.adminUsername = adminUsername;
        this.isPrivate = isPrivate;
        this.password = password;
        this.isStarted = false;
    }

    public LobbyModel(String lobbyName, int capacity, ArrayList<String> usernames, String adminUsername, boolean isPrivate) {
        this.lobbyName = lobbyName;
        this.capacity = capacity;
        this.usernames = usernames;
        this.adminUsername = adminUsername;
        this.isStarted = false;
        this.isPrivate = isPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

}
