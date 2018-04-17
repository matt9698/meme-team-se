/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
public abstract class Property
{
    private static final int MORTGAGE_FACTOR = 2;

    public abstract String getDescription();

    public abstract boolean hasGroup();

    public abstract Group getGroup();

    public abstract void setGroup(Group g);

    public abstract boolean hasOwner();

    public abstract Player getOwner();

    public abstract int getValue();

    public int getMortgageValue()
    {
        return getValue() / MORTGAGE_FACTOR;
    }

    public abstract int getRentCost();

    public abstract Level getLevel();

    public boolean isImproved()
    {
        return getLevel() != Level.UNIMPROVED;
    }

    public abstract boolean isMortgaged();

    public abstract boolean isValid();

    public abstract void buy();

    public abstract void sell();

    public abstract void improve(Level to);

    public abstract void trade(Player with);

    public int getImproveCost(Level to)
    {
        if(!hasGroup()) {
            throw new IllegalStateException();
        }

        return getGroup().getHouseCost()
            * (to.getValue() - getLevel().getValue());
    }

    public abstract void mortgage();

    public abstract void unmortgage();

    public static enum Level
    {
        UNIMPROVED(0),
        ONE_HOUSE(1),
        TWO_HOUSES(2),
        THREE_HOUSES(3),
        FOUR_HOUSES(4),
        ONE_HOTEL(5)

        private Level(int value)
        {
            this.value = value;
        }

        private int value;

        public int getValue()
        {
            return value;
        }
    }

    public static class Group
    {
        private String name;
        private Property properties[];
        private int houseCost;

        public String getDescription()
        {
            return name;
        }

        public boolean hasOwner()
        {
            if(!properties[0].hasOwner()) {
                return false;
            }

            Player expectedOwner = properties[0].getOwner();
            int i = 1;
            while(i < properties.length
                && properties[i].hasOwner()
                && properties[i].getOwner() == expectedOwner) {
                i++;
            }

            return i == properties.length;
        }

        public Player getOwner()
        {
            if(!hasOwner()) {
                throw new IllegalStateException();
            }

            return properties[0].getOwner();
        }

        public Level getLevel()
        {
            Level min = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().getValue() < min.getValue()) {
                    min = properties[i].getLevel();
                }
            }

            return min;
        }

        public int getHouseCost()
        {
            return houseCost;
        }
    }
}
