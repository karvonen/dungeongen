
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
        Level test = new Level(testLevel);
        assertEquals(new Location(3, 8), test.getStaircaseDown());
    }

    @Test
    public void getTileFromLocationWall() {
        Level test = new Level(testLevel);
        assertEquals('#', test.getTileFromLocation(new Location(0, 0)));
    }

    @Test
    public void getTileFromLocationFloor() {
        Level test = new Level(testLevel);
        assertEquals('.', test.getTileFromLocation(new Location(3, 1)));
    }
}
