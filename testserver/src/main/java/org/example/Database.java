package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    public static ArrayList<Chat> chats = new ArrayList<>();
    public static ArrayList<LobbyModel> lobbyModels = new ArrayList<>();

    static {
        try {
            chats.add(new Chat(User.getAllUsers(), ChatType.PUBLIC_CHAT));
            submitChanges(chats);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void submitChanges(ArrayList<Chat> updatedChats) {
        chats.get(0).usernames = updatedChats.get(0).usernames;
        for (int i = 0; i < updatedChats.size(); i++) {
            if (i >= chats.size()) {
                chats.add(updatedChats.get(i));
                continue;
            }
            ArrayList<Message> forDelete = new ArrayList<>();
            for (int l = 0; l < updatedChats.get(i).messages.size(); l++) {
                if (updatedChats.get(i).messages.get(l).isDeleted) {
                    forDelete.add(updatedChats.get(i).messages.get(l));
                }
                if (chats.get(i).messages.size() <= l) {
                    chats.get(i).messages.add(updatedChats.get(i).messages.get(l));
                }
                chats.get(i).messages.set(l, updatedChats.get(i).messages.get(l));
            }
            for (int p = 0; p < forDelete.size(); p++) {
                chats.get(i).messages.remove(forDelete.get(p));
            }
        }
        ChatJsonSerializer.saveChatsToJson(chats, "chats.json");
    }

    public static void submitUsers(ShareUsers shareUsers) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(shareUsers.users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void submitLobby(LobbySend shareUsers) {
        Database.lobbyModels = shareUsers.chats;
    }
}
