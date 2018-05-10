/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.scene.control.Label;
import property_tycoon.model.BoardPosition;
import property_tycoon.model.Card;

/**
 *
 * @author adam
 */
public class CardPositionView extends BoardPositionView
{
    
    private Card.Group cardGroup;
    
    public CardPositionView(Card.Group cardGroup){
        this.cardGroup=cardGroup;
        getChildren().add(new Label(cardGroup.getDescription()));
    }
    
    @Override
    public Card.Group getModel()
    {
        return cardGroup;
    }   
}
