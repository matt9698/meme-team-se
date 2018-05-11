/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import property_tycoon.model.BoardPosition;

/**
 * Control representing a position on the game board.
 *
 * @author meme-team
 * @version 01/05/2018
 */
 public abstract class BoardPositionView extends StackPane
 {
    public abstract BoardPosition getModel();
    
    public BoardPositionView(){
        setBackground(new Background(new BackgroundFill(Color.web("#bfdbae"), CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefWidth(96);
        setPrefHeight(146);
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, new BorderWidths(4, 2, 4, 2))));
    }
 }
