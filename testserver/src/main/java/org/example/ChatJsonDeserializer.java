package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChatJsonDeserializer {

    public static ArrayList<Chat> loadChatsFromJson(String fileName) {
        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Type chatListType = new TypeToken<ArrayList<Chat>>(){}.getType();
            return gson.fromJson(reader, chatListType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(); // Return an empty list if there's an error
    }
}
