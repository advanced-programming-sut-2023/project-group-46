package Controller;

import Enums.Commands.ShopMenuCommands;
import Model.Empire;
import Model.User;
import View.Menu;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class ShopMenuControllerTest {

    private static final ShopMenuController shopMenuController = new ShopMenuController();


    @Test
    public void testBuyInvalidItem() {
        Empire empire = new Empire(new User("u" , "p" , "n" , "e") , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);

        String input = "buy -i invalidItem -a 10";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);
        String expectedOutput = "invalid item";
        String actualOutput = shopMenuController.buy(matcher);
        assertEquals(expectedOutput, actualOutput);
        GameMenuController.setCurrentEmpire(null);
    }





    @Test
    public void testSellInvalidItem() {
        Empire empire = new Empire(new User("u" , "p" , "n" , "e") , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);

        String input = "sell -i invalidItem -a 5";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.SELL_REGEX);
        String expectedOutput = "invalid item";
        String actualOutput = shopMenuController.sell(matcher);
        assertEquals(expectedOutput, actualOutput);
        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testBuyRegexMatches() {
        String input = "buy -i gold -a 10";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);
        assertNotNull(matcher);
        assertEquals("gold", matcher.group("name"));
        assertEquals("10", matcher.group("amount"));
    }

    @Test
    public void testBuyRegexDoesNotMatchMissingArgument() {
        String input = "buy -i gold";
        ShopMenuCommands command = ShopMenuCommands.BUY_REGEX;
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);
        assertNull(matcher);
    }

    @Test
    public void testBuyRegexDoesNotMatchInvalidOption() {
        String input = "buy -x gold -a 10";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);
        assertNull(matcher);
    }

    @Test
    public void testSellRegexMatches() {
        String input = "sell -i wood -a 5";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.SELL_REGEX);
        assertNotNull(matcher);
        assertEquals("wood", matcher.group("name"));
        assertEquals("5", matcher.group("amount"));
    }

    @Test
    public void testSellRegexDoesNotMatchMissingArgument() {
        String input = "sell -i wood";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);

        assertNull(matcher);
    }

    @Test
    public void testSellRegexDoesNotMatchInvalidOption() {
        String input = "sell -x wood -a 5";
        Matcher matcher = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_REGEX);
        assertNull(matcher);
    }

}