/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.scene.layout.StackPane;
import property_tycoon.model.BoardPosition;

/**
 * Control representing a position on the game board.
 *
 * @author Matt
 * @version 01/05/2018
 */
 public abstract class BoardPositionView extends StackPane
 {
    public abstract BoardPosition getModel();
 }
