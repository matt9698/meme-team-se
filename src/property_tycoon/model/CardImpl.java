/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

/**
 *
 * @author Matt
 */
class CardImpl extends Card
{
    private final Action[] choices;
    private final String description;
    private Group group;
    private final boolean isImmediate;

    public CardImpl(String description, boolean isImmediate, Action... choices)
    {
        // Check arguments
        assert choices != null : "choices should not be null.";
        assert choices.length != 0 : "choices should not be empty.";

        // Check choices elements aren't null
        int i = 0;
        while(i < choices.length && choices[i] != null) {
            i++;
        }

        assert i == choices.length : "choices should not contain null elements.";

        if(choices.length == 1) {
            assert description != null :
                "description cannot be null for a choice card.";
            assert !description.isEmpty() :
                "description cannot be empty for a choice card.";
        }
        
        // If the card is immediate use then check at least
        // one of its actions is always executable.
        if(isImmediate) {
            while(i < choices.length && !choices[i].isAlwaysExecutable()) {
                i++;
            }
            
            assert i != choices.length : 
                "actions should contain at least one always"
                    + " executable action for an immediate use card.";
        }
            
        // Assign fields
        if(choices.length > 1) {
            this.description = description;
        }
        else {
            this.description = null;
        }

        // Copy the array so that element cannot
        // be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);
        
        this.isImmediate = isImmediate;

        group = null;
    }
    @Override
    public int getActionCount()
    {
        return choices.length;
    }

    @Override
    public String getActionDescritpion(int action)
    {
        if(action < 0 || action >= choices.length) {
            throw new IndexOutOfBoundsException("action index is out of bounds.");
        }

        return choices[action].getDescription();
    }
    @Override
    public String getDescription()
    {
        if(isChoice()) {
            return description;
        }
        
        return getActionDescritpion(0);
    }
    @Override
    public Group getGroup()
    {
        if(group == null) {
            throw new IllegalStateException("Card has no group.");
        }
        
        return group;
    }
    @Override
    public void setGroup(Group g)
    {
        if(group != null) {
            throw new IllegalStateException("Card is already in a group.\n"
                + "A Card can only be assigned a group once.");
        }
        
        if(g == null) {
            throw new IllegalArgumentException("group cannot be null.");
        }
        
        group = g;
    }
    @Override
    public boolean isGrouped()
    {
        return group != null;
    }
    @Override
    public boolean isImmediate()
    {
        return isImmediate;
    }
    
    @Override
    public boolean isUseable()
    {
        int i = 0;
        while(choices[i].isExecutable() && i < choices.length) {
            i++;
        }

        return i == choices.length;
    }

    @Override
    public boolean isUseable(int action)
    {
        if(action < 0 || action >= choices.length) {
            throw new IndexOutOfBoundsException("action index is out of bounds.");
        }

        return choices[action].isExecutable();
    }
    @Override
    public boolean isValid()
    {
        throw new UnsupportedOperationException(
            "isValid() is not supported by real cards.");
    }

    @Override
    public void use(int action)
    {
        if(group == null) {
            throw new IllegalStateException("Card has no owner Group."
                + " A Card must be assigned an owner before being used.");
        }

        if(action < 0 || action >= choices.length) {
            throw new IndexOutOfBoundsException("action index is out of bounds.");
        }

        if(!choices[action].isExecutable()) {
            throw new IllegalArgumentException("action should be useable.");
        }

        choices[action].execute();
        getGroup().replace(this);
    }
}
