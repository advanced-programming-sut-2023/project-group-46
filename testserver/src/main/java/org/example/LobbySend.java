package org.example;

import java.util.ArrayList;

public class LobbySend {
    ArrayList<LobbyModel> chats;

    public LobbySend() {
        this.chats = Database.lobbyModels;
    }
}
