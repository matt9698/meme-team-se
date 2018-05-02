/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * @author Matt
 * @version 29/04/2018
 */
class CardProxy extends Card
{
    private final Card realCard;
    private boolean isValid;

    public CardProxy(Card realCard)
    {
        assert realCard != null : "realCard should not be null.";
        assert realCard.isGrouped() : "realCard should have a group.";
        this.realCard = realCard;

        isValid = true;

        // Forward events from realCard to listeners
        realCard.addPropertyChangeListener(
            e -> this.getPropertyChangeSupport().firePropertyChange(e));
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
    public Group getGroup()
    {
        return realCard.getGroup();
    }

    @Override
    public Player getOwner()
    {
        return realCard.getOwner();
    }

    @Override
    protected void setOwner(Player owner)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean isGrouped()
    {
        return realCard.isGrouped();
    }

    @Override
    public boolean isImmediate()
    {
        return realCard.isImmediate();
    }

    @Override
    public boolean isOwned()
    {
        return realCard.isOwned();
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
            throw new IllegalStateException("Proxy card is not valid.");
        }

        realCard.use(action);
        isValid = false;
    }

    @Override
    protected void setGroup(Group group)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

}
