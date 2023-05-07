package Controller;

import Enums.Commands.MapMenuCommands;
import Enums.EnvironmentType;
import Model.Cell;
import Model.Map;
import Model.Unit;
import View.MapMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

public class MapMenuController {

    private final MapMenu mapMenu;

    private String username = LoginMenuController.getLoggedInUser().getUsername();
    private Map map;
    private int x;
    private int y;


    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            map = objectMapper.readValue(new File(username + ".json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MapMenuController() {
        mapMenu = new MapMenu(this);
    }

    public String showMap(Matcher matcher) {
        x = Integer.parseInt(matcher.group("x"));
        y = Integer.parseInt(matcher.group("y"));
        return makeOutputInStandard(getPartOfMap());
    }

    private String[][] getPartOfMap() {
        int x1 = cornersRow()[0], x2 = cornersRow()[1];
        int y1 = cornersColumn()[0], y2 = cornersColumn()[1];
        String[][] partOfMap = new String[x2 - x1][y2 - y1];
        for (int i = 0; i < x2 - x1; i++) {
            for (int j = 0; j < y2 - y1; j++)
                partOfMap[i][j] = cellForShow(map.getMap()[x1 + i][y1 + j]);
        }
        return partOfMap;
    }

    private int[] cornersRow() {
        int[] corner = new int[2];
        if (x >= map.getSize() - 3) {
            corner[1] = map.getSize() - 1;
            corner[0] = map.getSize() - 5;
        } else if (x <= 2) {
            corner[0] = 0;
            corner[1] = 4;
        } else {
            corner[0] = x - 2;
            corner[1] = x + 2;
        }
        return corner;
    }

    private int[] cornersColumn() {
        int[] corner = new int[2];
        if (y >= map.getSize() - 8) {
            corner[1] = map.getSize() - 1;
            corner[0] = map.getSize() - 15;
        } else if (y <= 7) {
            corner[0] = 0;
            corner[1] = 14;
        } else {
            corner[0] = y - 7;
            corner[1] = y + 7;
        }
        return corner;
    }

    private String cellForShow(Cell cell) {
        String stringCellForShow = EnvironmentType.getEnvironmentTypeByName(cell.getType()).getColor();
        if (cell.getUnits().size() > 0) {
            stringCellForShow += "S";
        } else if (cell.getBuilding() != null) {
            stringCellForShow += "B";
        }else if (cell.getEnvironmentName() != null && !cell.getEnvironmentName().equals("rock")) {
            stringCellForShow += "T";
        } else if (cell.getEnvironmentName() != null && cell.getEnvironmentName().equals("rock")) {
            stringCellForShow += "R";
        } else stringCellForShow += " ";
        String reset = "\033[0m";
        stringCellForShow += reset;
        return stringCellForShow;
    }

    private String makeOutputInStandard(String[][] partOfMap) {
        String stringMakeOutputInStandard = "";
        for (int i = 0; i <= 16; i++) {
            if (i % 4 == 0) {
                if (i != 0 && i != (16)) stringMakeOutputInStandard += "\n";
            } else {
                for (int j = 0; j <= 6 * 14; j++) {
                    if (j % 6 == 0)
                        stringMakeOutputInStandard += "|";
                    else {
                        if (i % 4 == 2 && j % 6 == 3) {
                            stringMakeOutputInStandard += (partOfMap[i / 4][j / 6]);
                        } else
                            stringMakeOutputInStandard += "#";
                    }
                }
                stringMakeOutputInStandard += "\n";
            }
        }
        return stringMakeOutputInStandard;
    }

    public String moveInMap(Matcher matcher) {
        int x = 0;
        int y = 0;
        String command = matcher.group("command");
        Matcher up = MapMenuCommands.getMatcher(command, MapMenuCommands.UP);
        Matcher down = MapMenuCommands.getMatcher(command, MapMenuCommands.DOWN);
        Matcher left = MapMenuCommands.getMatcher(command, MapMenuCommands.LEFT);
        Matcher right = MapMenuCommands.getMatcher(command, MapMenuCommands.RIGHT);
        boolean upFound = up.find(), downFound = down.find(), leftFound = left.find(), rightFound = right.find();
        if (!upFound && !downFound && !leftFound && !rightFound) {
            return "Invalid command";
        }
        if (upFound && (up.group("count") == null || up.group("count").isEmpty())) y += 1;
        else if(upFound && !(up.group("count") == null || up.group("count").isEmpty())) y +=  Integer.parseInt(up.group("count"));
        if (downFound && (down.group("count") == null || down.group("count").isEmpty())) y -= 1;
        else if(downFound && !(down.group("count") == null || down.group("count").isEmpty())) y -=  Integer.parseInt(down.group("count"));
        if (rightFound && (right.group("count") == null || right.group("count").isEmpty())) x += 1;
        else if(rightFound && !(right.group("count") == null || right.group("count").isEmpty())) x +=  Integer.parseInt(right.group("count"));
        if (leftFound && (left.group("count") == null || left.group("count").isEmpty())) x -= 1;
        else if(leftFound && !(left.group("count") == null || left.group("count").isEmpty())) x -=  Integer.parseInt(left.group("count"));
        if (!checkMove(x, y)) return "Invalid move";
        this.x += x;
        this.y += y;
        return makeOutputInStandard(getPartOfMap());
    }

    private boolean checkMove(int x, int y) {
        if (this.x + x < 0 || this.x + x >= map.getSize()) return false;
        return this.y + y >= 0 && this.y + y < map.getSize();
    }

    public String showDetail(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String stringShowDetail = "type : " + map.getMap()[x][y].getType();
        if (map.getMap()[x][y].getBuilding() != null)
            stringShowDetail += "\nbuilding : " + map.getMap()[x][y].getBuilding().getBuildingType().getName();
        for (Unit unit : map.getMap()[x][y].getUnits()) {
            if (unit != null) stringShowDetail += "\nUnit : " + unit.getUnitType().getName();
        }
        if (map.getMap()[x][y].getEnvironmentName() != null && !map.getMap()[x][y].getEnvironmentName().equals("rock"))
            stringShowDetail += "\nrecourse : wood";
        if (map.getMap()[x][y].getType().equals("rockTexture")) stringShowDetail += "\nrecourse : stone";
        if (map.getMap()[x][y].getType().equals("oil")) stringShowDetail += "\nrecourse : pitch";
        if (map.getMap()[x][y].getType().equals("ironTexture")) stringShowDetail += "\nrecourse : iron";
        return stringShowDetail;
    }
}
