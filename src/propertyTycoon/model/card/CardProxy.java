/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model.card;

import propertyTycoon.model.Player;

/**
 *
 * @author matth
 */
class CardProxy extends Card
{
    private boolean isSpent;
    private final Card realCard;
    
    public CardProxy(Card realCard)
    {
        // Argument checking        
        assert realCard != null : "realCard cannot be null.";
        
        // Assign arguments to fields
        this.realCard = realCard;
        
        // Assign default values to fields
        isSpent = false;
    }
    
    @Override
    public int getActionCount()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public String getActionDescription(int action)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public String getDescription()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean isSpent()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void spend(int action, Player context)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }   
}
