package fi.ak.dungeongen.util;

import fi.ak.dungeongen.logic.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FloodFillTest {

    public FloodFillTest() {
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
    public void possibleToFill() {
        char[][] testLevel = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#', '#', '#', '.', '.', '#'},
            {'#', '.', '<', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '.', '.', '.', '#', '#', '#', '#', '>', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
        FloodFill ff = new FloodFill(testLevel, new Location(2, 2), new Location(3, 8));
        ff.start();
        assertEquals(true, ff.checkFill());
    }

    @Test
    public void unableToFill() {
        char[][] testLevel = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '#', '.', '.', '.', '.', '#'},
            {'#', '.', '<', '.', '#', '.', '.', '.', '.', '#'},
            {'#', '.', '.', '.', '#', '.', '.', '#', '>', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
        FloodFill ff = new FloodFill(testLevel, new Location(2, 2), new Location(3, 8));
        ff.start();
        assertEquals(false, ff.checkFill());
    }

}
