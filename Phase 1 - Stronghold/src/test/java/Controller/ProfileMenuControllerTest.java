package Controller;

import Enums.Commands.ProfileMenuCommands;
import Model.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMenuControllerTest {

    @Test
    void usernameShouldOnlyContainLettersOrDigitsOrUnderline() throws Exception {
        Matcher matcher;
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String command = "profile change -u user/name1";

        matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_USERNAME);

        assertEquals("Invalid username format! Username is only consisted of English letters, numbers and underline character." ,
                profileMenuController.changeUsername(matcher));
    }

    @Test
    void testChangeUsernameAction() throws Exception {
        // Set up test data
        File file = new File("test-change-username.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        User user = new User(username , password , nickname , email);
        LoginMenuController.setLoggedInUser(user);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        ProfileMenuController profileMenuController = new ProfileMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-username.json");
        profileMenuController.updateUserInJsonFile("newUsername" ,"username", "test-change-username.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"newUsername\"}]";
        String expectedOutput2 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"newUsername\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"newUsername\"}]";
        String expectedOutput4 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"newUsername\",\"numberOfSecurityQuestion\":0}]";


        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-username.json")));
        file.delete();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(actualOutput.equals(expectedOutput) || actualOutput.equals(expectedOutput2) || actualOutput.equals(expectedOutput3) || actualOutput.equals(expectedOutput4));

    }

    @Test
    void testChangeNicknameAction() throws Exception {
        // Set up test data
        File file = new File("test-change-nickname.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        User user = new User(username , password , nickname , email);
        LoginMenuController.setLoggedInUser(user);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        ProfileMenuController profileMenuController = new ProfileMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-nickname.json");
        profileMenuController.updateUserInJsonFile("newNickname" ,"nickname", "test-change-nickname.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"newNickname\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput2 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"newNickname\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"newNickname\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput4 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"newNickname\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";

        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-nickname.json")));
        file.delete();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput) || expectedOutput3.equals(actualOutput) || expectedOutput4.equals(actualOutput));

    }

    @Test
    void showUserRankWorksWell() throws Exception {
        ProfileMenuController profileMenuController = new ProfileMenuController();

        String showRank = profileMenuController.showUserRank();

        assertTrue(showRank.startsWith("Rank: "));
    }

    @Test
    void showUserSloganWorksWell() throws Exception {
        ProfileMenuController profileMenuController = new ProfileMenuController();
        User user = new User("" , "" , "" , "");
        LoginMenuController.setLoggedInUser(user);

        String showSlogan = profileMenuController.showUserSlogan();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(showSlogan.startsWith("Slogan: "));
    }

    @Test
    void changeNicknameWithoutDoubleQuotesWithSpace() throws Exception {
        Matcher matcher;
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String command = "profile change -n nick name";

        matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME);

        assertEquals("The nickName you entered has some whitespaces and is not between double quotes" ,
                profileMenuController.changeNickname(matcher));
    }

    @Test
    void changeNicknameWith1DoubleQuote() throws Exception {
        Matcher matcher;
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String command = "profile change -n nick\"name";

        matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_NICKNAME);

        assertEquals("The nickName you entered has only 1 double quote!" ,
                profileMenuController.changeNickname(matcher));
    }

    @Test
    void checkStringWithTwoDoubleQuotesForDoubleQuote() throws Exception {

        String command = "\"This is some      random     text\"";
        int a = ProfileMenuController.checkStringForDoubleQuote(command);

        assertEquals(a , 2);
    }


    @Test
    void checkStringWithoutTwoDoubleQuotesWithSpaces() throws Exception {

        String command = "This is some random text";
        int a = ProfileMenuController.checkStringForDoubleQuote(command);

        assertEquals(a , 3);
    }


    @Test
    void checkCompletelyNormalStringForDoubleQuotes() throws Exception {

        String command = "text";
        int a = ProfileMenuController.checkStringForDoubleQuote(command);

        assertEquals(a , 4);
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
        LoginMenuController.setLoggedInUser(user);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        ProfileMenuController profileMenuController = new ProfileMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-password.json");
        profileMenuController.updateUserInJsonFile("newPassword" ,"password", "test-change-password.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"score\":0,\"password\":\"newPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput2 = "[{\"password\":\"newPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"newPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput4 = "[{\"password\":\"newPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";

        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-password.json")));
        file.delete();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput) || expectedOutput3.equals(actualOutput) || expectedOutput4.equals(actualOutput));

    }


    @Test
    void InvalidEmailFormatShouldNotBeAllowed() throws Exception {
        Matcher matcher;
        ProfileMenuController profileMenuController = new ProfileMenuController();
        String command = "profile change -e email@gmailcom";

        matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_EMAIL);

        assertEquals("Invalid email format!" ,
                profileMenuController.changeEmail(matcher));
    }


    @Test
    void testChangeEmailAction() throws Exception {
        // Set up test data
        File file = new File("test-change-email.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        User user = new User(username , password , nickname , email);
        LoginMenuController.setLoggedInUser(user);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        ProfileMenuController profileMenuController = new ProfileMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-email.json");
        profileMenuController.updateUserInJsonFile("newEmail@gmail.com" , "email" ,"test-change-email.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"newEmail@gmail.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput2 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"newEmail@gmail.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"newEmail@gmail.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput4 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"email\":\"newEmail@gmail.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";

        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-email.json")));
        file.delete();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput) || expectedOutput3.equals(actualOutput) || expectedOutput4.equals(actualOutput));

    }

    @Test
    void testChangeSloganAction() throws Exception {
        // Set up test data
        File file = new File("test-change-slogan.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        User user = new User(username , password , nickname , email);
        LoginMenuController.setLoggedInUser(user);
        // Write data to file
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        ProfileMenuController profileMenuController = new ProfileMenuController();
        signUpMenuController.writeInJsonFile(username, password, email, nickname, slogan, "test-change-slogan.json");
        profileMenuController.updateUserInJsonFile("newSlogan" , "slogan" ,"test-change-slogan.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"slogan\":\"newSlogan\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";
        String expectedOutput2 = "[{\"password\":\"testPassword\",\"score\":0,\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"slogan\":\"newSlogan\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput3 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"slogan\":\"newSlogan\",\"email\":\"testUser@example.com\",\"numberOfSecurityQuestion\":0,\"username\":\"testUser\"}]";
        String expectedOutput4 = "[{\"score\":0,\"password\":\"testPassword\",\"stayedLoggedIn\":false,\"nickname\":\"Test User\",\"slogan\":\"newSlogan\",\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"numberOfSecurityQuestion\":0}]";

        String actualOutput = new String(Files.readAllBytes(Paths.get("test-change-slogan.json")));
        file.delete();
        LoginMenuController.setLoggedInUser(null);

        assertTrue(expectedOutput.equals(actualOutput) || expectedOutput2.equals(actualOutput) || expectedOutput3.equals(actualOutput) || expectedOutput4.equals(actualOutput));

    }

    @Test
    void checkChangePasswordWhenOldOneIsWrongIsInvalid() throws Exception {
        Matcher matcher;
        ProfileMenuController profileMenuController = new ProfileMenuController();

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        User user = User.getUsersFromJsonFile().get(randomNumber);
        LoginMenuController.setLoggedInUser(user);
        String command = "profile change -o " + user.getPassword() + "2 -n newPassword@";

        matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.CHANGE_PASSWORD);

        assertEquals("Current password is incorrect!" ,
                profileMenuController.changePassword(matcher));

        LoginMenuController.setLoggedInUser(null);

    }


    @Test
    void checkShowProfileInfo() throws Exception {
        ProfileMenuController profileMenuController = new ProfileMenuController();

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        User user = User.getUsersFromJsonFile().get(randomNumber);
        LoginMenuController.setLoggedInUser(user);

        String info = profileMenuController.showProfileInfo();

        assertTrue(info.contains("Username"));

        LoginMenuController.setLoggedInUser(null);

    }

    @Test
    void checkShowHighScore() throws Exception {
        ProfileMenuController profileMenuController = new ProfileMenuController();

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        User user = User.getUsersFromJsonFile().get(randomNumber);
        LoginMenuController.setLoggedInUser(user);

        String info = profileMenuController.showUserHighScore();

        assertTrue(info.contains("Score:"));

        LoginMenuController.setLoggedInUser(null);

    }



}