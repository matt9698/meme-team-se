/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model.card;

import java.util.List;
import propertyTycoon.model.Player;

/**
 * Represents a card.
 *
 * @author Matt
 */
public abstract class Card
{
    public static Card create(Group owner, Action... choices)
    {
        // Argument checking
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
        
        return new CardImpl(owner, choices);
    }
    
    /**
     * Gets the number of action choices associated with this card.
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
    public boolean isChoice()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
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
    public void spend(Player context)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
    public abstract void spend(int action, Player context);

    /**
     * Represents the action associated with a card.
     */
    public static interface Action
    {
        String getDescription();
        
        /**
         * Executes this action against the specified context.
         *
         * @param context The context (Player) against which
         *                this action should be executed.
         */
        void execute(Player context);
    }

    /**
     * Represents a deck of cards.
     */
    public static class Group
    {
        private List<Card> cards;
        
        public Card drawCard()
        {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        public String getDescription()
        {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        
        public void shuffle()
        {
            throw new UnsupportedOperationException("Not yet implemented");
        }
        
        protected void replaceCard()
        {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
