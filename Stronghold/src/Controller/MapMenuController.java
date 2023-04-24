package Controller;

import Model.*;
import View.MapMenu;

import java.util.ArrayList;
import java.util.Scanner;
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

    private ArrayList<ArrayList<String>> getPartOfMap(){
        ArrayList<ArrayList<String>> partOfMap = new ArrayList<>();
        int x1 = cornersRow()[0], x2 = cornersRow()[1];
        int y1 = cornersColumn()[0], y2 = cornersColumn()[1];
        for (int i = x1; i < x2; i++) {
            partOfMap.add(new ArrayList<>());
            for (int j = y1; j < y2; j++)
                partOfMap.get(i).add(cellForShow(map.getMap()[i][j]));
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
        if (cell.getUnits() != null)
            return "S";
        if (cell.getBuilding() != null)
            return "B";
        if (cell.getEnvironmentName() != null && !cell.getEnvironmentName().equals("rock"))
            return "T";
        return "";
    }

    private String makeOutputInStandard(ArrayList<ArrayList<String>> partOfMap){
        String stringMakeOutputInStandard = new String();
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
                        if (i % 4 == 2 && j % 6 == 3 && !partOfMap.get(row).get(column).equals("")) {
                            stringMakeOutputInStandard += (partOfMap.get(row).get(column));
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
        int length= 25;
        String stringSplitRowsForIn = new String();
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
        int length= 25;
        String stringSplitRowsForEnd = new String();
        while (length > 0) {
            stringSplitRowsForEnd += "-";
            length--;
        }
        stringSplitRowsForEnd += "\n";
        return stringSplitRowsForEnd;
    }


    public String moveInMap(Matcher matcher) {
        int x= 0;
        int y= 0;
        while (matcher.find()) {
            String type = matcher.group("type");
            int count = Integer.parseInt(matcher.group("count"));
            if(type.equals("up")) y += count;
            else if(type.equals("down")) y -= count;
            else if(type.equals("right")) x += count;
            else if(type.equals("left")) x -= count;
        }
        return "Success";
    }

    public String showDetail(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String stringShowDetail = "type : " + map.getMap()[x][y].getType();
        if (map.getMap()[x][y].getBuilding() != null)
            stringShowDetail += "building : " + map.getMap()[x][y].getBuilding().getName();
        for (Unit unit : map.getMap()[x][y].getUnits()) {
            if (unit != null) stringShowDetail += "Unit : " + unit.getName();
        }
        for (Machine machine : map.getMap()[x][y].getMachines()) {
            if (machine != null) stringShowDetail += "machine : " + machine.getName();
        }
        //TODO resource
        return stringShowDetail;
    }
}
