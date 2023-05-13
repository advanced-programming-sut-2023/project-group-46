package Controller;

import Enums.Commands.TradeMenuCommands;
import Model.Empire;
import Model.Game;
import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeMenuControllerTest {

    @Test
    public void testTrade_invalidItem() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        String input = "trade -m hello -w invalid -g resource -wa 5 -ga 2";
        TradeMenuController controller = new TradeMenuController();
        String output = controller.trade(TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_REGEX));
        assertEquals("invalid item", output);
    }

    @Test
    public void testTrade_notEnoughGivenResource() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        String input = "trade -m hello -w food -g wood -wa 5 -ga 100000";
        TradeMenuController controller = new TradeMenuController();
        String output = controller.trade(TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_REGEX));
        assertEquals("you don't have enough amount of this item", output);
    }

    @Test
    public void testTrade_success() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        Game game = new Game();
        GameMenuController.setGame(game);
        String input = "trade -m hello -w food -g wood -wa 5 -ga 2";
        TradeMenuController controller = new TradeMenuController();
        String output = controller.trade(TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_REGEX));
        assertEquals("success", output);
    }

    @Test
    public void testTradeList_outputFormat() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        Game game = new Game();
        GameMenuController.setGame(game);
        TradeMenuController controller = new TradeMenuController();
        String output = controller.tradeList();
        assertTrue(output.matches("(id-> \\d+ | wanted : \\S+->\\d+ | given : \\S+->\\d+ | sender'sID->\\d+\n){0,}"));
    }

    @Test
    public void testTradeAccept_invalidId() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        Game game = new Game();
        GameMenuController.setGame(game);
        String input = "trade accept -i 1000";
        TradeMenuController controller = new TradeMenuController();
        String output = controller.tradeAccept(TradeMenuCommands.getMatcher(input, TradeMenuCommands.TRADE_ACCEPT_REGEX));
        assertEquals("invalid trade ID", output);
    }

    @Test
    public void testTradeHistory_outputFormat() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20  , 20);
        GameMenuController.setCurrentEmpire(empire);
        Game game = new Game();
        GameMenuController.setGame(game);
        TradeMenuController controller = new TradeMenuController();
        String output = controller.tradeHistory();
        assertTrue(output.matches("(id-> \\d+ | wanted : \\S+->\\d+ | given : \\S+->\\d+ | sender'sID->\\d+ | getter'sID->\\d+\n){0,}"));
    }

}