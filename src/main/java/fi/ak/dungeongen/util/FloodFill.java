package fi.ak.dungeongen.util;

import fi.ak.dungeongen.logic.Location;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;

public class FloodFill {

    private char[][] map;
    private ArrayDeque<Location> queue;
    private Random random;

    public FloodFill(char[][] baseMap) {
        setNewMap(baseMap);
        random = new Random();
    }

    public void setNewMap(char[][] newMap) {
        this.map = new char[newMap.length][newMap[0].length];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[0].length; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }

    public void setNewMap(char[][] newMap, Location stairsDown, Location stairsUp) {
        this.map = new char[newMap.length][newMap[0].length];
        for (int i = 0; i < newMap.length; i++) {
            for (int j = 0; j < newMap[0].length; j++) {
                map[i][j] = newMap[i][j];
            }
        }
        map[stairsDown.getRow()][stairsDown.getCol()] = '<';
        map[stairsUp.getRow()][stairsUp.getCol()] = '>';
    }

    public void start() {
        queue = new ArrayDeque<>();
        try {
            queue.addLast(getStairsDown());
        } catch (Exception ex) {
            for (char[] row : map) {
                System.out.print(Arrays.toString(row));
                System.out.println("");
            }
            System.out.println(ex);
            System.exit(3);
        }
        try {

            while (!queue.isEmpty()) {
                Location current = queue.removeFirst();

                if (map[current.getRow()][current.getCol()] != '#') {
                    map[current.getRow()][current.getCol()] = '#';
                    Location south = new Location(current.getRow(), current.getCol() + 1);
                    if (map[south.getRow()][south.getCol()] != '#') {
                        queue.addLast(south);
                    }
                    Location north = new Location(current.getRow(), current.getCol() - 1);
                    if (map[north.getRow()][north.getCol()] != '#') {
                        queue.addLast(north);
                    }
                    Location east = new Location(current.getRow() + 1, current.getCol());
                    if (map[east.getRow()][east.getCol()] != '#') {
                        queue.addLast(east);
                    }
                    Location west = new Location(current.getRow() - 1, current.getCol());
                    if (map[west.getRow()][west.getCol()] != '#') {
                        queue.addLast(west);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            for (char[] row : map) {
                System.out.print(Arrays.toString(row));
                System.out.println("");
            }
            System.exit(1);
        }
    }

    public boolean checkFill() {
        for (char[] row : map) {
            String rowString = Arrays.toString(row);
            if (rowString.contains(".") || rowString.contains("<") || rowString.contains(">")) {
                return false;
            }
        }
        return true;
    }

    public Location getRandomTile() {
        while (true) {
            int row = random.nextInt(map.length - 2) + 1;
            int col = random.nextInt(map[0].length - 2) + 1;
            if (map[row][col] == '.') {
                return new Location(row, col);
            }
        }

    }

    public boolean isTraversable(Location location) {
        if (map[location.getCol()][location.getRow()] == '.'
                || map[location.getCol()][location.getRow()] == '<'
                || map[location.getCol()][location.getRow()] == '>') {
            return true;
        }
        return false;
    }

    public Location getStairsDown() throws Exception {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '<') {
                    return new Location(i, j);
                }
            }
        }
        throw new Exception("Could not find stairs down");
    }

}
