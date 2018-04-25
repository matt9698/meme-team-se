package property_tycoon.model;

/**
 *
 * @author mm933
 */


public abstract class PropertyImpl extends Property
{
    
    private String description;
    private int price;
    private PropertyGroup group;
    private PropertyLevel level;
    private boolean mortgaged;
    private Player owner;
    
    public PropertyImpl(String description, int price, PropertyLevel level){
        
        
        assert description != null : "description should not be null.";
        assert !description.isEmpty() : "description should not be empty.";
        assert price > 0 : "price should be positive.";
        assert level != null : "level should not be null.";
        this.description = description;
        this.price = price;
        this.level = level;
        mortgaged = false;
    }
    
    @Override
    public String getDescription()
    {
        return description;
    }
    
    @Override
    public PropertyGroup getGroup()
    {
        if (!isGrouped()){
            throw new IllegalStateException("Property has no group");
        }
        
        return group;
    }
    
    @Override
    public PropertyLevel getLevel()
    {
        return level;
    }
    
    @Override
    public Player getOwner()
    {
        if (!isOwned()){
            throw new IllegalStateException("Property has no owner");
        }
        
        return owner;
    }
    
    @Override
    public int getPrice()
    {
        return price;
    }
    
    @Override 
    public boolean isGrouped()
    {
        return group != null;
    }
    
    @Override 
    public boolean isMortgaged()
    {
        return mortgaged;
    }
    
    @Override
    public boolean isOwned()
    {
        return owner != null;
    }
    
    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @Override
    public int mortgage()
    {
        mortgaged = true;
        return getMortgagedPrice();
    }
    
    @Override
    public int unmortgage()
    {
        mortgaged = false;
        return getPrice() - getMortgagedPrice();
    }
    
    @Override
    public Property buy(Player buyer)
    {
        this.owner = buyer;
        return this;
    }
    
    @Override
    public int sell()
    {
        owner = null;
        return getPrice();
    }
    
    @Override
    public void setGroup(PropertyGroup group)
    {
        this.group = group;
    }
}
