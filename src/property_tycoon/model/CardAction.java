/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * @author Matt
 * @version 29/04/2018
 */
public interface CardAction
{
    void execute(Player target);

    String getDescription();

    boolean isAlwaysExecutable();

    boolean isExecutable();
}
