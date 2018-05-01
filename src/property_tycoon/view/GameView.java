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
        Property p1 = Property.create("Ollie's Bin", 10, new int[] { 0, 0, 0, 0, 0, 0 });
        Property p2 = Property.create("T's Crib", 100, new int[] { 0, 0, 0, 0, 0, 0 });
        Property.Group g = Property.Group.create("Red", Color.RED, PropertyLevel.Group.REGULAR_LEVELS,
            30, p1, p2);
        
        Player p = new Player();
        
        property_tycoon.model.BoardPosition[] properties = new property_tycoon.model.PropertyPosition[] { new property_tycoon.model.PropertyPosition(p1), new property_tycoon.model.PropertyPosition(p2) };
        Player[] players = new Player[] { p };
        
        property_tycoon.model.Board b = new property_tycoon.model.Board(properties, players);
        
        Board board = Board.create(b);
        
        Scene scene = new Scene(board);
        
        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }
    
}
