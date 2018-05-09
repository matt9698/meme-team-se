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
import property_tycoon.control.HumanController;
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
        
        Player buyer = new Player("jim", Color.CORAL , new HumanController());       
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
//        assertEquals(buyer, instance.getOwner());
//        assertNull(instance.isOwned());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testBuyErrorState()
    {
        Player buyer = new Player("jim", Color.CORAL , new HumanController());  
        Player buyer2 = new Player("bob", Color.CORAL , new HumanController());
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
        Player buyer = new Player("jim", Color.CORAL , new HumanController());
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
//        Player buyer = new Player("T", Color.CORAL);
        Property instance = Property.create("shop", 40, new int[]{1,1,1,1,1,1});
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        //instance.buy(buyer);
        int improve = instance.getImprovementCost();
        assertEquals(group.getImprovementCost(),improve);        
    }

    /**
     * Test of getMortgagedPrice method, of class Property.
     */
    @Test
    public void testGetMortgagedPrice()
    {
        Property instance = Property.create("shop", 40, new int[]{1,1,1,1,1,1});
        int mortgaged = instance.getMortgagedPrice();
        assertEquals(instance.getPrice()/2,mortgaged);
        
    }

    /**
     * Test of getRentPrice method, of class Property.
     */
    @Test
    public void testGetRentPrice_Regular()
    {
        Player buyer = new Player("jim", Color.CORAL , new HumanController());
        Property instance = Property.create("shop", 40, new int[]{1,5,10,15,20,30});
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        assertEquals(1, instance.getRentPrice(group.getLevels().getLevel(0), 0));
        instance.upgrade();
        assertEquals(5, instance.getRentPrice(group.getLevels().getLevel(1), 0));
    }
    
        /**
     * Test of getRentPrice method, of class Property.
     */
    @Test
    public void testGetRentPrice_Station()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property station1 = Property.createStation("station", 50);
        Property station2 = Property.createStation("station2", 50);
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.STATION_LEVELS,60, station1,station2);
        station1.buy(buyer);
        assertEquals(25, station1.getRentPrice(group.getLevels().getLevel(0), 0));
        station2.buy(buyer);
        assertEquals(50, station2.getRentPrice(group.getLevels().getLevel(1), 0));

    }
    
        /**
     * Test of getRentPrice method, of class Property.
     */
    @Test
    public void testGetRentPrice_Utility()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property utility1 = Property.createUtility("util 1", 30);
        Property utility2 = Property.createUtility("util 2", 30);
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.UTILITY_LEVELS,60, utility1,utility2);
        utility1.buy(buyer);
        assertEquals(24, utility1.getRentPrice(group.getLevels().getLevel(0), 6));
        utility2.buy(buyer);
        assertEquals(70, utility1.getRentPrice(group.getLevels().getLevel(1),7));
    }
    
    
    

    /**
     * Test of sell method, of class Property.
     *    
     */
    @Test
    public void testSell()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("shop", 40, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        int sellPrice = instance.sell();
        assertEquals(instance.getPrice(),sellPrice);
        assertFalse(instance.isOwned());
    }
    
    
    /**
     * Test of upgrade method, of class Property.
     */
    @Test
    public void testUpgrade()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        instance.upgrade();
        assertEquals(PropertyLevel.Group.REGULAR_LEVELS.getLevel(1),instance.getLevel());
    }

    /**
     * Test of setGroup method, of class Property.
     */
    @Test
    public void testSetGroup()
    {
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group group = Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        assertEquals(PropertyLevel.Group.REGULAR_LEVELS.getLevel(0),instance.getLevel());
        assertEquals(instance.getGroup(),group);
    }

    /**
     * Test of getLevel method, of class Property.
     */
    @Test
    public void testGetLevel()
    {
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property utility = Property.createUtility("Waters", 50);
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        Property.Group.create("utility Group", Color.BLUE,PropertyLevel.Group.UTILITY_LEVELS,60, utility);
        assertEquals(PropertyLevel.Group.REGULAR_LEVELS.getLevel(0),instance.getLevel());
        assertEquals(PropertyLevel.Group.UTILITY_LEVELS.getLevel(0),utility.getLevel());
    }

    /**
     * Test of getOwner method, of class Property.
     */
    @Test
    public void testGetOwner()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        assertEquals(instance.getOwner(),buyer);
        assertTrue(instance.isOwned());
    }

    /**
     * Test of getPrice method, of class Property.
     */
    @Test
    public void testGetPrice()
    {
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        assertEquals(instance.getPrice(),50);
        assertFalse(instance.isMortgaged());
    }

    /**
     * Test of isGrouped method, of class Property.
     */
    @Test
    public void testIsGrouped()
    {
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        Property utility = Property.createUtility("water", 50);
        Property.Group.create("util Group", Color.BLUE,PropertyLevel.Group.UTILITY_LEVELS,60, utility);
        Property station = Property.createStation("rail", 70);
        Property.Group.create("station Group", Color.RED,PropertyLevel.Group.STATION_LEVELS,60, station);
        assertTrue(instance.isGrouped());
        assertTrue(utility.isGrouped());
        assertTrue(station.isGrouped());
    }

    /**
     * Test of isImprovable method, of class Property.
     */
    @Test
    public void testIsImprovable()
    {
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        Property utility = Property.createUtility("water", 50);
        Property.Group.create("util Group", Color.BLUE,PropertyLevel.Group.UTILITY_LEVELS,60, utility);
        Property station = Property.createStation("rail", 70);
        Property.Group.create("station Group", Color.RED,PropertyLevel.Group.STATION_LEVELS,60, station);
        
        assertTrue(instance.isImprovable());
        assertFalse(utility.isImprovable());
        assertFalse(station.isImprovable());
    }

    /**
     * Test of isMortgaged method, of class Property.
     * return true if this property is currently mortgaged; false otherwise.
     */
    @Test
    public void testIsMortgaged()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        assertFalse(instance.isMortgaged());
        instance.mortgage();
        assertTrue(instance.isMortgaged());
    }

    /**
     * Test of isOwned method, of class Property.
     * return true if this property is currently owned; false otherwise.
     */
    @Test
    public void testIsOwned()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        assertFalse(instance.isOwned());
        instance.buy(buyer);
        assertTrue(instance.isOwned());
        instance.sell();
        assertFalse(instance.isOwned());
    }

    /**
     * Test of isValid method, of class Property.
     */
    @Test
    public void testIsValid()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance = instance.buy(buyer);
        assertTrue(instance.isValid());
        instance.sell();
        assertFalse(instance.isValid());
    }

    /**
     * Test of mortgage method, of class Property.
     */
    @Test
    public void testMortgage()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        instance.mortgage();
        assertEquals(instance.getMortgagedPrice(),instance.getPrice()/2);
        assertTrue(instance.isMortgaged());
    }

    /**
     * Test of unmortgage method, of class Property.
     */
    @Test
    public void testUnmortgage()
    {
        Player buyer = new Player("T", Color.CORAL , new HumanController());
        Property instance = Property.create("Adam's Hut", 50, new int[]{1,1,1,1,1,1});
        Property.Group.create("shop Group", Color.GREEN,PropertyLevel.Group.REGULAR_LEVELS,60, instance);
        instance.buy(buyer);
        instance.mortgage();
        instance.unmortgage();
        assertEquals(instance.getPrice(),50);
        assertFalse(instance.isMortgaged());
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
