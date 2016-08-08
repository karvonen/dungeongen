package fi.ak.dungeongen.util;

import fi.ak.dungeongen.logic.Location;
import java.util.ArrayDeque;
import java.util.Arrays;

public class FloodFill {

    private char[][] map;
    private ArrayDeque<Location> queue;

    public FloodFill(char[][] baseMap) {
        this.map = new char[baseMap.length][baseMap[0].length];
        for (int i = 0; i < baseMap.length; i++) {
            for (int j = 0; j < baseMap[0].length; j++) {
                map[i][j] = baseMap[i][j];
            }
        }
    }

    public void floodFill() {
        queue = new ArrayDeque<>();
        try {
            queue.addLast(getStairsDown());
        } catch (Exception ex) {
            System.exit(1);
        }

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
                if ( map[east.getRow()][east.getCol()] != '#') {
                    queue.addLast(east);
                }
                Location west = new Location(current.getRow() - 1, current.getCol());
                if (map[west.getRow()][west.getCol()] != '#') {
                    queue.addLast(west);
                }
            }
        }
    }

    public boolean checkFill() {
        boolean check = true;

        for (char[] row : map) {
            if (Arrays.toString(row).contains(".") || Arrays.toString(row).contains("<") || Arrays.toString(row).contains(">")) {
                check = false;
            }
        }

        return check;
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
