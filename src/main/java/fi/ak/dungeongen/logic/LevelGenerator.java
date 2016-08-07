package fi.ak.dungeongen.logic;

import java.util.Random;

public class LevelGenerator {

    private char[][] map;
    private int height;
    private int width;
    private Random random;

    public LevelGenerator(int height, int width) {
        this.random = new Random();
        this.height = height;
        this.width = width;
    }

    /**
     * Method generates a new two dimensional character array that will
     * act as the level.
     *
     * @return char[][] that is the level.
     */
    public char[][] generate() {
        map = new char[height][width];
        fillRect(new Location(0, 0), new Location(width - 1, height - 1), '#');
        fillRect(new Location(1, 1), new Location(width - 2, height - 2), '.');
        placeStaircases();
        return map;
    }

    private void fillRect(Location topLeft, Location bottomRight, char tile) {
        for (int i = topLeft.getCol(); i <= bottomRight.getCol(); i++) {
            for (int j = topLeft.getRow(); j <= bottomRight.getRow(); j++) {
                map[i][j] = tile;
            }
        }

    }

    private void placeStaircases() {
        while (true) {
            if (map[random.nextInt(height - 2) + 1][random.nextInt(width - 2) + 1] == '.') {
                map[random.nextInt(height - 2) + 1][random.nextInt(width - 2) + 1] = '<';
                break;
            }
        }
        while (true) {
            if (map[random.nextInt(height - 2) + 1][random.nextInt(width - 2) + 1] == '.') {
                map[random.nextInt(height - 2) + 1][random.nextInt(width - 2) + 1] = '>';
                break;
            }
        }
    }

}
