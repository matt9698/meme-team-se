/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model;

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
        return realCard.getActionCount();
    }

    @Override
    public String getActionDescription(int action)
    {
        return realCard.getActionDescription(action);
    }

    @Override
    public String getDescription()
    {
        return realCard.getDescription();
    }

    @Override
    public Group getOwner()
    {
        return realCard.getOwner();
    }

    @Override
    public boolean isSpent()
    {
        return isSpent;
    }

    @Override
    public void spend(int action, Player context)
    {
        if(isSpent()) {
            throw new IllegalStateException("Card has already been spent.");
        }
        
        realCard.spend(action, context);
        isSpent = true;
    }   
}
