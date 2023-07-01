package Client;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Connection {

    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public Connection(String host, int port) throws IOException {
        System.out.println("Starting Client service...");
        Socket socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public static synchronized void handelClient(String input) throws Exception {
        dataOutputStream.writeUTF(input);
        if (input.equals("refresh")) {
            String json = dataInputStream.readUTF();
            Gson gson = new Gson();
            GsonSend gsonSend = gson.fromJson(json, GsonSend.class);
            Database.updateData(gsonSend.chats);
        } else if (input.equals("submit changes")) {
            GsonSend gsonSend = new GsonSend();
            Gson gson = new Gson();
            String json = gson.toJson(gsonSend);
            dataOutputStream.writeUTF(json);
        } else if (input.equals("refresh users")) {
            String json = dataInputStream.readUTF();
            Gson gson = new Gson();
            ShareUsers shareUsers = gson.fromJson(json, ShareUsers.class);
            Database.updateUsers(shareUsers);
        } else if (input.equals("submit users")) {
            ShareUsers shareUsers = new ShareUsers();
            Gson gson = new Gson();
            String json = gson.toJson(shareUsers);
            dataOutputStream.writeUTF(json);
        } else if (input.equals("submit lobby")) {
            LobbySend shareUsers = new LobbySend();
            Gson gson = new Gson();
            String json = gson.toJson(shareUsers);
            dataOutputStream.writeUTF(json);
        } else if (input.equals("refresh lobby")) {
            String json = dataInputStream.readUTF();
            Gson gson = new Gson();
            LobbySend shareUsers = gson.fromJson(json, LobbySend.class);
            Database.updateLobby(shareUsers);
        }
    }
}
