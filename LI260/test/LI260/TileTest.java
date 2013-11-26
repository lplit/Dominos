/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LI260;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike
 */
public class TileTest {
    
    public TileTest() {
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

    /**
     * Test of inverse method, of class Tile.
     */
    @Test
    public void testInverse() {
        System.out.println("inverse");
        Tile instance = new Tile(1,2);
        instance.inverse();
        int expResult = 2;
        int result = instance.getNb1();
    }

    /**
     * Test of getNb1 method, of class Tile.
     */
    @Test
    public void testGetNb1() {
        System.out.println("getNb1");
        Tile instance = new Tile(1,2);
        int expResult = 1;
        int result = instance.getNb1();
        assertEquals(expResult, result);
        }

    /**
     * Test of getNb2 method, of class Tile.
     */
    @Test
    public void testGetNb2() {
        System.out.println("getNb2");
        Tile instance = new Tile(1,2);
        int expResult = 2;
        int result = instance.getNb2();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sum method, of class Tile.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        Tile instance = new Tile(1,2);
        int expResult = 3;
        int result = instance.sum();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDouble method, of class Tile.
     */
    @Test
    public void testIsDouble() {
        System.out.println("isDouble");
        Tile instance = new Tile(1,2);
        boolean expResult = false;
        boolean result = instance.isDouble();
        assertEquals(expResult, result);
        }

    /**
     * Test of isHigher method, of class Tile.
     */
    @Test
    public void testIsHigher() {
        System.out.println("isHigher");
        Tile t2 = new Tile (3,4);
        Tile instance = new Tile (1,2);
        boolean expResult = false;
        boolean result = instance.isHigher(t2);
        assertEquals(expResult, result);
        }

    /**
     * Test of toString method, of class Tile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Tile instance = new Tile (1,2);
        String expResult = ("[" +1 + "|" + 2 + "]");
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
}
