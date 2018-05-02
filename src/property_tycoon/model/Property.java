/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.scene.paint.Color;

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
 * @author meme-team
 * @version 30/04/2018
 */
public abstract class Property
{
    private static final int MORTGAGE_FACTOR = 2;

    public static Property create(
        String description, int price, int[] rents)
    {
        return new PropertyImpl(description, price, rents);
    }

    public static Property createStation(String description, int price)
    {
        return new PropertyImpl(
            description, price, null);
    }

    public static Property createUtility(String description, int price)
    {
        return new PropertyImpl(
            description, price, null);
    }

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Registers the specified property change listener
     * with this <code>Property</code> object.
     *
     * @param listener the listener to register.
     */
    public final void addPropertyChangeListener(PropertyChangeListener listener)
    {
        // TODO: Investigate using Properties
        // TODO: Investigate using weak references
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Simulates the purchase of this property by setting the owner
     * and returning a version of itself that is owner interactable
     * (can be interacted with: upgraded, downgraded, mortgaged, unmortgaged or sold.)
     *
     * @param buyer The player to set as the owner of this property.
     *
     * @return An owner interactable version of this property.
     *
     * @throws IllegalStateException    if this property has no group
     *                                  or already has an owner.
     * @throws IllegalArgumentException if buyer is null.
     */
    public abstract Property buy(Player buyer);

    /**
     * Downgrades this property by one improvement level.
     *
     * @return the cash to be credited for downgrading this property.
     *
     * @throws IllegalStateException if this property is a proxy and is not valid,
     *                               if this property has no group
     *                               (meaning it has no level), no owner,
     *                               is not improvable,
     *                               is already the minimum improvement level
     *                               or if downgrading would cause
     *                               improvement levels in this property's
     *                               group to differ by more than one.
     */
    public abstract int downgrade();

    /**
     * Gets the name of this property.
     *
     * @return The name of this property.
     */
    public abstract String getDescription();

    /**
     * Gets the group to which this property belongs.
     *
     * @return The group to which this property belongs,
     *         or null if this property has no group.
     */
    public abstract Group getGroup();

    /**
     * Gets the cost of upgrading (debit) or downgrading (credit) this property.
     *
     * @return the cost of upgrading or downgrading this property.
     *
     * @throws UnsupportedOperationException if this property is not improvable.
     */
    public final int getImprovementCost()
    {
        if(!isImprovable()) {
            throw new UnsupportedOperationException(
                "Property is not improvable.");
        }

        if(!isGrouped()) {
            throw new IllegalStateException(
                "Property has no group, improvement cost is defined by Property$Group.getImprovementCost().");
        }

        return getGroup().getImprovementCost();
    }

    /**
     * Gets the mortgaged value of this property.
     *
     * @return the mortgaged value of this property.
     */
    public final int getMortgagedPrice()
    {
        return getPrice() / MORTGAGE_FACTOR;
    }

    /**
     * Gets the cost of renting this property at its current
     * improvement level with the specified value shown on the dice.
     *
     * @return the cost of renting this property at its current improvement level.
     *
     * @param diceValue The value shown on the dice.
     *
     * @throws IllegalArgumentException if this property has no group
     *                                  (meaning it has no level).
     */
    public final int getRentPrice(int diceValue)
    {
        return getRentPrice(getLevel(), diceValue);
    }

    /**
     * Gets the cost of renting this property at the specified
     * improvement level with the specified value shown on the dice.
     *
     * @return the cost of renting this property at the specified improvement level.
     *
     * @param level     the level of interest.
     * @param diceValue The value shown on the dice.
     *
     * @throws IllegalArgumentException if this property has no group
     *                                  (meaning it has no level),
     *                                  if dice value is not positive,
     *                                  or if level is not in the level
     *                                  group for this property.
     */
    public abstract int getRentPrice(PropertyLevel level, int diceValue);

    /**
     * Simulates the sale of this property by unsetting the owner.
     *
     * @return the cash to be credited for selling this property.
     *
     * @throws IllegalStateException if this property is a proxy and is not valid,
     *                               if this property has no owner,
     *                               or is improvable and is not the minimum level.
     */
    public abstract int sell();

    /**
     * Upgrades this property by one improvement level.
     *
     * @return the cash to be debited for upgrading this property.
     *
     * @throws IllegalStateException if this property is a proxy and is not valid,
     *                               if this property has no group
     *                               (meaning it has no level), no owner,
     *                               is not improvable,
     *                               is already the maximum improvement level
     *                               or if upgrading would cause
     *                               improvement levels in this property's
     *                               group to differ by more than one.
     */
    public abstract int upgrade();

    /**
     * Sets the group to which this property belongs.
     * This method should only be called by <code>Property$Group</code>.
     * Subclasses should provide an implementation of this method,
     * but should never call it themselves.
     * When group is set, level should also be set
     * to <code>group.getLevels().getMin()</code>.
     *
     * @param group The group to set.
     *
     * @throws IllegalStateException if this property already has a group.
     * @throws IllegalArgumentException if group is null.
     */
    protected abstract void setGroup(Group group);

    /**
     * Gets the current improvement level of this property.
     *
     * @return The current improvement level of this property,
     *         or null if this property has no level (because it has not group).
     */
    public abstract PropertyLevel getLevel();

    /**
     * Gets the player that currently owns this property.
     *
     * @return The player that currently owns this property,
     *         or null if this property has no owner.
     */
    public abstract Player getOwner();

    /**
     * Gets the unmortgaged value of this property.
     *
     * @return the unmortgaged value of this property.
     */
    public abstract int getPrice();

    /**
     * Indicates if this property is a member of a group.
     *
     * @return true if this property is a member of a group; false otherwise.
     */
    public abstract boolean isGrouped();

    /**
     * Indicates if this property can be upgraded and downgraded.
     * This method only indicates that a property is genrally improvable.
     * If a property is maximum <code>(getLevel().isMax())</code>
     * or minimum <code>(getLevel().isMin())</code> level
     * calls to <code>upgrade</code> and <code>downgrade()</code>,
     * respectively, will still fail.
     *
     * @return true if this property can be upgraded and downgraded; false otherwise.
     *
     * @throws IllegalStateException if this property has no group.
     */
    public final boolean isImprovable()
    {
        if(!isGrouped()) {
            throw new IllegalStateException(
                "Property has no group, improvable is defined by Property$Group.isImprovable().");
        }

        return getGroup().isImprovable();
    }

    /**
     * Indicates if this property is currently mortgaged.
     *
     * @return true if this property is currently mortgaged; false otherwise.
     */
    public abstract boolean isMortgaged();

    /**
     * Indicates if this property is currently owned.
     *
     * @return true if this property is currently owned; false otherwise.
     */
    public abstract boolean isOwned();

    /**
     * Indicates if this property is a valid proxy that can
     * be used access mutator methods of an underlying real property.
     * This method always returns true for real properties.
     * Any attempt to call a mutator method on a proxy property
     * will raise an exception is this method returns false.
     *
     * @return true if this property is a valid proxy; false otherwise.
     */
    public abstract boolean isValid();

    /**
     * Mortgages this property.
     *
     * @return the cash to be credited for mortgaging this property.
     *
     * @throws IllegalStateException if this property a proxy and is not valid,
     *                               has no owner, is already mortgaged or
     *                               is improvable and is not the minimum level.
     */
    public abstract int mortgage();

    /**
     * Unregisters the specified property change listener
     * with this <code>Property</code> object.
     *
     * @param listener the listener to unregister.
     */
    public final void removePropertyChangeListener(
        PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Unmortgages this property.
     *
     * @return the cash to be debited for unmortgaging this property.
     *
     * @throws IllegalStateException if this property a proxy and is not valid,
     *                               if this property is not mortgaged,
     *                               or has no owner.
     */
    public abstract int unmortgage();

    /**
     * Returns the <code>PropertyChangeSupport</code>
     * associated with this <code>Property</code> object that is
     * used to manage Java Bean property change event handlers.
     * Any time the value of a property in this class changes
     * this object should be used to notify observers.
     *
     * @return The <code>PropertyChangeSupport</code>
     *         associated with this <code>Property</code>.
     */
    protected final PropertyChangeSupport getPropertyChangeSupport()
    {
        return pcs;
    }

    public static final class Group
    {
        // TODO: change to create method for each different property type.
        public static Group create(
            String description,
            Color color,
            PropertyLevel.Group levels,
            int houseCost,
            Property... properties)
        {
            Group group = new Group(description, color, levels, houseCost, properties);
            for(Property property : properties) {
                property.setGroup(group);
            }

            return group;
        }

        private final Color color;
        private final String description;
        private final int improvementCost;
        private final PropertyLevel.Group levels;
        private final Property[] properties;

        private Group(
            String description,
            Color color,
            PropertyLevel.Group levels,
            int houseCost,
            Property... properties)
        {
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null.");
            }
            if(description.isEmpty()) {
                throw new IllegalArgumentException(
                    "description should not be empty.");
            }
            this.description = description;

            if(color == null) {
                throw new IllegalArgumentException(
                    "color should not be null.");
            }
            this.color = color;

            if(levels == null) {
                throw new IllegalArgumentException(
                    "levels should not be null.");
            }
            this.levels = levels;

            if(levels.isImprovable()) {
                if(houseCost < 1) {
                    throw new IllegalArgumentException(
                        "houseCost should be positive");
                }
                this.improvementCost = houseCost;
            }
            else {
                this.improvementCost = 0;
            }

            if(properties == null) {
                throw new IllegalArgumentException(
                    "properties should not be null.");
            }

            for(Property property : properties) {
                if(property == null) {
                    throw new IllegalArgumentException(
                        "properties should not contain null elements.");
                }
                if(property.isGrouped()) {
                    throw new IllegalArgumentException(
                        "properties should not contain elements that are already in a group.");
                }
            }

            // Copy the array so that elements cannot
            // be subsequently modified by external code.
            this.properties = Arrays.copyOf(properties, properties.length);
        }

        /**
         * Gets the color of this group.
         *
         * @return The color of this group.
         */
        public Color getColor()
        {
            return color;
        }

        /**
         * Gets the name of this group.
         *
         * @return The name of this group.
         */
        public String getDescription()
        {
            return description;
        }

        /**
         * Gets the highest improvement level of any property in this group.
         *
         * @return The highest improvement level of any property in this group.
         */
        public PropertyLevel getHighestLevel()
        {
            PropertyLevel max = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().compareTo(max) > 0) {
                    max = properties[i].getLevel();
                }
            }

            return max;
        }

