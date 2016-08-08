package fi.ak.dungeongen.util;

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
        FloodFill ff = new FloodFill(testLevel);
        ff.floodFill();
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
        FloodFill ff = new FloodFill(testLevel);
        ff.floodFill();
        assertEquals(false, ff.checkFill());
    }

}
