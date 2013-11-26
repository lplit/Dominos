/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LI260;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Mike
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({LI260.TestyMain.class, LI260.TileTest.class, LI260.PiocheTest.class, LI260.PlayerTest.class, LI260.PlateauTest.class})
public class LI260Suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
