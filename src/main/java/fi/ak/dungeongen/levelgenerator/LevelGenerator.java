package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.logic.Location;
import fi.ak.dungeongen.util.FloodFill;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Class generates new levels to play.
 *
 */
public class LevelGenerator {

    private char[][] map;
    private int height;
    private int width;
    private Random random;
    private ArrayList<Room> rooms;
    private Location stairsDown;
    private Location stairsUp;

    public LevelGenerator(int height, int width) {
        this.random = new Random();
        this.height = height;
        this.width = width;
        rooms = new ArrayList<>();
    }

    /**
     * Method generates a new two dimensional character array that will act as
     * the level.
     *
     * @return char[][] that is the level.
     */
    public char[][] generate() {
        map = new char[height][width];
        fillRect(new Location(0, 0), new Location(width - 1, height - 1), '#');
        placeRooms(222);
        placeStaircases();

        FloodFill floodFill = new FloodFill(map);

        carve(floodFill);
        desperateCarve(floodFill);

        map[stairsDown.getRow()][stairsDown.getCol()] = '<';
        map[stairsUp.getRow()][stairsUp.getCol()] = '>';
        return map;
    }

    /**
     * Method prints the map (used for debugging)
     *
     */
    public void print() {
        for (char[] row : map) {
            System.out.print(Arrays.toString(row));
            System.out.println("");
        }
        System.out.println("");
    }

    private void carve(FloodFill floodFill) {
        for (int i = 0; i < 600; i++) {
            floodFill.setNewMap(map, stairsDown, stairsUp);
            floodFill.floodFill();

            if (floodFill.checkFill()) {
                break;
            } else {
                Location randomLocation = floodFill.getRandomTile();
                carveToConnect(randomLocation);
            }
        }
    }

    private void desperateCarve(FloodFill floodFill) {
        while (true) {
            floodFill.setNewMap(map, stairsDown, stairsUp);
            floodFill.floodFill();

            if (floodFill.checkFill()) {
                break;
            } else {
                Location randomLocation = floodFill.getRandomTile();
                desperateConnect(randomLocation);
            }
        }
    }

    private void carveToConnect(Location location) {
        int direction = 0;
        if (map[location.getRow() - 1][location.getCol()] == '#') {
            direction = 0;
            for (int i = 0; i < 10; i++) {
                if (location.getRow() - i < 2) {
                    return;
                }
                if (map[location.getRow() - i][location.getCol()] == '.') {
                    connect(location, direction, i);
                    return;
                }
            }
        } else if (map[location.getRow()][location.getCol() + 1] == '#') {
            direction = 1;
            for (int i = 1; i < 25; i++) {
                if (location.getCol() > map[0].length - 1) {
                    return;
                }
                if (map[location.getRow() - i][location.getCol()] == '.') {
                    connect(location, direction, i);
                    return;
                }
            }
        } else if (map[location.getRow() + 1][location.getCol()] == '#') {
            direction = 2;
            for (int i = 1; i < 10; i++) {
                if (location.getRow() + i > map.length - 1) {
                    return;
                }
                if (map[location.getRow() + i][location.getCol()] == '.') {
                    connect(location, direction, i);
                    return;
                }
            }
        } else if (map[location.getRow()][location.getCol() - 1] == '#') {
            direction = 3;
            for (int i = 1; i < 25; i++) {
                if (location.getCol() - i < 2) {
                    return;
                }
                if (map[location.getRow()][location.getCol() - i] == '.') {
                    connect(location, direction, i);
                    return;
                }
            }
        }

    }

    private void desperateConnect(Location location) {
        int len = random.nextInt(15) + 2;
        int direction = random.nextInt(4);

        connect(location, direction, len);
    }

    private void connect(Location location, int direction, int length) {
        if (direction == 0 && location.getRow() - length > 0) {
            for (int i = 0; i < length; i++) {
                map[location.getRow() - i][location.getCol()] = '.';
            }
        } else if (direction == 1 && location.getCol() + length < map[0].length - 1) {
            for (int i = 0; i < length; i++) {
                map[location.getRow()][location.getCol() + i] = '.';
            }
        } else if (direction == 2 && location.getRow() + length < map.length - 1) {
            for (int i = 0; i < length; i++) {
                map[location.getRow() + i][location.getCol()] = '.';
            }
        } else if (direction == 3 && location.getCol() - length > 0) {
            for (int i = 0; i < length; i++) {
                map[location.getRow()][location.getCol() - i] = '.';
            }
        }
    }

    private void placeRooms(int number) {
        for (int i = 0; i < number; i++) {

            int roomRow = random.nextInt(height - 1) + 1;
            int roomCol = random.nextInt(width - 1) + 1;
            int roomHeight = random.nextInt(5) + 4;
            int roomWidth = random.nextInt(15) + 4;
            Room temp = new Room(new Location(roomRow, roomCol), roomHeight, roomWidth);
            if (checkBoundsForRoom(temp) && doesRoomFit(temp)) {
                rooms.add(temp);
                addRoom(temp);
            }
        }
    }

    //http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other/306332#306332
    private boolean doesRoomFit(Room room) {
        for (Room existingRoom : rooms) {
            boolean intersect = (room.getLocation().getCol() < existingRoom.getLocation().getCol() + existingRoom.getWidth()
                    && room.getLocation().getCol() + room.getWidth() > existingRoom.getLocation().getCol()
                    && room.getLocation().getRow() < existingRoom.getLocation().getRow() + existingRoom.getHeight()
                    && room.getLocation().getRow() + room.getHeight() > existingRoom.getLocation().getRow());
            if (intersect) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBoundsForRoom(Room room) {
        return room.getLocation().getRow() + room.getHeight() < map.length - 1 && room.getLocation().getCol() + room.getWidth() < map[0].length - 1;
    }

    private void fillRect(Location topLeft, Location bottomRight, char tile) {
        for (int i = topLeft.getCol(); i < bottomRight.getCol(); i++) {
            for (int j = topLeft.getRow(); j < bottomRight.getRow(); j++) {
                map[i][j] = tile;
            }
        }
    }

    private void addRoom(Room room) {
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                map[room.getLocation().getRow() + i][room.getLocation().getCol() + j] = '.';
            }
        }
    }

    private void placeStaircases() {
        while (true) {
            int randomRow = random.nextInt(height - 2) + 1;
            int randomCol = random.nextInt(width - 2) + 1;
            if (map[randomRow][randomCol] == '.') {
                stairsDown = new Location(randomRow, randomCol);
                break;
            }
        }
        while (true) {
            int randomRow = random.nextInt(height - 2) + 1;
            int randomCol = random.nextInt(width - 2) + 1;
            if (map[randomRow][randomCol] == '.' && new Location(randomRow, randomCol) != stairsDown) {
                stairsUp = new Location(randomRow, randomCol);
                break;
            }
        }
    }

    /**
     * Method resets the level generator to default state so that a new level
     * can be generated using the same instance of the object.
     *
     */
    public void reset() {
        map = new char[height][width];
        fillRect(new Location(0, 0), new Location(width - 1, height - 1), '#');
        rooms.clear();
    }
}
