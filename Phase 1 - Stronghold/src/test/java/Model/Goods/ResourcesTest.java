package Model.Goods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {

    @Test
    public void testAddFlour() {
        Resources resources = new Resources();
        resources.addFlour(50);
        Assertions.assertEquals(50, resources.getFlour());
    }

    @Test
    public void testAddHop() {
        Resources resources = new Resources();
        resources.addHop(25);
        Assertions.assertEquals(25, resources.getHop());
    }

    @Test
    public void testAddAle() {
        Resources resources = new Resources();
        resources.addAle(10);
        Assertions.assertEquals(10, resources.getAle());
    }

    @Test
    public void testAddStone() {
        Resources resources = new Resources();
        resources.addStone(100);
        Assertions.assertEquals(100, resources.getStone());
    }

    @Test
    public void testAddIron() {
        Resources resources = new Resources();
        resources.addIron(75);
        Assertions.assertEquals(75, resources.getIron());
    }

    @Test
    public void testAddWood() {
        Resources resources = new Resources();
        resources.addWood(200);
        Assertions.assertEquals(200, resources.getWood());
    }

    @Test
    public void testAddPitch() {
        Resources resources = new Resources();
        resources.addPitch(5);
        Assertions.assertEquals(5, resources.getPitch());
    }

    @Test
    public void testAddFreeCapacityStockpile() {
        Resources resources = new Resources();
        resources.addFreeCapacityStockpile(50);
        Assertions.assertEquals(50, resources.getFreeCapacityStockpile());
    }

    @Test
    public void testAddResource() {
        Resources resources = new Resources();
        resources.addResource("gold", 50);
        Assertions.assertEquals(50, resources.getGold());

        resources.addResource("wheat", 20);
        Assertions.assertEquals(20, resources.getWheat());
        Assertions.assertEquals(-20, resources.getFreeCapacityStockpile());

        resources.addResource("flour", 10);
        Assertions.assertEquals(10, resources.getFlour());
        Assertions.assertEquals(-30, resources.getFreeCapacityStockpile());

        resources.addResource("hop", 5);
        Assertions.assertEquals(5, resources.getHop());
        Assertions.assertEquals(-35, resources.getFreeCapacityStockpile());

        resources.addResource("ale", 3);
        Assertions.assertEquals(3, resources.getAle());
        Assertions.assertEquals(-38, resources.getFreeCapacityStockpile());

        resources.addResource("stone", 100);
        Assertions.assertEquals(100, resources.getStone());
        Assertions.assertEquals(-138, resources.getFreeCapacityStockpile());

        resources.addResource("iron", 75);
        Assertions.assertEquals(75, resources.getIron());
        Assertions.assertEquals(-213, resources.getFreeCapacityStockpile());

        resources.addResource("wood", 200);
        Assertions.assertEquals(200, resources.getWood());
        Assertions.assertEquals(-413, resources.getFreeCapacityStockpile());

        resources.addResource("pitch", 5);
        Assertions.assertEquals(5, resources.getPitch());
        Assertions.assertEquals(-418, resources.getFreeCapacityStockpile());
    }


}