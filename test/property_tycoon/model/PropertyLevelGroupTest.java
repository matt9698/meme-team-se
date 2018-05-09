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
public class PropertyLevelGroupTest
{
    
    public PropertyLevelGroupTest()
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
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertTrue(group.isImprovable());
    }
    
    @Test    
    public void testContains()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertTrue(group.contains(level2));
    }
        
    @Test    
    public void testGetIndex()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        int level2Index = group.getIndex(level2);
        assertEquals(1,level2Index);
    }
    
    @Test    
    public void testGetLevel()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertEquals(level2,group.getLevel(1));
    }
    
    @Test
    public void testGetLevelCount()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertEquals(group.getLevelCount(),2);
    }
    
    @Test
    public void testGetMax()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertEquals(group.getMax(),level2);
    }
    
    @Test
    public void testGetMin()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);  
        assertEquals(group.getMin(),level1);
    }
    
    @Test
    public void testGetNext()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        PropertyLevel nextLvl = group.getNext(level1);
        assertEquals(level2,nextLvl);
    }
    
    @Test
    public void testGetPrevious()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        PropertyLevel prevLvl = group.getPrevious(level2);
        assertEquals(group.getPrevious(level2),prevLvl);
    }
    
    @Test
    public void testIsImprovable()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertTrue(group.isImprovable());
    }
    
    @Test
    public void testIsMax()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertTrue(group.isMax(level2));
    }
    
    @Test
    public void testIsMin()
    {
        PropertyLevel level1 = new PropertyLevel("One House");
        PropertyLevel level2 = new PropertyLevel("Two Houses");
        PropertyLevel.Group group = PropertyLevel.Group.create(true, level1, level2);
        assertTrue(group.isMin(level1));
    }
    
}
