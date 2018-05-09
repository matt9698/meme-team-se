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
public class BoardTest
{
    
    public BoardTest()
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
     * Test of moveForward method, of class Board.
     */
    @Test
    public void testMoveForward()
    {
        System.out.println("moveForward");
        Player player = null;
        BoardPosition to = null;
        Board instance = null;
        instance.moveForward(player, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveBackward method, of class Board.
     */
    @Test
    public void testMoveBackward()
    {
        System.out.println("moveBackward");
        Player player = null;
        BoardPosition to = null;
        Board instance = null;
        instance.moveBackward(player, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveSequential method, of class Board.
     */
    @Test
    public void testMoveSequential()
    {
        System.out.println("moveSequential");
        Player player = null;
        int by = 0;
        Board instance = null;
        instance.moveSequential(player, by);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveDirect method, of class Board.
     */
    @Test
    public void testMoveDirect()
    {
        System.out.println("moveDirect");
        Player player = null;
        BoardPosition to = null;
        Board instance = null;
        instance.moveDirect(player, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNext method, of class Board.
     */
    @Test
    public void testGetNext()
    {
        System.out.println("getNext");
        BoardPosition position = null;
        Board instance = null;
        BoardPosition expResult = null;
        BoardPosition result = instance.getNext(position);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrevious method, of class Board.
     */
    @Test
    public void testGetPrevious()
    {
        System.out.println("getPrevious");
        BoardPosition position = null;
        Board instance = null;
        BoardPosition expResult = null;
        BoardPosition result = instance.getPrevious(position);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndex method, of class Board.
     */
    @Test
    public void testGetIndex()
    {
        System.out.println("getIndex");
        BoardPosition position = null;
        Board instance = null;
        int expResult = 0;
        int result = instance.getIndex(position);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class Board.
     */
    @Test
    public void testGetPosition_int()
    {
        System.out.println("getPosition");
        int index = 0;
        Board instance = null;
        BoardPosition expResult = null;
        BoardPosition result = instance.getPosition(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class Board.
     */
    @Test
    public void testGetPosition_Player()
    {
        System.out.println("getPosition");
        Player of = null;
        Board instance = null;
        BoardPosition expResult = null;
        BoardPosition result = instance.getPosition(of);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositionCount method, of class Board.
     */
    @Test
    public void testGetPositionCount()
    {
        System.out.println("getPositionCount");
        Board instance = null;
        int expResult = 0;
        int result = instance.getPositionCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
