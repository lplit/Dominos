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
public class PiocheTest {
    
    public PiocheTest() {
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
     * Test of drawTile method, of class Pioche.
     */
    @Test
    public void testDrawTile() {
        System.out.println("drawTile");
        Pioche instance = new Pioche();
        int expResult = 27;
        instance.drawTile();
        int result = instance.getSize();
        assertEquals(expResult, result);
    
    }

    /**
     * Test of getSize method, of class Pioche.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Pioche instance = new Pioche();
        int expResult = 28;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class Pioche.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Pioche instance = new Pioche();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Pioche.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pioche instance = new Pioche();
        String expResult = "Size: " + 28 + " tiles left: " + 28;
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
}
