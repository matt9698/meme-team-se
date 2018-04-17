/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author mm933
 */
public class PropertyProxy extends Property
{
    private boolean isValid;
    private final Property realProperty;
    
    public PropertyProxy(Property realProperty)
    {
        // Check arguments
        assert realProperty != null : "realProperty should not be null";
        assert realProperty.hasGroup() : "realProperty should have a group.";
        
        // Assign fields
        this.realProperty = realProperty;
        
        isValid = true;
    }
    
    @Override
    public Property buy(Player buyer)
    {
        throw new UnsupportedOperationException("buy() is not supported by proxy properties.");
    }

    @Override
    public void downgrade()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Group getGroup()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGroup(Group g)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Level getLevel()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player getOwner()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRentCost(Level l)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValue()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasGroup()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasOwner()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMortgaged()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mortgage()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }
        
        realProperty.mortgage();
    }

    @Override
    public int sell()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }
        
        int value = realProperty.sell();
        
        isValid = false;
        
        return value;
    }

    @Override
    public Property trade(Player buyer, Player seller)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unmortgage()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void upgrade()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
