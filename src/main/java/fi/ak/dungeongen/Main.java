package fi.ak.dungeongen;

import fi.ak.dungeongen.logic.Game;
import fi.ak.dungeongen.levelgenerator.LevelGenerator;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        LevelGenerator generator = new LevelGenerator(30, 140);
//        Game game = new Game(scanner, generator, false);
//        game.start();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> ajat = new ArrayList<>();
        LevelGenerator generator = new LevelGenerator(30, 140);
        for (int i = 0; i < 100; i++) {
            char[][] test = generator.generate();
            generator.reset();
        }

    }

}
