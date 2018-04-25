/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

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
 * @version 25/04/2018
 */
public abstract class Card
{
    /**
     * Creates and returns an instance of a single action card
     * with the specified action associated with it.
     * A single action card is a <code>Card</code>
     * that offers no choice of action.
     *
     * @param action      The <code>Action</code> that should
     *                    be associated with this card. Should not be null.
     *                    <code>isAlwaysExecutable() == true</code>
     *                    when <code>isImmediate == true</code>.
     * @param isImmediate Indicates if this card should be used on
     *                    the same turn it is drawn.
     *
     * @return A new <code>Card</code> instance.
     *
     * @throws IllegalArgumentException if <code>action</code> is null or if
     *                                  <code>action.isAlwaysExecutable() == false</code>
     *                                  when <code>isImmediate == true</code>.
     */
    public static Card create(Action action, boolean isImmediate)
    {
        // Check argumments
        if(action == null) {
            throw new IllegalArgumentException("action should not be null.");
        }

        return create(null, isImmediate, action);
    }

    /**
     * Creates and returns an instance of a choice card
     * with the specified description and actions associated with it.
     * A choice card is a <code>Card</code>
     * that offers a choice of action.
     *
     * @param description A description of the this card and its choices.
     *                    Ignored if choices has only one element
     *                    <code>(choices.length == 1)</code>.
     *                    Should not be null or empty when there is more
     *                    than one element in choices
     *                    <code>(choices.length &gt; 1)</code>.
     * @param isImmediate Indicates if this card should be used on
     *                    the same turn it is drawn.
     * @param choices     The <code>Actions</code> that should
     *                    be associated with this card.
     *                    Should not be null, empty or contain null elements.
     *                    If <code>isImmediate == true</code> then
     *                    <code>isAlwaysExecutable() == true</code> for at least one action.
     *
     * @return A new <code>Card</code> instance.
     *
     * @throws IllegalArgumentException if <code>choices</code> is null, empty
     *                                  or contains null elements, or if
     *                                  description is null or empty when there
     *                                  is more than one element in choices
     *                                  <code>(choices.length &gt; 1)</code>,
     *                                  or if <code>isImmediate == true</code> but
     *                                  <code>isAlwaysExecutable() != true</code>
     *                                  for any action in choices.
     */
    public static Card create(
        String description,
        boolean isImmediate,
        Action... choices)
    {
        // Check arguments
        if(choices == null) {
            throw new IllegalArgumentException("choices should not be null.");
        }

        if(choices.length == 0) {
            throw new IllegalArgumentException("choices should not be empty.");
        }

        // Check choices elements aren't null
        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }

        if(i != choices.length) {
            throw new IllegalArgumentException(
                String.format(
                    "choices should not contain null elements."
                        + " One or more elements in choices is null;"
                        + " the first occurence of null is at index %d.",
                    i));
        }

        if(choices.length > 1) {
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null for a choice card.");
            }

