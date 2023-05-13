package Model.Goods;

import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmouryTest {

    @Test
    void returnedHorseIsCorrect() throws Exception {
        Armoury armoury = new Armoury(10 , 12 , 14 , 16 , 18 , 20 , 22 , 24 , 26 , 28);
        assertEquals(armoury.getHorse() , 28);
    }

    @Test
    public void testAddArmoury() {
        Armoury armoury = new Armoury();
        armoury.addArmoury("sword", 5);
        assertEquals(5, armoury.getSword());
        assertEquals(-5, armoury.getFreeCapacityArmoury());
    }

    @Test
    public void testGetArmouryAmount() {
        Armoury armoury = new Armoury(2, 3, 4, 1, 0, 6, 7, 8 , 10 , 12);
        assertEquals(2, armoury.getArmouryAmount("bow"));
        assertEquals(3, armoury.getArmouryAmount("crossbow"));
        assertEquals(4, armoury.getArmouryAmount("spear"));
        assertEquals(1, armoury.getArmouryAmount("pike"));
        assertEquals(0, armoury.getArmouryAmount("mace"));
        assertEquals(6, armoury.getArmouryAmount("sword"));
        assertEquals(7, armoury.getArmouryAmount("leatherArmor"));
        assertEquals(8, armoury.getArmouryAmount("metalArmor"));
        assertEquals(10, armoury.getFreeCapacityArmoury());
    }

}