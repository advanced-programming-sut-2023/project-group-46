package Controller;

import Enums.BuildingType;
import Enums.Commands.EditMapMenuCommands;
import Enums.Commands.GameMenuCommands;
import Enums.UnitType;
import Model.*;
import Model.Goods.Armoury;
import Model.Goods.Resources;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class GameMenuControllerTest {

    GameMenuController controller = new GameMenuController();
    User user;
    Empire empire;
    Map map;
    @BeforeEach
    void setUp() throws Exception {
        user = new User("u" , "p" , "n" , "e");
        LoginMenuController.setLoggedInUser(user);
        empire = new Empire(user , 0 , 20 , 30);
        map = new Map(200);
        ArrayList<int[]> coordinates = new ArrayList<>();
        int[] cors = {20 , 30};
        coordinates.add(cors);
        map.setEmpireCoordinates(coordinates);
        GameMenuController.setCurrentEmpire(empire);
        controller = new GameMenuController();
        GameMenuController.setMap(map);
    }

    @Test
    void testShowKeepCoordinates() {
        String expected = "x-> 20 y-> 30";
        assertEquals(expected, controller.showKeepCoordinates());
    }

    @Test
    void testNeighbors() {
        assertEquals(2, controller.neighbors(0, 0).size());
        assertEquals(4, controller.neighbors(1, 1).size());
        assertEquals(4, controller.neighbors(4, 4).size());
    }

    @Test
    void testAnyEnemyNear() {
        assertFalse(controller.anyEnemyNear(0, 0));
        assertFalse(controller.anyEnemyNear(3, 3));
        assertFalse(controller.anyEnemyNear(4, 4));
    }

    @Test
    public void testCheckNumberOfTheTurns() {
        GameMenuController gameMenuController = new GameMenuController();
        Game game = new Game();
        Empire empire = new Empire(new User("u" , "p" , "n" , "e") , 1 , 20 , 20);
        GameMenuController.setGame(game);
        GameMenuController.setCurrentEmpire(empire);

        String expectedOutput = "Game Started  u  is now playing";
        String actualOutput = gameMenuController.checkNumberOfTheTurns(1);
        assertEquals(expectedOutput, actualOutput);
        GameMenuController.setGame(null);
        GameMenuController.setCurrentEmpire(null);
    }

    @Test
    void testSelectedBuildingIsNull() {
        String expected = "you should select a building first";
        String actual = controller.repair();
        assertEquals(expected, actual);
    }

    @Test
    void createUnitErrors()
    {
        String input = "create unit -t abbas -c 100";
        Matcher matcher = GameMenuCommands.getMatcher(input , GameMenuCommands.CREATE_UNIT_REGEX);
        String expected = "invalid name for unit";
        assertEquals(expected , controller.createUnit(matcher));

        Building building = new Building(BuildingType.BAKERY , empire);
        controller.setSelectedBuilding(building);

        input = "create unit -t Archer -c 10000";
        expected = "select proper building";
        matcher = GameMenuCommands.getMatcher(input , GameMenuCommands.CREATE_UNIT_REGEX);
        assertEquals(expected , controller.createUnit(matcher));

        building = new Building(BuildingType.MERCENARY_POST , empire);
        controller.setSelectedBuilding(building);

        input = "create unit -t Archer -c 10000";
        expected = "not enough unemployed people";
        matcher = GameMenuCommands.getMatcher(input , GameMenuCommands.CREATE_UNIT_REGEX);
        assertEquals(expected , controller.createUnit(matcher));

        // Test creating an invalid unit at Barrack
        building = new Building(BuildingType.MERCENARY_POST, empire);
        controller.setSelectedBuilding(building);

        input = "create unit -t Archer -c 10";
        matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CREATE_UNIT_REGEX);
        assertEquals("MercenaryPost can't create Archer", controller.createUnit(matcher));

    }

    @Test
    void attackMachinesErrors()
    {
        String input = "attack machines -x 20000 -y 10";
        Matcher matcher = GameMenuCommands.getMatcher(input , GameMenuCommands.ATTACK_MACHINES_REGEX);
        String expected = "invalid coordinate";
        assertEquals(expected , controller.attackMachines(matcher));
    }


//    @Test
//    public void testChangeAndShowRates() {
//
//        // Test changing and showing food rate
//        String foodRateInput = "set food rate to 50";
//        controller.changeFoodRate(controller.getMatcher(foodRateInput));
//        int expectedFoodRate = 50;
//        int actualFoodRate = controller.foodRateShow();
//        assertEquals(expectedFoodRate, actualFoodRate);
//
//        // Test changing and showing tax rate
//        String taxRateInput = "set tax rate to 25";
//        controller.changeTaxRate(controller.getMatcher(taxRateInput));
//        int expectedTaxRate = 25;
//        int actualTaxRate = controller.taxRateShow();
//        assertEquals(expectedTaxRate, actualTaxRate);
//
//        // Test changing and showing fear rate
//        String fearRateInput = "set fear rate to 75";
//        controller.changeFearRate(controller.getMatcher(fearRateInput));
//        int expectedFearRate = 75;
//        int actualFearRate = controller.fearRateShow();
//        assertEquals(expectedFearRate, actualFearRate);
//    }

}