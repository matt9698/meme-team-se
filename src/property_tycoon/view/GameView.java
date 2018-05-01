/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import property_tycoon.model.Player;
import property_tycoon.model.Property;
import property_tycoon.model.PropertyLevel;

/**
 *
 * @author Matt
 */
public class GameView extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        Property[] properties = new Property[] {
            Property.create("Crapper Street", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Gangsters Paradise", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Weeping Angel", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Potts Avenue", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Nardole Drive", 120, new int[] {1, 1, 1, 1, 1, 1})
        };
        
        
        
        Property.Group browns = Property.Group.create("Browns", Color.BROWN, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[0], properties[1]);
        
        Property.Group blues = Property.Group.create("Blues", Color.BLUE, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[2], properties[3], properties[4]);

        Player p = new Player();

        property_tycoon.model.BoardPosition[] positions = new property_tycoon.model.BoardPosition[] {
            new property_tycoon.model.PropertyPosition(properties[0]),
            new property_tycoon.model.PropertyPosition(properties[1]),
            new property_tycoon.model.PropertyPosition(properties[2]),
            new property_tycoon.model.PropertyPosition(properties[3]),
            new property_tycoon.model.PropertyPosition(properties[4]),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition(),
            new property_tycoon.model.FakePosition()
          };
        Player[] players = new Player[] { p };

        property_tycoon.model.Board b = new property_tycoon.model.Board(positions, players);

        Board board = new Board(b);

        Scene scene = new Scene(board);

        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }

}
