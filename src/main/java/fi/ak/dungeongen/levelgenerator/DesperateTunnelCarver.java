package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.logic.Location;

/**
 * Class provides a desperate tunnel carver if everything else fails.
 */
public class DesperateTunnelCarver {

    /**
     * Method starts carving a tunnel from location to a direction.
     *
     * @param map the level being carved on.
     * @param location starting location of the carving process
     * @param direction direction of the tunnel
     * @param length how long the tunnel is.
     */
    public void connect(char[][] map, Location location, int direction, int length) {
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
}
