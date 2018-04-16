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
 * thus cannot be instantiated directly.
 * 
 * The only way to create a <code>Card</code> instance is to
 * use the static <code>create()</code> methods (recommended)
 * or to extend this class (not recommended).
 * 
 * @author Matt
 */
public abstract class Card
{
    /**
     * Creates and returns an instance of a single action card
     * with the specified action associated with it.
     * A single action card is a <code>Card</code>
     * that offers no choice of action.
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
     *                    Should not be null or empty when there is more
     *                    than one element in choices
     *                    <code>(choices.length > 1)</code>.
     * @param choices     The <code>Actions</code> that should
     *                    be associated with this card.
     *                    Should not be null, empty or contain null elements.
     *
     * @return A new <code>Card</code> instance.
     *
     * @throws IllegalArgumentException if <code>choices</code> is null, empty
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
                        + " One or more elements in choices is null;"
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
     *                                   <code>action >= getActionCount()</code>.
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
     * (<code>hasGroup() == true)</code> will raise an IllegalStateException.
     * 
     * @param g The group to set as the owner.
     * 
     * @throws IllegalArgumentException if g is null.
     * @throws IllegalStateException if this card already has an owner.
     */
    public abstract void setGroup(Group g);
    
    /**
     * Indicates if this card has an owner <code>Group</code> (deck).
     * 
     * @return true if this card has an owner, false otherwise.
     */
    public abstract boolean hasGroup();

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
     * Indicates if at least one action associated with this card is useable.
     * This method should always return true for 
     * an immediate use card at the time it is drawn.
     * 
     * @return true if at least one action associate with this card is useable,
     *         false otherwise.
     */
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

    /**
     * Represents an action associated with a card.
     */
    public static interface Action
    {
        void execute();
        
        String getDescription();

        boolean isExecutable();

    }

    /**
     * Represents a deck of cards.
     */
    public static class Group
    {
        private Card awaitingReplace;
        private List<Card> cards;
        private String description;

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

            if(!c.hasGroup() || c.getGroup() != this) {
                throw new IllegalArgumentException(
                    "card must be owned by this group.");
            }

            cards.add(c);
        }
    }
}
