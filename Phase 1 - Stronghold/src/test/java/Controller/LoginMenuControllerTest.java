package Controller;

import Enums.Commands.LoginMenuCommands;
import Model.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuControllerTest {

    @Test
    void cleanStringWithFlagWorksWell() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String string = "  -c Chert O Pert  ";

        assertEquals("Chert O Pert" , loginMenuController.cleanStringWithAFlag(string, "-c"));
    }

    @Test
    void existingUsernameForLogin() throws Exception {
        Matcher matcher;
        LoginMenuController loginMenuController = new LoginMenuController();
        String command = "user login -u scndnmsffvm1234524 -p Password1@";

        matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGGING_IN);

        assertEquals("No user with this username found!" ,
                loginMenuController.login(matcher));
    }

    @Test
    void existingUsernameForForgettingPassword() throws Exception {
        Matcher matcher;
        LoginMenuController loginMenuController = new LoginMenuController();
        String command = "forgot my password -u scndnmsffvm1234524";

        matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.FORGOT_PASSWORD);

        assertEquals("No user with this username found!" ,
                loginMenuController.forgetPassword(matcher));
    }

    @Test
    void usernameAndPasswordShouldMatch() throws Exception {

        if(User.getUsersFromJsonFile().size() == 0)
            fail();

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        String username = User.getUsersFromJsonFile().get(randomNumber).getUsername();

        String input = "user login -u " + username + " -p scdm52015mckldxmv2@";
        Matcher matcher = LoginMenuCommands.getMatcher(input , LoginMenuCommands.LOGGING_IN);

        LoginMenuController loginMenuController = new LoginMenuController();
        assertEquals("Username and password did not match!" ,
                loginMenuController.login(matcher));
    }

    @Test
    void passwordLengthShouldBeLongerThan6() {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "Pass1";

        assertEquals("Weak password! Password length should be more than 5." , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void passwordShouldContainSmallEnglishLetters() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "PASS1@";

        assertEquals("Weak password! Password should have at least one small English letter." , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void passwordShouldContainCapitalEnglishLetters() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "pass1@";

        assertEquals("Weak password! Password should have at least one capital English letter." , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void passwordShouldContainAtLeast1Digit() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "Password@";

        assertEquals("Weak password! Password should have at least one digit." , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void passwordShouldContainAtLeast1NonDigitOrLetter() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "Password1";

        assertEquals("Weak password! Password should have at least one character except english letters and digits." , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void AGeneralPasswordCheck() throws Exception {

        LoginMenuController loginMenuController = new LoginMenuController();
        String password = "#kiArash1@";

        assertEquals("" , loginMenuController.handlePasswordErrors(password));
    }

    @Test
    void testChangePasswordAction() throws Exception {
        // Set up test data
        File file = new File("test-change-password.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        User user = new User(username , password , nickname , email);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        LoginMenuController loginMenuController = new LoginMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-password.json");
        loginMenuController.changePasswordAction(user , "newTestPassword" , "test-change-password.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"password\":\"newTestPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput2 = "[{\"password\":\"newTestPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"newTestPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput4 = "[{\"score\":0,\"password\":\"newTestPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";

        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-password.json")));
        file.delete();

        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput) || expectedOutput3.equals(actualOutput) || expectedOutput4.equals(actualOutput));

    }


//    @Test
//    void testStayLoggedInAction() throws Exception {
//        // Set up test data
//        File file = new File("test-change-stayLoggedIn.json");
//        file.createNewFile();
//
//        String username = "testUser";
//        String password = "testPassword";
//        String email = "testUser@example.com";
//        String nickname = "Test User";
//        String slogan = "Test slogan";
//
//        User user = new User(username , password , nickname , email);
//        LoginMenuController.setLoggedInUser(user);
//        // Write data to file
//        SignUpMenuController signUpMenuController = new SignUpMenuController();
//        LoginMenuController loginMenuController = new LoginMenuController();
//        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-stayLoggedIn.json");
//        loginMenuController.handleStayLoggedIn("test-change-stayLoggedIn.json");
//
//        // Read file contents and check that they match expected output
//        String expectedOutput = "[{\"password\":\"testPassword\",\"highScore\":0,\"stayedLoggedIn\":true,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
//        String expectedOutput2 = "[{\"password\":\"testPassword\",\"highScore\":0,\"stayedLoggedIn\":true,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
//        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-stayLoggedIn.json")));
//        file.delete();
//        LoginMenuController.setLoggedInUser(null);
//        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput));
//    }



}