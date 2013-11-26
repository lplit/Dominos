/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LI260;

import java.util.ArrayList;
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
public class PlateauTest {
    
    public PlateauTest() {
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
     * Test of addRight method, of class Plateau.
     */
    @Test
    public void testAddRight() {
        System.out.println("addRight");
        Tile t = new Tile(1,3);
        Plateau instance = new Plateau();
        instance.addRight(t);
        assertEquals(instance.getRight(), t.getNb2());
    }

    /**
     * Test of addLeft method, of class Plateau.
     */
    @Test
    public void testAddLeft() {
        System.out.println("addLeft");
       Tile t = new Tile(1,3);
        Plateau instance = new Plateau();
        instance.addLeft(t);
        assertEquals(instance.getLeft(), t.getNb1());
    }

    /**
     * Test of tileFits method, of class Plateau.
     */
    @Test
    public void testTileFits() {
        System.out.println("tileFits");
        Tile t = new Tile(1,2);
        Plateau instance = new Plateau();
        boolean expResult = false;
        boolean result = instance.tileFits(t);
        assertEquals(expResult, result);
            }

    /**
     * Test of playTile method, of class Plateau.
     */
    @Test
    public void testPlayTile() {
        System.out.println("playTile");
        Tile t = new Tile(1,2);
        Plateau instance = new Plateau();
        instance.playTile(t);
        assertEquals(instance.getLeft(), 0);
    }

    /**
     * Test of placeFirstTile method, of class Plateau.
     */
    @Test
    public void testPlaceFirstTile() {
        System.out.println("placeFirstTile");
        Tile t = new Tile(1,5);
        Plateau instance = new Plateau();
        instance.placeFirstTile(t);
        assertEquals( instance.getLeft(), 5);
    }

    /**
     * Test of getLeft method, of class Plateau.
     */
    @Test
    public void testGetLeft() {
        System.out.println("getLeft");
        Plateau instance = new Plateau();
        int expResult = 0;
        int result = instance.getLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRight method, of class Plateau.
     */
    @Test
    public void testGetRight() {
        System.out.println("getRight");
        Plateau instance = new Plateau();
        int expResult = 0;
        int result = instance.getRight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTiles method, of class Plateau.
     */
    @Test
    public void testGetTiles() {
        System.out.println("getTiles");
        Plateau instance = new Plateau();
        ArrayList result = instance.getTiles();
        assertNotNull(result);
        
    }

    /**
     * Test of toString method, of class Plateau.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Plateau instance = new Plateau();
        Tile t = new Tile(1,1);
        instance.placeFirstTile(t);
        System.out.println(instance.toString());
        String expResult = "The left edge is: 1, the right edge is: 1 there are now 1 tiles on the board: \n[1|1]";
        String result = instance.toString();
        assertNotNull(result);
            }
}
