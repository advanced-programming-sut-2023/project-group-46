package Controller;

import Client.Connection;
import Model.User;
import View.AvatarMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AvatarMenuController {
    private AvatarMenu avatarMenu;

    public AvatarMenuController(AvatarMenu avatarMenu) {
        this.avatarMenu = avatarMenu;
    }

    public void chooseAvatar(String avatar) throws Exception {
        LoginMenuController.getLoggedInUser().setImage(avatar);
        updateAvatar(avatar);
        avatarMenu.getStage().close();
    }

    public void fromFile(Stage stage) throws Exception {
        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile= fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            String imagePath= selectedFile.toURI().toString();
            Image avatar= new Image(imagePath);
            LoginMenuController.getLoggedInUser().setImage(avatar.getUrl());
            updateUserInJsonFile(avatar.getUrl() ,"image", "users.json");
            avatarMenu.getStage().close();
        }
    }

    public static void updateAvatar(String avatar) throws Exception {
        User user = LoginMenuController.getLoggedInUser();
        user.setImage(avatar);
        ArrayList<User> users = User.getAllUsersMain();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                break;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection.handelClient("submit users");
    }

    public void updateUserInJsonFile(String newField, String fieldType, String filename) throws Exception {
        // get the currently logged in user
        User loggedInUser = LoginMenuController.getLoggedInUser();

        // read the existing contents of the users.json file into a JSONArray
        String jsonString = new String(Files.readAllBytes(Paths.get(filename)));
        JSONArray usersArray = new JSONArray(jsonString);

        // find the index of the JSONObject for the updated User
        int userIndex = -1;
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject jsonObject = usersArray.getJSONObject(i);
            if (jsonObject.getString("username").equals(loggedInUser.getUsername())) {
                userIndex = i;
                break;
            }
        }
        Connection.handelClient("submit users");
    }
}
