/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 * Represents a player.
 * An array of players is passed in to the board class upon initialisation of a board.
 * Players have three properties; an integer amount of cash, a list of properties
 * that they own and a list of cards that they own.
 * A string description of the player can also be returned.
 *
 * @author meme-team
 * @version 08/04/2018
 */
public class Player
{
    public static final int STARTING_CASH = 1500;

    private final ObservableList<Card> cards;
    private final ReadOnlyIntegerWrapper cash;
    private final Color color;
    private Controller controller;
    private final String description;
    private final ObservableList<Property> properties;

    public Player(String description, Color color)
    {
        if(description == null) {
            throw new IllegalArgumentException("description should not be null.");
        }
        if(description.isEmpty()) {
            throw new IllegalArgumentException(
                "description should not be empty.");
        }
        this.description = description;

        if(color == null) {
            throw new IllegalArgumentException("color should not be null.");
        }
        this.color = color;

        cash = new ReadOnlyIntegerWrapper(this, "cash", STARTING_CASH);

        properties = FXCollections.observableArrayList();
        cards = FXCollections.observableArrayList();

        controller = null;
    }

    /**
     * Changes ownership of the passed in property from the bank to this player.
     * Reduces this player's cash by the corresponding amount.
     *
     * @param property The property that is to get added to this player's array of
     *                 properties.
     *
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalStateException    if the player doesn't have enough cash to buy
     *                                  the passed in property.
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

    public ReadOnlyIntegerProperty cashProperty()
    {
        return cash.getReadOnlyProperty();
    }

    /**
     * Increases the cash owned by this player by the amount it pays to downgrade
     * the passed in property.
     *
     * @param property The property to be downgraded by this player.
     *
     * @throws IllegalArgumentException if the passed in property is not currently
     *                                  owned by this player.
     */
    public void downgrade(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }
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

    public ObservableList<Card> getCards()
    {
        return FXCollections.unmodifiableObservableList(cards);
    }

    /**
     * Returns the amount of cash the player currently owns.
     *
     * @return An integer value representing the player's current cash.
     */
    public int getCash()
    {
        return cash.get();
    }

    public Color getColor()
    {
        return color;
    }

    public Controller getController()
    {
        return controller;
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
    public ObservableList<Property> getProperties()
    {
        return FXCollections.unmodifiableObservableList(properties);
    }

    public boolean isControlled()
    {
        return controller != null;
    }

    /**
     * Changes the state of the passed in property to be mortgaged.
     * Increases the player's cash by the corresponding amount.
     *
     * @param property The property to be mortgaged by this player.
     *
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalArgumentException if the passed in property is not currently
     *                                  owned by this player.
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
     * Transfers cash from this player to the passed in property's owner.
     * Rent is determined by the property's group, its level and the dice roll.
     * Dice roll must be included since utilities rely on this information.
     *
     * @param on        The property whose owner must be paid rent.
     * @param diceValue The dice value this player object rolled to land on the property.
     *
     * @throws IllegalArgumentException if the passed in player is null.
     * @throws IllegalArgumentException if the passed in property doesn't have an owner.
     * @throws IllegalArgumentException if this player doesn't have enough cash to
     *                                  pay rent.
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

    /**
     * Removes ownership of the passed in property from this player.
     * Increases this player's cash by the corresponding amount.
     *
     * @param property The property to be removed from this player's property array.
     *
     * @throws IllegalArgumentException if the passed in property is null.
     * @throws IllegalArgumentException if the passed in property is not currently
     *                                  owned by this player.
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
     * Changes the state of the passed in property to be unmortgaged.
     * Decreases the player's cash by the corresponding amount.
     *
     * @param property The property to be mortgaged by this player.
     *
     * @throws IllegalArgumentException if the passed in property is not currently
     *                                  owned by this player.
     * @throws IllegalStateException    if the player doesn't have enough cash to unmortgage
     *                                  the passed in property.
     */
    public void unmortgage(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }
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
     *                                  owned by this player.
     * @throws IllegalStateException    if the player doesn't have enough cash to upgrade
     *                                  the passed in property.
     */
    public void upgrade(Property property)
    {
        if(property == null) {
            throw new IllegalArgumentException("property should not be null.");
        }
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
     * @param card   The card to be used by this player.
     * @param action The action to be executed by the card.
     *
     * @throws IllegalArgumentException if the card is a null object.
     * @throws IllegalArgumentException if the passed in card is not currently
     *                                  owned by this player.
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
        card.use(action);
        cards.remove(card);
    }

    protected void setController(Controller controller)
    {
        if(isControlled()) {
            throw new IllegalStateException("Player already has a controller.");
        }
        if(controller == null) {
            throw new IllegalArgumentException("controller should not be null.");
        }
        this.controller = controller;
    }

    private void setCash(int amount)
    {
        assert amount >= 0 : "amount should not be negative.";
        cash.set(amount);
    }

    public static abstract class Controller
    {
        private final Player player;

        public Controller(Player player)
        {
            this.player = player;
        }

        public Player getPlayer()
        {
            return player;
        }
    }
}
