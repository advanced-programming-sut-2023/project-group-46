package org.example;

import java.util.ArrayList;

public class ShareUsers {
    ArrayList<User> users;

    public ShareUsers() throws Exception {
        this.users = User.getAllUsersMain();
    }
}
