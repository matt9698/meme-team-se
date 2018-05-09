/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import property_tycoon.model.Card;
import property_tycoon.model.CornerPosition;
import property_tycoon.model.PropertyPosition;
import property_tycoon.model.TaxPosition;

public class BoardView extends GridPane
{
    private final BoardPositionMapping[] BOARD_POS_MAPPINGS
        = new BoardPositionMapping[] {
            // Left
            new BoardPositionMapping(0, 10),
            new BoardPositionMapping(0, 9, 90d),
            new BoardPositionMapping(0, 8, 90d),
            new BoardPositionMapping(0, 7, 90d),
            new BoardPositionMapping(0, 6, 90d),
            new BoardPositionMapping(0, 5, 90d),
            new BoardPositionMapping(0, 4, 90d),
            new BoardPositionMapping(0, 3, 90d),
            new BoardPositionMapping(0, 2, 90d),
            new BoardPositionMapping(0, 1, 90d),
            // Top
            new BoardPositionMapping(0, 0, 90d),
            new BoardPositionMapping(1, 0, 180d),
            new BoardPositionMapping(2, 0, 180d),
            new BoardPositionMapping(3, 0, 180d),
            new BoardPositionMapping(4, 0, 180d),
            new BoardPositionMapping(5, 0, 180d),
            new BoardPositionMapping(6, 0, 180d),
            new BoardPositionMapping(7, 0, 180d),
            new BoardPositionMapping(8, 0, 180d),
            new BoardPositionMapping(9, 0, 180d),
            // Right
            new BoardPositionMapping(10, 0, 180d),
            new BoardPositionMapping(10, 1, 270d),
            new BoardPositionMapping(10, 2, 270d),
            new BoardPositionMapping(10, 3, 270d),
            new BoardPositionMapping(10, 4, 270d),
            new BoardPositionMapping(10, 5, 270d),
            new BoardPositionMapping(10, 6, 270d),
            new BoardPositionMapping(10, 7, 270d),
            new BoardPositionMapping(10, 8, 270d),
            new BoardPositionMapping(10, 9, 270d),
            // Bottom
            new BoardPositionMapping(10, 10, 270d),
            new BoardPositionMapping(9, 10),
            new BoardPositionMapping(8, 10),
            new BoardPositionMapping(7, 10),
            new BoardPositionMapping(6, 10),
            new BoardPositionMapping(5, 10),
            new BoardPositionMapping(4, 10),
            new BoardPositionMapping(3, 10),
            new BoardPositionMapping(2, 10),
            new BoardPositionMapping(1, 10)
        };

    private property_tycoon.model.Board model;
    private BoardPositionView[] positions;
    private BoardPositionView selected;

    public BoardView(property_tycoon.model.Board model)
    {
        if(model == null) {
            throw new IllegalArgumentException();
        }
        if(model.getPositionCount() != BOARD_POS_MAPPINGS.length) {
            throw new IllegalArgumentException();
        }
        this.model = model;
        
        Region r = new Region();
        r.setBackground(new Background(new BackgroundFill(Color.web("#bfdbae"), CornerRadii.EMPTY, Insets.EMPTY)));
        add(r, 1, 1, 9, 9);

        positions = new BoardPositionView[BOARD_POS_MAPPINGS.length];
        for(int i = 0; i < positions.length; i++) {
            property_tycoon.model.BoardPosition pos = model.getPosition(i);
            if(pos instanceof property_tycoon.model.PropertyPosition) {
                positions[i] = new PropertyPositionView((PropertyPosition)pos);
                positions[i].setRotate(BOARD_POS_MAPPINGS[i].getRotation());
                positions[i].setOnMouseClicked(e -> selected
                    = (BoardPositionView)e.getSource());
                add(new Group(positions[i]), BOARD_POS_MAPPINGS[i].getColumn(),
                    BOARD_POS_MAPPINGS[i].getRow());

            }
            else if(pos instanceof CornerPosition) {
                positions[i] = new CornerPositionView((CornerPosition)pos);
                positions[i].setRotate(BOARD_POS_MAPPINGS[i].getRotation());
                add(new Group(positions[i]), BOARD_POS_MAPPINGS[i].getColumn(),
                    BOARD_POS_MAPPINGS[i].getRow());
            }

            else if(pos instanceof TaxPosition) {
                positions[i] = new TaxPositionView((TaxPosition)pos);
                positions[i].setRotate(BOARD_POS_MAPPINGS[i].getRotation());
                add(new Group(positions[i]), BOARD_POS_MAPPINGS[i].getColumn(),
                    BOARD_POS_MAPPINGS[i].getRow());
            }

            else if(pos instanceof Card.Group) {
                positions[i] = new CardPositionView((Card.Group)pos);
                positions[i].setRotate(BOARD_POS_MAPPINGS[i].getRotation());
                add(new Group(positions[i]), BOARD_POS_MAPPINGS[i].getColumn(),
                    BOARD_POS_MAPPINGS[i].getRow());
            }
        }
    }

    public BoardPositionView getSelectedPosition()
    {
        return selected;
    }
}
