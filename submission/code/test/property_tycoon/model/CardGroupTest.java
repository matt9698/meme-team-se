/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import property_tycoon.control.HumanController;

/**
 *
 * @author atiqu
 */
public class CardGroupTest
{
    
    public CardGroupTest()
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
        Card pot = Card.create("any", true, new FakeAction(),new FakeAction());
        Card opp = Card.create("jail", true, new FakeAction(), new FakeAction());
        
        Card.Group potOfLuck = Card.Group.create("pot of luck", pot);
        Card.Group Opportunity = Card.Group.create("opportunity knocks", opp);
        
        assertEquals("pot of luck",potOfLuck.getDescription());
        assertEquals("opportunity knocks",Opportunity.getDescription());
        
    }
    
        
    @Test
    public void testDraw()
    {
        Player player = new Player("T", Color.CORAL , new HumanController());
        Card pot = Card.create("any", true, new FakeAction(),new FakeAction());
        Card.Group potOfLuck = Card.Group.create("pot of luck", pot);
        pot = potOfLuck.draw(player);
        assertEquals(player,pot.getOwner());
    }
        
    @Test        
    public void testReplace()
    {
        Player player = new Player("T", Color.CORAL , new HumanController());
        Card pot = Card.create("any", true, new FakeAction(),new FakeAction());
        Card.Group potOfLuck = Card.Group.create("pot of luck", pot);
        pot = potOfLuck.draw(player);
        player.use(pot);
        potOfLuck.replace(pot);
        assertEquals(null,pot.getOwner());
        
    }
            
    
}
