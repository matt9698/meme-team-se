/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matt
 * @version 29/04/2018
 */
public abstract class Card
{
    public static Card create()
    {
        // TODO: Implement
    }

    private PropertyChangeSupport pcs;

    protected PropertyChangeSupport getPropertyChangeSupport()
    {
        return pcs;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        pcs.removePropertyChangeListener(listener);
    }

    public abstract int getActionCount();

    public abstract String getActionDescription(int action);

    public abstract String getDescription();

    public abstract Group getGroup();

    protected abstract void setGroup(Group group);

    public abstract boolean isGrouped();

    public final boolean isChoice()
    {
        return getActionCount() > 1;
    }

    public abstract boolean isImmediate();

    public abstract boolean isUseable();

    public abstract boolean isUseable(int action);

    public abstract boolean isValid();

    public final void use()
    {
        if(isChoice()) {
            throw new UnsupportedOperationException(
                "Choice cards do not support use().");
        }

        use(0);
    }

    public abstract void use(int action);

    public abstract boolean isOwned();

    public abstract Player getOwner();

    protected abstract void setOwner(Player owner);

    public static final class Group
    {
        public static Group create(String description, Card... cards)
        {
            Group group = new Group(description, cards);
            for(Card card : cards) {
                card.setGroup(group);
            }

            return group;
        }

        private final String description;
        private final List<Card> cards;

        private Group(String description, Card... cards)
        {
            if(description == null) {
                throw new IllegalArgumentException(
                    "description should not be null.");
            }
            if(description.isEmpty()) {
                throw new IllegalArgumentException(
                    "description should not be empty.");
            }
            this.description = description;

            if(cards == null) {
                throw new IllegalArgumentException(
                    "cards should not be null.");
            }
            if(cards.length == 0) {
                throw new IllegalArgumentException(
                    "cards should not be empty.");
            }

            for(Card card : cards) {
                if(card == null) {
                    throw new IllegalArgumentException(
                        "cards should not contain null elements.");
                }
                if(card.isGrouped()) {
                    throw new IllegalArgumentException(
                        "properties should not contain elements that already have a group.");
                }
            }

            // Copy the array so that elements cannot
            // be subsequently modified by external code.
            this.cards = new ArrayList<Card>(Arrays.asList(cards));
        }

        public String getDescription()
        {
            return description;
        }

        public Card draw(Player drawer)
        {
            if(drawer == null) {
                throw new IllegalArgumentException("drawer should not be null.");
            }

            // TODO: Deal with empty card list somehow

            Card card = cards.remove(0);
            card.setOwner(drawer);
            return new CardProxy(card);
        }

        protected void replace(Card card)
        {
            if(card == null) {
                throw new IllegalArgumentException("card should not be null.");
            }

            if(!card.isGrouped() || !card.getGroup().equals(this)) {
                throw new IllegalArgumentException(
                    "card should be a member of this group.");
            }

            card.setOwner(null);
            cards.add(card);
        }

        public void shuffle()
        {
            // TODO: Implement
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    }
}
