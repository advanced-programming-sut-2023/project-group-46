package org.example;

import java.util.ArrayList;

public class GsonSend {
    ArrayList<Chat> chats;

    public GsonSend() {
        this.chats = Database.chats;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }
}