        /**
         * Gets the cost of improving properties in this group.
         *
         * @return the cost of improving properties in this group.
         *
         * @throws UnsupportedOperationException if this group is not improvable.
         */
        public int getImprovementCost()
        {
            if(!isImprovable()) {
                throw new UnsupportedOperationException(
                    "Property group is not improvable.");
            }

            return improvementCost;
        }

        /**
         * Gets the level group associated with this property group.
         *
         * @return The level group associated with this property group
         */
        public PropertyLevel.Group getLevels()
        {
            return levels;
        }

        /**
         * Gets the lowest improvement level of any property in this group.
         *
         * @return The lowest improvement level of any property in this group.
         */
        public PropertyLevel getLowestLevel()
        {
            PropertyLevel min = properties[0].getLevel();
            for(int i = 1; i < properties.length; i++) {
                if(properties[i].getLevel().compareTo(min) < 0) {
                    min = properties[i].getLevel();
                }
            }

            return min;
        }

        /**
         * Gets the player that currently owns all properties in this group.
         *
         * @return The player that currently owns all properties in this group
         *         or null if there is no such player.
         */
        public Player getOwner()
        {
            if(!isOwned()) {
                return null;
            }

            return properties[0].getOwner();
        }

        /**
         * Gets a read-only list of the properties in this group.
         *
         * @return A read-only list of properties in this group.
         */
        public List<Property> getProperties()
        {
            return Collections.unmodifiableList(Arrays.asList(properties));
        }

        /**
         * Indicates if properties in this group can be upgraded and downgraded.
         *
         * @return true if properties in this group can be upgraded and downgraded;
         *         false otherwise.
         */
        public boolean isImprovable()
        {
            return getLevels().isImprovable();
        }

        /**
         * Indicates if the same player currently owns all properties in this group.
         *
         * @return true if the same player currently owns all properties in this group; false otherwise.
         */
        public boolean isOwned()
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
    }
}
