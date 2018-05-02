/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player.
 * An array of players is passed in to the board class upon initialisation of a board.
 * Players have three properties; an integer amount of cash, a list of properties
 * that they own and a list of cards that they own.
 * A string description of the player can also be returned.
 * 
 * @author meme-team
 * @version 29/04/2018
 */
public class Player
{
    private String description;
    private int cash;
    private List<Property> properties;
    private List<Card> cards;
    private Board board;

    /**
     * Creates a player object and assigns it a default amount of cash, a description
     * and an empty list of properties they own.
     */
    public Player()
    {
        // TODO: Implement
        cash = 1000;
        description = "ollmor";
        properties = new ArrayList<>();
    }

    /**
     * Returns the amount of cash the player currently owns.
     * 
     * @return An integer value representing the player's current cash.
     */
    public int getCash()
    {
        return cash;
    }

    /**
     * Returns a description of this player object.
     * 
     * @return A string description of this player.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns a list of properties currently owned by this player. 
     * This list cannot be modified upon access by this method.
     * 
     * @return The list of properties this player owns.
     */
    public List<Property> getProperties()
    {
        return Collections.unmodifiableList(properties);
    }

    /**
     * Returns a list of cards currently owned by this player.
     * This list cannot be modified upon access by this method.
     * 
     * @return The list of cards this player owns. 
     */
    public List<Card> getCards()
    {
        return Collections.unmodifiableList(cards);
    }

    private void setCash(int amount)
    {
        assert amount >= 0 : "amount should not be negative.";
        cash = amount;
    }

    /**
     * Changes ownership of the passed in property from the bank to this player.
     * Reduces this player's cash by the corresponding amount.
     * 
     * @param property The property that is to get added to this player's array of 
     *        properties.
     * 
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalStateException if the player doesn't have enough cash to buy
     *         the passed in property.
     */
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

    /**
     * Removes ownership of the passed in property from this player.
     * Increases this player's cash by the corresponding amount.
     * 
     * @param property The property to be removed from this player's property array.
     * 
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalArgumentException if the passed in property is not currently 
     *         owned by this player.
     */
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

    /**
     * Changes the state of the passed in property to be mortgaged.
     * Increases the player's cash by the corresponding amount.
     * 
     * @param property The property to be mortgaged by this player.
     * 
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalArgumentException if the passed in property is not currently 
     *         owned by this player.
     */
    public void mortgage(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }
        
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        setCash(getCash() + property.mortgage());
    }

    /**
     * Changes the state of the passed in property to be unmortgaged.
     * Decreases the player's cash by the corresponding amount.
     * 
     * @param property The property to be mortgaged by this player.
     * 
     * @throws IllegalArgumentException if the passed in property is not currently 
     *         owned by this player.
     * @throws IllegalStateException if the player doesn't have enough cash to unmortgage
     *         the passed in property.
     */
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

    /**
     * Reduces the cash owned by this player by the amount it costs to upgrade
     * the passed in property.
     * 
     * @param property The property to be upgraded by this player.
     * 
     * @throws IllegalArgumentException if the passed in property is not currently 
     *         owned by this player.
     * @throws IllegalStateException if the player doesn't have enough cash to upgrade
     *         the passed in property.
     */
    public void upgrade(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        if(getCash() < property.getImprovementCost()) {
            throw new IllegalStateException(
                "Player does not have enough cash to upgrade property.");
        }

        setCash(getCash() - property.upgrade());
    }
    
    /**
     * Increases the cash owned by this player by the amount it pays to downgrade
     * the passed in property.
     * 
     * @param property The property to be downgraded by this player.
     * 
     * @throws IllegalArgumentException if the passed in property is not currently 
     *         owned by this player.
     */
    public void downgrade(Property property)
    {
        if(!property.isOwned() || !this.equals(property.getOwner())) {
            throw new IllegalArgumentException(
                "property should be owned by this player.");
        }

        setCash(getCash() + property.downgrade());
    }

    /**
     * Adds a card from the passed in card group to this player's hand.
     * 
     * @param from The card group from which the card will be drawn.
     * 
     * @throws IllegalArgumentException if the card group is a null object.
     */
    public void draw(Card.Group from)
    {
        if(from == null) {
            throw new IllegalArgumentException("from should not be null.");
        }

        cards.add(from.draw(this));
    }

    /**
     * Executes the associated action of the passed in card by calling the alternate 
     * use() method.
     * Since this method is only called when the passed in card has a single action, 
     * the index of the action is 0.
     * 
     * @param card The card to be used by this player.
     */
    public void use(Card card)
    {
        use(card, 0);
    }

    /**
     * Executes the passed in action of the passed in card.
     * Removes the card from this player's possession.
     * 
     * @param card The card to be used by this player.
     * @param action The action to be executed by the card.
     * 
     * @throws IllegalArgumentException if the card is a null object.
     * @throws IllegalArgumentException if the passed in card is not currently 
     *         owned by this player.
     */
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

    /**
     * Transfers cash from this player to the passed in property's owner.
     * Rent is determined by the property's group, its level and the dice roll.
     * Dice roll must be included since utilities rely on this information.
     * 
     * @param on The property whose owner must be paid rent.
     * @param diceValue The dice value this player object rolled to land on the property.
     * 
     * @throws IllegalArgumentException if the passed in player is null.
     * @throws IllegalArgumentException if the passed in property doesn't have an owner.
     * @throws IllegalArgumentException if this player doesn't have enough cash to 
     *         pay rent.
     */
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
