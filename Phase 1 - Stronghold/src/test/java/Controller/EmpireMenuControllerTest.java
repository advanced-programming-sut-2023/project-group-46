package Controller;

import Model.Empire;
import Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpireMenuControllerTest {

    @Test
    void calculatePopularityFactorsTest() {

        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);
        empire.setFoodRate(-1);
        empire.setTaxRate(2);
        empire.setFearRate(3);

        EmpireMenuController.calculatePopularityFactors();

        assertEquals(-4, empire.getFoodPopularity());
        assertEquals(-4, empire.getTaxPopularity());
        assertEquals(3, empire.getFearPopularity());

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    void calculatePopularityFactorsTestAllCases() {
        // Test all possible cases for the calculatePopularityFactors method

        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);

        // Test food rate cases
        empire.setFoodRate(-2);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-8, empire.getFoodPopularity());

        empire.setFoodRate(-1);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-4, empire.getFoodPopularity());

        empire.setFoodRate(0);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(0, empire.getFoodPopularity());

        empire.setFoodRate(1);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(4, empire.getFoodPopularity());

        empire.setFoodRate(2);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(8, empire.getFoodPopularity());

        // Test tax rate cases
        empire.setTaxRate(-3);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(7, empire.getTaxPopularity());

        empire.setTaxRate(-2);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(5, empire.getTaxPopularity());

        empire.setTaxRate(-1);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(3, empire.getTaxPopularity());

        empire.setTaxRate(0);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(1, empire.getTaxPopularity());

        empire.setTaxRate(1);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-2, empire.getTaxPopularity());

        empire.setTaxRate(2);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-4, empire.getTaxPopularity());

        empire.setTaxRate(3);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(GameMenuController.getCurrentEmpire().getFearRate(), empire.getFearPopularity());

        empire.setTaxRate(4);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-8, empire.getTaxPopularity());

        empire.setTaxRate(5);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-12, empire.getTaxPopularity());

        empire.setTaxRate(6);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-16, empire.getTaxPopularity());

        empire.setTaxRate(7);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-20, empire.getTaxPopularity());

        empire.setTaxRate(8);
        EmpireMenuController.calculatePopularityFactors();
        assertEquals(-24, empire.getTaxPopularity());

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    void calculateFoodAndTaxTest() {

        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);

        empire.setUnemployedPeople(20);
        empire.addEmployedPeople(30);

        empire.getFoodStock().addApple(0);
        empire.getFoodStock().addBread(0);
        empire.getFoodStock().addMeat(0);
        empire.getFoodStock().addCheese(0);
        GameMenuController.getCurrentEmpire().setFoodRate(-1);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(-1, empire.getFoodRate());

        empire.getFoodStock().addApple(10);
        empire.getFoodStock().addBread(10);
        empire.getFoodStock().addMeat(0);
        empire.getFoodStock().addCheese(0);
        GameMenuController.getCurrentEmpire().setFoodRate(0);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(0, empire.getFoodRate());

        empire.getFoodStock().addApple(30);
        empire.getFoodStock().addBread(20);
        empire.getFoodStock().addMeat(10);
        empire.getFoodStock().addCheese(5);
        GameMenuController.getCurrentEmpire().setFoodRate(1);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(1, empire.getFoodRate());

        empire.getFoodStock().addApple(35);
        empire.getFoodStock().addBread(20);
        empire.getFoodStock().addMeat(10);
        empire.getFoodStock().addCheese(5);
        GameMenuController.getCurrentEmpire().setFoodRate(2);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(2, empire.getFoodRate());

        empire.getFoodStock().addApple(40);
        empire.getFoodStock().addBread(30);
        empire.getFoodStock().addMeat(20);
        empire.getFoodStock().addCheese(10);
        GameMenuController.getCurrentEmpire().setFoodRate(3);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(3, empire.getFoodRate());

        empire.getResources().addGold(40);

        GameMenuController.getCurrentEmpire().setTaxRate(-1);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(-1, empire.getTaxRate());

        GameMenuController.getCurrentEmpire().setTaxRate(-2);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(-2, empire.getTaxRate());

        GameMenuController.getCurrentEmpire().setTaxRate(-3);
        EmpireMenuController.calculateFoodAndTax();
        assertEquals(-3, empire.getTaxRate());


        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    void foodListIsShownCorrectly() {
        EmpireMenuController empireMenuController = new EmpireMenuController();
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        GameMenuController.setCurrentEmpire(empire);

        String output = empireMenuController.showFoodList();

        assertTrue(output.contains("cheese"));

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testShowPopularityFactors() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.setFoodPopularity(10);
        empire.setTaxPopularity(20);
        empire.setFearPopularity(30);
        empire.addReligionPopularity(40);
        empire.addAleCoverage(50);
        GameMenuController.setCurrentEmpire(empire);

        // call method and check output
        String expectedOutput = "Food : 10\n" +
                "Tax : 20\n" +
                "Fear : 30\n" +
                "Religion : 40\n" +
                "aleCoverage : 50\n";

        assertEquals(expectedOutput, empireMenuController.showPopularityFactors());
        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testShowPopularity() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.setFoodPopularity(5);
        empire.setTaxPopularity(2);
        empire.setFearPopularity(3);
        empire.addReligionPopularity(40);
        empire.addAleCoverage(50);
        GameMenuController.setCurrentEmpire(empire);

        int expectedPopularity = 100;
        assertEquals(expectedPopularity, empireMenuController.showPopularity());

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testShowArmoury() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.getArmoury().addArmoury("bow", 3);
        empire.getArmoury().addArmoury("crossbow", 4);
        empire.getArmoury().addArmoury("pike", 2);
        empire.getArmoury().addArmoury("mace", 9);
        empire.getArmoury().addArmoury("leatherArmor", 3);
        empire.getArmoury().addArmoury("sword", 8);
        empire.getArmoury().addArmoury("spear", 7);
        empire.getArmoury().addArmoury("metalArmor", 1);

        String expectedOutput = "bow->3\ncrossbow->4\nspear->7\npike->2\nmace->9\nsword->8\nleatherArmor->3\nmetalArmor->1\n";
        assertEquals(expectedOutput, empireMenuController.showArmoury());

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testShowStockpile() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.getResources().addResource("gold", 100);
        empire.getResources().addResource("wheat", 50);
        empire.getResources().addResource("iron", 10);

        String expectedOutput = "gold->5100\nwheat->50\nflour->0\nhop->0\nale->0\nstone->50\niron->10\nwood->100\npitch->0\n";
        assertEquals(expectedOutput, empireMenuController.showStockpile());

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testCalculatePopulationWhenUnemployedPeoplePlusPopularityIsGreaterThanMaxPopulation() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.addMaxPopulation(100);
        empire.setUnemployedPeople(80);
        empire.setFearPopularity(10);
        empire.setFoodPopularity(20);
        empire.addReligionPopularity(30);
        empire.setTaxPopularity(40);
        empire.addAleCoverage(50);

        GameMenuController.setCurrentEmpire(empire);
        EmpireMenuController.calculatePopularityFactors();

        assertEquals(empire.getUnemployedPeople(), 80);

        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testCalculatePopulationWhenUnemployedPeoplePlusPopularityIsLessThanZero() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.addMaxPopulation(100);
        empire.setUnemployedPeople(10);
        empire.setFearPopularity(-20);
        empire.setFoodPopularity(-30);
        empire.addReligionPopularity(-40);
        empire.setTaxPopularity(-50);
        empire.addAleCoverage(-60);

        GameMenuController.setCurrentEmpire(empire);
        EmpireMenuController.calculatePopulation();

        assertEquals(empire.getUnemployedPeople(), 0);
        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    public void testCalculatePopulationWhenUnemployedPeoplePlusPopularityIsWithinBounds() {
        User user = new User("u" , "" , "" , "");
        Empire empire = new Empire(user , 1 , 20 , 20);
        EmpireMenuController empireMenuController = new EmpireMenuController();
        GameMenuController.setCurrentEmpire(empire);

        empire.addMaxPopulation(100);
        empire.setUnemployedPeople(20);
        empire.setFearPopularity(5);
        empire.setFoodPopularity(10);
        empire.addReligionPopularity(-15);
        empire.setTaxPopularity(-20);
        empire.addAleCoverage(30);

        GameMenuController.setCurrentEmpire(empire);
        EmpireMenuController.calculatePopulation();

        assertEquals(empire.getUnemployedPeople(), 50);
        GameMenuController.setCurrentEmpire(null);
    }


}