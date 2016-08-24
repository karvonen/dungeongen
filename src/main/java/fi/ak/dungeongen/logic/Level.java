package fi.ak.dungeongen.logic;

/**
 * Class constructs and maintains a level of the game. Only one instance of this
 * class exists at one time and that is the currently being played level.
 */

public class Level {

    private Player player;
    private final char[][] map;

    public Level(char[][] map) {
        this.map = map;
        initialise();
    }

    /**
     * Initializes the level by going through the character array which
     * represents the map.
     *
     */
    private void initialise() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (map[i][j] == '<') {
                    player = new Player(new Location(i, j));
                }
            }
        }
    }

    /**
     * Method returns true if it's possible to move on to the tile which is
     * given as Location object parameter.
     *
     * @param location Location of the tile.
     *
     * @return Boolean for the tile being free to be moved on.
     */
    public boolean isTileFreeToBeMovedOn(Location location) {
        return (getTileFromLocation(location) == '.'
                || getTileFromLocation(location) == '>'
                || getTileFromLocation(location) == '<');
    }

    /**
     * Method returns the tile at the location given as parameter.
     *
     * @param location Location of the tile.
     *
     * @return tile as char.
     */
    public char getTileFromLocation(Location location) {
        return getMap()[location.getRow()][location.getCol()];
    }

    /**
     *
     * @return Character array which represents the map of the level.
     */
    public char[][] getMap() {
        return map;
    }

    /**
     *
     * @return Height of the level.
     */
    public int getHeight() {
        return map.length;
    }

    /**
     *
     * @return Width of the level.
     */
    public int getWidth() {
        return map[0].length;
    }

    /**
     *
     * @return Player object of the level.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method checks if a Location has a staircase.
     *
     * @param location Location object for the location where the check is done.
     *
     * @return boolean for staircase existance.
     */
    public boolean checkStaircase(Location location) {
        return getTileFromLocation(location) == '<' || getTileFromLocation(location) == '>';
    }

    /**
     * Method for instantly completing a level.
     *
     * @return a location object of the staircase down.
     */
    public Location getStaircaseDown() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '>') {
                    return new Location(i, j);
                }
            }
        }
        return player.getLocation();
    }
}
