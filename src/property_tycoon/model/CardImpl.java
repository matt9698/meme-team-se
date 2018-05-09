/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

/**
 * @author meme-team
 * @version 29/04/2018
 */
final class CardImpl extends Card
{
    private final String description;
    private final boolean isImmediate;
    private final CardAction[] choices;
    private Group group;
    private Player owner;

    public CardImpl(String description, boolean isImmediate, CardAction... choices)
    {
        if(choices == null) {
            throw new IllegalArgumentException("choices should not be null.");
        }
        if(choices.length == 0) {
            throw new IllegalArgumentException("choices should not be empty.");
        }

        for(CardAction action : choices) {
            if(action == null) {
                throw new IllegalArgumentException(
                    "choices should not contain null elements.");
            }
        }

        // If the card is immediate use then check at least
        // one of its actions is always executable.
        if(isImmediate) {
            int i = 0;
            while(i < choices.length && !choices[i].isAlwaysExecutable()) {
                i++;
            }

            if(i == choices.length) {
                throw new IllegalArgumentException(
                    "choices should contain at least one always"
                        + " executable action for an immediate use card.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);

        if(choices.length > 1) {
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null for a choice card.");
            }
            if(description.isEmpty()) {
                throw new IllegalArgumentException(
                    "description should not be empty for a choice card.");
            }
            this.description = description;
        }
        else {
            this.description = null;
        }

        this.isImmediate = isImmediate;

        this.group = null;
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

        return getActionDescription(0);
    }

    @Override
    public Group getGroup()
    {
        if(!isGrouped()) {
            throw new IllegalStateException("Card has no group.");
        }

        return group;
    }

    protected void setGroup(Group group)
    {
        if(isGrouped()) {
            throw new IllegalStateException("Card already has a group.");
        }

        if(group == null) {
            throw new IllegalArgumentException("group should not be null.");
        }

        this.group = group;
        getPropertyChangeSupport().firePropertyChange("group", null, group);
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
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean isUseable(int action)
    {
        if(action < 0 || action >= choices.length) {
            throw new IndexOutOfBoundsException(
                "action index is out of bounds.");
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
        if(!isGrouped()) {
            throw new IllegalStateException("Card has no group.");
        }

        if(action < 0 || action >= choices.length) {
            throw new IndexOutOfBoundsException("action index is out of bounds.");
        }

        if(!isUseable(action)) {
            throw new IllegalArgumentException("action should be useable.");
        }

        choices[action].execute(getOwner());
        getGroup().replace(this);
    }

    @Override
    public boolean isOwned()
    {
        return owner != null;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    @Override
    protected void setOwner(Player owner)
    {
        Player old = getOwner();
        this.owner = owner;
        getPropertyChangeSupport().firePropertyChange("owner", old, owner);
    }
}
