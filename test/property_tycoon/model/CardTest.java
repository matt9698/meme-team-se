/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import property_tycoon.model.Card.Group;

/**
 *
 * @author atiqu
 */
public class CardTest
{
    
    public CardTest()
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
     * Test of create method, of class Card.
     */
    @Test
    public void testCreate_3args()
    {
        Card action =  Card.create("jail",false,new FakeAction(),new FakeAction());
        assertEquals(action.getDescription(),"jail");
        assertEquals(action.isImmediate(), false);
        assertEquals(action.getActionCount(),2);
    }

    /**
     * Test of create method, of class Card.
     */
    @Test
    public void testCreate_CardAction_boolean()
    {
        CardAction act = new FakeAction();
        Card action =  Card.create(act, true);
        assertEquals(action.getDescription(),act.getDescription());
    }

    /**
     * Test of getActionCount method, of class Card.
     * 
     * 
     */
    
        /**
     * Gets the number of action choices associated with this card.
     * For a single action card this method returns 1.
     *
     * @return The number of action choices associated with this card.
     */
    @Test
    public void testGetActionCount()
    {
        Card action = Card.create("jail", true ,new FakeAction(),new FakeAction(),new FakeAction());
        assertEquals(action.getActionCount(),3);
        
    }

    /**
     * Test of getActionDescription method, of class Card.
     */
    @Test
    public void testGetActionDescription()
    {
        CardAction act =  new FakeAction();
        Card action =  Card.create("jail",false,new FakeAction(),act);
        Card action2 = Card.create(act, true);
        assertEquals(action.getActionDescription(1),act);
        assertEquals(action2.getActionDescription(0),act);
    }

    /**
     * Test of getDescription method, of class Card.
     */
    @Test
    public void testGetDescription()
    {
        Card action =  Card.create("jail",false,new FakeAction(),new FakeAction());
        CardAction act =  new FakeAction();
        Card action2 = Card.create(act, true);
        assertEquals(action.getDescription(),"jail");
        assertEquals(action2.getDescription(),act.getDescription());
    }

    /**
     * Test of getGroup method, of class Card.
     */
    @Test
    public void testGetGroup()
    {        
        Card action =  Card.create("jail",false,new FakeAction(),new FakeAction());
        Card action2 =  Card.create("jail",false,new FakeAction(),new FakeAction());
        Group potLuck = Group.create("potLuck", action);
        Group opp = Group.create("opportunity knocks", action2);
        assertEquals(action.getGroup(),potLuck);
        assertEquals(action2.getGroup(),opp);
    }


    /**
     * Test of isGrouped method, of class Card.
     */
    @Test
    public void testIsGrouped()
    {
        Card action =  Card.create("jail",false,new FakeAction(),new FakeAction());
        Group.create("potLuck", action);
        assertTrue(action.isGrouped());
    }

    /**
     * Test of isChoice method, of class Card.
     */
    @Test
    public void testIsChoice()
    {
        Card action =  Card.create("jail",false,new FakeAction(),new FakeAction());
        CardAction act =  new FakeAction();
        Card action2 = Card.create(act, true);
        assertTrue(action.isChoice());
        assertFalse(action2.isChoice());
    }

    /**
     * Test of isImmediate method, of class Card.
     */
    @Test
    public void testIsImmediate()
    {
        Card action =  Card.create("tax",true,new FakeAction(),new FakeAction());
        assertTrue(action.isImmediate());
    }

    /**
     * Test of isUseable method, of class Card.
     */
    @Test
    public void testIsUseable_0args()
    {
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        assertTrue(action.isUseable());
    }

    /**
     * Test of isUseable method, of class Card.
     */
    @Test
    public void testIsUseable_int()
    {
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        assertTrue(action.isUseable(1));
    }

    /**
     * Test of isValid method, of class Card.
     */
    @Test
    public void testIsValid()
    {
        Player player = new Player();
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        Group group = Group.create("potLuck", action);
        action = group.draw(player);
        assertTrue(action.isValid());
    }

    /**
     * Test of use method, of class Card.
     */
    @Test
    public void testUse_0args()
    {
        Player player = new Player();
        CardAction act = new FakeAction();
        Card action = Card.create(act, true);
        Group group = Group.create("potLuck", action);
        action = group.draw(player);
        assertTrue(action.isValid());
        action.use();
        assertFalse(action.isValid());
    }

    /**
     * Test of use method, of class Card.
     */
    @Test
    public void testUse_int()
    {
        Player player = new Player();
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        Group group = Group.create("potLuck", action);
        action = group.draw(player);
        assertTrue(action.isValid());
        action.use(1);
        assertFalse(action.isValid());
        
    }

    /**
     * Test of isOwned method, of class Card.
     */
    @Test
    public void testIsOwned()
    {
        Player player = new Player();
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        Group group = Group.create("potLuck", action);
        assertFalse(action.isOwned());
        action = group.draw(player);
        assertTrue(action.isOwned());
        action.use(1);
        assertFalse(action.isOwned());
    }

    @Test
    public void testIsOwned_0args()
    {
        Player player = new Player();
        CardAction act = new FakeAction();
        Card action = Card.create(act, true);
        Group group = Group.create("potLuck", action);
        assertFalse(action.isOwned());
        action = group.draw(player);
        assertTrue(action.isOwned());
        action.use();
        assertFalse(action.isOwned());
    }
    
    /**
     * Test of getOwner method, of class Card.
     */
    @Test
    public void testGetOwner()
    {
        Player player = new Player();
        Card action = Card.create("tax",true,new FakeAction(),new FakeAction());
        Group group = Group.create("potLuck", action);
        assertFalse(action.isOwned());
        action = group.draw(player);
        assertTrue(action.isOwned());
        assertEquals(action.getOwner(), player);
    }



    public class CardImpl extends Card
    {
        public int getActionCount()
        {
            return 0;
        }

        public String getActionDescription(int action)
        {
            return "";
        }

        public String getDescription()
        {
            return "";
        }

        public Group getGroup()
        {
            return null;
        }

        public void setGroup(Group group)
        {
        }

        public boolean isGrouped()
        {
            return false;
        }

        public boolean isImmediate()
        {
            return false;
        }

        public boolean isUseable()
        {
            return false;
        }

        public boolean isUseable(int action)
        {
            return false;
        }

        public boolean isValid()
        {
            return false;
        }

        public void use(int action)
        {
        }

        public boolean isOwned()
        {
            return false;
        }

        public Player getOwner()
        {
            return null;
        }

        public void setOwner(Player owner)
        {
        }
    }
    
}
