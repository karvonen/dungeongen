package fi.ak.dungeongen.logic;

import java.util.Scanner;
import fi.ak.dungeongen.ui.Ui;

/**
 * Class has methods for starting and playing the game.
 *
 */
public class Game {

    private Level level;
    private Ui ui;
    private Scanner scanner;
    private LevelGenerator generator;
    private boolean debugMode;
    private char[][] testLevel = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '.', '.', '#', '#', '#', '.', '.', '#'},
        {'#', '.', '<', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '.', '#', '#', '#', '#', '>', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public Game(Scanner scanner, LevelGenerator generator, boolean debugMode) {
        this.generator = generator;
        this.scanner = scanner;
        this.debugMode = debugMode;
    }

    /**
     * Starts a new game.
     *
     */
    public void start() {
        if (debugMode) {
            startInTestMode();
            return;
        }
        
        char[][] map = generator.generate();
        this.level = new Level(map);
        this.ui = new Ui(level);
        ui.draw();
        System.out.println("1-9 (numpad) to move, w to win instantly:");
        run();
    }

    /**
     * Starts a new game in test mode, used for JUnit tests.
     *
     */
    public void startInTestMode() {
        this.level = new Level(testLevel);
        this.ui = new Ui(level);
    }

    /**
     * Starts the game loop.
     *
     */
    public void run() {
        while (true) {
            char command = getCommand();
            executeGameCommand(command);
            ui.draw();
        }
    }

    /**
     * Method executes a movement command that is given as a char. The chars are
     * directions as seen on normal computer keyboard's numpad.
     *
     * @param command char that is interpreted as a direction.
     */
    public void executeGameCommand(char command) {

        Location newPlayerLocation = new Location(level.getPlayer().getLocation());
        if (command == '1') {
            newPlayerLocation.setCol(newPlayerLocation.getCol() - 1);
            newPlayerLocation.setRow(newPlayerLocation.getRow() + 1);
        } else if (command == '2') {
            newPlayerLocation.setRow(newPlayerLocation.getRow() + 1);
        } else if (command == '3') {
            newPlayerLocation.setRow(newPlayerLocation.getRow() + 1);
            newPlayerLocation.setCol(newPlayerLocation.getCol() + 1);
        } else if (command == '4') {
            newPlayerLocation.setCol(newPlayerLocation.getCol() - 1);
        } else if (command == '6') {
            newPlayerLocation.setCol(newPlayerLocation.getCol() + 1);
        } else if (command == '7') {
            newPlayerLocation.setRow(newPlayerLocation.getRow() - 1);
            newPlayerLocation.setCol(newPlayerLocation.getCol() - 1);
        } else if (command == '8') {
            newPlayerLocation.setRow(newPlayerLocation.getRow() - 1);
        } else if (command == '9') {
            newPlayerLocation.setRow(newPlayerLocation.getRow() - 1);
            newPlayerLocation.setCol(newPlayerLocation.getCol() + 1);
        } else if (command == 'w') {
            newPlayerLocation.setRow(level.getStaircaseDown().getRow());
            newPlayerLocation.setCol(level.getStaircaseDown().getCol());
        }

        if (newPlayerLocation != level.getPlayer().getLocation()) {
            attemptPlayerMove(newPlayerLocation);
        }
        ui.draw();
    }

    /**
     * Method tries to move the player to a new location. If the new location is
     * a staircase, a new game is started.
     *
     * @param newPlayerLocation Location to which the move is attempted.
     *
     */
    public void attemptPlayerMove(Location newPlayerLocation) {
        if (level.isTileFreeToBeMovedOn(newPlayerLocation)) {
            level.getPlayer().movePlayer(newPlayerLocation);
            if (level.checkStaircase(newPlayerLocation)) {
                start();
            }
        }
    }

    /**
     * Method reads a command from scanner.
     *
     * @return the first character of the string read.
     */
    private char getCommand() {
        String command = scanner.nextLine();
        if (!command.isEmpty()) {
            return command.charAt(0);
        } else {
            return '5';  // 5 as in 'wait' in roguelikes
        }
    }

    /**
     * @return Currently active level.
     *
     */
    public Level getLevel() {
        return level;
    }
}
