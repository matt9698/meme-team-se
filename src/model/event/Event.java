/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package model.event;

import java.util.EventObject;

/**
 *
 * @author matth
 */
public class Event<T> extends EventObject
{
    public Event(T source)
    {
        if(source == null) {
          throw new IllegalArgumentException("source can not be null");
        }

        super(source);
    }

    @Override
    public T getSource()
    {
        return (T)super.source;
    }
}
