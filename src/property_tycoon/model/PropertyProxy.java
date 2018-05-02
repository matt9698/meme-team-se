package property_tycoon.model;

/**
 * @author Matt
 * @version 25/04/2018
 */
final class PropertyProxy extends Property
{
    private boolean isValid;
    private final Property realProperty;

    public PropertyProxy(Property realProperty)
    {
        assert realProperty != null : "realProperty should not be null";
        assert realProperty.isGrouped() : "realProperty should have a group.";
        this.realProperty = realProperty;

        isValid = true;

        // Forward events from realProperty to listeners
        realProperty.addPropertyChangeListener(
            e -> this.getPropertyChangeSupport().firePropertyChange(e));
    }

    @Override
    public Property buy(Player buyer)
    {
        throw new UnsupportedOperationException(
            "buy() is not supported by proxy properties.");
    }

    @Override
    public int downgrade()
    {
        if(!isValid()) {
            throw new IllegalStateException("Proxy property is not valid.");
        }

        return realProperty.downgrade();
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
            "setGroup() is not supported by proxy properties.");
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
        return isValid;
    }

    @Override
    public int mortgage()
    {
        if(!isValid()) {
            throw new IllegalStateException("Proxy property is not valid.");
        }

        return realProperty.mortgage();
    }

    @Override
    public int sell()
    {
        if(!isValid()) {
            throw new IllegalStateException("Proxy property is not valid.");
        }

        isValid = false;
        return realProperty.sell();
    }

    @Override
    public int unmortgage()
    {
        if(!isValid()) {
            throw new IllegalStateException("Proxy property is not valid.");
        }

        return realProperty.unmortgage();
    }

    @Override
    public int upgrade()
    {
        if(!isValid()) {
            throw new IllegalStateException("Proxy property is not valid.");
        }

        return realProperty.upgrade();
    }
}
