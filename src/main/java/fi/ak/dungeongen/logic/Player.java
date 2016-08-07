package fi.ak.dungeongen.logic;

/**
 * Class provides functionality for a Player object. Only one instance of this
 * class exists at one time and it is tied to the Level object. Player object is
 * drawn on top of level.
 */
public class Player {

    private Location location;

    /**
     *
     * @param location Starting location.
     */
    public Player(Location location) {
        this.location = location;
    }

    /**
     *
     * @return Row where the player is located.
     */
    public int getRow() {
        return location.getRow();
    }

    /**
     *
     * @return Column where the player is located.
     */
    public int getCol() {
        return location.getCol();
    }

    /**
     * Sets the player location to the Location object coordinates given as the
     * parameter.
     *
     * @param location Sets player location to parameter.
     */
    public void movePlayer(Location location) {
        this.location = location;
    }

    /**
     *
     * @return Location object where the player is located on.
     */
    public Location getLocation() {
        return location;
    }
}
