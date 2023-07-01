package org.example;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    Socket socket;

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                handelClient();
            }
        } catch (IOException e) {
            System.out.println("Connection " + socket.getInetAddress() + ":" + socket.getPort() + " lost!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void handelClient() throws Exception {
        if (dataInputStream.available() != 0) {
            String data = dataInputStream.readUTF();
            if (data.equals("refresh")) {
                GsonSend gsonSend = new GsonSend();
                Gson gson = new Gson();
                String json = gson.toJson(gsonSend);
                dataOutputStream.writeUTF(json);
            } else if (data.equals("submit changes")) {
                String json = dataInputStream.readUTF();
                Gson gson = new Gson();
                GsonSend gsonSend = gson.fromJson(json, GsonSend.class);
                Database.submitChanges(gsonSend.chats);
            } else if (data.equals("refresh users")) {
                ShareUsers gsonSend = new ShareUsers();
                Gson gson = new Gson();
                String json = gson.toJson(gsonSend);
                dataOutputStream.writeUTF(json);
            } else if (data.equals("submit users")) {
                String json = dataInputStream.readUTF();
                Gson gson = new Gson();
                ShareUsers shareUsers = gson.fromJson(json, ShareUsers.class);
                Database.submitUsers(shareUsers);
            } else if (data.equals("submit lobby")) {
                String json = dataInputStream.readUTF();
                Gson gson = new Gson();
                LobbySend shareUsers = gson.fromJson(json, LobbySend.class);
                Database.submitLobby(shareUsers);
            } else if (data.equals("refresh lobby")) {
                LobbySend gsonSend = new LobbySend();
                Gson gson = new Gson();
                String json = gson.toJson(gsonSend);
                dataOutputStream.writeUTF(json);
            }
        }
    }
}
