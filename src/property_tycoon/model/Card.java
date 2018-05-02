/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a card.
 * This class is abstract and cannot be instantiated directly.
 * Subclasses of this class are not publicly exposed and
 * thus cannot be instantiated directly either.
 *
 * The only way to create a <code>Card</code> instance is to
 * use the static <code>create()</code> methods (recommended)
 * or to extend this class (not recommended).
 *
 * @author Matt
 * @version 02/05/2018
 */
public abstract class Card
{
    public static Card create()
    {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Returns the <code>PropertyChangeSupport</code>
     * associated with this <code>Card</code> object that is
     * used to manage Java Bean property change event handlers.
     * Any time the value of a property in this class changes
     * this object should be used to notify observers.
     *
     * @return The <code>PropertyChangeSupport</code>
     *         associated with this <code>Card</code>.
     */
    protected PropertyChangeSupport getPropertyChangeSupport()
    {
        return pcs;
    }

    /**
     * Registers the specified property change listener
     * with this <code>Card</code> object.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Unregisters the specified property change listener
     * with this <code>Card</code> object.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Gets the number of action choices associated with this card.
     * For a single action card this method returns 1.
     *
     * @return The number of action choices associated with this card.
     */
    public abstract int getActionCount();

    /**
     * Gets the description of the specified action.
     *
     * @param action The index of the action in question.
     *
     * @return The description of the action at the specified index.
     *
     * @throws IndexOutOfBoundsException if the action index is out of bounds.
     */
    public abstract String getActionDescription(int action);

    /**
     * Gets the description of this card.
     * For a single action card calling this method is equivalent to
     * calling <code>getActionDescription(0)</code>.
     *
     * @return The description of this card.
     */
    public abstract String getDescription();

    /**
     * Gets the group to which this card belongs.
     *
     * @return The group to which this card belongs,
     *         or null if this card has no group.
     */
    public abstract Group getGroup();

    /**
     * Sets the group to which this card belongs.
     * This method should only be called by <code>Card$Group</code>.
     * Subclasses should provide an implementation of this method,
     * but should never call it themselves.
     *
     * @param group The group to set.
     *
     * @throws IllegalStateException if this card already has a group.
     * @throws IllegalArgumentException if group is null.
     */
    protected abstract void setGroup(Group group);

    /**
     * Indicates if this card is a member of a group.
     *
     * @return true if this card is a member of a group; false otherwise.
     */
    public abstract boolean isGrouped();

    /**
     * Indicates if this card offers a choice of action.
     *
     * @return true if this card offers a choice of action; false otherwise.
     */
    public final boolean isChoice()
    {
        return getActionCount() > 1;
    }

    /**
     * Indicates if this card must be used immediately when drawn,
     * or if it can be kept for later use.
     *
     * @return true if this card must be used immediately;
     *         false if it can be kept for later use.
     */
    public abstract boolean isImmediate();

    /**
     * Indicates if at least one action associated with this card is usable at this time.
     * This method should always return true for
     * an immediate use card.
     *
     * @return true if at least one action associated with this card is usable;
     *         false otherwise.
     */
    public abstract boolean isUseable();

    /**
     * Indicates if the specified action is usable at this time.
     * @param action The index of the action in question.
     *
     * @return true if the specified action is usabl;, false otherwise.
     *
     * @throws IndexOutOfBoundsException if the action index is out of bounds.
     */
    public abstract boolean isUseable(int action);

    /**
     * Indicates if this card is a valid proxy that can
     * be used access mutator methods of an underlying real property.
     * This method always returns true for real cards.
     * Any attempt to call a mutator method on a proxy card
     * will raise an exception is this method returns false.
     *
     * @return true if this card is a valid proxy; false otherwise.
     */
    public abstract boolean isValid();

    /**
     * Uses this card, executing its associated action
     * and replacing it in its owner Group.
     * This is a convenience method for using a single action card
     * without specifying the action index explicitly.
     * Calling this method on a single action card is
     * equivalent to calling <code>use(0)</code>.
     * Calling this method on a choice card will raise an exception.
     *
     * @throws UnsupportedOperationException if this card is a choice card.
     * @throws IllegalStateException if this card is not useable at this time.
     */
    public final void use()
    {
        if(isChoice()) {
            throw new UnsupportedOperationException(
                "Choice cards do not support use().");
        }

        use(0);
    }

    /**
     * Uses the card, executing the specified action
     * and replacing it in its owner group.
     * Raises an exception if the specified action is not usable at this time.
     *
     * @param action The index of the action to execute when using this card.
     *
     * @throws IndexOutOfBoundsException if the action index is out of bounds.
     * @throws IllegalArgumentException  if action is not usable at this time.
     */
    public abstract void use(int action);

    /**
     * Indicates if this card is currently owned.
     *
     * @return true if this card is currently owned; false otherwise.
     */
    public abstract boolean isOwned();

    /**
     * Gets the player that currently owns this card.
     *
     * @return The player that currently owns this card,
     *         or null if this card has no owner.
     */
    public abstract Player getOwner();

    /**
     * Sets the owner of this card.
     * This method should only be called by <code>Card$Group</code>.
     * Subclasses should provide an implementation of this method,
     * but should never call it themselves.
     *
     * @param owner The player to set as the owner.
     */
    protected abstract void setOwner(Player owner);

    /**
     * Represents a deck of cards.
     */
    public static final class Group
    {
        public static Group create(String description, Card... cards)
        {
            Group group = new Group(description, cards);
            for(Card card : cards) {
                card.setGroup(group);
            }

            return group;
        }

        private final String description;
        private final List<Card> cards;

        private Group(String description, Card... cards)
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

            if(cards == null) {
                throw new IllegalArgumentException(
                    "cards should not be null.");
            }
            if(cards.length == 0) {
                throw new IllegalArgumentException(
                    "cards should not be empty.");
            }

            for(Card card : cards) {
                if(card == null) {
                    throw new IllegalArgumentException(
                        "cards should not contain null elements.");
                }
                if(card.isGrouped()) {
                    throw new IllegalArgumentException(
                        "properties should not contain elements that already have a group.");
                }
            }

            // Copy the array so that elements cannot
            // be subsequently modified by external code.
            this.cards = new ArrayList<Card>(Arrays.asList(cards));
        }

        public String getDescription()
        {
            return description;
        }

        public Card draw(Player drawer)
        {
            if(drawer == null) {
                throw new IllegalArgumentException("drawer should not be null.");
            }

            // TODO: Deal with empty card list somehow

            Card card = cards.remove(0);
            card.setOwner(drawer);
            return new CardProxy(card);
        }

        protected void replace(Card card)
        {
            if(card == null) {
                throw new IllegalArgumentException("card should not be null.");
            }

            if(!card.isGrouped() || !card.getGroup().equals(this)) {
                throw new IllegalArgumentException(
                    "card should be a member of this group.");
            }

            card.setOwner(null);
            cards.add(card);
        }

        public void shuffle()
        {
            // TODO: Implement
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    }
}
