package Model;

import Controller.GameMenuController;
import Enums.BuildingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    @Test
    void returnedHitpointsIsCorrect() throws Exception {
        Empire empire = new Empire();
        Building building = new Building(BuildingType.BAKERY , empire);

        assertEquals(building.getHp() , 300);
    }

    @Test
    public void testGettersAndSetters() {
        // initialize a building
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);
        Building building = new Building(BuildingType.ARMOURY , empire);

        // test getters
        assertEquals(BuildingType.ARMOURY, building.getBuildingType());
        assertEquals(50, building.getFreeCapacity());
        assertFalse(building.isIsPassableForEnemies());

        // test setters

        assertNotNull(building.getOwner()); // owner is now set

        building.setFreeCapacity(100);
        assertEquals(100, building.getFreeCapacity());

        building.setMode("spear");
        assertEquals("spear", building.getMode());

        building.setIsPassableForEnemies(true);
        assertTrue(building.isIsPassableForEnemies());

        building.setHp(80);
        assertEquals(80, building.getHp());

        building.setRate(25);
        assertEquals(25, building.getRate());
    }

}