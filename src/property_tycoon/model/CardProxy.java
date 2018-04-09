/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
class CardProxy extends Card
{
    private boolean isValid;
    private final Card realCard;

    public CardProxy(Card realCard)
    {
        // Check arguments
        assert realCard != null : "realCard should not be null.";
        assert realCard.hasGroup() : "realCard should have a group.";

        // Assign fields
        this.realCard = realCard;

        isValid = true;
    }
    @Override
    public int getActionCount()
    {
        return realCard.getActionCount();
    }

    @Override
    public String getActionDescritpion(int action)
    {
        return realCard.getActionDescritpion(action);
    }
    @Override
    public String getDescription()
    {
        return realCard.getDescription();
    }
    @Override
    public Group getGroup()
    {
        return realCard.getGroup();
    }
    @Override
    public void setGroup(Group g)
    {
        throw new UnsupportedOperationException(
            "setGroup() is not supported by proxy cards.");
    }
    @Override
    public boolean hasGroup()
    {
        return realCard.hasGroup();
    }
    @Override
    public boolean isImmediate()
    {
        return realCard.isImmediate();
    }

    @Override
    public boolean isUseable()
    {
        return realCard.isUseable();
    }

    @Override
    public boolean isUseable(int action)
    {
        return realCard.isUseable(action);
    }
    @Override
    public boolean isValid()
    {
        return isValid;
    }

    @Override
    public void use(int action)
    {
        if(!isValid()) {
            throw new IllegalStateException();
        }

        realCard.use(action);
        isValid = false;
    }

}
