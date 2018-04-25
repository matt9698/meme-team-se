/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author matth
 */
public class Player
{
    private final String description;
    private int cash;
    private final List<Property> properties;
    
    public Player(String description)
    {
        this.description = description;
        properties = new ArrayList<Property>();
        cash = 1500;
    }
    
    public void buy(Property p)
    {
        properties.add(p.buy(this));
        cash -= p.getPrice();
    }

    public int getCash()
    {
        return cash;
    }
    
    public void sell(Property p)
    {
        cash += p.sell();
        properties.remove(p);
    }
    
    public void mortgage(Property p)
    {
        cash += p.mortgage();
    }
    
    public void unmortgage(Property p)
    {
        cash -= p.unmortgage();
    }
    
    public void upgrade(Property p)
    {
        p.upgrade();
        cash -= p.getHouseCost();
    }
    
    public void downgrade(Property p)
    {
        p.downgrade();
        cash += p.getHouseCost();
    }
    
    public void use(Card c, int action)
    {
        c.use(action);
    }

    public String getDescription()
    {
        return description;
    }
    
    public List<Property> getProperties()
    {
        return Collections.unmodifiableList(properties);
    }
}
