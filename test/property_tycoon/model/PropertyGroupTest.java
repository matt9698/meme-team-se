/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Collections;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import property_tycoon.model.Property;
import property_tycoon.model.PropertyLevel;
import property_tycoon.model.PropertyLevel.Group;

/**
 *
 * @author atiqu
 */
public class PropertyGroupTest
{
    
    public PropertyGroupTest()
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

    @Test
    public void testCreate()
    {
        Property regular = Property.create("regular", 10, new int[] {1,1,1,1,1,1});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                                                    10,
                                                    regular);
        Property station = Property.create("station group", 10, new int[] {1,1,1,1,1,1});
        Property.Group sat =Property.Group.create("station group", 
                                                    Color.BLACK,property_tycoon.model.PropertyLevel.Group.STATION_LEVELS,
                                                    10,
                                                    station);
        Property utility = Property.create("utility", 10, new int[] {1,1,1,1,1,1});
        Property.Group util = Property.Group.create("utility group",
                                                     Color.RED,property_tycoon.model.PropertyLevel.Group.UTILITY_LEVELS, 
                                                     10,
                                                     utility);
        
        assertEquals(reg,regular.getGroup());
        assertEquals(sat,station.getGroup());
        assertEquals(util,utility.getGroup());
        assertEquals("regular group",reg.getDescription());
        assertEquals("station group",sat.getDescription());
        assertEquals("utility group",util.getDescription());
        assertEquals(Color.CORAL,reg.getColor());
        assertEquals(Color.BLACK,sat.getColor());
        assertEquals(Color.RED, util.getColor());
        
    }
        
    @Test
    public void testGetColour()
    {
        Property regular = Property.create("regular", 10, new int[] {1,1,1,1,1,1});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular);
        assertEquals(Color.CORAL,reg.getColor());
    }
        
    @Test
    public void testGetDescription()
    {
        Property regular = Property.create("regular", 10, new int[] {1,1,1,1,1,1});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular);
        assertEquals("regular group",reg.getDescription());
    }
            
    @Test
    public void testGetHighestLevel()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        assertEquals(reg.getHighestLevel(),regular.getLevel());
    }
            
    @Test
    public void testGetLowestLevel()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        assertEquals(reg.getLowestLevel(),regular2.getLevel());
    }
        
    @Test
    public void testGetImprovementCost()
    {
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    20,
                                                    regular,
                                                    regular2);
        assertEquals(20,reg.getImprovementCost());
        
    }
        
    @Test
    public void testIsImprovable()
    {
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property utility = Property.createUtility("utility", 20);
        
        Property.Group reg = Property.Group.create("regular group",
                               Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                               20,
                               regular,
                               regular2);
        Property.Group util = Property.Group.create("util group",
                                                    Color.RED,
                                                    Group.UTILITY_LEVELS,
                                                    0,
                                                    utility);
        assertTrue(reg.isImprovable());
        assertFalse(util.isImprovable());
    }
     
    @Test
    public void testGetLevels()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        regular2.upgrade();
        regular.upgrade();
        
        assertEquals(PropertyLevel.Group.REGULAR_LEVELS, reg.getLevels());
        
    }
        
    @Test
    public void testGetOwner()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        
        assertEquals(buyer,reg.getOwner());
    }
        
    @Test
    public void testGetProperties()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular2", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        assertTrue(reg.getProperties().contains(regular));
        assertTrue(reg.getProperties().contains(regular2));
        
    }
        
    @Test
    public void testIsOwned()
    {
        Player buyer = new Player(); 
        Property regular = Property.create("regular", 10, new int[] {1,2,3,4,5,6});
        Property regular2 = Property.create("regular2", 10, new int[] {1,2,3,4,5,6});
        Property.Group reg = Property.Group.create("regular group",
                                                    Color.CORAL,property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS,
                                                    10,
                                                    regular,
                                                    regular2);
        regular.buy(buyer);
        regular2.buy(buyer);
        
        assertTrue(reg.isOwned());
    }
    
}
