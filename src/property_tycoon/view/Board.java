/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

/**
 * Control representing a property position on the game board.
 *
 * @author Matt
 * @version 01/05/2018
 */
 public class Board extends GridPane
 {
     private property_tycoon.model.Board model;

    public Board(property_tycoon.model.Board model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;
    }
 }
