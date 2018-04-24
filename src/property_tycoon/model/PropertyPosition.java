 /*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
class PropertyPosition extends Property implements Board.Position
{
    private final Property realProperty;

    public PropertyPosition(Property realProperty)
    {
        // Check arguments
        assert realProperty != null : "realProperty should not be null";
        assert realProperty.isGrouped() : "realProperty should have a group.";

        // Assign fields
        this.realProperty = realProperty;
    }

    @Override
    public Property buy(Player buyer)
    {
        return realProperty.buy(buyer);
    }

    @Override
    public void downgrade()
    {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int mortgage()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int sell()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Property trade(Player buyer, Player seller)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int unmortgage()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void upgrade()
    {
        throw new UnsupportedOperationException();
    }

}
