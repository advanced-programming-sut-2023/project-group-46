package Controller;

import Enums.Commands.MapMenuCommands;
import Model.Map;
import Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class MapMenuControllerTest {


    private static MapMenuController mapMenuController;

    @BeforeEach
    public void setUp() {
        User user = new User("u" , "p" , "n" , "e");
        LoginMenuController.setLoggedInUser(user);
        Map map = new Map(200);
        GameMenuController.setMap(map);
        mapMenuController = new MapMenuController();

    }

    @Test
    public void testShowMap() {
        String input = "show map -x 3 -y 4";
        Matcher matcher = MapMenuCommands.getMatcher(input , MapMenuCommands.SHOW_MAP);
        String output = mapMenuController.showMap(matcher);

        // Assert that the output is not empty
        Assertions.assertFalse(output.isEmpty());

        // Assert that the output contains the expected character "#"
        Assertions.assertTrue(output.contains("#"));
    }

//    @Test
//    public void testMoveInMap() {
//        String input = "map up left";
//        Matcher matcher = MapMenuCommands.getMatcher(input , MapMenuCommands.MOVE_IN_MAP);
//        String output = mapMenuController.moveInMap(matcher);
//
//        // Assert that the output is not empty
//        Assertions.assertFalse(output.isEmpty());
//
//        // Assert that the output contains the expected character "#"
//        Assertions.assertTrue(output.contains("#"));
//    }

//    @Test
//    public void testShowDetail() {
//        String input = "show details -x 5 -y 6";
//        Matcher matcher = MapMenuCommands.getMatcher(input , MapMenuCommands.SHOW_DETAILS);
//        String output = mapMenuController.showDetail(matcher);
//
//        // Assert that the output is not empty
//        Assertions.assertFalse(output.isEmpty());
//
//        // Assert that the output contains the expected string "type : "
//        Assertions.assertTrue(output.contains("type : "));
//    }

}