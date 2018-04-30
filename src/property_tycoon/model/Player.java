/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Collections;
import java.util.List;

/**
 * @author Matt
 * @version 29/04/2018
 */
public class Player
{
    private String description;
    private int cash;
    private List<Property> properties;
    private List<Card> cards;
    private Board board;

    public Player()
    {
        // TODO: Implement
    }

    public int getCash()
    {
        return cash;
    }

    public String getDescription()
    {
        return description;
    }

    public List<Property> getProperties()
    {
        return Collections.unmodifiableList(properties);
    }

    public List<Card> getCards()
    {
        return Collections.unmodifiableList(cards);
    }

    private void setCash(int amount)
    {
        assert amount >= 0 : "amount should not be negative.";
        cash = amount;
    }

    public void buy(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }

        if(getCash() < property.getPrice()) {
            throw new IllegalStateException(
                "Player does not have enough cash to buy property.");
        }

        properties.add(property.buy(this));
        setCash(getCash() - property.getPrice());
    }

    public void sell(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }

        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        properties.remove(property);
        setCash(getCash() + property.sell());
    }

    public void mortgage(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        setCash(getCash() + property.mortgage());
    }

    public void unmortgage(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        if(getCash() < property.getPrice()) {
            throw new IllegalStateException(
                "Player does not have enough cash to unmortgage property.");
        }

        setCash(getCash() - property.unmortgage());
    }

    public void upgrade(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        if(getCash() < property.getHousePrice()) {
            throw new IllegalStateException(
                "Player does not have enough cash to upgrade property.");
        }

        setCash(getCash() - property.upgrade());
    }

    public void downgrade(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        setCash(getCash() + property.downgrade());
    }

    public void draw(Card.Group from)
    {
        if(from == null) {
            throw new IllegalArgumentException("from should not be null.");
        }

        cards.add(from.draw(this));
    }

    public void use(Card card)
    {
        use(card, 0);
    }

    public void use(Card card, int action)
    {
        if(card == null) {
            throw new IllegalArgumentException("card should not be null.");
        }

        if(!card.isOwned() || !this.equals(card.getOwner())) {
            throw new IllegalArgumentException(
                "card should be owned by this player.");
        }

        cards.remove(card);
        card.use(action);
    }

    public void moveForward(BoardPosition to)
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveBackward(BoardPosition to)
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveSequential(int by)
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveDirect(BoardPosition to)
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void enterJail()
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void exitJail()
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public boolean isInJail()
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void payRent(Property on, int diceValue)
    {
        if(on == null) {
            throw new IllegalArgumentException("on should not be null.");
        }
        if(!on.isOwned()) {
            throw new IllegalArgumentException("on should have an owner.");
        }

        if(getCash() < on.getRentPrice(diceValue)) {
            throw new IllegalStateException(
                "Player does not have enough cash to pay rent.");
        }

        Player to = on.getOwner();
        to.setCash(to.getCash() + on.getRentPrice(diceValue));
        this.setCash(getCash() - on.getRentPrice(diceValue));
    }
}
