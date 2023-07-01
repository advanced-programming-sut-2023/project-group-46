package Client;

import Model.User;

import java.util.ArrayList;

public class ShareUsers {
    ArrayList<User> users;

    public ShareUsers() throws Exception {
        this.users = User.getAllUsersMain();
    }
}
