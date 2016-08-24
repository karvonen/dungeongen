
package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.logic.Location;
/**
 * A room object is used to keep track of generated rooms.
 */
public class Room{

    private final Location location;
    private final int height;
    private final int width;

    public Room(Location location, int height, int width) {
        this.location = location;
        this.height = height;
        this.width = width;
}
    
    public Location getLocation() {
        return location;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
}
