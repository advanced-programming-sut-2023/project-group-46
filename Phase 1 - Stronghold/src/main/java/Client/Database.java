package Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    public static ArrayList<Chat> chats = new ArrayList<>();
    public static RefreshUsers refreshUsers = new RefreshUsers();
    public static ArrayList<LobbyModel> lobbyModels = new ArrayList<>();

    public static Matcher getMatcher(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public static void updateData(ArrayList<Chat> newchat) {
        chats = newchat;
    }

    public static void updateUsers(ShareUsers shareUsers) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(shareUsers.users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLobby(LobbySend shareUsers) {
        lobbyModels = shareUsers.chats;
    }
}
