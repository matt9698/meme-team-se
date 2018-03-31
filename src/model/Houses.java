/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package model;

/**
 *
 * @author matth
 */
public class Houses implements Asset
{
    private int quantity;
    private boolean unbounded;
    
    public Houses()
    {
        quantity = 0;
        unbounded = true;
    }
    
    public Houses(int quantity)
    {
        this.quantity = quantity;
        unbounded = false;
    }
}
