/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

/**
 * @author Matt
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
        assert choices != null : "choices should not be null.";
        assert choices.length != 0 : "choices should not be empty.";

        // TODO: Convert into lambda based foreach in order to  avoid loop exceution when assertions are disabled.
        for(CardAction action : choices) {
            assert action != null : "choices should not contain null elements.";
        }

        // TODO: If the card is immediate use then check at least
        // one of its actions is always executable.

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.choices = Arrays.copyOf(choices, choices.length);

        if(choices.length > 1) {
            assert description != null :
                "description should not be null for a choice card.";
            assert !description.isEmpty() :
                "description should not be empty for a choice card.";

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
        if(!isOwned()) {
            throw new IllegalStateException("Card has no owner.");
        }

        return owner;
    }

    @Override
    protected void setOwner(Player owner)
    {
        this.owner = owner;
    }
}
