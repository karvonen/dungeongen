package fi.ak.dungeongen.levelgenerator;


import fi.ak.dungeongen.util.FloodFill;
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
        generator = new LevelGenerator(30, 125);
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
    
    @Test
    public void allRoomsAreConnected() {
        
        FloodFill ff = new FloodFill(generator.generate(), generator.stairsDown, generator.stairsUp);
        
        for (int i = 0; i < 200; i++) {
            generator.reset();
            char[][] level = generator.generate();
//            System.out.println("new");
            ff.setNewMap(level, generator.stairsDown, generator.stairsUp);
            ff.start();
            assertEquals(true, ff.checkFill());
        }
    }
    
//    @Test
//    public void levelIsSurroundedWithWalls() {
//        char[][] level = generator.generate();
//        int count = 0;
//        for (int i = 0; i < level.length; i++) {
//            if (level[i][0] == '#') count++;
//            if (level[i][level[0].length - 1] == '#') count++;
//        }
//        
//        for (int i = 0; i < level[0].length; i++) {
//            if (level[0][i] == '#') count++;
//            if (level[level.length - 1][i] == '#') count++;
//            
//        }
//        
//        int correct = level[0].length * 2 + level.length * 2;
//        assertEquals(correct, count);
//        
//    }
}
