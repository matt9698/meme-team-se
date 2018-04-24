/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

/**
 * Represents a property.
 * This class is abstract and cannot be instantiated directly.
 * Subclasses of this class are not publicly exposed and
 * thus cannot be instantiated directly either.
 *
 * The only way to create a <code>Property</code> instance is to
 * use the static <code>create()</code> methods (recommended)
 * or to extend this class (not recommended).
 *
 * @author Matt
 */
public abstract class Property
{
    private static final int MORTGAGE_FACTOR = 2;

    /**
     * Creates and returns an instance of a <code>Property</code>
     * with the specified description (name), value and rent prices.
     *
     * @param description The name of the property.
     * @param value The value if the property.
     * @param rent The rent prices of the property.
     * Should be six elements (Level.LEVEL_COUNT) in length,
     * with each element corresponding to the rent price
     * at the improvement levels in the following order:
     * UNIMPROVED, ONE_HOUSE, TWO_HOUSES,
     * THREE_HOUSES, FOUR_HOUSES, ONE_HOTEL.
     *
     * @return A new <code>Property</code> instance.
     *
     * @throws IllegalArgumentException if <code>description</code> or
     * <code>rent</code> are null,
     * or if <code>rent</code> does not contain exactly six (Level.LEVEL_COUNT)
     * positive elements, or if value is not positive.
     */
    public static Property create(String description, int value, int[] rent)
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

        if(value < 1) {
          throw new IllegalArgumentException(
              "value should be positive.");
        }

        if(rent == null) {
            throw new IllegalArgumentException("rent should not be null.");
        }

        if(rent.length != Level.LEVEL_COUNT) {
            throw new IllegalArgumentException(
                String.format(
                    "rent should contain %d (Level.LEVEL_COUNT) elements not %d.", 
                    Level.LEVEL_COUNT, 
                    rent.length));
        }

        // Check rent elements are positive
        int i = 0;
        while(i < rent.length && rent[i] > 0) {
            i++;
        }

        if(i != rent.length) {
            throw new IllegalArgumentException(
                String.format("rent should only contain positive elements.\n"
                    + "One or more elements in rent is not positive;\n"
                    + "the first occurence of a non-positive element is at index %d.",
                i));
        }

