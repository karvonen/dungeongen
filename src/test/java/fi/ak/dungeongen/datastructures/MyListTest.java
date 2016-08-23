/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ak.dungeongen.datastructures;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aa
 */
public class MyListTest {

    public MyListTest() {
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
    public void newListInitialization() {
        MyList<String> testList = new MyList<>();
        assertEquals(0, testList.size());
        assertEquals(true, testList.isEmpty());
    }

    @Test
    public void addOneItem() {
        MyList<String> testList = new MyList<>();
        testList.add("test");
        assertEquals(1, testList.size());
        assertEquals("test", testList.get(0));
    }

    @Test
    public void addMultipleItems() {
        MyList<String> testList = new MyList<>();
        for (int i = 0; i < 12; i++) {
            testList.add("test " + i);
        }
        for (int i = 0; i < 12; i++) {
            assertEquals("test " + i, testList.get(i));
        }

        assertEquals(12, testList.size());
    }

    @Test
    public void listSize() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            MyList<String> testList = new MyList<>();
            int goal = rand.nextInt(155);
            for (int j = 0; j < goal; j++) {
                testList.add("test");
            }
            assertEquals(goal, testList.size());
        }
    }

    @Test
    public void clear() {
        MyList<String> testList = new MyList<>();
        for (int i = 0; i < 12; i++) {
            testList.add("test " + i);
        }
        testList.clear();
        assertEquals(0, testList.size());
    }
}
