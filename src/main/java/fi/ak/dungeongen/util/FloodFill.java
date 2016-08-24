package fi.ak.dungeongen.util;

import fi.ak.dungeongen.datastructures.MyQueue;
import fi.ak.dungeongen.logic.Location;
import java.util.Arrays;
import java.util.Random;

/**
 * Class provides a flood-fill algorithm to check if all rooms in a level
 * existing in a char[][] is fully connected.
 */
public class FloodFill {

    private char[][] map;
    private MyQueue<Location> queue;
    private final Random random;

//    public FloodFill(char[][] baseMap) {
//        setNewMap(baseMap);
//        random = new Random();
//    }

    public FloodFill(char[][] baseMap, Location stairsDown, Location stairsUp) {
        setNewMap(baseMap, stairsDown, stairsUp);
        random = new Random();
    }

//    /**
//     * Method sets the flood-fill to use a new map including stairs as
//     * locations.
//     *
//     * @param newMap new map to be flood-filled.
//     *
//     */
//    public void setNewMap(char[][] newMap) {
//        this.map = new char[newMap.length][newMap[0].length];
//        for (int i = 0; i < newMap.length; i++) {
//            for (int j = 0; j < newMap[0].length; j++) {
//                map[i][j] = newMap[i][j];
//            }
//        }
//    }

    /**
     * Method sets the flood-fill to use a new map including stairs as
     * locations.
     *
     * @param newMap new map to be flood-filled.
     * @param stairsDown location for stairs down.
     * @param stairsUp location for stairs up.
     *
     */
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

    /**
     * Method starts flood-filling a level from stairs down.
     *
     */
    public void start() {
        queue = new MyQueue<>();
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
            System.exit(1);
        }
    }

    /**
     * Method checks if flood-fill successfully traversed the whole level.
     *
     * @return true if whole level was traversed, false otherwise.
     */
    public boolean checkFill() {
        for (char[] row : map) {
            String rowString = Arrays.toString(row);
            if (rowString.contains(".") || rowString.contains("<") || rowString.contains(">")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method finds a random floor tile.
     *
     * @return location for a random floor tile.
     */
    public Location getRandomFloorTile() {
        while (true) {
            int row = random.nextInt(map.length - 2) + 1;
            int col = random.nextInt(map[0].length - 2) + 1;
            if (map[row][col] == '.') {
                return new Location(row, col);
            }
        }

    }

    /**
     * Method checks if a location has a tile that can be moved on.
     *
     * @param location location being inspected
     *
     * @return boolean true if location can be moved on, otherwise false.
     */
    public boolean isTraversable(Location location) {
        if (map[location.getCol()][location.getRow()] == '.'
                || map[location.getCol()][location.getRow()] == '<'
                || map[location.getCol()][location.getRow()] == '>') {
            return true;
        }
        return false;
    }

    /**
     * Method finds and returns location where stairs are.
     *
     * @return location of the stairs down..
     * @throws java.lang.Exception if stairs are not found.
     */
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
