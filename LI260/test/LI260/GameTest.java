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
public class GameTest {

    public GameTest() {
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
    Pioche pioche = new Pioche();
    Plateau plateau = new Plateau();
    Player p1 = new Player("hulk smash", 6, 6, 1, 2, 1, 3);
    Player p2 = new Player("Loki", 1, 2, 3, 4, 5, 6);

    /**
     * Test of whoStarts method, of class Game.
     */
    @Test
    public void testWhoStarts() {
        System.out.println("whoStarts");
        Player p1 = new Player("hulk smash", 6, 6, 1, 2, 1, 3);
        Player p2 = new Player("Loki", 1, 2, 3, 4, 5, 6);
        Game instance = new Game(pioche, plateau, p1, p2);
        Player expResult = p1;
        Player result = instance.whoStarts(p1, p2);
        assertEquals(expResult, result);
    }

    /**
     * Test of gameOver method, of class Game.
     */
    @Test
    public void testGameOver() {
        System.out.println("gameOver");
        Game instance = new Game(pioche, plateau, p1, p2);
        boolean expResult = false;
        boolean result = instance.gameOver();
        assertEquals(expResult, result);
    }
}