        return new PropertyImpl(description, value, rent);
    }

    /**
     * Simulates the purchase of this property by returning
     * a version of itself that is owner interactable
     * (can be interacted with: improved, mortgaged, traded or sold).
     *
     * @param buyer The player to set as the owner of this property.
     * 
     * @return An owner interactable version of this property.
     *
     * @throws IllegalStateException if this property already has an owner.
     * @throws IllegalArgumentException if buyer is null.
     */
    public abstract Property buy(Player buyer);

    /**
     * Downgrades this property by one level.
     *
     * @throws IllegalStateException if this property is already the minimum improvement level,
     * or downgrading would cause improvement levels in this property's group to differ by more than one.
     */
    public abstract void downgrade();

    /**
     * Gets the name of this property.
     *
     * @return The name of this property.
     */
    public abstract String getDescription();

    /**
     * Gets the <code>Group</code> to which this property belongs.
     *
     * @return The <code>Group</code> to which this property belongs.
     *
     * @throws IllegalStateException if this property has no owner.
     */
    public abstract Group getGroup();

    /**
     * Sets the <code>Group</code> tho which this property belongs.
     * This method can only be called once, and subsequent attempts to call it
     * (<code>hasGroup() == true</code>) will raise an IllegalStateException.
     *
     * @param g The group to set.
     *
     * @throws IllegalArgumentException if g is null.
     * @throws IllegalStateException if this property already has a group.
     */
    public abstract void setGroup(Group g);

    /**
     * Gets the cost to improve this property from its current level
     * to the specified level. If the specified level is lower than
     * the current level a negative value will be returned,
     * indicating it as credit rather than debit.
     *
     * @param to The improvement level of interest.
     *
     * @throws IllegalStateException if this property has no group.
     * This method depends on the group house cost.
     */
    public int getImprovementCost(Level to)
    {
        if(!isGrouped()) {
            throw new IllegalStateException(
                "Property has no group, house cost is derived from Group.getHouseCost(). ");
        }

        return getHouseCost() * (to.getValue() - getLevel().getValue());
    }

    /**
     * Gets the current improvement level of this property.
     *
     * @return The current improvement level of this property.
     */
    public abstract Level getLevel();

    /**
     * Gets the value of this property whilst it is mortgaged.
     *
     * @return The value of this property whilst it is mortgaged.
     */
    public int getMortgagedValue()
    {
        return getValue() / MORTGAGE_FACTOR;
    }

    /**
     * Gets the <code>Player</code> that owns this property.
     *
     * @return The <code>Player</code> that owns this property.
     *
     * @throws IllegalStateException if this property has no owner.
     */
    public abstract Player getOwner();

    /**
     * Gets the cost of rent of this property at its current improvement level
     *
     * @return The cost of rent of this property at its current improvement level.
     */
    public int getRentCost()
    {
        return getRentCost(getLevel());
    }

    /**
     * Gets the cost of rent of this property at the specified improvement level.
     *
     * @param level The level of interest.
     * 
     * @return The cost of rent of this property at the specified improvement level
     */
    public abstract int getRentCost(Level level);

    /**
     * Gets the cost of buying a house or hotel for this property.
     * 
     * @return The cost of buying a house or hotel for this property.
     * 
     * @throws IllegalStateException if this property has no group.
     * This method depends on the group house cost. 
     */
    public int getHouseCost()
    {
        if(!isGrouped()) {
            throw new IllegalStateException(
                "Property has no group, house cost is defined by Group.getHouseCost().");
        }

        return getGroup().getHouseCost();
    }

    /**
     * Gets the value of this property.
     * 
     * @return The value of this property.
     */
    public abstract int getValue();

    public abstract boolean isGrouped();

    public abstract boolean isOwned();

    public boolean isImproved()
    {
        return getLevel() != Level.UNIMPROVED;
    }

    public abstract boolean isMortgaged();

    public abstract boolean isValid();

    public abstract int mortgage();

    public abstract int sell();
    
    @Override
    public String toString()
    {
        return String.format("Property{description=%s, group=%s, value=%d, owner=%s}",
            getDescription(),
            isGrouped() ? getGroup().getDescription() : "<none>",
            getValue(),
            isOwned() ? getOwner().getDescription() : "<none>");
    }

    public abstract Property trade(Player buyer, Player seller);

    public abstract int unmortgage();

    public abstract void upgrade();

    public static class Group
    {
        private final int houseCost;
        private final String description;
        private final Property properties[];
        
        public Group(String description, int houseCost, Property... properties)
        {
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null.");
            }
            
            if(description.isEmpty()) {
                throw new IllegalArgumentException(
                    "description should not be empty.");
            }
            
            if(houseCost < 1) {
                throw new IllegalArgumentException(
                    "houseCost should be positive");
            }
            
            if(properties == null) {
                throw new IllegalArgumentException(
                    "properties should not be null.");
            }
            
            if(properties.length == 0) {
                throw new IllegalArgumentException(
                    "properties should not be empty.");
            }
            
            // Check properties elements aren't null
            int i = 0;
            while(i < properties.length && properties[i] != null) {
                i++;
            }

            if(i != properties.length) {
                throw new IllegalArgumentException(
                    String.format("properties should not contain null elements.\n"
                        + "One or more elements in properties is null;\n"
                        + "the first occurence of a null element is at index %d.",
                    i));
            }
            
            // Check properties don't already have a group
            i = 0;
            while(i < properties.length && !properties[i].isGrouped()) {
                i++;
            }
            
            if(i != properties.length) {
                throw new IllegalArgumentException(
                    String.format("properties should not contain elements that already have a group.\n"
                        + "One or more elements in properties already has a group;\n"
                        + "the first occurence of an element with a group is at index %d.",
                    i));
            }
            
            this.description = description;
            this.houseCost = houseCost;
            this.properties = properties;
            
            // Set this group for all properties
            for(Property p : properties) {
                p.setGroup(this);
            }
        }

        public String getDescription()
        {
            return description;
        }

        public Level getHighestLevel()
        {
            Level max = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().compareTo(max) > 0) {
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
                if(properties[i].getLevel().compareTo(min) < 0) {
                    min = properties[i].getLevel();
                }
            }

            return min;
        }

        public Player getOwner()
        {
            if(!hasOwner()) {
                throw new IllegalStateException("Group has no owner.");
            }

            return properties[0].getOwner();
        }

        public boolean hasOwner()
        {
            if(!properties[0].isOwned()) {
                return false;
            }

            Player expectedOwner = properties[0].getOwner();
            int i = 1;
            while(i < properties.length
                && properties[i].isOwned()
                && properties[i].getOwner() == expectedOwner) {
                i++;
            }

            return i == properties.length;
        }

        @Override
        public String toString()
        {
            return String.format(
                "Group{description=%s, properties=%s, houseCost=%d}",
                getDescription(),
                Arrays.toString(properties),
                getHouseCost());
        }
    }

    public static final class Level implements Comparable<Level>
    {
        public static final int LEVEL_COUNT = 6;

        public static final Level UNIMPROVED;
        public static final Level ONE_HOUSE;
        public static final Level TWO_HOUSES;
        public static final Level THREE_HOUSES;
        public static final Level FOUR_HOUSES;
        public static final Level ONE_HOTEL;

        private static final Level[] LEVELS;

        // Static initialiser
        // Used to assert that LEVELS is instantiated correctly.
        static {
            LEVELS = new Level[] {
                UNIMPROVED = new Level("UNIMPROVED"),
                ONE_HOUSE = new Level("ONE_HOUSE"),
                TWO_HOUSES = new Level("TWO_HOUSES"),
                THREE_HOUSES = new Level("THREE_HOUSES"),
                FOUR_HOUSES = new Level("FOUR_HOUSES"),
                ONE_HOTEL = new Level("ONE_HOTEL")
            };

            assert LEVELS.length == LEVEL_COUNT : String.format(
                "Level.LEVELS should contain %d (Level.LEVEL_COUNT) elements not %d.",
                LEVEL_COUNT, 
                LEVELS.length);
        }

        private static int indexOf(Level level)
        {
            int i = 0;
            while(LEVELS[i] != level) {
                i++;
            }

            return i;
        }
        
        private final String description;
        
        private Level(String description)
        {
            // Check argument
            assert description != null : "description should not be null.";
            assert !description.isEmpty() : "description should not be empty.";
            
            // Assign field
            this.description = description;
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
                    String.format(
                        "%s is the highest level.", 
                        getDescription()));
            }

            return LEVELS[indexOf(this) + 1];
        }

        public Level getPrevious()
        {
            if(isMin()) {
                throw new IllegalStateException(
                    String.format(
                        "%s is the lowest level.", 
                        getDescription()));
            }

            return LEVELS[indexOf(this) - 1];
        }

        public int getValue()
        {
            return indexOf(this);
        }
        
        public String getDescription()
        {
            return description;
        }

        public boolean isMax()
        {
            return indexOf(this) == LEVELS.length - 1;
        }

        public boolean isMin()
        {
            return indexOf(this) == 0;
        }

        @Override
        public String toString()
        {
            return String.format("Level.%s", description);
        }
    }
}