            if(description.isEmpty()) {
                throw new IllegalArgumentException(
                    "description should not be empty for a choice card.");
            }
        }

        // If the card is immediate use then check at least
        // one of its actions is always executable.
        if(isImmediate) {
            i = 0;
            while(i < choices.length && !choices[i].isAlwaysExecutable()) {
                i++;
            }

            if(i == choices.length) {
                throw new IllegalArgumentException(
                    "actions should contain at least one always"
                        + " executable action for an immediate use card.");
            }
        }

        return new CardImpl(description, isImmediate, choices);
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
     * @throws IndexOutOfBoundsException if the action index is out of bounds
     *                                   <code>action &lt; 0</code> or
     *                                   <code>action &gt;= getActionCount()</code>.
     */
    public abstract String getActionDescritpion(int action);

    /**
     * Gets the description of this card.
     * For a single action card calling this method is equivalent to
     * calling <code>getActionDescription(0)</code>.
     *
     * @return The description of this card.
     */
    public abstract String getDescription();

    /**
     * Gets the owner <code>Group</code> (deck) of this card.
     * Throws and IllegalStateException if this card has no owner
     * (<code>hasGroup() == false</code> and <code>setGroup()</code>
     * has not been called previously).
     *
     * @return The owner <code>Group</code> (deck) of this card.
     *
     * @throws IllegalStateException if this card has no owner.
     */
    public abstract Group getGroup();

    /**
     * Sets the owner <code>Group</code> (deck) of this card.
     * This method can only be called once, and subsequent attempts to call it
     * (<code>hasGroup() == true</code>) will raise an IllegalStateException.
     *
     * @param g The group to set.
     *
     * @throws IllegalArgumentException if g is null.
     * @throws IllegalStateException if this card already has a group.
     */
    public abstract void setGroup(Group g);

    /**
     * Indicates if this card has an owner <code>Group</code> (deck).
     *
     * @return true if this card has an owner, false otherwise.
     */
    public abstract boolean isGrouped();

    /**
     * Indicates if this card offers a choice of action.
     *
     * @return true if this card offers a choice of action, false otherwise.
     */
    public final boolean isChoice()
    {
        return getActionCount() > 1;
    }

    /**
     * Indicates if this card must be used immediately when drawn,
     * or if it can be kept for later use.
     *
     * @return true if this card must be used immediately,
     *         false if it can be kept for later use.
     */
    public abstract boolean isImmediate();

    /**
     * Indicates if at least one action associated with this card is usable.
     * This method should always return true for
     * an immediate use card.
     *
     * @return true if at least one action associate with this card is usable,
     *         false otherwise.
     */
    public abstract boolean isUseable();

    /**
     * Indicates if the specified action is usable at this time.
     * @param action The index of the action in question.
     *
     * @return true if the specified action is usable, false otherwise.
     *
     * @throws IndexOutOfBoundsException if the action index is out of bounds
     *                                   <code>action &lt; 0</code> or
     *                                   <code>action &gt;= getActionCount()</code>.
     */
    public abstract boolean isUseable(int action);

    /**
     * Indicates if this card is a valid proxy that can
     * be used access mutator methods of a real card.
     * This method doesn't make sense and is unsupported by real cards.
     * Any mutator methods on a proxy card will raise
     * an exception if this method returns false.
     *
     * @return true if this card is a valid proxy, false otherwise
     *
     * @throws UnsupportedOperationException if this is a real card.
     */
    public abstract boolean isValid();

    /**
     * Uses the card, executing its associated action
     * and replacing it in its owner Group.
     * This is a convenience method for using a single action card
     * without specifying the action index explicitly.
     * Calling this method on a single action card is
     * equivalent to calling <code>use(0)</code>.
     * Calling this method on a choice card will raise an exception.
     *
     * @throws UnsupportedOperationException if this card is a choice card.
     */
    public final void use()
    {
        if(isChoice()) {
            throw new UnsupportedOperationException();
        }

        use(0);
    }

    /**
     * Uses the card, executing the specified action
     * and replacing it in its owner group.
     * Raises an exception if the card is not usable for the specified action.
     *
     * @param action The index of the action to execute when using this card.
     *
     * @throws IndexOutOfBoundsException if the action index is out of bounds
     *                                   <code>action &lt; 0</code> or
     *                                   <code>action &gt;= getActionCount()</code>.
     * @throws IllegalArgumentException  if action is not usable at this time.
     */
    public abstract void use(int action);

    /**
     * Represents an action associated with a card.
     */
    public static interface Action
    {
        /**
         * Executes this action.
         * Raises an exception if this action is not executable at this time.
         */
        void execute();

        /**
         * Gets a description of this action.
         *
         * @return A description of this action.
         */
        String getDescription();

        /**
         * Indicates if this action is always executable.
         *
         * @return true if this action is always executable, false otherwise.
         */
        boolean isAlwaysExecutable();

        /**
         * Indicates if this action is executable at this time.
         * Always returns true is isAlwaysExecutable() == true.
         *
         * @return true if this action is executable, false otherwise.
         */
        boolean isExecutable();
    }

    /**
     * Represents a deck of cards.
     */
    public static class Group implements Board.Position
    {
        private Card awaitingReplace;
        private List<Card> cards;
        private String description;

        /**
         * Creates a new group instance with the specified description
         * and containing the specified cards.
         *
         * @param description The name of this group.
         * @param cards       The cards contained in this group.
         *                    Should not be null, empty or contain null elements.
         *                    Should not contain any cards that already have an owner.
         *
         * @throws IllegalArgumentException if description is null or empty or
         *                                  if any of the cards already
         *                                  belong to another group.
         */
        public Group(String description, Card[] cards)
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

            if(cards == null) {
                throw new IllegalArgumentException(
                    "cards should not be null.");
            }

            if(cards.length == 0) {
                throw new IllegalArgumentException(
                    "cards should not be empty.");
            }

            // Check cards elements aren't null
            int i = 0;
            while(i < cards.length && cards[i] != null) {
                i++;
            }

            if(i != cards.length) {
                throw new IllegalArgumentException(
                    String.format(
                        "cards should not contain null elements."
                            + " One or more elements in cards is null;"
                            + " the first occurence of null is at index %0$td.",
                        i));
            }

            i = 0;
            while(i < cards.length && !cards[i].isGrouped()) {
                i++;
            }

            if(i != cards.length) {
                throw new IllegalArgumentException(
                    String.format(
                    "cards should not contain elements that already have a group."
                        + " One or more elements in cards already has an owner;"
                        + " the first occurence of a card with an owner is at index %0$td.",
                    i));
            }

            // Assign fields
            this.description = description;

            // Create cards list
            this.cards = new ArrayList(Arrays.asList(cards));

            awaitingReplace = null;
        }


        public Card draw()
        {
            if(awaitingReplace != null) {
                throw new IllegalStateException(
                    "Group is awaiting replacement of an immediate use card."
                        + " An immediate use card (isImmediate() == true)"
                        + " has not been used and returned to this Group");
            }

            assert !cards.isEmpty() : "cards should not be empty";

            Card c = cards.remove(0);
            if(c.isImmediate()) {
                awaitingReplace = c;
            }

            return new CardProxy(c);
        }
        public String getDescription()
        {
            return description;
        }

        public void shuffle()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }

        protected void replace(Card c)
        {
            if(c == null) {
                throw new IllegalArgumentException(
                    "card should not be null.");
            }

            if(awaitingReplace != null) {
                if(c != awaitingReplace) {
                    throw new IllegalStateException(
                    "Group is awaiting replacement of an immediate use card."
                        + " An immediate use card (isImmediate() == true)"
                        + " has not been used and returned to this Group");
                }

                awaitingReplace = null;
            }

            if(!c.isGrouped() || c.getGroup() != this) {
                throw new IllegalArgumentException(
                    "card must be owned by this group.");
            }

            cards.add(c);
        }
    }
}
