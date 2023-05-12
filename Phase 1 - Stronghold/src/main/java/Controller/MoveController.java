package Controller;

import Enums.EnvironmentType;
import Enums.UnitType;
import Model.Cell;
import Model.Map;
import Model.Unit;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MoveController {
    Map map;

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

    private Stack tracePath(CellInMove[][] cellDetails, Pair<Integer, Integer> dest) {
        int row = dest.getObject1();
        int col = dest.getObject2();
        Stack<Pair<Integer, Integer>> path = new Stack<>();
        while (!(cellDetails[row][col].parent_i == row && cellDetails[row][col].parent_j == col)) {
            path.push(new Pair<>(row, col));
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }
        path.push(new Pair<>(row, col));
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

    public String aStarSearch(Cell[][] map, Pair<Integer, Integer> src, Pair<Integer, Integer> dest, Unit unit) {
        int[][] grid = grid(map);
        int capacity = capacity(map, grid, dest.getObject1(), dest.getObject2());
        if (!isValid(src.getObject1(), src.getObject2())) return "Source is invalid";
        if (!isValid(dest.getObject1(), dest.getObject2())) return "Destination is invalid";
        if (!isUnBlocked(grid, src.getObject1(), src.getObject2()) || !isUnBlocked(grid, dest.getObject1(), dest.getObject2()))
            return "Source or the destination is blocked";
        if (isDestination(src.getObject1(), src.getObject2(), dest)) return "We are already at the destination";
        if (capacity == 0) return "No capacity";
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
            //------------------------------------North
            if (isValid(i - 1, j)) {
                for(i =0;i<map[i][j].getUnits().size();i++){
                    if(map[i][j].getUnits().get(i).getUnitType().equals(UnitType.LADDER_MAN));
                }
                if ((map[i][j].getBuilding().getBuildingType().getName().equals("Stairs")) && grid[i - 1][j] != 0)
                    grid[i - 1][j] = 1;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i - 1, j, dest)) {
                    cellDetails[i - 1][j].parent_i = i;
                    cellDetails[i - 1][j].parent_j = j;
                    if(tracePath(cellDetails, dest).size() > unit.getUnitType().getSpeed()) return "Too slow to reach";
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(1);
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-1);
                    map[i][j].getUnits().remove(unit);
                    map[i - 1][j].getUnits().add(unit);
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
                if (map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") && grid[i + 1][j] != 0)
                    grid[i + 1][j] = 1;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i + 1, j, dest)) {
                    cellDetails[i + 1][j].parent_i = i;
                    cellDetails[i + 1][j].parent_j = j;
                    if(tracePath(cellDetails, dest).size() > unit.getUnitType().getSpeed()) return "Too slow to reach";
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(1);
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-1);
                    map[i][j].getUnits().remove(unit);
                    map[i + 1][j].getUnits().add(unit);
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
                if (map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") && grid[i][j + 1] != 0)
                    grid[i][j + 1] = 1;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i, j + 1, dest)) {
                    cellDetails[i][j + 1].parent_i = i;
                    cellDetails[i][j + 1].parent_j = j;
                    if(tracePath(cellDetails, dest).size() > unit.getUnitType().getSpeed()) return "Too slow to reach";
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(1);
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-1);
                    map[i][j].getUnits().remove(unit);
                    map[i][j + 1].getUnits().add(unit);
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
                if (map[i][j].getBuilding().getBuildingType().getName().equals("Stairs") && grid[i][j - 1] != 0)
                    grid[i][j - 1] = 1;
                if (grid[i][j] == 2 && grid[i - 1][j] == 2) grid[i - 1][j] = 1;
                if (isDestination(i, j - 1, dest)) {
                    cellDetails[i][j - 1].parent_i = i;
                    cellDetails[i][j - 1].parent_j = j;
                    if(tracePath(cellDetails, dest).size() > unit.getUnitType().getSpeed()) return "Too slow to reach";
                    if (grid[src.getObject1()][src.getObject2()] == 2)
                        map[src.getObject1()][src.getObject2()].getBuilding().addFreeCapacity(1);
                    if (grid[dest.getObject1()][dest.getObject2()] == 2)
                        map[dest.getObject1()][dest.getObject2()].getBuilding().addFreeCapacity(-1);
                    map[i][j].getUnits().remove(unit);
                    map[i][j - 1].getUnits().add(unit);
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
