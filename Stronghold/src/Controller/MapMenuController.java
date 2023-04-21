package Controller;

import Model.*;
import View.MapMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenuController {

    private final MapMenu mapMenu;

    public MapMenuController() {
        mapMenu = new MapMenu(this);
    }

    public String showMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return "Success";
    }

    public String moveInMap(Matcher matcher) {
        while (true){
        String type = matcher.group("type");
        if(type.equals(null)) break;
        int count = Integer.parseInt(matcher.group("count"));
        //TODO do function
        }
        return "Success";
    }

    public String showDetail(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String stringShowDetail = "type : " + Map.getMap1()[x][y].getType();
        if (Map.getMap1()[x][y].getBuilding() != null)
            stringShowDetail += "building : " + Map.getMap1()[x][y].getBuilding().getName();
        for (Unit unit : Map.getMap1()[x][y].getUnits()) {
            if (unit != null) stringShowDetail += "Unit : " + unit.getName();
        }
        for (Machine machine : Map.getMap1()[x][y].getMachines()) {
            if (machine != null) stringShowDetail += "machine : " + machine.getName();
        }
        //TODO resource
        return stringShowDetail;
    }
}
