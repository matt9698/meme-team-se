package property_tycoon.model;

/**
 * Represents a property position.
 * This class instantiates a board position object specifically with a property position.
 * This class is also a child of the Property class, hence the extension.
 * This object must be constructed using a 'real' property.
 * 
 * @author meme-team
 * @version 02/05/2018
 */
public final class PropertyPosition extends Property implements BoardPosition
{
    private final Property realProperty;

    public PropertyPosition(Property realProperty)
    {
        assert realProperty != null : "realProperty should not be null";
        assert realProperty.isGrouped() : "realProperty should have a group.";
        this.realProperty = realProperty;

        // Forward events from realProperty to listeners
        realProperty.addPropertyChangeListener(
            e -> this.getPropertyChangeSupport().firePropertyChange(e));
    }

    @Override
    public Property buy(Player buyer)
    {
        return realProperty.buy(buyer);
    }

    @Override
    public int downgrade()
    {
        throw new UnsupportedOperationException(
            "downgrade() is not supported by property positions.");
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) {
            return true;
        }
        if(o == null) {
            return false;
        }
        if(!(o instanceof Property)) {
            return false;
        }

        Property position = (Property)o;
        return position.equals(realProperty);
    }

    @Override
    public String getDescription()
    {
        return realProperty.getDescription();
    }

    @Override
    public Group getGroup()
    {
        return realProperty.getGroup();
    }

    @Override
    public void setGroup(Group g)
    {
        throw new UnsupportedOperationException(
            "setGroup() is not supported by property positions.");
    }

    @Override
    public PropertyLevel getLevel()
    {
        return realProperty.getLevel();
    }

    @Override
    public Player getOwner()
    {
        return realProperty.getOwner();
    }

    @Override
    public int getPrice()
    {
        return realProperty.getPrice();
    }

    public Property getProperty()
    {
        return realProperty;
    }

    @Override
    public int getRentPrice(PropertyLevel level, int diceValue)
    {
        return realProperty.getRentPrice(level, diceValue);
    }

    @Override
    public int hashCode()
    {
        return realProperty.hashCode();
    }

    @Override
    public boolean isGrouped()
    {
        return realProperty.isGrouped();
    }

    @Override
    public boolean isMortgaged()
    {
        return realProperty.isMortgaged();
    }

    @Override
    public boolean isOwned()
    {
        return realProperty.isOwned();
    }

    @Override
    public boolean isValid()
    {
        return true;
    }

    @Override
    public void land(Player player)
    {
        if(isOwned() && !getOwner().equals(this.getOwner())) {
            player.payRent(this, 0); // TODO: get dice value somehow
        }

        // TODO: Deal with unowned properties
    }

    @Override
    public int mortgage()
    {
        throw new UnsupportedOperationException(
            "mortgage() is not supported by property positions.");
    }

    @Override
    public int sell()
    {
        throw new UnsupportedOperationException(
            "sell() is not supported by property positions.");
    }

    @Override
    public void step(Player player)
    {
        // TODO: Implement
    }

    @Override
    public int unmortgage()
    {
        throw new UnsupportedOperationException(
            "unmortgage() is not supported by property positions.");
    }

    @Override
    public int upgrade()
    {
        throw new UnsupportedOperationException(
            "upgrade() is not supported by property positions.");
    }
}
