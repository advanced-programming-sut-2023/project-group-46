package Controller;

import Enums.EnvironmentType;
import Model.Cell;
import Model.Machine;
import Model.Map;
import Model.Unit;
import View.MapMenu;

import java.util.regex.Matcher;

public class MapMenuController {

    private final MapMenu mapMenu;
    private Map map;
    private int x;
    private int y;

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
                partOfMap[i][j] = cellForShow(map.getMap()[i][j]);
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
        if (cell.getUnits() != null)
            stringCellForShow += "S\n";
        if (cell.getBuilding() != null)
            stringCellForShow += "B\n";
        if (cell.getEnvironmentName() != null && !cell.getEnvironmentName().equals("rock"))
            stringCellForShow += "T";
        String reset = "\033[0m";
        stringCellForShow += reset;
        return stringCellForShow;
    }

    private String makeOutputInStandard(String[][] partOfMap) {
        String stringMakeOutputInStandard = "";
        for (int i = 0; i <= 16; i++) {
            if (i % 4 == 0) {
                if (i != 0 && i != (16)) stringMakeOutputInStandard += splitRowsForIn();
                else stringMakeOutputInStandard += splitRowsForEnd();
            } else {
                for (int j = 0; j <= 6 * 14; j++) {
                    if (j % 6 == 0)
                        stringMakeOutputInStandard += "|";
                    else {
                        int row = i / 4;
                        int column = j / 6;
                        if (i % 4 == 2 && j % 6 == 3 && !partOfMap[row][column].equals("")) {
                            stringMakeOutputInStandard += (partOfMap[row][column]);
                        } else
                            stringMakeOutputInStandard += "#";
                    }
                }
            }
            stringMakeOutputInStandard += "\n";
        }
        return stringMakeOutputInStandard;
    }

    private String splitRowsForIn() {
        int length = 25;
        String stringSplitRowsForIn = "";
        length -= 2;
        stringSplitRowsForIn += "|";
        while (length > 0) {
            stringSplitRowsForIn += "-";
            length--;
        }
        stringSplitRowsForIn += "|\n";
        return stringSplitRowsForIn;
    }

    private String splitRowsForEnd() {
        int length = 25;
        String stringSplitRowsForEnd = "";
        while (length > 0) {
            stringSplitRowsForEnd += "-";
            length--;
        }
        stringSplitRowsForEnd += "\n";
        return stringSplitRowsForEnd;
    }


    public String moveInMap(Matcher matcher) {
        int x = 0;
        int y = 0;
        while (matcher.find()) {
            String type = matcher.group("type");
            int count = Integer.parseInt(matcher.group("count"));
            if (type.equals("up")) y += count;
            else if (type.equals("down")) y -= count;
            else if (type.equals("right")) x += count;
            else if (type.equals("left")) x -= count;
        }
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
            stringShowDetail += "\nbuilding : " + map.getMap()[x][y].getBuilding().getName();
        for (Unit unit : map.getMap()[x][y].getUnits()) {
            if (unit != null) stringShowDetail += "\nUnit : " + unit.getName();
        }
        for (Machine machine : map.getMap()[x][y].getMachines()) {
            if (machine != null) stringShowDetail += "\nmachine : " + machine.getName();
        }
        if (map.getMap()[x][y].getEnvironmentName() != null && !map.getMap()[x][y].getEnvironmentName().equals("rock"))
            stringShowDetail += "\nrecourse : wood";
        if (map.getMap()[x][y].getType().equals("rockTexture")) stringShowDetail += "\nrecourse : stone";
        if (map.getMap()[x][y].getType().equals("oil")) stringShowDetail += "\nrecourse : pitch";
        if (map.getMap()[x][y].getType().equals("ironTexture")) stringShowDetail += "\nrecourse : iron";
        return stringShowDetail;
    }
}
