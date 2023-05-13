package Model;

import Controller.GameMenuController;
import Enums.BuildingType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpireTest {

    private Empire empire;
    @BeforeEach
    void setUp() {
        this.empire = new Empire(new User("test" , "password" , "nickname" , "email"), 0, 0, 0);
    }


    @Test
    void getMaxPopulation_returnsMaxPopulation() {
        assertEquals(10, empire.getMaxPopulation());
    }

    @Test
    void addMaxPopulation_increasesMaxPopulation() {
        empire.addMaxPopulation(5);
        assertEquals(15, empire.getMaxPopulation());
    }

    @Test
    void getAleCoverage_returnsAleCoverage() {
        assertEquals(0, empire.getAleCoverage());
    }

    @Test
    void addAleCoverage_increasesAleCoverage() {
        empire.addAleCoverage(20);
        assertEquals(20, empire.getAleCoverage());
    }

    @Test
    void getKeepCoordinates_returnsKeepCoordinates() {
        int[] expected = {0, 0};
        assertArrayEquals(expected, empire.getKeepCoordinates());
    }

    @Test
    void getColor_returnsColor() {
        assertEquals("red", empire.getColor());
    }

    @Test
    void getFoodStock_returnsFoodStock() {
        assertNotNull(empire.getFoodStock());
    }

    @Test
    void getUser_returnsUser() {
        assertNotNull(empire.getUser());
    }

    @Test
    void getUnemployedPeople_returnsUnemployedPeople() {
        assertEquals(10, empire.getUnemployedPeople());
    }

    @Test
    void setUnemployedPeople_setsUnemployedPeople() {
        empire.setUnemployedPeople(5);
        assertEquals(5, empire.getUnemployedPeople());
    }

    @Test
    void getEmployedPeople_returnsEmployedPeople() {
        assertEquals(0, empire.getEmployedPeople());
    }

    @Test
    void getFoodRate_returnsFoodRate() {
        assertEquals(0, empire.getFoodRate());
    }

    @Test
    void setFoodRate_setsFoodRate() {
        empire.setFoodRate(5);
        assertEquals(5, empire.getFoodRate());
    }

    @Test
    void getFearPopularity_returnsFearPopularity() {
        assertEquals(0, empire.getFearPopularity());
    }

    @Test
    void setFearPopularity_setsFearPopularity() {
        empire.setFearPopularity(5);
        assertEquals(5, empire.getFearPopularity());
    }

    @Test
    void getTaxPopularity_returnsTaxPopularity() {
        assertEquals(0, empire.getTaxPopularity());
    }

    @Test
    void setTaxPopularity_setsTaxPopularity() {
        empire.setTaxPopularity(5);
        assertEquals(5, empire.getTaxPopularity());
    }

    @Test
    void getReligionPopularity_returnsReligionPopularity() {
        assertEquals(0, empire.getReligionPopularity());
    }

    @Test
    void getTaxRate_returnsTaxRate() {
        assertEquals(0, empire.getTaxRate());
    }

    @Test
    void setTaxRate_setsTaxRate() {
        empire.setTaxRate(5);
        assertEquals(5, empire.getTaxRate());
    }

    @Test
    void getFearRate_returnsFearRate() {
        assertEquals(0, empire.getFearRate());
    }

    @Test
    void setFearRate_setsFearRate() {
        empire.setFearRate(5);
        assertEquals(5, empire.getFearRate());
    }

    @Test
    void getEmpireId_returnsEmpireId() {
        assertEquals(0, empire.getEmpireId());
    }

    @Test
    void getArmoury_returnsArmoury() {
        assertNotNull(empire.getArmoury());
    }

    @Test
    void getResources_returnsResources() {
        assertNotNull(empire.getResources());
    }

    @Test
    void addUnemployedPeople_increasesUnemployedPeople() {
        empire.addUnemployedPeople(5);
        assertEquals(15, empire.getUnemployedPeople());
    }

    @Test
    void addEmployedPeople_increasesEmployedPeopleAndDecreasesUnemployedPeople() {
        empire.addEmployedPeople(5);
        assertEquals(5, empire.getEmployedPeople());
        assertEquals(5, empire.getUnemployedPeople());
    }

    @Test
    void addReligionPopularity_increasesReligionPopularity() {
        empire.addReligionPopularity(2);
        assertEquals(2, empire.getReligionPopularity());
    }


    @Test
    void empireReturnsMaxPopulationCorrectly() throws Exception {
        User user = new User("test" , "password" , "nickname" , "email");
        Empire empire = new Empire(user , 1 , 50 , 50);

        assertEquals(empire.getMaxPopulation() , 10);
    }



}