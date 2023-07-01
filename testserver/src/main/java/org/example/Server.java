package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(int port) throws IOException {
        System.out.println("Starting Server...");
        Database.chats = ChatJsonDeserializer.loadChatsFromJson("chats.json");
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket);
            connection.start();
        }

    }
}