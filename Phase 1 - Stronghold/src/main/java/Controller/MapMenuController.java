package Controller;

import Enums.EnvironmentType;
import Model.Cell;
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
        map = GameMenuController.getMap();
    }

    public String showMap(Matcher matcher) {
        y = Integer.parseInt(matcher.group("x"));
        x = Integer.parseInt(matcher.group("y"));
        return makeOutputInStandard(getPartOfMap());
    }

    private String[][] getPartOfMap() {
        int x1 = cornersRow()[0], x2 = cornersRow()[1];
        int y1 = cornersColumn()[0], y2 = cornersColumn()[1];
        String[][] partOfMap = new String[y2 - y1][x2 - x1];
        for (int i = 0; i < x2 - x1; i++) {
            for (int j = 0; j < y2 - y1; j++)
                partOfMap[j][i] = cellForShow(map.getMap()[y1 + j][x1 + i]);
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
        } else if (cell.getEnvironmentName() != null && !cell.getEnvironmentName().equals("rock")) {
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
                            stringMakeOutputInStandard += (partOfMap[j / 6][i / 4]);
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
        String[] commands = command.split(" ");
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].matches("up[\\d]?")) {
                ;
                if (commands[i].length() > 2)
                    y += Integer.parseInt(commands[i].substring(2));
                else y++;
            } else if (commands[i].matches("down[\\d]?")) {
                if (commands[i].length() > 4)
                    y -= Integer.parseInt(commands[i].substring(4));
                else y--;
            } else if (commands[i].matches("left[\\d]?")) {
                if (commands[i].length() > 4)
                    x -= Integer.parseInt(commands[i].substring(4));
                else x--;
            } else if (commands[i].matches("right[\\d]?")) {
                if (commands[i].length() > 5)
                    x += Integer.parseInt(commands[i].substring(5));
                else x++;
            }
        }
        if (!checkMove(x, y)) return "Invalid move";
        this.x += y;
        this.y += x;
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
        if (map.getMap()[x][y].getBuilding() != null) {
            stringShowDetail += "\nbuilding : " + map.getMap()[x][y].getBuilding().getBuildingType().getName() + "(hp:" + map.getMap()[x][y].getBuilding().getHp() + ")";
            if (map.getMap()[x][y].getBuilding().getOwner() != null)
                stringShowDetail += map.getMap()[x][y].getBuilding().getOwner().getUser().getUsername();
        }
        for (Unit unit : map.getMap()[x][y].getUnits()) {
            if (unit != null) {
                stringShowDetail += "\nUnit : " + unit.getUnitType().getName() + "(hp:" + unit.getHp() + ")";
                if (unit.getOwner() != null) stringShowDetail += " owner: " + unit.getOwner().getUser().getUsername();
            }
        }
        if (map.getMap()[x][y].getEnvironmentName() != null && !map.getMap()[x][y].getEnvironmentName().equals("rock"))
            stringShowDetail += "\nresource : wood";
        if (map.getMap()[x][y].getType().equals("rockTexture")) stringShowDetail += "\nresource : stone";
        if (map.getMap()[x][y].getType().equals("oil")) stringShowDetail += "\nresource : pitch";
        if (map.getMap()[x][y].getType().equals("ironTexture")) stringShowDetail += "\nresource : iron";
        return stringShowDetail;
    }
}