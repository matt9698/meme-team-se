/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import property_tycoon.model.Card.Group;
import property_tycoon.model.Player.Controller;

/**
 *
 * @author atiqu
 */
public class PlayerTest
{
    
    public PlayerTest()
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
     * Test of buy method, of class Player.
     */
    @Test
    public void testBuy()
    {
        System.out.println("buy");
        Property property = null;
        Player instance = null;
        instance.buy(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of downgrade method, of class Player.
     */
    @Test
    public void testDowngrade()
    {
        System.out.println("downgrade");
        Property property = null;
        Player instance = null;
        instance.downgrade(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class Player.
     */
    @Test
    public void testDraw()
    {
        System.out.println("draw");
        Group from = null;
        Player instance = null;
        instance.draw(from);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getCash method, of class Player.
     */
    @Test
    public void testGetCash()
    {
        System.out.println("getCash");
        Player instance = null;
        int expResult = 0;
        int result = instance.getCash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColor()
    {
        System.out.println("getColor");
        Player instance = null;
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getController method, of class Player.
     */
    @Test
    public void testGetController()
    {
        System.out.println("getController");
        Player instance = null;
        Controller expResult = null;
        Controller result = instance.getController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Player.
     */
    @Test
    public void testGetDescription()
    {
        System.out.println("getDescription");
        Player instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of isControlled method, of class Player.
     */
    @Test
    public void testIsControlled()
    {
        System.out.println("isControlled");
        Player instance = null;
        boolean expResult = false;
        boolean result = instance.isControlled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mortgage method, of class Player.
     */
    @Test
    public void testMortgage()
    {
        System.out.println("mortgage");
        Property property = null;
        Player instance = null;
        instance.mortgage(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of payRent method, of class Player.
     */
    @Test
    public void testPayRent()
    {
        System.out.println("payRent");
        Property on = null;
        int diceValue = 0;
        Player instance = null;
        instance.payRent(on, diceValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sell method, of class Player.
     */
    @Test
    public void testSell()
    {
        System.out.println("sell");
        Property property = null;
        Player instance = null;
        instance.sell(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unmortgage method, of class Player.
     */
    @Test
    public void testUnmortgage()
    {
        System.out.println("unmortgage");
        Property property = null;
        Player instance = null;
        instance.unmortgage(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upgrade method, of class Player.
     */
    @Test
    public void testUpgrade()
    {
        System.out.println("upgrade");
        Property property = null;
        Player instance = null;
        instance.upgrade(property);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of use method, of class Player.
     */
    @Test
    public void testUse_Card()
    {
        System.out.println("use");
        Card card = null;
        Player instance = null;
        instance.use(card);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of use method, of class Player.
     */
    @Test
    public void testUse_Card_int()
    {
        System.out.println("use");
        Card card = null;
        int action = 0;
        Player instance = null;
        instance.use(card, action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
