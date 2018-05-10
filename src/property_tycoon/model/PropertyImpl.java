package property_tycoon.model;

import java.util.Arrays;

/**
 * @author meme-team
 * @version 30/04/2018
 */
final class PropertyImpl extends Property
{
    private static final int STATION_BASE_RENT = 25;
    private static final int UTILITY_HIGH_RENT = 10;
    private static final int UTILITY_LOW_RENT = 4;

    private final String description;
    private Group group;
    private boolean isMortgaged;
    private PropertyLevel level;
    private Player owner;
    private final int price;
    private final int[] rents;

    public PropertyImpl(String description, int price, int[] rents)
    {
        // Check arguments
        if(description == null) {
            throw new IllegalArgumentException(
                "description should not be null.");
        }
        if(description.isEmpty()) {
            throw new IllegalArgumentException(
                "description should not be empty.");
        }
        this.description = description;

        if(price < 1) {
            throw new IllegalArgumentException(
                "price should be positive.");
        }
        this.price = price;

        if(rents != null) {
            if(rents.length == 0) {
                throw new IllegalArgumentException("rents should not be empty.");
            }

            // Copy the array so that elements cannot
            // be subsequently modified by external code.
            this.rents = Arrays.copyOf(rents, rents.length);
        }
        else {
            this.rents = null;
        }

        level = null;
        isMortgaged = false;
        group = null;
        owner = null;
    }

    @Override
    public Property buy(Player buyer)
    {
        if(!isGrouped()) {
            throw new IllegalStateException("Property does not have a group.");
        }

        if(isOwned()) {
            throw new IllegalStateException("Property has an owner");
        }

        if(buyer == null) {
            throw new IllegalArgumentException("buyer should not be null.");
        }

        owner = buyer;
        getPropertyChangeSupport().firePropertyChange("owner", null, owner);
        getPropertyChangeSupport().firePropertyChange("owned", false, true);

        return new PropertyProxy(this);
    }

