/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model;

import java.util.List;

/**
 * Represents a card.
 *
 * @author Matt
 */
public abstract class Card
{
    /**
     * Creates and returns an instance of a single action card
     * with the specified action associated with it.
     * A single action card is a <code>Card</code>
     * that offers no choice of action when spent.
     *
     * @param action The <code>Action</code> that should
     *               be associated with this card. Should not be null.
     *
     * @return A new <code>Card</code> instance.
     *
     * @throws IllegalArgumentException if <code>action</code> is null.
     */
    public static Card create(Action action)
    {
        if(action == null) {
            throw new IllegalArgumentException("action should not be null.");
        }

        return create(null, action);
    }

    /**
     * Creates and returns an instance of a choice card
     * with the specified description and actions associated with it.
     * A choice card is a <code>Card</code>
     * that offers a choice of action when spent.
     *
     * @param description A description of the this card and its choices.
     *                    Ignored if choices has only one element
     *                    <code>(choices.length == 1)</code>.
     *                    Should not be empty or null when there is more
     *                    than one element in choices
     *                    <code>(choices.length > 1)</code>.
     * @param choices     The <code>Actions</code> that should
     *                    be associated with this card.
     *                    Should not be null or contain null elements.
     *
     * @return A new <code>Card</code> instance.
     *
     * @throws IllegalArgumentException if <code>choices</code> is null
     *                                  or contains null elements, or if
     *                                  description is null or empty when there
     *                                  is more than one element in choices
     *                                  <code>(choices.length > 1)</code>.
     */
    public static Card create(String description, Action... choices)
    {
        // Check arguments
        if(choices == null) {
            throw new IllegalArgumentException("choices should not be null.");
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
                    + " One or more choices elements is null;"
                    + " the first occurence of null is at index %0$td.",
                    i));
        }

        if(choices.length > 1 && description == null) {
            throw new IllegalArgumentException(
                "description should be null for a choice card.");
        }

        return new CardImpl(description, choices);
    }

    /**
     * Gets the number of action choices associated with this card.
     *
     * @return The number of action choices associated with this card.
     */
    public abstract int getActionCount();

    /**
     * Gets the description of the specified <code>Action</code>.
     * The <code>Action</code> is specified with an index.
     * associated with the specified index.
     *
     * @param index The index associated with the
     *               <code>Action</code> in question.
     *
     * @return The description of the specified <code>Action</code>.
     */
    public abstract String getActionDescription(int index);

    /**
     * Gets the description of this card.
     * For single action cards calling this method is 
     * equivalent to calling <code>getActionDescription(0)</code>.
     * @return The description of this card.
     */
    public abstract String getDescription();

    /**
     * Gets the owner <code>Group</code> of this card.
     * The owner must be set using <code>setOwner()</code> 
     * before this method can be used.
     * 
     * @return The owner group of this card.
     */
    public abstract Group getOwner();

    public abstract void setOwner(Group g);

    public abstract boolean hasOwner();

    /**
     * Indicates if this card offers a choice of actions.
     *
     * @return true if the card offers a choice, false otherwise.
     */
    public final boolean isChoice()
    {
        return getActionCount() == 0;
    }

    /**
     * Indicates if this card has been spent.
     * If the card has been spent then calls to spend()
     * will raise an exception.
     *
     * @return true if the card has been spent, false otherwise.
     */
    public abstract boolean isSpent();

    /**
     * Executes the action associated with this card and
     * replaces it at the bottom of its owner deck (Group).
     *
     * @param context
     */
    public final void spend(Player context)
    {
        if(isChoice()) {
            throw new UnsupportedOperationException(
                "Card.spend(Player context) is not supported for choice cards."
                + " Use Card.spend(int action, Player context) instead.");
        }

        spend(0, context);
    }

    public abstract void spend(int action, Player context);

    /**
     * Represents the action associated with a card.
     */
    public static interface Action
    {

        /**
         * Executes this action against the specified context.
         *
         * @param context The context (Player) against which
         *                this action should be executed.
         */
        void execute(Player context);

        String getDescription();
    }

    /**
     * Represents a deck of cards.
     */
    public static class Group
    {
        private List<Card> cards;
        private String description;

        public Card drawCard()
        {
            assert !cards.isEmpty();
            return new CardProxy(cards.remove(0));
        }

        public String getDescription()
        {
            return description;
        }

        public void shuffle()
        {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        protected void replaceCard(Card c)
        {
            if(c.hasOwner() && c.getOwner() != this) {
                throw new IllegalArgumentException(
                    "card must be owned by this group.");
            }

            cards.add(c);
        }
    }
}
