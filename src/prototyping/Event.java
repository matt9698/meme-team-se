/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package prototyping;

import java.util.EventObject;

/**
 *
 * @author matth
 */
public class Event<T> extends EventObject
{
    public Event(T source)
    {
        super(source);
    }

    @Override
    public T getSource()
    {
        return (T)super.source;
    }
}
