/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * Represents a card action.
 * This class is an interface and cannot be instantiated at all.
 * Every card action has multiple properties as well as a description of what it does.
 * 
 * @author meme-team
 * @version 29/04/2018
 */
public interface CardAction
{
    /**
     * Proceeds with executing the card action on the passed in player.
     * 
     * @param target The player who will be targeted with the card action.
     */
    void execute(Player target);

    /**
     * Returns a description of what the card action does.
     * 
     * @return The description.
     */
    String getDescription();

    /**
     * Returns whether the card's action(s) can be executed at any time.
     * 
     * @return A boolean value representing if the action can be executed at any time.
     */
    boolean isAlwaysExecutable();

    /**
     * Returns whether this card action can be executed at the time of method call.
     * 
     * @return A boolean value representing if the action can be executed at the time
     *         of method call.
     */
    boolean isExecutable();
}
