package Controller;

import Model.Map;
import View.MapMenu;

import java.util.regex.Matcher;

public class MapMenuController {

    private final MapMenu mapMenu;

    public MapMenuController() {
        mapMenu = new MapMenu(this);
    }

    public void run() {

    }

    private String showMap(Matcher matcher) {
        Map.getMap1()[0][0].getBuilding();
        return null;
    }

    private String showDetail(Matcher matcher) {
        return null;
    }

    private String moveInMap(Matcher matcher) {
        return null;
    }
}
