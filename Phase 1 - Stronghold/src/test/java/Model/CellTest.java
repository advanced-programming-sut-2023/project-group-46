package Model;

import Enums.BuildingType;
import Enums.UnitType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void cellReturnsAndSetsBuildingCorrectly() throws Exception {
        Empire empire = new Empire();
        Cell cell = new Cell();
        Building building = new Building(BuildingType.BAKERY , empire);
        cell.setBuilding(building);
        assertEquals(cell.getBuilding() , building);
    }

    @Test
    void testSettersAndGetters() {
        Cell cell = new Cell();
        Building building = new Building();
        cell.setBuilding(building);
        assertEquals(cell.getBuilding(), building);

        User user = new User("u" , "p" , "n" , "e");
        Empire empire = new Empire(user , 1 , 20 , 20);
        Unit unit1 = new Unit(UnitType.ASSASSIN , empire);
        Unit unit2 = new Unit(UnitType.ASSASSIN , empire);
        cell.addUnit(unit1);
        cell.addUnit(unit2);
        assertEquals(cell.getUnits().size(), 2);

        cell.setType("water");
        assertEquals(cell.getType(), "water");

        cell.setEnvironmentName("forest");
        assertEquals(cell.getEnvironmentName(), "forest");
    }

    @Test
    void testAddUnit() {
        Cell cell = new Cell();
        Unit unit = new Unit();
        cell.addUnit(unit);
        assertEquals(cell.getUnits().size(), 1);
        assertEquals(cell.getUnits().get(0), unit);
    }

}