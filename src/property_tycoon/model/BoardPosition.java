/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * Represents a board position.
 * This class is an interface and cannot be instantiated at all.
 * 
 * @author meme-team
 * @version 29/04/2018
 */
public interface BoardPosition
{
    /**
     * Represents an action taken when the passed in player lands on and exits this
     * board position object.
     * 
     * @param player The player to land on & exit the board position.
     */
    void step(Player player);

    /**
     * Represents an action taken when the passed in player lands on this board position
     * object.
     * 
     * @param player The player to land on the board position.
     */
    void land(Player player);
}
