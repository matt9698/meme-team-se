/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package propertyTycoon.model;

import java.util.Arrays;

/**
 *
 * @author matth
 */
class CardImpl extends Card
{
    private final Action[] choices;
    private final String description;
    private Group owner;

    public CardImpl(String description, Action... choices)
    {
        // Check arguments
        assert choices != null : "choice cannot be null.";

        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }
        assert i == choices.length : "choices elements cannot be null.";

        assert choices.length == 1 || description != null :
            "description cannot be null for a choice card.";

        // Assign fields
        if(choices.length > 1) {
            this.description = description;
        }
        else {
            this.description = null;
        }

        // Copy the array so that element references
        // cannot be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);
        
        this.owner = null;
    }

    @Override
    public int getActionCount()
    {
        return choices.length;
    }

    @Override
    public String getActionDescription(int action)
    {
        return choices[action].getDescription();
    }

    @Override
    public String getDescription()
    {
        if(isChoice()) {
            return description;
        }
        else {
            return getActionDescription(0);
        }
    }

    @Override
    public Group getOwner()
    {
        if(owner == null) {
            throw new IllegalStateException("Card has no owner.");
        }
        
        return owner;
    }

    @Override
    public void setOwner(Group g)
    {
        if(owner != null) {
            throw new IllegalStateException("Card already has an owner.");
        }
        
        if(g == null) {
            throw new IllegalArgumentException("group cannot be null.");
        }
        
        owner = g;
    }

    @Override
    public boolean hasOwner()
    {
        return owner != null;
    }

    @Override
    public boolean isSpent()
    {
        return false;
    }

    @Override
    public void spend(int action, Player context)
    {
        if(owner == null) {
            throw new IllegalStateException("Card has no owner Group."
                + " A Card must be assigned an owner before being used.");
        }
        
        choices[action].execute(context);
        owner.replaceCard(this);
    }
}
