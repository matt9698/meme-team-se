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
    private final Group owner;

    public CardImpl(Group owner, String description, Action... choices)
    {
        // Check arguments       
        assert owner != null : "owner cannot be null.";
        assert choices != null : "choice cannot be null.";

        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }
        assert i == choices.length : "choices elements cannot be null.";

        assert choices.length == 1 || description != null :
            "description cannot be null for a choice card.";

        // Assign fields
        this.owner = owner;

        if(choices.length > 1) {
            this.description = description;
        }
        else {
            this.description = null;
        }

        // Copy the array so that element references
        // cannot be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);
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
        return owner;
    }

    @Override
    public boolean isSpent()
    {
        return false;
    }

    @Override
    public void spend(int action, Player context)
    {
        choices[action].execute(context);
        owner.replaceCard(this);
    }
}
