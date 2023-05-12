package Controller;

import Enums.Commands.SignupMenuCommands;
import Model.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class SignUpMenuControllerTest {


    @Test
    void usernameForRegisterShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u  -p Password1@  -c differentPassword1@ -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void usernameForRegisterWithRandomPasswordShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u -p random -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid command! Please enter your username, email and nickname correctly!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void passwordForRegisterShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u username1 -p  -c Password1@ -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void passwordConfirmationForRegisterShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u username1 -p Password1@ -c -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void emailForRegisterShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c differentPassword1@ -u username1 -p Password1@   -e -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void emailForRegisterWithRandomPasswordShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -p random -u username1 -e -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid command! Please enter your username, email and nickname correctly!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void nicknameForRegisterShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c differentPassword1@  -n -u username1 -p Password1@ -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Please enter your username, password, password confirmation, email and nickname correctly!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void nicknameForRegisterWithRandomPasswordShouldExist() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u username1 -p random -n -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid command! Please enter your username, email and nickname correctly!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }


    @Test
    void cleanStringWithFlagWorksWell() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String string = "  -c Chert O Pert  ";

        assertEquals("Chert O Pert" , signUpMenuController.cleanStringWithAFlag(string, "-c"));
    }

    @Test
    void passwordLengthShouldBeLongerThan6() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "Pass1";
        String passwordConfirmation = "Pass1";

        assertEquals("Weak password! Password length should be more than 5." , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void passwordShouldContainSmallEnglishLetters() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "PASS1@";
        String passwordConfirmation = "PASS1@";

        assertEquals("Weak password! Password should have at least one small English letter." , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void passwordShouldContainCapitalEnglishLetters() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "pass1@";
        String passwordConfirmation = "pass1@";

        assertEquals("Weak password! Password should have at least one capital English letter." , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void passwordShouldContainAtLeast1Digit() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "Password@";
        String passwordConfirmation = "Password@";

        assertEquals("Weak password! Password should have at least one digit." , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void passwordShouldContainAtLeast1NonDigitOrLetter() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "Password1";
        String passwordConfirmation = "Password1";

        assertEquals("Weak password! Password should have at least one character except english letters and digits." , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void passwordAndPasswordConfirmationShouldBeTheSame() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "SomePass1@";
        String passwordConfirmation = "SomePass1@ ";

        assertEquals("Password and Password Confirmation are not the same!" , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void AGeneralPasswordCheck() throws Exception {

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String password = "#kiArash1@";
        String passwordConfirmation = "#kiArash1@";

        assertEquals("" , signUpMenuController.handlePasswordErrors(password , passwordConfirmation));
    }

    @Test
    void StringWithOneDoubleQuoteIsInvalid() throws Exception {
        String input = "This is \"  some random text";
        assertEquals(1 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithTwoDoubleQuotesInStartAndEndIsValid() throws Exception {
        String input = "\"This is some random text\"";
        assertEquals(2 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithSomeDoubleQuoteInMiddleWithoutInStartAndEndIsInvalid() throws Exception {
        String input = "\"This is\" some \"\"random text\" with some double quotes";
        assertEquals(3 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithSomeDoubleQuoteInMiddleWithInStartOrEndIsValid() throws Exception {
        String input = "\"This is\" some \"\"random text\" with some double quotes including 1 in start and 1 in then end\"";
        assertEquals(2 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithSomeDoubleQuoteInMiddleWithoutSpacesIsValid() throws Exception {
        String input = "ThisIs\"some\"\"randomText\"WithSomeDoubleQuotes";
        assertEquals(4 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithSomeDoubleQuoteInMiddleWithSpacesIsInvalid() throws Exception {
        String input = "ThisIs\"some\"\"randomText\"WithSomeDoubleQuotes andSomeWhitespaces";
        assertEquals(3 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithoutTwoDoubleQuotesInStartAndEndWithSpaceIsInvalid() throws Exception {
        String input = "ThisIsSomeRandomText    containingSomeWhitespaces";
        assertEquals(3 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void StringWithoutTwoDoubleQuotesInStartAndEndWithoutSpaceIsValid() throws Exception {
        String input = "ThisIsSomeRandomTextWithoutWhitespaces";
        assertEquals(4 , SignUpMenuController.checkStringForDoubleQuote(input));
    }

    @Test
    void usernameShouldOnlyContainLettersOrDigitsOrUnderline() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u user/name1 -p Password1@  -c Password1@ -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid username format! Username should contain only English letters, digits and underline character." ,
                signUpMenuController.register(matcher));
    }

    @Test
    void usernameShouldOnlyContainLettersOrDigitsOrUnderlineForRandomPasswordRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u user/name1 -p random -e email1@gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid username format! Username should contain only English letters, digits and underline character." ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void EmailShouldHave1dotAfterAtsign() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p Password1@ -c Password1@ -e email1@gmail -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid email format!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void EmailShouldHave1dotAfterAtsignInRandomPasswordRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p random -e email1@gmail -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid email format!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void EmailShouldHave1Atsign() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p Password1@ -c Password1@ -e email1gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid email format!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void EmailShouldHave1AtsignForRandomPasswordRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p random -e email1gmail.com -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid email format!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void EmailShouldHaveAtLeast2CharsAfterDot() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p Password1@ -c Password1@ -e email1@gmail.c -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid email format!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void EmailShouldHaveAtLeast2CharsAfterDotForRandomPasswordRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameee1 -p random -e email1@gmail.c -n nickname1";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid email format!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void checkNicknameWith1DoubleQuoteForRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c Password1@  -n ni\"ckname -u username12222222 -p Password1@ -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("The nickname you entered has only 1 double quote!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void checkNicknameWith1DoubleQuoteForRegisterWithRandomPassword() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n ni\"ckname -u username12222222 -p random -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("The nickname you entered has only 1 double quote!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }


    @Test
    void checkNicknameWithoutDoubleQuotesWithSpaceForRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c Password1@  -n nick name -u username12222222 -p Password1@ -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("The nickname you entered has some whitespaces and is not between double quotes" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void checkNicknameWithoutDoubleQuotesWithSpaceForRegisterWithRandomPassword() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nick name -u username12222222 -p random -e emaillllllll1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("The nickname you entered has some whitespaces and is not between double quotes" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }


    @Test
    void checkNicknameWithDoubleQuotesWithSpaceForRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c Password1@  -n \"nick name\" -u username12222222 -p Password1@ -e email1@gmail.com";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        String nickname = matcher.group("nickname");
        nickname = signUpMenuController.cleanNickname(nickname);

        assertEquals("nick name" , nickname.substring(1 , nickname.length() - 1));
    }

    @Test
    void ifUsernameIsAlreadyTakenGiveError() throws Exception {

        if(User.getUsersFromJsonFile().size() == 0)
            assertTrue(true);

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        String username = User.getUsersFromJsonFile().get(randomNumber).getUsername();

        String input = "user create -u " + username + " -p Password1@ -c Password1@ -e email1@gmail.com -n nickname1";
        Matcher matcher = SignupMenuCommands.getMatcher(input , SignupMenuCommands.CREATE_A_NEW_USER);

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        assertTrue(signUpMenuController.register(matcher).startsWith("This username is already taken! You can use this username instead: "));
    }

    @Test
    void checkSuggestUsername() throws Exception {

        if(User.getUsersFromJsonFile().size() == 0)
            assertTrue(true);

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        String username = User.getUsersFromJsonFile().get(randomNumber).getUsername();

        SignUpMenuController signUpMenuController = new SignUpMenuController();

        assertTrue(signUpMenuController.suggestUsername(username).startsWith("This username is already taken! You can use this username instead: "));
    }

    @Test
    void SuggestedUsernameIsOk() throws Exception {

        if(User.getUsersFromJsonFile().size() == 0)
            assertTrue(true);

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        String username = User.getUsersFromJsonFile().get(randomNumber).getUsername();

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String newUsername = signUpMenuController.suggestUsername(username).substring(67);

        assertNull(User.getUserByUsername(newUsername));
    }

    @Test
    void randomGeneratedPasswordIsOk() throws Exception {

        String generatedPassword = SignUpMenuController.generateRandomPassword();

        if(generatedPassword.length() < 6 || !generatedPassword.matches(".*[a-z].*") || !generatedPassword.matches(".*[A-Z].*") || !generatedPassword.matches(".*[0-9].*") || !generatedPassword.matches(".*[^a-zA-Z0-9|_].*"))
            fail();

        assertTrue(true);
    }

    @Test
    void securityQuestionsReShownCorrectly() throws Exception {
        SignUpMenuController signUpMenuController = new SignUpMenuController();

        String securityQuestions = signUpMenuController.showSecurityQuestion();

        assertEquals(securityQuestions , "Pick your security question:\n" +
                "1. What is my father’s name?\n" +
                "2. What is my favorite color?\n" +
                "3. What is my mother’s last name?");
    }

    @Test
    void emptySloganInRegisterIsInvalid() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameeee1 -p Password1@  -c Password1@ -e emailllll1@gmail.com -n nickname1 -s ";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("Invalid command! Slogan flag and slogan come with together!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void emptySloganInRegisterWithRandomPasswordIsInvalid() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -u usernameeee1 -p random -e emailllll1@gmail.com -n nickname1 -s ";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("Invalid command! Slogan flag and slogan come with together!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void checkSloganWith1DoubleQuoteForRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c Password1@  -n nickname1 -u username12222222 -p Password1@ -e emailll1@gmail.com -s this\"isSlogan";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("The slogan you entered has only 1 double quote!" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void checkSloganWith1DoubleQuoteForRegisterWithRandomPassword() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname1 -u username12222222 -p random -e emailll1@gmail.com -s this\"isSlogan";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("The slogan you entered has only 1 double quote!" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void checkSloganWithoutDoubleQuotesWithSpaceForRegister() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -c Password1@  -n nickname -u username12222222 -p Password1@ -e emailllll1@gmail.com -s this is my slogan";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);

        assertEquals("The slogan you entered has some whitespaces and is not between double quotes" ,
                signUpMenuController.register(matcher));
    }

    @Test
    void checkSloganWithoutDoubleQuotesWithSpaceForRegisterWithRandomPassword() throws Exception {
        Matcher matcher;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname -u username12222222 -p random -e emailllll1@gmail.com -s this is my slogan";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER_WITH_RANDOM_PASSWORD);

        assertEquals("The slogan you entered has some whitespaces and is not between double quotes" ,
                signUpMenuController.registerWithRandomPassword(matcher));
    }

    @Test
    void numberOfSecurityQuestionShouldBeCorrect() throws Exception {
        Matcher matcher , matcher1;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname1 -u username12222222 -p Password1@ -e emailllll1@gmail.com -c Password1@";
        String questionPick = "question pick -q 4 -a dad1222 -c dad1222";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);
        matcher1 = SignupMenuCommands.getMatcher(questionPick , SignupMenuCommands.PICK_A_SECURITY_QUESTION);

        assertEquals("Please choose a question number between 1 and 3!" ,
                signUpMenuController.chooseSecurityQuestion(matcher , matcher1));
    }

    @Test
    void answerToSecurityQuestionAndItsConfirmationShouldBeTheSame() throws Exception {
        Matcher matcher , matcher1;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname1 -u username12222222 -p Password1@ -e emailllll1@gmail.com -c Password1@";
        String questionPick = "question pick -q 3 -a dad1222 -c ddad1222";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);
        matcher1 = SignupMenuCommands.getMatcher(questionPick , SignupMenuCommands.PICK_A_SECURITY_QUESTION);

        assertEquals("Answer and answer confirmation didn't match!" ,
                signUpMenuController.chooseSecurityQuestion(matcher , matcher1));
    }

    @Test
    void checkAnswerToSecurityQuestionWith1DoubleQuote() throws Exception {
        Matcher matcher , matcher1;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname1 -u username12222222 -p Password1@ -e emailllll1@gmail.com -c Password1@";
        String questionPick = "question pick -q 3 -a dad\"1222 -c dad\"1222";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);
        matcher1 = SignupMenuCommands.getMatcher(questionPick , SignupMenuCommands.PICK_A_SECURITY_QUESTION);

        assertEquals("The answer you entered has only 1 double quote!" ,
                signUpMenuController.chooseSecurityQuestion(matcher , matcher1));
    }

    @Test
    void checkAnswerToSecurityQuestionWithoutDoubleQuotesWithSpace() throws Exception {
        Matcher matcher , matcher1;
        SignUpMenuController signUpMenuController = new SignUpMenuController();
        String command = "user create -n nickname1 -u username12222222 -p Password1@ -e emailllll1@gmail.com -c Password1@";
        String questionPick = "question pick -q 3 -a dad 1222 -c dad 1222";

        matcher = SignupMenuCommands.getMatcher(command, SignupMenuCommands.CREATE_A_NEW_USER);
        matcher1 = SignupMenuCommands.getMatcher(questionPick , SignupMenuCommands.PICK_A_SECURITY_QUESTION);

        assertEquals("The answer you entered has some whitespaces and is not between double quotes" ,
                signUpMenuController.chooseSecurityQuestion(matcher , matcher1));
    }

    @Test
    void ifEmailIsAlreadyTakenGiveError() throws Exception {

        if(User.getUsersFromJsonFile().size() == 0)
            assertTrue(true);

        Random rand = new Random();
        int randomNumber  = rand.nextInt(User.getUsersFromJsonFile().size()) ;

        String email = User.getUsersFromJsonFile().get(randomNumber).getEmail();

        String input = "user create -u userrrrnamee12 -p Password1@ -c Password1@ -e " + email + " -n nickname1";
        Matcher matcher = SignupMenuCommands.getMatcher(input , SignupMenuCommands.CREATE_A_NEW_USER);

        SignUpMenuController signUpMenuController = new SignUpMenuController();
        assertEquals("This email is already taken!" , signUpMenuController.register(matcher));
    }

    @Test
    void testWriteInJsonFile() throws Exception {
        // Set up test data
        File file = new File("test-users.json");
        file.createNewFile();

        String username = "testUser";
        String password = "testPassword";
        String email = "testUser@example.com";
        String nickname = "Test User";
        String slogan = "Test slogan";

        // Write data to file
        SignUpMenuController controller = new SignUpMenuController();
        controller.writeInJsonFile(username, password, email, nickname, slogan, "test-users.json");

        // Read file contents and check that they match expected output
        String expectedOutput = "[{\"answer to security question\":\"\",\"password\":\"testPassword\",\"nickname\":\"Test User\",\"slogan\":\"Test slogan\",\"number of security question\":0,\"email\":\"testUser@example.com\",\"username\":\"testUser\",\"is stayed logged in\":false}]";
        String actualOutput = new String(Files.readAllBytes(Paths.get("test-users.json")));
        file.delete();
        assertEquals(expectedOutput, actualOutput);
    }


}