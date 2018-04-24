/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
class PropertyProxy extends Property
{
    private boolean isValid;
    private final Property realProperty;

    public PropertyProxy(Property realProperty)
    {
        // Check arguments
        assert realProperty != null : "realProperty should not be null";
        assert realProperty.isGrouped() : "realProperty should have a group.";

        // Assign fields
        this.realProperty = realProperty;

        isValid = true;
    }

    @Override
    public Property buy(Player buyer)
    {
        throw new UnsupportedOperationException(
            "buy() is not supported by proxy properties.");
    }

    @Override
    public void downgrade()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }

        realProperty.downgrade();
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
    public Level getLevel()
    {
        return realProperty.getLevel();
    }

    @Override
    public Player getOwner()
    {
        return realProperty.getOwner();
    }

    @Override
    public int getRentCost(Level l)
    {
        return realProperty.getRentCost();
    }

    @Override
    public int getValue()
    {
        return realProperty.getValue();
    }

    @Override
    public boolean isGrouped()
    {
        return realProperty.isGrouped();
    }

    @Override
    public boolean isOwned()
    {
        return realProperty.isOwned();
    }

    @Override
    public boolean isMortgaged()
    {
        return realProperty.isMortgaged();
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
            throw new IllegalStateException();
        }

        return realProperty.mortgage();
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int unmortgage()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }

        return realProperty.unmortgage();
    }

    @Override
    public void upgrade()
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }

        realProperty.upgrade();
    }

}
