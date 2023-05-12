package Controller;

import Enums.BuildingType;
import Enums.EnvironmentType;
import Enums.UnitType;
import Model.Cell;
import Model.Map;
import Model.Unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MoveController {
    Map map;

    private ArrayList<Pair<Integer, Integer>> path;
    private int[] currentCell;

    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < map.getSize() && col < map.getSize();
    }

    private boolean isUnBlocked(int[][] grid, int row, int col) {
        return grid[row][col] != 0;
    }

    private boolean isDestination(int row, int col, Pair<Integer, Integer> dest) {
        return row == dest.getObject1() && col == dest.getObject2();
    }

    private double calculateHValue(int row, int col, Pair<Integer, Integer> dest) {
        return (Math.sqrt(Math.pow((row - dest.getObject1()), 2) + Math.pow((col - dest.getObject2()), 2)));
    }

    private int capacity(Cell[][] map, int[][] grid, int row, int col) {
        if (grid[row][col] == 2)
            return map[row][col].getBuilding().getFreeCapacity();
        else return 1;
    }

    private ArrayList<Pair<Integer, Integer>> tracePath(CellInMove[][] cellDetails, Pair<Integer, Integer> dest) {
        int row = dest.getObject1();
        int col = dest.getObject2();
        ArrayList<Pair<Integer, Integer>> path = new ArrayList<>();
        while (!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)) {
            path.add(new Pair<>(row, col));
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }
        path.add(new Pair<>(row, col));
        return path;
    }

    private int[][] grid(Cell[][] map) {
        int grid[][] = new int[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!EnvironmentType.getEnvironmentTypeByName(map[i][j].getEnvironmentName()).isPassable())
                    grid[i][j] = 1;
                else if (map[i][j].getBuilding() != null) {
                    grid[i][j] = 1;
                    if (map[i][j].getBuilding().getBuildingType().getCapacity() != 0)
                        grid[i][j] = map[i][j].getBuilding().getBuildingType().getCapacity();
                } else grid[i][j] = 0;
            }
        }
        return grid;
    }

    private void checkTraps(ArrayList<Unit> units){
        for (Pair<Integer, Integer> pair : path){
            if(map.getMap()[pair.getObject1()][pair.getObject2()].getBuilding() != null &&
                    map.getMap()[pair.getObject1()][pair.getObject2()].getBuilding().getBuildingType().equals(BuildingType.KILLING_PIT)){
                for (Unit unit : units){
                    unit.getDamage(100);
                }
                map.getMap()[pair.getObject1()][pair.getObject2()].getBuilding().getOwner().getBuildings().remove(map.getMap()[pair.getObject1()][pair.getObject2()].getBuilding());
                map.getMap()[pair.getObject1()][pair.getObject2()].setBuilding(null);
            }
        }
    }

    private void initializeAfterSuccess(CellInMove[][] cellDetails, Pair<Integer, Integer> dest){
        path= tracePath(cellDetails, dest);
        currentCell= new int[path.size()];
        for (int k = 0; k < path.size(); k++) {
            currentCell[k]= 0;
        }
    }

    public String aStarSearch(Cell[][] map, Pair<Integer, Integer> src, Pair<Integer, Integer> dest, ArrayList<Unit> units) {
        int[][] grid = grid(map);
        int capacity = capacity(map, grid, dest.getObject1(), dest.getObject2());
        if (!isValid(src.getObject1(), src.getObject2())) return "Source is invalid";
        if (!isValid(dest.getObject1(), dest.getObject2())) return "Destination is invalid";
        if (!isUnBlocked(grid, src.getObject1(), src.getObject2()) || !isUnBlocked(grid, dest.getObject1(), dest.getObject2()))
            return "Source or the destination is blocked";
        if (isDestination(src.getObject1(), src.getObject2(), dest)) return "We are already at the destination";
        if (capacity <= units.size()) return "No capacity";
        for (Unit unit : units) {
            if ((unit.getUnitType().equals(UnitType.HORSE_ARCHER) || unit.getUnitType().equals(UnitType.KNIGHT)) && grid[dest.getObject1()][dest.getObject2()] == 2)
                return "Can't reach top a wall";
        }
        boolean[][] closedList = new boolean[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                closedList[i][j] = false;
            }
        }
        CellInMove[][] cellDetails = new CellInMove[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                cellDetails[i][j] = new CellInMove();
            }
        }
        int i, j;
        for (i = 0; i < 100; i++) {
            for (j = 0; j < 100; j++) {
                cellDetails[i][j].f = Integer.MAX_VALUE;
                cellDetails[i][j].g = Integer.MAX_VALUE;
                cellDetails[i][j].h = Integer.MAX_VALUE;
                cellDetails[i][j].parent_i = -1;
                cellDetails[i][j].parent_j = -1;
            }
        }

        i = src.getObject1();
        j = src.getObject2();
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parent_i = i;
        cellDetails[i][j].parent_j = j;
        Set<Pair<Double, Pair<Integer, Integer>>> openList = new HashSet<>();
        openList.add(new Pair<>(0.0, new Pair<>(i, j)));

        while (!openList.isEmpty()) {
            Pair<Double, Pair<Integer, Integer>> p = openList.stream().findFirst().get();
            openList.remove(openList.iterator().next());
            i = p.getObject2().getObject1();
            j = p.getObject2().getObject2();
            closedList[i][j] = true;
            double gNew, hNew, fNew;
            int flag = 0;
            //------------------------------------North
            if (isValid(i - 1, j)) {
                for (Unit unit : map[i][j].getUnits()) {
                    if (unit.getUnitType().equals(UnitType.LADDER_MAN) || unit.getUnitType().equals(UnitType.SIEGE_TOWER)) {
                        flag = 1;
                        break;
                    }
                }
                if ((map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") || flag == 1) && grid[i - 1][j] != 0)
                    grid[i - 1][j] = 1;
                flag = 0;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i - 1, j, dest)) {
                    cellDetails[i - 1][j].parent_i = i;
                    cellDetails[i - 1][j].parent_j = j;
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(units.size());
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-units.size());
                    initializeAfterSuccess(cellDetails, dest);
                    checkTraps(units);
                    return "Success";

                } else if (!closedList[i - 1][j] && isUnBlocked(grid, i - 1, j)) {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i - 1, j, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i - 1][j].f == Integer.MAX_VALUE || cellDetails[i - 1][j].f > fNew) {
                        openList.add(new Pair<>(fNew, new Pair<>(i - 1, j)));
                        cellDetails[i - 1][j].f = fNew;
                        cellDetails[i - 1][j].g = gNew;
                        cellDetails[i - 1][j].h = hNew;
                        cellDetails[i - 1][j].parent_i = i;
                        cellDetails[i - 1][j].parent_j = j;
                    }
                }
            }
            //------------------------------------South
            if (isValid(i + 1, j)) {
                for (Unit machine : map[i][j].getUnits()) {
                    if (machine.getUnitType().equals(UnitType.LADDER_MAN) || machine.getUnitType().equals(UnitType.SIEGE_TOWER)) {
                        flag = 1;
                        break;
                    }
                }
                if ((map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") || flag == 1) && grid[i + 1][j] != 0)
                    grid[i + 1][j] = 1;
                flag = 0;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i + 1, j, dest)) {
                    cellDetails[i + 1][j].parent_i = i;
                    cellDetails[i + 1][j].parent_j = j;
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(units.size());
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-units.size());
                    initializeAfterSuccess(cellDetails, dest);
                    checkTraps(units);
                    return "Success";
                } else if (closedList[i + 1][j] && isUnBlocked(grid, i + 1, j)) {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i + 1, j, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i + 1][j].f == Integer.MAX_VALUE || cellDetails[i + 1][j].f > fNew) {
                        openList.add(new Pair<>(fNew, new Pair<>(i + 1, j)));
                        cellDetails[i + 1][j].f = fNew;
                        cellDetails[i + 1][j].g = gNew;
                        cellDetails[i + 1][j].h = hNew;
                        cellDetails[i + 1][j].parent_i = i;
                        cellDetails[i + 1][j].parent_j = j;
                    }
                }
            }
            //------------------------------------East
            if (isValid(i, j + 1)) {
                for (Unit machine : map[i][j].getUnits()) {
                    if (machine.getUnitType().equals(UnitType.LADDER_MAN) || machine.getUnitType().equals(UnitType.SIEGE_TOWER)) {
                        flag = 1;
                        break;
                    }
                }
                if ((map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") || flag == 1) && grid[i][j + 1] != 0)
                    grid[i][j + 1] = 1;
                flag = 0;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i, j + 1, dest)) {
                    cellDetails[i][j + 1].parent_i = i;
                    cellDetails[i][j + 1].parent_j = j;
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(units.size());
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-units.size());
                    initializeAfterSuccess(cellDetails, dest);
                    checkTraps(units);
                    return "Success";
                } else if (closedList[i][j + 1] && isUnBlocked(grid, i, j + 1)) {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i, j + 1, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i][j + 1].f == Integer.MAX_VALUE || cellDetails[i][j + 1].f > fNew) {
                        openList.add(new Pair<>(fNew, new Pair<>(i, j + 1)));
                        cellDetails[i][j + 1].f = fNew;
                        cellDetails[i][j + 1].g = gNew;
                        cellDetails[i][j + 1].h = hNew;
                        cellDetails[i][j + 1].parent_i = i;
                        cellDetails[i][j + 1].parent_j = j;
                    }
                }
            }
            //------------------------------------West
            if (isValid(i, j - 1)) {
                for (Unit machine : map[i][j].getUnits()) {
                    if (machine.getUnitType().equals(UnitType.LADDER_MAN) || machine.getUnitType().equals(UnitType.SIEGE_TOWER)) {
                        flag = 1;
                        break;
                    }
                }
                if ((map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") || flag == 1) && grid[i][j - 1] != 0)
                    grid[i][j - 1] = 1;
                flag = 0;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i, j - 1, dest)) {
                    cellDetails[i][j - 1].parent_i = i;
                    cellDetails[i][j - 1].parent_j = j;
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(units.size());
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-units.size());
                    initializeAfterSuccess(cellDetails, dest);
                    checkTraps(units);
                    return "Success";
                } else if (closedList[i][j - 1] && isUnBlocked(grid, i, j - 1)) {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i, j - 1, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i][j - 1].f == Integer.MAX_VALUE || cellDetails[i][j - 1].f > fNew) {
                        openList.add(new Pair<>(fNew, new Pair<>(i, j - 1)));
                        cellDetails[i][j - 1].f = fNew;
                        cellDetails[i][j - 1].g = gNew;
                        cellDetails[i][j - 1].h = hNew;
                        cellDetails[i][j - 1].parent_i = i;
                        cellDetails[i][j - 1].parent_j = j;
                    }
                }
            }
        }
        return "Failed to find the Destination Cell";
    }

    public void moveUnits(ArrayList<Unit> units){
        for(int i= 0; i < units.size(); i++){
            if(currentCell[i] != path.size()) {
                map.getMap()[path.get(currentCell[i]).getObject1()][path.get(currentCell[i]).getObject2()].getUnits().remove(units.get(i));
                currentCell[i] += units.get(i).getUnitType().getSpeed();
                map.getMap()[path.get(currentCell[i]).getObject1()][path.get(currentCell[i]).getObject2()].getUnits().add(units.get(i));
            }
        }

    }

    public static class Pair<A, B> {
        private A object1;
        private B object2;

        public Pair(A object1, B object2) {
            this.object1 = object1;
            this.object2 = object2;
        }

        public A getObject1() {
            return object1;
        }

        public B getObject2() {
            return object2;
        }
    }

    public static class CellInMove {
        int parent_i, parent_j;
        double f, g, h;
    }
}
