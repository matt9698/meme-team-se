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

    public abstract Property buy(Player buyer);

    public abstract void downgrade();

    public abstract String getDescription();

    public abstract Group getGroup();

    public abstract void setGroup(Group g);

    public int getImprovementCost(Level to)
    {
        if(!hasGroup()) {
            throw new IllegalStateException("Property has no group.");
        }

        return getHouseCost()
            * (to.getValue() - getLevel().getValue());
    }

    public abstract Level getLevel();

    public int getMortgagedValue()
    {
        return getValue() / MORTGAGE_FACTOR;
    }

    public abstract Player getOwner();

    public int getRentCost()
    {
        return getRentCost(getLevel());
    }
    
    public abstract int getRentCost(Level l);
    
    public int getHouseCost()
    {
        if(!hasGroup()) {
            throw new IllegalStateException("Property has no group.");
        }
        
        return getGroup().getHouseCost();
    }

    public abstract int getValue();

    public abstract boolean hasGroup();

    public abstract boolean hasOwner();

    public boolean isImproved()
    {
        return getLevel() != Level.UNIMPROVED;
    }

    public abstract boolean isMortgaged();

    public abstract boolean isValid();

    public abstract void mortgage();

    public abstract int sell();

    public abstract Property trade(Player buyer, Player seller);

    public abstract void unmortgage();

    public abstract void upgrade();

    public static class Group
    {
        private int houseCost;
        private String name;
        private Property properties[];

        public String getDescription()
        {
            return name;
        }

        public Level getHighestLevel()
        {
            Level max = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().getValue() > max.getValue()) {
                    max = properties[i].getLevel();
                }
            }

            return max;
        }

        public int getHouseCost()
        {
            return houseCost;
        }

        public Level getLowestLevel()
        {
            Level min = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().getValue() < min.getValue()) {
                    min = properties[i].getLevel();
                }
            }

            return min;
        }

        public Player getOwner()
        {
            if(!hasOwner()) {
                throw new IllegalStateException();
            }

            return properties[0].getOwner();
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
    }

    public static class Level implements Comparable<Level>
    {
        public static final Level FOUR_HOUSES = new Level();
        public static final Level ONE_HOTEL = new Level();
        public static final Level ONE_HOUSE = new Level();
        public static final Level THREE_HOUSES = new Level();
        public static final Level TWO_HOUSES = new Level();
        public static final Level UNIMPROVED = new Level();

        private static final Level[] LEVELS = {
            UNIMPROVED,
            ONE_HOUSE,
            TWO_HOUSES,
            THREE_HOUSES,
            FOUR_HOUSES,
            ONE_HOTEL
        };

        private static int indexOf(Level level)
        {
            int i = 0;
            while(LEVELS[i] != level) {
                i++;
            }

            return i;
        }

        private Level()
        {
        }

        @Override
        public int compareTo(Level other)
        {
            return Integer.compare(getValue(), other.getValue());
        }

        public Level getNext()
        {
            if(isMax()) {
                throw new IllegalStateException(
                    "ONE_HOTEL is the highest level.");
            }

            return LEVELS[indexOf(this) + 1];
        }

        public Level getPrevious()
        {
            if(isMin()) {
                throw new IllegalStateException(
                    "UNIMPROVED is the lowest level.");
            }

            return LEVELS[indexOf(this) - 1];
        }

        public int getValue()
        {
            return indexOf(this);
        }

        public boolean isMax()
        {
            return indexOf(this) == LEVELS.length - 1;
        }

        public boolean isMin()
        {
            return indexOf(this) == 0;
        }
    }
}
