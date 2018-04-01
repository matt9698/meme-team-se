/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model.card;

import java.util.Arrays;
import propertyTycoon.model.Player;

/**
 *
 * @author matth
 */
class CardImpl extends Card
{
    private final Action[] choices;
    private final Group owner;
    
    public CardImpl(Group owner, Action... choices)
    {
        // Argument checking       
        assert owner != null : "owner cannot be null.";
        assert choices != null : "choice cannot be null.";
        
        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }
        assert i == choices.length : "choices elements cannot be null.";
        
        // Assign arguments to fields
        this.owner = owner;
        
        // Copy the array so that element references
        // cannot be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);
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
