package Model.Goods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodStockTest {

    @Test
    public void testGetFoodAmount() {
        // Initialize FoodStock object
        FoodStock stock = new FoodStock(2.5, 4.0, 1.2, 3.8, 10.0);

        // Test getting meat amount
        assertEquals(2.5, stock.getFoodAmount("meat"), 0.01);

        // Test getting apple amount
        assertEquals(4.0, stock.getFoodAmount("apple"), 0.01);

        // Test getting cheese amount
        assertEquals(1.2, stock.getFoodAmount("cheese"), 0.01);

        // Test getting bread amount
        assertEquals(3.8, stock.getFoodAmount("bread"), 0.01);

        // Test getting amount of non-existent food type
        assertEquals(-1, stock.getFoodAmount("banana"), 0.01);
    }


}