package Controller;

import Model.Map;
import View.EditMapMenu;

import java.util.regex.Matcher;

public class EditMapMenuController {
    private final EditMapMenu editMapMenu;
    public EditMapMenuController() {
        editMapMenu = new EditMapMenu(this);
    }

    public void run() {
    }

    private String showListOfMaps(){
        return null;
    }
    private String setTexture(Matcher matcher) {
        Map.getMap1()[0][0].getBuilding();
        return null;
    }

    private String setTextureRectangle(Matcher matcher) {
        return null;
    }

    private String clear(Matcher matcher) {
        return null;
    }

    private String dropRock(Matcher matcher) {
        return null;
    }

    private String dropTree(Matcher matcher) {
        return null;
    }
}
