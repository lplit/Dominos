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
public class PlayerTest {

    public PlayerTest() {
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
    
    Pioche p = new Pioche();
    Player playa1 = new Player("HULK SMASH", 1, 2, 2, 3, 4, 4);
    Plateau board1 = new Plateau();

    /**
     * Test of getTilesAtHand method, of class Player.
     */
    @Test
    public void testGetTilesAtHand() {
        System.out.println("getTilesAtHand");
        Player instance = playa1;
        int expResult = 3;
        int result = instance.getTilesAtHand().size();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTilesPlayed method, of class Player.
     */
    @Test
    public void testGetTilesPlayed() {
        System.out.println("getTilesPlayed");
        Player instance = playa1;
        int expResult = 0;
        int result = instance.getTilesPlayed().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = new Player("Hulk Smash 123");
        String expResult = "Hulk Smash 123";
        String result = instance.getName();
        assertEquals(expResult, result);


    }

    /**
     * Test of getScore method, of class Player.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Player instance = playa1;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHighestDouble method, of class Player.
     */
    @Test
    public void testGetHighestDouble() {
        System.out.println("getHighestDouble");
        Player instance = playa1;
        Tile t = new Tile(4, 4);
        Tile expResult = t;
        Tile result = instance.getHighestDouble();
        assertTrue((expResult.getNb1() == result.getNb1())
                && (expResult.getNb2() == result.getNb2()));

    }

    /**
     * Test of getHighestNonDouble method, of class Player.
     */
    @Test
    public void testGetHighestNonDouble() {
        System.out.println("getHighestNonDouble");
        Tile t = new Tile(2, 3);
        Player instance = playa1;
        Tile expResult = t;
        Tile result = instance.getHighestNonDouble();
        assertTrue((expResult.getNb1() == result.getNb1())
                && (expResult.getNb2() == result.getNb2()));
    }

    /**
     * Test of getStartingTile method, of class Player.
     */
    @Test
    public void testGetStartingTile() {
        System.out.println("getStartingTile");
        Player instance = playa1;
        Tile t = new Tile(4, 4);
        Tile expResult = t;
        Tile result = instance.getStartingTile();
        assertTrue((expResult.getNb1() == result.getNb1())
                && (expResult.getNb2() == result.getNb2()));
    }

    /**
     * Test of hasDouble method, of class Player.
     */
    @Test
    public void testHasDouble() {
        System.out.println("hasDouble");
        Player instance = playa1;
        boolean expResult = true;
        boolean result = instance.hasDouble();
        assertEquals(expResult, result);
    }

    /**
     * Test of canStillPLay method, of class Player.
     */
    @Test
    public void testCanStillPLay() {
        System.out.println("canStillPLay");
        Plateau p = new Plateau();
        Pioche bag = new Pioche();
        Player instance = playa1;
        int expResult = 2;
        int result = instance.canStillPLay(p, bag);
        assertEquals(expResult, result);
            }

    /**
     * Test of playTile method, of class Player.
     */
    @Test
    public void testPlayTile() {
        System.out.println("playTile");
        Tile t = new Tile(7, 7);
        Plateau p = new Plateau();
        Player instance = playa1;
        int expResult = 1;
        instance.playTile(t, p);
    
    }

    /**
     * Test of playFirstTile method, of class Player.
     */
    @Test
    public void testPlayFirstTile() {
        System.out.println("playFirstTile");
        Plateau p = new Plateau();
        Player instance = playa1;
        instance.playFirstTile(p);
        assertEquals(1, p.getSize());
    }

    /**
     * Test of playTileAuto method, of class Player.
     */
    @Test
    public void testPlayTileAuto() {
        System.out.println("playTileAuto");
        Plateau plat = board1;
        int bagsize = board1.getSize();
        Pioche bag = p;
        Player instance = playa1;
        instance.playTileAuto(plat, bag);
        // Two tiles from generated player fit the board, hence the 2 compare.
        assertEquals(2, board1.getSize());
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Player instance = playa1;
        System.out.println(playa1.toString());
               
        String expResult = "HULK SMASH [1|2] [2|3] [4|4] ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
