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
    public static Card create(Group owner, Action action)
    {
        return create(owner, null, action);
    }

    public static Card create(Group owner, String description, Action... choices)
    {
        // Check arguments
        if(owner == null) {
            throw new IllegalArgumentException("owner cannot be null.");
        }
        if(choices == null) {
            throw new IllegalArgumentException("choices cannot be null.");
        }

        // Check choices elements aren't null
        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }

        if(i != choices.length) {
            throw new IllegalArgumentException(
                String.format(
                    "choices elements cannot be null."
                    + " One or more choices elements is null;"
                    + " the first occurence of null is at index %0$td.",
                    i));
        }

        if(choices.length > 1 && description == null) {
            throw new IllegalArgumentException(
                "description cannot be null for a choice card.");
        }

        return new CardImpl(owner, description, choices);
    }

    /**
     * Gets the number of action choices associated with this card.
     *
     * @return
     */
    public abstract int getActionCount();

    public abstract String getActionDescription(int action);

    public abstract String getDescription();

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

    public abstract Group getOwner();

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
            return cards.remove(0);
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
            if(c.getOwner() != this) {
                throw new IllegalArgumentException(
                    "card must be owned by this group.");
            }
            
            cards.add(c);
        }
    }
}
