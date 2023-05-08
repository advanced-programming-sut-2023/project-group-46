package Controller;


import Model.EnvironmentType;
import Model.Map;
import View.EditMapMenu;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;

public class EditMapMenuController {
    private Map map;
    private final EditMapMenu editMapMenu;

    public EditMapMenuController() {
        editMapMenu = new EditMapMenu(this);
    }

    public String setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= map.getSize() && y >= map.getSize())) return "Invalid coordinate";
        if (map.getMap()[x][y].getBuilding() != null) return "Not empty";
        String type = matcher.group("type");
        map.getMap()[x][y].setType(type);
        return "Success";
    }

    public String setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if ((x1 < 0 && y1 < 0) || (x2 > map.getSize() && y2 > map.getSize())) return "Invalid coordinate";
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (map.getMap()[x][y].getBuilding() != null) return "Not empty";
            }
        }
        String type = matcher.group("type");
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                map.getMap()[x][y].setType(type);
            }
        }
        return "Success";
    }

    public String clear(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= map.getSize() && y >= map.getSize())) return "Invalid coordinate";
        map.getMap()[x][y].setType("earth");
        map.getMap()[x][y].setEnvironmentName(null);
        return "Success";
    }

    public String dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= map.getSize() && y >= map.getSize())) return "Invalid coordinate";
        String[] typeDirection = {"n", "e", "w", "s"};
        String direction = matcher.group("direction");
        if (direction.equals("r")) {
            Random random = new Random();
            direction = typeDirection[random.nextInt(4)];
        } else if (Arrays.asList(typeDirection).contains(direction)) return "Invalid direction";
        map.getMap()[x][y].setEnvironmentName("rock " + direction);
        return "Success";
    }

    public String dropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= map.getSize() && y >= map.getSize())) return "Invalid coordinate";
        if(!EnvironmentType.getEnvironmentTypeByName(map.getMap()[x][y].getType()).isDropTree()) return "Invalid cell type";
        String type = matcher.group("type");
        map.getMap()[x][y].setEnvironmentName(type);
        return "Success";
    }
}
