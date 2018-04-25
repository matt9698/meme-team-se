/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

public abstract class Property
{
    private static final int MORTGAGE_FACTOR = 2;
    
    public final int getMortgagedPrice()
    {
        return getPrice() / MORTGAGE_FACTOR;
    }
    
    public final int getHouseCost()
    {
        if(!isGrouped()) {
            throw new IllegalStateException(
                "Property has no group, house cost is defined by Group.getHouseCost().");
        }

        return getGroup().getHouseCost();
    }
    
    public final int getRentCost()
    {
        return getRentCost(getLevel());
    }
    
    public final boolean isImproved()
    {
        return !getLevel().isMin();
    }
    
    public abstract String getDescription();
    
    public abstract Property buy(Player buyer);
    
    public abstract int sell();
    
    public abstract int upgrade();
    
    public abstract int downgrade();
    
    public abstract PropertyGroup getGroup();
    
    public abstract void setGroup(PropertyGroup g);
    
    public abstract boolean isGrouped();
    
    public abstract PropertyType getType();
    
    public abstract int getPrice();
    
    public abstract Player getOwner();
    
    public abstract boolean isOwned();
    
    public abstract PropertyLevel getLevel();
    
    public abstract int mortgage();
    
    public abstract int unmortgage();
    
    public abstract boolean isMortgaged();
    
    public abstract boolean isValid();
    
    public abstract int getRentCost(PropertyLevel level);
    
    public abstract int getRentCost(PropertyLevel level, int diceValue);
    
    public abstract boolean isImprovable();
}