    @Override
    public int downgrade()
    {
        if(!isGrouped()) {
            throw new IllegalArgumentException("Property has no group.");
        }
        if(!isImprovable()) {
            throw new UnsupportedOperationException(
                "Property is not improvable.");
        }
        if(!isOwned()) {
            throw new IllegalStateException("Property has no owner.");
        }
        if(getLevel().isMin()) {
            throw new IllegalStateException("Property cannot be downgraded.\n"
                + "It is already at the minimum improvment level.");
        }
        if(getLevel().compareTo(getGroup().getHighestLevel()) < 0) {
            throw new IllegalStateException("Property cannot be downgraded.\n"
                + "Doing so would cause improvment levels"
                + " in this group to differ by more than one.");
        }

        PropertyLevel old = getLevel();
        level = old.getPrevious();
        getPropertyChangeSupport().firePropertyChange("level", old, level);

        return getImprovementCost();
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Group getGroup()
    {
        return group;
    }

    @Override
    public int getRentPrice(PropertyLevel level, int diceValue)
    {
        if(!isGrouped()) {
            throw new IllegalStateException("Property has no group.");
        }

        PropertyLevel.Group levels = getGroup().getLevels();
        if(!levels.contains(level)) {
            throw new IllegalArgumentException(
                "level is not valid for this property.");
        }

        if(levels.equals(PropertyLevel.Group.REGULAR_LEVELS)) {
            return rents[level.getIndex()];
        }
        else if(levels.equals(PropertyLevel.Group.STATION_LEVELS)) {
            return STATION_BASE_RENT * (int)Math.pow(2, level.getIndex());
        }
        else if(levels.equals(PropertyLevel.Group.UTILITY_LEVELS)) {
            switch(level.getIndex()) {
                case 0:
                    return UTILITY_LOW_RENT * diceValue;
                case 1:
                    return UTILITY_HIGH_RENT * diceValue;
            }
        }

        throw new AssertionError("levels has an invalid value.");
    }

    @Override
    public int upgrade()
    {
        if(!isGrouped()) {
            throw new IllegalArgumentException("Property has no group.");
        }
        if(!isImprovable()) {
            throw new UnsupportedOperationException(
                "Property is not improvable.");
        }
        if(!isOwned()) {
            throw new IllegalStateException("Property has no owner.");
        }
        if(getLevel().isMax()) {
            throw new IllegalStateException("Property cannot be upgraded.\n"
                + "It is already at the maximum improvment level.");
        }
        if(getLevel().compareTo(getGroup().getLowestLevel()) > 0) {
            throw new IllegalStateException("Property cannot be upgraded.\n"
                + "Doing so would cause improvment levels"
                + " in this group to differ by more than one.");
        }

        PropertyLevel old = getLevel();
        level = old.getNext();
        getPropertyChangeSupport().firePropertyChange("level", old, level);

        return getImprovementCost();
    }

    @Override
    protected void setGroup(Group group)
    {
        if(isGrouped()) {
            throw new IllegalStateException("Property already has a group.");
        }

        if(group == null) {
            throw new IllegalArgumentException("group should not be null.");
        }

        PropertyLevel.Group levels = group.getLevels();
        if(levels != PropertyLevel.Group.REGULAR_LEVELS
            && levels != PropertyLevel.Group.STATION_LEVELS
            && levels != PropertyLevel.Group.UTILITY_LEVELS) {
                throw new IllegalArgumentException(
                    "group.getLevels() should be one of:\n"
                        + "PropertyLevel.Group.REGULAR_LEVELS\n"
                        + "PropertyLevel.Group.STATION_LEVELS\n"
                        + "PropertyLevel.Group.UTILITY_LEVELS\n"
                        + "for the default property (implementation).");
        }

        if(levels == PropertyLevel.Group.REGULAR_LEVELS) {
            if(rents == null) {
                throw new IllegalArgumentException(
                    "group should not be PropertyLevel.Group.REGULAR_LEVELS"
                        + " for a default property created with no rents array.");
            }
            if(rents.length != levels.getLevelCount()) {
                throw new IllegalStateException(
                    "Property rents array does not contain the corret number of elements");
            }
        }
        this.level = levels.getMin();
        getPropertyChangeSupport().firePropertyChange("level", null, level);

        this.group = group;
        getPropertyChangeSupport().firePropertyChange("group", null, group);
    }

    @Override
    public PropertyLevel getLevel()
    {
        return level;
    }

    @Override
    public Player getOwner()
    {
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
        return isMortgaged;
    }

    @Override
    public boolean isOwned()
    {
        return owner != null;
    }

    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException(
            "Real properties do not support isValid().");
    }

    @Override
    public int mortgage()
    {
        if(!isOwned()) {
            throw new IllegalStateException("Property is not owned.\n"
                + "It must be owned to be mortgaged.");
        }

        if(isMortgaged()) {
            throw new IllegalStateException("Property is already mortgaged.");
        }

        if(isImprovable() && !getLevel().isMin()) {
            throw new IllegalStateException("Property is improved.\n"
                + "It must be at its lowest improvement level before being mortgaged.");
        }

        isMortgaged = true;
        getPropertyChangeSupport().firePropertyChange("mortgaged", false, true);
        return getPrice() - getMortgagedPrice();
    }

    @Override
    public int sell()
    {
        if(!isOwned()) {
            throw new IllegalStateException("Property is not owned.");
        }

        if(isImprovable() && !getLevel().isMin()) {
            throw new IllegalStateException("Property is improved.\n"
                + "It must be at its lowest improvement level before being sold.");
        }

        int credit = getPrice();
        if(isMortgaged()) {
            credit -= unmortgage();
        }

        Player old = owner;
        owner = null;
        getPropertyChangeSupport().firePropertyChange("owner", old, null);
        getPropertyChangeSupport().firePropertyChange("owned", true, false);

        return credit;
    }

    @Override
    public int unmortgage()
    {
        if(!isOwned()) {
            throw new IllegalStateException("Property is not owned.");
        }

        if(!isMortgaged()) {
            throw new IllegalStateException("Property is not mortgaged.");
        }

        isMortgaged = false;
        getPropertyChangeSupport().firePropertyChange("mortgaged", true, false);
        return getPrice() - getMortgagedPrice();
    }

}
