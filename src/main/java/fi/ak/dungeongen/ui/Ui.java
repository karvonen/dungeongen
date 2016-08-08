package fi.ak.dungeongen.ui;

import fi.ak.dungeongen.logic.*;

public class Ui {

    private Level level;

    public Ui(Level level) {
        this.level = level;
    }

    /**
     * Method prints out the board and player.
     * 
     */
    public void draw() {
        for (int i = 0; i < level.getHeight(); i++) {
            for (int j = 0; j < level.getWidth(); j++) {
                if (level.getPlayer().getCol() == j && level.getPlayer().getRow() == i) {
                    System.out.print("@");
                } else {
                    System.out.print(level.getTileFromLocation(new Location(i, j)));
                }
            }
            System.out.println("");
        }
    }
}
