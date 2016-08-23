package fi.ak.dungeongen.levelgenerator;

import fi.ak.dungeongen.datastructures.MyList;
import fi.ak.dungeongen.logic.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomGeneratorTest {

    private static Random rand;

    public RoomGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        rand = new Random();
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

    private char[][] testLevel() {
        return new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    }

    @Test
    public void carveRoom() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(3, 2), 4, 5);
        generator.carveRoom(level, test);

        char[][] correct = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '.', '.', '.', '.', '.', '#', '#', '#'},
            {'#', '#', '.', '.', '.', '.', '.', '#', '#', '#'},
            {'#', '#', '.', '.', '.', '.', '.', '#', '#', '#'},
            {'#', '#', '.', '.', '.', '.', '.', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
        assertArrayEquals(correct, level);
    }

    @Test
    public void doesRoomFit1() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(3, 2), 4, 5);
        MyList<Room> rooms = new MyList<>();
        rooms.add(test);
        generator.carveRoom(level, test);
        Room test2 = new Room(new Location(5, 4), 5, 3);
        assertEquals(false, generator.doesRoomFit(rooms, test2));
    }

    @Test
    public void doesRoomFit2() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(3, 2), 4, 5);
        MyList<Room> rooms = new MyList<>();
        rooms.add(test);
        generator.carveRoom(level, test);
        Room test2 = new Room(new Location(1, 1), 2, 2);
        assertEquals(true, generator.doesRoomFit(rooms, test2));
    }

    @Test
    public void checkBoundsTowardsRight() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(3, 8), 4, 5);
        assertEquals(false, generator.checkBoundsForRoom(test));
    }

    @Test
    public void checkBoundsTowardsDown() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(12, 2), 8, 5);
        assertEquals(false, generator.checkBoundsForRoom(test));
    }

    @Test
    public void checkBounds() {
        char[][] level = testLevel();
        RoomGenerator generator = new RoomGenerator(rand, 10, 10, level.length, level[0].length);
        Room test = new Room(new Location(4, 2), 8, 5);
        assertEquals(true, generator.checkBoundsForRoom(test));
    }
}
