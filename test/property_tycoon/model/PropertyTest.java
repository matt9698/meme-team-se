/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import property_tycoon.model.Property.Group;

/**
 *
 * @author atiqu
 */
public class PropertyTest
{

    public PropertyTest()
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
     * Test of create method, of class Property.
     */
    @Test
    public void testCreateNormal()
    {
        String description = "Robbie's Den";
        int price = 250;
        int[] rents = {20, 40, 60, 80, 100, 120};
        Property property = Property.create(description, price, rents);

        Property.Group group = Property.Group.create("Test Group", Color.RED,
            PropertyLevel.Group.REGULAR_LEVELS, 60, property);

        assertEquals(description, property.getDescription());
        assertEquals(price, property.getPrice());

        PropertyLevel.Group levels = group.getLevels();
        for(int i = 0; i < levels.getLevelCount(); i++) {
            assertEquals(rents[i], property.getRentPrice(levels.getLevel(i), 0));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateError()
    {
        String description = null;
        int price = -250;
        int[] rents = null;
        Property.create(description, price, rents);

    }

    /**
     * Test of createStation method, of class Property.
     */
    @Test
    public void testCreateStationNormal()
    {
        String description = "Matt's Rail";
        int price = 200;
        Property property = Property.createStation(description, price);
        assertEquals(description, property.getDescription());
        assertEquals(price, property.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateStationError()
    {
        String description = null;
        int price = -200;
        Property.createStation(description, price);
    }

    /**
     * Test of createUtility method, of class Property.
     */
    @Test
    public void testCreateUtility()
    {
        String description = "Ollie's Tears";
        int price = 100;
        Property property = Property.createUtility(description, price);
        assertEquals(description, property.getDescription());
        assertEquals(price, property.getPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUtilityError()
    {
        String description = null;
        int price = -200;
        Property.createUtility(description, price);
    }

    /**
     * Test of buy method, of class Property.
     */
    @Test
    public void testBuy()
    {
        
        Player buyer = new Player();       
        Property instance = Property.create("shop", 100, new int[]{1,1,1,1,1,1});
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        assertEquals(buyer, instance.getOwner());
        assertTrue(instance.isOwned());
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testBuyErrorBuyer()
    {
        Player buyer = null;       
        Property instance = Property.create("shop", 100, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        assertEquals(buyer, instance.getOwner());
        assertTrue(instance.isOwned());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuyErrorState()
    {
        Player buyer = new Player();  
        Player buyer2 = new Player();
        Property instance = Property.create("shop", 100, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        instance.buy(buyer2);
        instance.buy(buyer);
        assertEquals(buyer, instance.getOwner());
        assertTrue(instance.isOwned());
    }
    
    
    /**
     * Test of downgrade method, of class Property.
     */
    @Test
    public void testDowngrade()
    {
        Player buyer = new Player();
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        instance.upgrade();
        int cash = instance.downgrade();
        assertEquals(cash,instance.getImprovementCost());
        assertEquals(PropertyLevel.Group.REGULAR_LEVELS.getLevel(0),instance.getLevel());
        
    }
    
    /**
     * Test of getGroup method, of class Property.
     */
    @Test
    public void testGetGroup()
    {
        Property instance = Property.create("T's Gym", 500, new int[]{1,1,1,1,1,1});
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        assertTrue(instance.isGrouped());
        assertEquals(group,instance.getGroup());
    }

    /**
     * Test of getImprovementCost method, of class Property.
     */
    @Test
    public void testGetImprovementCost()
    {
//        Player buyer = new Player();
        Property instance = Property.create("shop", 40, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        //instance.buy(buyer);
        instance.getImprovementCost();
        
        
    }

    /**
     * Test of getMortgagedPrice method, of class Property.
     */
    @Test
    public void testGetMortgagedPrice()
    {
        System.out.println("getMortgagedPrice");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.getMortgagedPrice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRentPrice method, of class Property.
     */
    @Test
    public void testGetRentPrice_int()
    {
        System.out.println("getRentPrice");
        int diceValue = 0;
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.getRentPrice(diceValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRentPrice method, of class Property.
     */
    @Test
    public void testGetRentPrice_PropertyLevel_int()
    {
        System.out.println("getRentPrice");
        PropertyLevel level = null;
        int diceValue = 0;
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.getRentPrice(level, diceValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sell method, of class Property.
     */
    @Test
    public void testSell()
    {
        System.out.println("sell");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.sell();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of upgrade method, of class Property.
     */
    @Test
    public void testUpgrade()
    {
        System.out.println("upgrade");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.upgrade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroup method, of class Property.
     */
    @Test
    public void testSetGroup()
    {
        System.out.println("setGroup");
        Group group = null;
        Property instance = new PropertyImpl();
        instance.setGroup(group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLevel method, of class Property.
     */
    @Test
    public void testGetLevel()
    {
        System.out.println("getLevel");
        Property instance = new PropertyImpl();
        PropertyLevel expResult = null;
        PropertyLevel result = instance.getLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Property.
     */
    @Test
    public void testGetOwner()
    {
        System.out.println("getOwner");
        Property instance = new PropertyImpl();
        Player expResult = null;
        Player result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Property.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.getPrice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGrouped method, of class Property.
     */
    @Test
    public void testIsGrouped()
    {
        System.out.println("isGrouped");
        Property instance = new PropertyImpl();
        boolean expResult = false;
        boolean result = instance.isGrouped();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isImprovable method, of class Property.
     */
    @Test
    public void testIsImprovable()
    {
        System.out.println("isImprovable");
        Property instance = new PropertyImpl();
        boolean expResult = false;
        boolean result = instance.isImprovable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMortgaged method, of class Property.
     */
    @Test
    public void testIsMortgaged()
    {
        System.out.println("isMortgaged");
        Property instance = new PropertyImpl();
        boolean expResult = false;
        boolean result = instance.isMortgaged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOwned method, of class Property.
     */
    @Test
    public void testIsOwned()
    {
        System.out.println("isOwned");
        Property instance = new PropertyImpl();
        boolean expResult = false;
        boolean result = instance.isOwned();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class Property.
     */
    @Test
    public void testIsValid()
    {
        System.out.println("isValid");
        Property instance = new PropertyImpl();
        boolean expResult = false;
        boolean result = instance.isValid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mortgage method, of class Property.
     */
    @Test
    public void testMortgage()
    {
        System.out.println("mortgage");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.mortgage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePropertyChangeListener method, of class Property.
     */
    @Test
    public void testRemovePropertyChangeListener()
    {
        System.out.println("removePropertyChangeListener");
        PropertyChangeListener listener = null;
        Property instance = new PropertyImpl();
        instance.removePropertyChangeListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unmortgage method, of class Property.
     */
    @Test
    public void testUnmortgage()
    {
        System.out.println("unmortgage");
        Property instance = new PropertyImpl();
        int expResult = 0;
        int result = instance.unmortgage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyChangeSupport method, of class Property.
     */
    @Test
    public void testGetPropertyChangeSupport()
    {
        System.out.println("getPropertyChangeSupport");
        Property instance = new PropertyImpl();
        PropertyChangeSupport expResult = null;
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PropertyImpl extends Property
    {
        public Property buy(Player buyer)
        {
            return null;
        }

        public int downgrade()
        {
            return 0;
        }

        public String getDescription()
        {
            return "";
        }

        public Group getGroup()
        {
            return null;
        }

        public int getRentPrice(PropertyLevel level, int diceValue)
        {
            return 0;
        }

        public int sell()
        {
            return 0;
        }

        public int upgrade()
        {
            return 0;
        }

        public void setGroup(Group group)
        {
        }

        public PropertyLevel getLevel()
        {
            return null;
        }

        public Player getOwner()
        {
            return null;
        }

        public int getPrice()
        {
            return 0;
        }

        public boolean isGrouped()
        {
            return false;
        }

        public boolean isMortgaged()
        {
            return false;
        }

        public boolean isOwned()
        {
            return false;
        }

        public boolean isValid()
        {
            return false;
        }

        public int mortgage()
        {
            return 0;
        }

        public int unmortgage()
        {
            return 0;
        }
    }

}
