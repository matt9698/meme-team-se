/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * @author Matt
 * @version 29/04/2018
 */
public interface BoardPosition
{
    void step(Player player);

    void land(Player player);
}
