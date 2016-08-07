
package fi.ak.dungeongen;

import fi.ak.dungeongen.logic.Game;
import fi.ak.dungeongen.logic.LevelGenerator;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LevelGenerator generator = new LevelGenerator(10, 25);
        Game game = new Game(scanner, generator);
        game.start();
    }
}
