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
 *
 * @author Matt
 */
public abstract class Card
{
    /**
     * Creates and returns an instance of a single action card
     * with the specified action associated with it.
     * A single action card is a <code>Card</code>
     * that offers no choice of action..
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
        // Check argumments
        if(action == null) {
            throw new IllegalArgumentException("action should not be null.");
        }

        return create(null, action);
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
                    + " One or more elements in choices  is null;"
                    + " the first occurence of null is at index %0$td.",
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

        return new CardImpl(description, choices);
    }
    public abstract int getActionCount();

    public abstract String getActionDescritpion(int action);
    public abstract String getDescription();
    public abstract Group getGroup();
    public abstract void setGroup(Group g);
    public abstract boolean hasGroup();

    public final boolean isChoice()
    {
        return getActionCount() > 1;
    }
    public abstract boolean isImmediate();

    public abstract boolean isUseable();

    public abstract boolean isUseable(int action);
    public abstract boolean isValid();

    public final void use()
    {
        if(isChoice()) {
            throw new UnsupportedOperationException();
        }

        use(0);
    }

    public abstract void use(int action);


    public static interface Action
    {
        String getDescription();

        boolean isExecutable();

        void execute();
    }

    public static class Group
    {
        private List<Card> cards;
        private String description;
        private Card awaitingReplace;

        public Group(String description, Card[] cards)
        {
            // Check arguments
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null.");
            }

            if(cards == null) {
                throw new IllegalArgumentException("cards should not be null.");
            }

            if(cards.length == 0) {
                throw new IllegalArgumentException("cards should not be empty.");
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

            // Assign fields
            this.description = description;

            // Create cards list
            this.cards = new ArrayList(Arrays.asList(cards));

            awaitingReplace = null;
        }

        public String getDescription()
        {
            return description;
        }

        public Card draw()
        {
            if(awaitingReplace != null) {
                throw new IllegalStateException();
            }

            assert !cards.isEmpty() : "cards should not be empty";

            Card c = cards.remove(0);
            if(c.isImmediate()) {
                awaitingReplace = c;
            }

            return new CardProxy(cards.remove(0));
        }

        public void shuffle()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }

        protected void replace(Card c)
        {
            if(awaitingReplace != null && c != awaitingReplace) {
                throw new IllegalStateException();
            }

            if(c == null) {
                throw new IllegalArgumentException(
                    "card should not be null.");
            }
            if(c.hasGroup() && c.getGroup() != this) {
                throw new IllegalArgumentException(
                    "card must be owned by this group.");
            }

            if(awaitingReplace != null) {
                awaitingReplace = null;
            }

            cards.add(c);
        }
    }
}
