package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.logic.Location;

/**
 * Class provides a simple tunnel carver that connects rooms to traversable
 * tiles.
 */
public class TunnelCarver {

    private static int tunnelLengthVertical;
    private static int tunnelLengthHorizontal;

    public TunnelCarver(int tunnelVertical, int tunnelHorizontal) {
        tunnelLengthHorizontal = tunnelHorizontal;
        tunnelLengthVertical = tunnelVertical;
    }

    /**
     * Method starts carving from a location next to a wall.
     *
     * @param map the level being carved on.
     * @param location location next to a wall where the carving begins.
     */
    public void startCarve(char[][] map, Location location) {
        if (map[location.getRow() - 1][location.getCol()] == '#') {
            carveUp(map, location);
        } else if (map[location.getRow()][location.getCol() + 1] == '#') {
            carveRight(map, location);
        } else if (map[location.getRow() + 1][location.getCol()] == '#') {
            carveDown(map, location);
        } else if (map[location.getRow()][location.getCol() - 1] == '#') {
            carveLeft(map, location);
        }
    }

    private void carveUp(char[][] map, Location location) {
        for (int i = 1; i < tunnelLengthVertical; i++) {
            if (location.getRow() - i < 2) {
                return;
            }
            if (map[location.getRow() - i][location.getCol()] == '.') {
                for (int j = 0; j < i; j++) {
                    map[location.getRow() - j][location.getCol()] = '.';
                }
                return;
            }
        }
    }

    private void carveRight(char[][] map, Location location) {
        for (int i = 1; i < tunnelLengthHorizontal; i++) {
            if (location.getCol() + i > map[0].length - 1) {
                return;
            }
            if (map[location.getRow()][location.getCol() + i] == '.') {
                for (int j = 0; j < i; j++) {
                    map[location.getRow()][location.getCol() + j] = '.';
                }
                return;
            }
        }
    }

    private void carveDown(char[][] map, Location location) {
        for (int i = 1; i < tunnelLengthVertical; i++) {
            if (location.getRow() + i > map.length - 1) {
                return;
            }
            if (map[location.getRow() + i][location.getCol()] == '.') {
                for (int j = 0; j < i; j++) {
                    map[location.getRow() + j][location.getCol()] = '.';
                }
                return;
            }
        }
    }

    private void carveLeft(char[][] map, Location location) {
        for (int i = 1; i < tunnelLengthHorizontal; i++) {
            if (location.getCol() - i < 2) {
                return;
            }
            if (map[location.getRow()][location.getCol() - i] == '.') {
                for (int j = 0; j < i; j++) {
                    map[location.getRow()][location.getCol() - j] = '.';
                }
                return;
            }
        }
    }

}
