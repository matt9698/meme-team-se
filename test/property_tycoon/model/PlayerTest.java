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
        Player player = new Player("T", Color.CORAL);
        int startCash = player.getCash();
        Property instance = Property.create("shop", 20, new int[]{1,1,1,1,1,1});
        Property.Group.create("regular group", Color.CORAL,
                                property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                                10,
                                instance);
        assertNull(instance.getOwner());
        player.buy(instance);
        assertEquals(player,instance.getOwner());
        assertEquals(startCash - instance.getPrice(),player.getCash());
    }

    /**
     * Test of downgrade method, of class Player.
     */
    @Test
    public void testDowngrade()
    {
        Player player = new Player("T", Color.CORAL);
        int cash = player.getCash();
        Property instance = Property.create("shop", 20, new int[]{1,1,1,1,1,1});
        Property instance2 = Property.create("shop2", 20, new int[]{1,1,1,1,1,1});
        Property.Group.create("regular group", Color.CORAL,
                               property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                               10,
                               instance,instance2);
        player.buy(instance);
        player.upgrade(instance);
        cash = cash-instance.getPrice()-instance.getImprovementCost();
        assertEquals(cash,player.getCash());
        player.downgrade(instance);
        cash = cash+instance.getImprovementCost();
        assertEquals(cash,player.getCash());
        
    }

    /**
     * Test of draw method, of class Player.
     */
    @Test
    public void testDraw()
    {
        Player player = new Player("T", Color.CORAL);
        Card card = Card.create("tax",true,new FakeAction(),new FakeAction());
        Group group = Group.create("potLuck", card);
        player.draw(group);
        assertEquals(player,card.getOwner());
    }

    /**
     * Test of getCash method, of class Player.
     */
    @Test
    public void testGetCash()
    {
        Player player = new Player("T", Color.CORAL);
        assertEquals(1500,player.getCash());
    }

    /**
     * Test of getColor method, of class Player.
     */
    @Test
    public void testGetColor()
    {
        Player player = new Player("T", Color.CORAL);
        assertEquals(Color.CORAL,player.getColor());
    }

    /**
     * Test of getDescription method, of class Player.
     */
    @Test
    public void testGetDescription()
    {
        Player player = new Player("T", Color.CORAL);
        assertEquals("T",player.getDescription());
    }

    /**
     * Test of mortgage method, of class Player.
     */
    @Test
    public void testMortgage()
    {
        Player player = new Player("T", Color.CORAL);
        int cash = player.getCash();
        Property instance = Property.create("shop", 10, new int[]{1,1,1,1,1,1});
        Property.Group.create("regular group", Color.CORAL,
                                property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                                10,
                                instance);
        player.buy(instance);
        player.mortgage(instance);
        cash = cash-instance.getPrice()+instance.getMortgagedPrice();
        assertEquals(cash,player.getCash());
    }

    /**
     * Test of payRent method, of class Player.
     */
    @Test
    public void testPayRent()
    {
        Player player1 = new Player("T", Color.CORAL);
        int p1Cash = player1.getCash();
        Player player2 = new Player("Ollie", Color.GRAY);
        int p2Cash = player2.getCash();
        Property instance = Property.create("shop", 10, new int[]{1,2,3,4,5,6});
        Property.Group group = Property.Group.create("regular group", Color.CORAL,
                                    property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                                    10,
                                    instance);
        
        player1.buy(instance);
        p1Cash = p1Cash - instance.getPrice();
        player2.payRent(instance, 5);
        p2Cash = p2Cash - instance.getRentPrice(7);
        p1Cash = p1Cash + instance.getRentPrice(7);
        assertEquals(p1Cash,player1.getCash());
        assertEquals(p2Cash,player2.getCash());
        
        player1.upgrade(instance);
        p1Cash = p1Cash - instance.getImprovementCost();
        player2.payRent(instance,6);
        p2Cash = p2Cash - instance.getRentPrice(7);
        p1Cash = p1Cash + instance.getRentPrice(2);
        
        assertEquals(p1Cash,player1.getCash());
        assertEquals(p2Cash,player2.getCash());
        
    }

    /**
     * Test of sell method, of class Player.
     */
    @Test
    public void testSell()
    {
        Player player = new Player("T", Color.CORAL);
        Property instance = Property.create("shop", 10, new int[]{1,1,1,1,1,1});
        int p1Cash = player.getCash();
        Property.Group.create("regular group",
                                Color.BLUE,
                               property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                               20,
                               instance);
        player.buy(instance);
        p1Cash = p1Cash - instance.getPrice();
        player.sell(instance);
        p1Cash = p1Cash + instance.getPrice();
        assertEquals(p1Cash, player.getCash());
    }

    /**
     * Test of unmortgage method, of class Player.
     */
    @Test
    public void testUnmortgage()
    {
        Player player = new Player("T", Color.CORAL);
        Property instance = Property.create("shop", 10, new int[]{1,1,1,1,1,1});
        int p1Cash = player.getCash();
        Property.Group.create("regular group",
                                Color.BLUE,
                               property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                               20,
                               instance);
        player.buy(instance);
        p1Cash = p1Cash -instance.getPrice();
        player.mortgage(instance);
        p1Cash = p1Cash + instance.getMortgagedPrice();
        player.unmortgage(instance);
        p1Cash = p1Cash - instance.getMortgagedPrice();
        assertEquals(p1Cash,player.getCash());
    }

    /**
     * Test of upgrade method, of class Player.
     */
    @Test
    public void testUpgrade()
    {
        Player player = new Player("T", Color.CORAL);
        int cash = player.getCash();
        Property instance = Property.create("shop", 20, new int[]{1,1,1,1,1,1});
        Property instance2 = Property.create("shop2", 20, new int[]{1,1,1,1,1,1});
        Property.Group.create("regular group", Color.CORAL,
                               property_tycoon.model.PropertyLevel.Group.REGULAR_LEVELS, 
                               10,
                               instance,instance2);
        player.buy(instance);
        player.upgrade(instance);
        cash = cash-instance.getPrice()-instance.getImprovementCost();
        assertEquals(cash,player.getCash());
    }

    /**
     * Test of use method, of class Player.
     */
    @Test
    public void testUse_Card()
    {
        Player player = new Player("T", Color.CORAL);
        CardAction action = new FakeAction();
        Card card = Card.create(action, true);
        Group group = Group.create("potLuck", card);
        player.draw(group);
        player.use(card);
        assertFalse(card.isOwned());
        
    }

    /**
     * Test of use method, of class Player.
     */
    @Test
    public void testUse_Card_int()
    {
        Player player = new Player("T", Color.CORAL);
        Card card = Card.create("card", true, new FakeAction(), new FakeAction());
        Group group = Group.create("potLuck", card);
        player.draw(group);
        player.use(card, 1);
        assertFalse(card.isOwned());
    }

    
}
