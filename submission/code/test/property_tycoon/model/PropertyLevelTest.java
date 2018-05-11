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
import property_tycoon.model.PropertyLevel.Group;

/**
 *
 * @author atiqu
 */
public class PropertyLevelTest
{
    
    public PropertyLevelTest()
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
     * Test of compareTo method, of class PropertyLevel.
     */
    @Test
    public void testCompareTo()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group.create(true, level1, level2);
        assertTrue(level1.compareTo(level2) < 0);
        assertTrue(level1.compareTo(level1) == 0);
        assertTrue(level2.compareTo(level1) > 0);
    }

    /**
     * Test of getDescription method, of class PropertyLevel.
     */
    @Test
    public void testGetDescription()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel.Group.create(true, level1);
        assertEquals(level1.getDescription(), "One House");
    }

    /**
     * Test of getGroup method, of class PropertyLevel.
     */
    @Test
    public void testGetGroup()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        Group group = PropertyLevel.Group.create(true, level1, level2);
        assertEquals(level1.getGroup(),group);
    }

    /**
     * Test of getIndex method, of class PropertyLevel.
     */
    @Test
    public void testGetIndex()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel level3 = new PropertyLevel("Three Houses");
        Group group = PropertyLevel.Group.create(true, level1, level2,level3);
        int level2Index = group.getIndex(level2);
        assertEquals(group.getIndex(level2),level2Index);
        
    }

    /**
     * Test of isGrouped method, of class PropertyLevel.
     */
    @Test
    public void testIsGrouped()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel level3 = new PropertyLevel("Three Houses");
        PropertyLevel.Group.create(true, level1, level2,level3);
        assertTrue(level2.isGrouped());
    }

    /**
     * Test of isMax method, of class PropertyLevel.
     */
    @Test
    public void testIsMax()
    {
        PropertyLevel level1 = new PropertyLevel("a");
        PropertyLevel level5 = new PropertyLevel("F");
        PropertyLevel.Group.create(true, level1, level5);
        assertFalse(level1.isMax());
        assertTrue(level5.isMax());
    }

    /**
     * Test of isMin method, of class PropertyLevel.
     */
    @Test
    public void testIsMin()
    {
        PropertyLevel level1 = new PropertyLevel("a");
        PropertyLevel level5 = new PropertyLevel("F");
        PropertyLevel.Group.create(true, level1, level5);
        assertFalse(level5.isMin());
        assertTrue(level1.isMin());
    }

    /**
     * Test of getNext method, of class PropertyLevel.
     */
    @Test
    public void testGetNext()
    {
        PropertyLevel level1 = new PropertyLevel("a");
        PropertyLevel level5 = new PropertyLevel("F");
        Group group = PropertyLevel.Group.create(true, level1, level5);
        PropertyLevel nextIn = group.getNext(level1);
        assertEquals(level5,nextIn);
    }

    /**
     * Test of getPrevious method, of class PropertyLevel.
     */
    @Test
    public void testGetPrevious()
    {
        PropertyLevel level1 = new PropertyLevel("a");
        PropertyLevel level5 = new PropertyLevel("F");
        Group group = PropertyLevel.Group.create(true, level1, level5);
        PropertyLevel before = group.getPrevious(level5);
        assertEquals(level1,before);
    }
    
}
