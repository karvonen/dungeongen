package fi.ak.dungeongen.logic;

import fi.ak.dungeongen.levelgenerator.LevelGenerator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    public void start() {
        game = new Game(new Scanner(System.in), new LevelGenerator(10, 10), true);
        game.start();
    }

    @Test
    public void executeGameCommand1() {
        start();
        game.executeGameCommand('1');
        Location expectedPlayerLocation = new Location(3, 1);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand2() {
        start();
        game.executeGameCommand('2');
        Location expectedPlayerLocation = new Location(3, 2);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand3() {
        start();
        game.executeGameCommand('3');
        Location expectedPlayerLocation = new Location(3, 3);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand4() {
        start();
        game.executeGameCommand('4');
        Location expectedPlayerLocation = new Location(2, 1);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand6() {
        start();
        game.executeGameCommand('6');
        Location expectedPlayerLocation = new Location(2, 3);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand7() {
        start();
        game.executeGameCommand('7');
        Location expectedPlayerLocation = new Location(1, 1);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand8() {
        start();
        game.executeGameCommand('8');
        Location expectedPlayerLocation = new Location(1, 2);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
    }

    @Test
    public void executeGameCommand9() {
        start();
        game.executeGameCommand('9');
        Location expectedPlayerLocation = new Location(1, 3);
        assertEquals(expectedPlayerLocation, game.getLevel().getPlayer().getLocation());
}
}
