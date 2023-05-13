package Controller;

import Enums.BuildingType;
import Enums.Commands.EditMapMenuCommands;
import Enums.EnvironmentType;
import Model.Cell;
import Model.Map;
import Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class EditMapMenuControllerTest {


//    @Test
//    void setTexture_success() throws IOException {
//        User user = new User("u" , "p" , "n" , "e");
//        LoginMenuController.setLoggedInUser(user);
//        String input = "settexture -x 9999 -y 99999";
//        Matcher matcher = EditMapMenuCommands.getMatcher(input , EditMapMenuCommands.SET_TEXTURE);
//        EditMapMenuController editMapMenuController = new EditMapMenuController();
//
//        String output = editMapMenuController.setTexture(matcher);
//
//        assertEquals("Invalid coordinate" , output);
//    }

//    @Test
//    void getMatcher_setTexture() {
//        String input = "settexture -x 4 -y 7 -t desert";
//        Matcher matcher = EditMapMenuCommands.getMatcher(input, EditMapMenuCommands.SET_TEXTURE);
//
//        assertTrue(matcher.matches());
//        assertEquals("4", matcher.group("x"));
//        assertEquals("7", matcher.group("y"));
//        assertEquals("desert", matcher.group("type"));
//    }
//
//


//    @Test
//    public void setTexture() {
//        User user = new User("u" , "p" , "n" , "e");
//        LoginMenuController.setLoggedInUser(user);
//        EditMapMenuController controller = new EditMapMenuController();
//        String input = "settexture -x 0 -y 0 -t water";
//        Matcher matcher = EditMapMenuCommands.getMatcher( input , EditMapMenuCommands.SET_TEXTURE);
//        String result = controller.setTexture(matcher);
//        assertEquals("Success", result);
//        assertEquals(EnvironmentType.BEACH.getName(), controller.getMap().getMap()[0][0].getType());
//    }

//    @Test
//    public void setTextureRectangle() {
//        Matcher matcher = Pattern.compile(EditMapMenuCommands.SET_TEXTURE_RECTANGLE.getRegex()).matcher("settexture -x1 0 -y1 0 -x2 2 -y2 2 -t desert");
//        String result = controller.setTextureRectangle(matcher);
//        assertEquals("Success", result);
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[0][0].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[0][1].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[0][2].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[1][0].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[1][1].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[1][2].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[2][0].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[2][1].getType());
//        assertEquals(EnvironmentType.DESERT.getName(), controller.getMap().getMap()[2][2].getType());
//    }
//
//
//    @Test
//    public void dropBuilding() {
//        User user = new User("u" , "p" , "n" , "e");
//        LoginMenuController.setLoggedInUser(user);
//        EditMapMenuController controller = new EditMapMenuController();
//        String input = "settexture -x 0 -y 0 -t earth";
//        controller.setTexture(EditMapMenuCommands.getMatcher(input , EditMapMenuCommands.SET_TEXTURE));
//
//        input = "dropbuilding -x 0 -y 0 -t keep";
//        Matcher matcher = EditMapMenuCommands.getMatcher(input , EditMapMenuCommands.DROP_BUILDING);
//        String result = controller.dropBuilding(matcher);
//        assertEquals("Success", result);
//        assertEquals(BuildingType.KEEP.getName(), controller.getMap().getMap()[0][0].getBuilding().getBuildingType().getName());
//    }
//

}