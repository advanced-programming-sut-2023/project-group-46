package org.example;

import java.util.ArrayList;

public class Chat {
    public ArrayList<String> usernames;
    public ArrayList<Message> messages;
    public String name;
    public ChatType chatType;

    public Chat(ArrayList<String> users, ChatType chatType) {
        this.usernames = users;
        messages = new ArrayList<>();
        this.chatType = chatType;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}
