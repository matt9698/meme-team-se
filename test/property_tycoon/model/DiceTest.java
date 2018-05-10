/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author atiqu
 */
public class DiceTest
{
    
    public DiceTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of rollDice method, of class Dice.
     */
    @Test
    public void testRollDice()
    {
        
        Dice dice = new Dice();
        
        
        System.out.println("rollDice");
        int[] expResult = null;
        int[] result = Dice.rollDice();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
