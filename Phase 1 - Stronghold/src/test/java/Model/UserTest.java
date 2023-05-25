package Model;

import Controller.SignUpMenuController;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void returnedUsernameIsCorrect() throws Exception {
        User user = new User("test" , "password" , "nickname" , "email");
        assertEquals(user.getUsername() , "test");
    }

    @Test
    void returnedPasswordIsCorrect() throws Exception {
        User user = new User("test" , "password" , "nickname" , "email");
        assertEquals(user.getPassword() , "password");
    }

    @Test
    void returnedNicknameIsCorrect() throws Exception {
        User user = new User("test" , "password" , "nickname" , "email");
        assertEquals(user.getNickname() , "nickname");
    }

    @Test
    void returnedEmailIsCorrect() throws Exception {
        User user = new User("test" , "password" , "nickname" , "email");
        assertEquals(user.getEmail() , "email");
    }
}