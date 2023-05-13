package Model;

import Enums.UnitType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitsTest {

    @Test
    void returnedAttackPowerIsCorrect() throws Exception {

        Empire empire = new Empire();
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        assertEquals(unit.getAttackPower() , 20);
    }

    @Test
    void testSetHp() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        int expectedHp = 20;
        assertEquals(expectedHp, unit.getHp());
    }

    @Test
    void testGetSetMode() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        String expectedMode = "moving";
        unit.setMode(expectedMode);
        assertEquals(expectedMode, unit.getMode());
    }

    @Test
    void testGetUnitType() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        assertEquals(UnitType.ASSASSIN, unit.getUnitType());
    }

    @Test
    void testGetSetOwner() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);

        assertEquals(empire, unit.getOwner());
    }

    @Test
    void testGetAttackPower() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        int expectedAttackPower = 20;
        assertEquals(expectedAttackPower, unit.getAttackPower());
    }

    @Test
    void testSetAttackPower() {
        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 ,20);
        Unit unit = new Unit(UnitType.ASSASSIN , empire);
        int expectedAttackPower = 10;
        unit.setAttackPower(expectedAttackPower);
        assertEquals(expectedAttackPower, unit.getAttackPower());
    }


}