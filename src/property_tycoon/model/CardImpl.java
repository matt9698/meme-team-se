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
    public final Action[] choices;
    public final String description;
    public Group group;

    public CardImpl(String description, Action... choices)
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

        this.group = null;
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
            throw new IllegalStateException("Card has no owner.");
        }
        
        return group;
    }
    @Override
    public void setGroup(Group g)
    {
        if(group != null) {
            throw new IllegalStateException("Card already has an owner."
                + " A Card can only be assigned an owner once.");
        }
        
        if(g == null) {
            throw new IllegalArgumentException("group cannot be null.");
        }
        
        group = g;
    }
    @Override
    public boolean hasGroup()
    {
        return group != null;
    }
    @Override
    public boolean isImmediate()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
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
