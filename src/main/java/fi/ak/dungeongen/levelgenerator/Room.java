
package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.logic.Location;

public class Room{

    private Location location;
    private int height;
    private int width;
    private boolean connected;

    public Room(Location location, int height, int width) {
        this.location = location;
        this.height = height;
        this.width = width;
        this.connected = false;
}

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
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
