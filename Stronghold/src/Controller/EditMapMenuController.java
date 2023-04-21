package Controller;

import Model.EditMapMenuCommands;
import View.EditMapMenu;
import Model.Map;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class EditMapMenuController {
    private final EditMapMenu editMapMenu;

    public EditMapMenuController() {
        editMapMenu = new EditMapMenu(this);
    }

    public String setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        if (Map.getMap4()[x][y].getBuilding() != null) return "Not empty";
        String type = matcher.group("type");
        Map.getMap4()[x][y].setType(type);
        return "Success";
    }

    public String setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        if ((x1 < 0 && y1 < 0) || (x2 > Map.getMap4().length && y2 > Map.getMap4().length)) return "Invalid coordinate";
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (Map.getMap4()[x][y].getBuilding() != null) return "Not empty";
            }
        }
        String type = matcher.group("type");
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                Map.getMap4()[x][y].setType(type);
            }
        }
        return "Success";
    }

    public String clear(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        Map.getMap4()[x][y].setType("earth");
        Map.getMap4()[x][y].setEnvironmentName(null);
        return "Success";
    }

    public String dropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        String[] typeDirection = {"n", "e", "w", "s"};
        String direction = matcher.group("direction");
        if (direction.equals("r")) {
            Random random = new Random();
            direction = typeDirection[random.nextInt(4)];
        } else if (Arrays.asList(typeDirection).contains(direction)) return "Invalid direction";
        Map.getMap4()[x][y].setEnvironmentName("rock " + direction);
        return "Success";
    }

    public String dropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        //TODO optimization ...
        if (Map.getMap4()[x][y].getType().equals("stone") || Map.getMap4()[x][y].getType().equals("iron") || Map.getMap4()[x][y].getType().equals("rock") ||
                Map.getMap4()[x][y].getType().equals("oil") || Map.getMap4()[x][y].getType().equals("plain") || Map.getMap4()[x][y].getType().equals("shallow water") ||
                Map.getMap4()[x][y].getType().equals("river") || Map.getMap4()[x][y].getType().equals("small pond") || Map.getMap4()[x][y].getType().equals("big pond") ||
                Map.getMap4()[x][y].getType().equals("beach") || Map.getMap4()[x][y].getType().equals("sea"))
            return "Invalid cell type";
        String type = matcher.group("type");
        Map.getMap4()[x][y].setEnvironmentName(type);
        return "Success";
    }

    public String dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        //TODO check type of building
        if (Map.getMap4()[x][y].getBuilding() != null) return "Not empty";
        String type = matcher.group("type");
        //TODO check validity type of cell for building
        //TODO add building
        return "Success";
    }

    public String dropUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if ((x < 0 && y < 0) || (x >= Map.getMap4().length && y >= Map.getMap4().length)) return "Invalid coordinate";
        //TODO check type of unit
        String type = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        //TODO check validity type of cell for unit
        //TODO add unit
        return "success";
    }
}
