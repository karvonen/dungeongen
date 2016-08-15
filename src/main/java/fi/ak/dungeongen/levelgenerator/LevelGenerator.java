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
    Location stairsDown;
    Location stairsUp;
    private static final int MAX_ROOM_HEIGHT = 6; // randoms 0-6   +4 = 4-10
    private static final int MAX_ROOM_WIDTH = 15; // randoms 0-15  +4 = 4-19
    private static final int MAX_TUNNEL_LENGTH_VERTICAL = 7;
    private static final int MAX_TUNNEL_LENGTH_HORIZONTAL = 25;
    /**
     * Directions: 0 = up, 1 = right, 2 = down, 3 = left This array is used to
     * as a weighted random direction selector. As the level is rectangular,
     * digging tunnels horizontally should happen more often than digging
     * vertically.
     */
    private static final int[] directionWeights = new int[]{0, 1, 1, 2, 3, 3};

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
        placeRooms(new RoomGenerator(random, MAX_ROOM_HEIGHT, MAX_ROOM_WIDTH, height, width), 222);
        placeStaircases();

        FloodFill floodFill = new FloodFill(map, stairsDown, stairsUp);
        TunnelCarver tunnelCarver = new TunnelCarver(MAX_TUNNEL_LENGTH_VERTICAL, MAX_TUNNEL_LENGTH_HORIZONTAL);
        carve(floodFill, tunnelCarver);
        desperateCarve(floodFill, new DesperateTunnelCarver());

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

    private void carve(FloodFill floodFill, TunnelCarver tunnelCarver) {
        for (int i = 0; i < 45; i++) {
            floodFill.setNewMap(map, stairsDown, stairsUp);
            floodFill.start();
            if (floodFill.checkFill()) {
                break;
            } else {
                Location randomLocatioNextToWall = getRandomLocatioNextToWall();
                tunnelCarver.startCarve(map, randomLocatioNextToWall);
            }
        }
    }

    private void desperateCarve(FloodFill floodFill, DesperateTunnelCarver desperateTunnelCarver) {
        while (true) {
            floodFill.setNewMap(map, stairsDown, stairsUp);
            floodFill.start();
            if (floodFill.checkFill()) {
                break;
            } else {
                Location randomLocation = floodFill.getRandomFloorTile();
                int len = random.nextInt(MAX_TUNNEL_LENGTH_HORIZONTAL) + 5;
                int direction = directionWeights[random.nextInt(directionWeights.length - 1)];
                desperateTunnelCarver.connect(map, randomLocation, direction, len);
            }
        }
    }

    private Location getRandomLocatioNextToWall() {
        Room room = rooms.get(random.nextInt(rooms.size()));

        int direction = directionWeights[random.nextInt(directionWeights.length - 1)];

        int wallLength = 0;
        if (direction == 1 || direction == 3) {
            wallLength = room.getHeight();
        } else {
            wallLength = room.getWidth();
        }

        int spot = random.nextInt(wallLength - 2) + 1;

        if (direction == 0) {
            return new Location(room.getLocation().getRow(), room.getLocation().getCol() + spot);
        } else if (direction == 1) {
            return new Location(room.getLocation().getRow() + spot, room.getLocation().getCol() + room.getWidth() - 1);
        } else if (direction == 2) {
            return new Location(room.getLocation().getRow() + room.getHeight() - 1, room.getLocation().getCol() + spot);
        } else {
            return new Location(room.getLocation().getRow() + spot, room.getLocation().getCol());
        }
    }

    private void placeRooms(RoomGenerator roomGenerator, int count) {
        for (int i = 0; i < count; i++) {
            Room temp = roomGenerator.generateRoom();
            if (roomGenerator.checkBoundsForRoom(temp) && roomGenerator.doesRoomFit(rooms, temp)) {
                rooms.add(temp);
                roomGenerator.carveRoom(map, temp);
            }
        }
    }

    private void fillRect(Location topLeft, Location bottomRight, char tile) {
        for (int i = topLeft.getCol(); i < bottomRight.getCol(); i++) {
            for (int j = topLeft.getRow(); j < bottomRight.getRow(); j++) {
                map[i][j] = tile;
            }
        }
    }

    private void placeStaircases() {
        while (true) {
            int randomRow = random.nextInt(height - 2) + 1;
            int randomCol = random.nextInt(width - 2) + 1;
            if (map[randomRow][randomCol] == '.') {
                stairsUp = new Location(randomRow, randomCol);
//                System.out.println("stairs up: " + stairsUp.getRow() + " " + stairsUp.getCol());
                break;
            }
        }
        while (true) {
            int randomRow = random.nextInt(height - 2) + 1;
            int randomCol = random.nextInt(width - 2) + 1;
            if (map[randomRow][randomCol] == '.' && stairsUp.getCol() != randomCol && stairsUp.getRow() != randomRow) {
                stairsDown = new Location(randomRow, randomCol);
//                System.out.println("stairs down: " + stairsDown.getRow() + " " + stairsDown.getCol());
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
