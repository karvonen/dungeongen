
import fi.ak.dungeongen.logic.Level;
import fi.ak.dungeongen.logic.LevelGenerator;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelGeneratorTest {

    private LevelGenerator generator;

    public LevelGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        generator = new LevelGenerator(10, 15);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void levelHasOneStaircaseDown() {
        char[][] level = generator.generate();
        int count = 0;
        for (char[] row : level) {
            if (Arrays.toString(row).contains("<")) {
                count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    public void levelHasOneStaircaseUp() {
        char[][] level = generator.generate();
        int count = 0;
        for (char[] row : level) {
            if (Arrays.toString(row).contains(">")) {
                count++;
            }
        }
        assertEquals(1, count);
    }
}
