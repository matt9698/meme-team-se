/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

/**
 *
 * @author Matt
 */


class PropertyImpl extends Property
{
    private final String description;
    private Group group;
    private boolean isMortgaged;
    private Level level;
    private Player owner;
    private final int[] rent;
    private final int value;

    public PropertyImpl(String description, int value, int[] rent)
    {
        // Check arguments
        assert description != null : "description should not be null.";
        assert !description.isEmpty() : "description should not be empty.";
        assert value > 0 : "value should be positive.";

        assert rent != null : "rent should not be null.";
        assert rent.length == 6 : "rent should contain exactly 6 elements.";

        // Check rent elements are positive
        int i = 0;
        while(i < rent.length && rent[i] > 0) {
            i++;
        }

        assert i == rent.length :
            "rent should only contain positive elements.";

        // Assign fields
        this.description = description;
        this.value = value;

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.rent = Arrays.copyOf(rent, rent.length);

        group = null;
        owner = null;
        level = Level.UNIMPROVED;
        isMortgaged = false;
    }

    @Override
    public Property buy(Player buyer)
    {
        if(hasOwner()) {
            throw new IllegalStateException("Property already has an owner."
                + " It must be sold before being rebought.");
        }

        if(buyer == null) {
            throw new IllegalArgumentException("buyer should not be null.");
        }

        owner = buyer;
        return new PropertyProxy(this);
    }

    @Override
    public void downgrade()
    {
        if(getLevel().compareTo(getGroup().getHighestLevel()) < 0) {
            throw new IllegalStateException("Property cannot be downgraded."
                + " Doing so would cause improvment levels"
                + " in this group to differ by more than one.");
        }

        if(getLevel().isMin()) {
            throw new IllegalStateException("Property cannot be downgraded."
                + " It is already at the minimum improvment level.");
        }

        level = getLevel().getPrevious();
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public Group getGroup()
    {
        if(!hasGroup()) {
            throw new IllegalStateException();
        }

        return group;
    }

    @Override
    public void setGroup(Group g)
    {
        if(hasGroup()) {
            throw new IllegalStateException();
        }

        if(g == null) {
            throw new IllegalArgumentException();
        }

        group = g;
    }

    @Override
    public Level getLevel()
    {
        return level;
    }

    @Override
    public Player getOwner()
    {
        if(!hasOwner()) {
            throw new IllegalStateException("Property does not have an owner.");
        }

        return owner;
    }

    @Override
    public int getRentCost()
    {
        return rent[getLevel().getValue()];
    }

    @Override
    public int getRentCost(Level l)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValue()
    {
        return value;
    }

    @Override
    public boolean hasGroup()
    {
        return group != null;
    }

    @Override
    public boolean hasOwner()
    {
        return owner != null;
    }

    @Override
    public void upgrade()
    {
        if(getLevel().compareTo(getGroup().getLowestLevel()) > 0) {
            throw new IllegalStateException("Property cannot be upgraded."
                + " Doing so would cause improvment levels"
                + " in this group to differ by more than one.");
        }

        if(getLevel().isMax()) {
            throw new IllegalStateException("Property cannot be upgraded."
                + " It is already at the maximum improvment level.");
        }

        level = getLevel().getNext();
    }

    @Override
    public boolean isMortgaged()
    {
        return isMortgaged;
    }

    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException(
            "isValid() is not supported by real properties.");
    }

    @Override
    public void mortgage()
    {
        if(!hasOwner()) {
            throw new IllegalStateException(
                "Property has no owner so cannot be morgaged.");
        }

        if(isMortgaged()) {
            throw new IllegalStateException(
                "Property is already mortgaged.");
        }

        isMortgaged = true;
    }

    @Override
    public int sell()
    {
        if(!hasOwner()) {
            throw new IllegalStateException("Property has no owner.");
        }

        owner = null;

        if(isMortgaged()) {
            isMortgaged = false;
            return getMortgagedValue();
        }
        else {
            return getValue();
        }
    }

    @Override
    public Property trade(Player buyer, Player seller)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unmortgage()
    {
        if(isMortgaged()) {
            throw new IllegalStateException(
                "Property is not mortgaged.");
        }

        isMortgaged = false;
    }

}
