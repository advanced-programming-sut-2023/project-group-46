package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChatJsonSerializer {

    public static void saveChatsToJson(ArrayList<Chat> chats, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(chats, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
