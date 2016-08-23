package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.datastructures.MyList;
import fi.ak.dungeongen.logic.Location;
import java.util.Random;

/**
 * Class generates rooms to be fitted on a level of char[][]. Each room's
 * coordinates and dimensions are checked to see if it fits.
 */
public class RoomGenerator {

    private final Random random;
    private final int maxRoomHeight;
    private final int maxRoomWidth;
    private final int levelHeight;
    private final int levelWidth;

    public RoomGenerator(Random random, int maxRoomHeight, int maxRoomWidth, int levelHeight, int levelWidth) {
        this.random = random;
        this.maxRoomHeight = maxRoomHeight;
        this.maxRoomWidth = maxRoomWidth;
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
    }

    /**
     * Method generates a new random room with location and size.
     *
     * @return a room.
     */
    public Room generateRoom() {
        int roomRow = random.nextInt(levelHeight - 1) + 1;
        int roomCol = random.nextInt(levelWidth - 1) + 1;
        int roomHeight = random.nextInt(maxRoomHeight) + 4;
        int roomWidth = random.nextInt(maxRoomWidth) + 4;
        return new Room(new Location(roomRow, roomCol), roomHeight, roomWidth);
    }

    /**
     * Method checks that if a room fits without overlapping with other rooms.
     * http://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other/306332#306332
     *
     * @param rooms all the rooms in a list.
     * @param room room to be checked.
     *
     * @return boolean true if room fits, false otherwise.
     */
    public boolean doesRoomFit(MyList<Room> rooms, Room room) {
        for (int i = 0; i < rooms.size(); i++) {
            Room existingRoom = rooms.get(i);
            boolean overlap = (room.getLocation().getCol() < existingRoom.getLocation().getCol() + existingRoom.getWidth()
                    && room.getLocation().getCol() + room.getWidth() > existingRoom.getLocation().getCol()
                    && room.getLocation().getRow() < existingRoom.getLocation().getRow() + existingRoom.getHeight()
                    && room.getLocation().getRow() + room.getHeight() > existingRoom.getLocation().getRow());
            if (overlap) {
                return false;
            }
        }
        return true;

//        for (Room existingRoom : rooms) {
//            boolean overlap = (room.getLocation().getCol() < existingRoom.getLocation().getCol() + existingRoom.getWidth()
//                    && room.getLocation().getCol() + room.getWidth() > existingRoom.getLocation().getCol()
//                    && room.getLocation().getRow() < existingRoom.getLocation().getRow() + existingRoom.getHeight()
//                    && room.getLocation().getRow() + room.getHeight() > existingRoom.getLocation().getRow());
//            if (overlap) {
//                return false;
//            }
//        }
//        return true;
    }

    /**
     * Method checks that a room to be generated is within bounds of the level.
     *
     * @param room room to be checked.
     *
     * @return boolean true if room fits, false otherwise.
     */
    public boolean checkBoundsForRoom(Room room) {
        return room.getLocation().getRow() + room.getHeight() < levelHeight - 1 && room.getLocation().getCol() + room.getWidth() < levelWidth - 1;
    }

    /**
     * Method carves a room in to the level.
     *
     * @param map the level on which the room is carved to.
     * @param room the room that is going to be carved.
     */
    public void carveRoom(char[][] map, Room room) {
        for (int i = 0; i < room.getHeight(); i++) {
            for (int j = 0; j < room.getWidth(); j++) {
                map[room.getLocation().getRow() + i][room.getLocation().getCol() + j] = '.';
            }
        }
    }
}
