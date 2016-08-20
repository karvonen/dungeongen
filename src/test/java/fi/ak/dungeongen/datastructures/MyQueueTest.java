package fi.ak.dungeongen.datastructures;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyQueueTest {

    private static Random rand;

    public MyQueueTest() {
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

    @Test
    public void newQueueInitialization() {
        MyQueue test = new MyQueue();
        assertEquals(true, test.isEmpty());
    }

    @Test
    public void addingAndRemoving() {
        MyQueue<String> test = new MyQueue();
        test.addLast("test1");
        test.addLast("test2");
        assertEquals("test1", test.removeFirst());
        assertEquals("test2", test.removeFirst());
        assertEquals(true, test.isEmpty());
    }

    @Test
    public void isEmpty() {
        MyQueue<String> test = new MyQueue();
        test.addLast("test1");
        test.addLast("test2");
        assertEquals(false, test.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmptyQueue() {
        MyQueue test = new MyQueue();
        test.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmptyQueue2() {
        MyQueue<String> test = new MyQueue();
        test.addLast("test1");
        test.addLast("test2");
        test.removeLast();
        test.removeLast();
        test.removeLast();
    }

    @Test
    public void addLastRemoveFirstOrderIsCorrect() {
        MyQueue<Integer> test = new MyQueue();
        ArrayDeque<Integer> correct = new ArrayDeque();

        for (int i = 0; i < 2000; i++) {
            int randomInt = rand.nextInt();
            test.addLast(randomInt);
            correct.addLast(randomInt);
        }

        while (!test.isEmpty()) {
            assertEquals(correct.removeFirst(), test.removeFirst());
        }
    }

    @Test
    public void size1() {
        MyQueue<Integer> test = new MyQueue();
        test.addLast(1);
        assertEquals(1, test.size());
        test.addLast(1);
        assertEquals(2, test.size());
    }

    @Test
    public void size2() {
        MyQueue<Integer> test = new MyQueue();
        int correct = rand.nextInt(1001);

        for (int i = 0; i < correct; i++) {
            test.addLast(i);
        }
        assertEquals(correct, test.size());
    }

    @Test
    public void clear() {
        MyQueue<Integer> test = new MyQueue();
        for (int i = 0; i < 15; i++) {
            test.addLast(i);
        }
        
        test.clear();
        assertEquals(0, test.size());
    }
}
