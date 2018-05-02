/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import property_tycoon.model.Property;
import javafx.scene.Group;


/**
 * @author meme-team
 * @version 29/04/2018
 */
public class Board extends GridPane
{
   private BoardPositionMapping[] BOARD_POS_MAPPINGS =
      new BoardPositionMapping[] {
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
   private BoardPosition[] positions;

   public Board(property_tycoon.model.Board model)
   {
     if(model == null) {
        throw new IllegalArgumentException();
     }
     if(model.getPositionCount() != BOARD_POS_MAPPINGS.length) {
        throw new IllegalArgumentException();
     }
      this.model = model;

      positions = new BoardPosition[BOARD_POS_MAPPINGS.length];
      for(int i = 0; i < positions.length; i++) {
          property_tycoon.model.BoardPosition pos = model.getPosition(i);
          if(pos instanceof property_tycoon.model.PropertyPosition) {
              positions[i] = PropertyPosition.create((Property)pos);
              positions[i].setRotate(BOARD_POS_MAPPINGS[i].getRotation());
              add(new Group(positions[i]), BOARD_POS_MAPPINGS[i].getColumn(), BOARD_POS_MAPPINGS[i].getRow());
             
          }
      }
   }
}
