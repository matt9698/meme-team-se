/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package prototyping;

/**
 *
 * @author matth
 */
public interface EventHandler<T extends Event>
{
    void handle(T event);
}
