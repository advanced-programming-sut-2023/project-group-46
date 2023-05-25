package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradeTest {

    @Test
    void returnedWantedResourceAmountIsCorrect() throws Exception {
        Empire empire = new Empire();
        Trade trade = new Trade("a" , 10 , "b" , 15 , "message" , empire);

        assertEquals(trade.getWantedResourceAmount() , 10);
    }

    @Test
    public void testSetAndGet() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 , 20);
        Trade trade = new Trade("wood" , 5 , "iron" , 2 , "message" , empire);

        String wantedResource = "wood";
        int wantedResourceAmount = 5;
        String givenResource = "iron";
        int givenResourceAmount = 2;
        String message = "message";

        assertEquals(wantedResource, trade.getWantedResource());
        assertEquals(wantedResourceAmount, trade.getWantedResourceAmount());
        assertEquals(givenResource, trade.getGivenResource());
        assertEquals(givenResourceAmount, trade.getGivenResourceAmount());
        assertEquals(message, trade.getMessage());
        assertEquals(empire, trade.getSenderEmpire());

        Empire empire2 = new Empire(user , 2 , 40 , 40);
        trade.setGetterEmpire(empire2);
        assertEquals(empire2 , trade.getGetterEmpire());

    }

}