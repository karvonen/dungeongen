package fi.ak.dungeongen.logic;

import fi.ak.dungeongen.logic.Level;
import fi.ak.dungeongen.logic.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelTest {

    private char[][] testLevel = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '<', '#', '.', '#', '#', '.', '.', '#'},
        {'#', '.', '.', '#', '.', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '.', '.', '#', '#', '#', '>', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
    private Level test = new Level(testLevel);

    public LevelTest() {
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

    @Test
    public void staircaseDown() {
        assertEquals(new Location(3, 8), test.getStaircaseDown());
    }

    @Test
    public void getTileFromLocationWall() {
        assertEquals('#', test.getTileFromLocation(new Location(0, 0)));
    }

    @Test
    public void getTileFromLocationFloor() {
        assertEquals('.', test.getTileFromLocation(new Location(3, 1)));
    }

    @Test
    public void stairsCanBeMovedOn() {
        assertEquals(true, test.isTileFreeToBeMovedOn(new Location(1, 2)));
        assertEquals(true, test.isTileFreeToBeMovedOn(new Location(3, 8)));
    }

    @Test
    public void wallsBlockMovement() {
        assertEquals(false, test.isTileFreeToBeMovedOn(new Location(0, 1)));
    }

}
