package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void returnsANonNegativeTurnsCount() throws Exception {
        Game game = new Game();
        assertTrue(game.getTurnsCounter() >= 0);
    }

}