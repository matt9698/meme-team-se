/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    
    private Board board;

    @Override
    public void start(Stage stage) throws IOException
    {
        Property[] properties = new Property[] {
            Property.create("Crapper Street", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Gangsters Paradise", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Weeping Angel", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Potts Avenue", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Nardole Drive", 120, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Skywalker Drive", 140, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Wookie Hole", 140, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Rey Lane", 160, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Cooper Drive", 180, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Wolowitz Street", 180, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Penny Lane", 200, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Yue Fei Square", 220, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Mulan Rouge", 220, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Han Xin Gardens", 240, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Kirk Close", 260, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Picard Avenue", 260, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Crusher Creek", 280, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Sirat Mews", 300, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Ghengis Cresent", 300, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Ibis Cresent", 320, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Hawking Way", 350, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Turing Heights", 400, new int[] {1, 1, 1, 1, 1, 1})
        };
        
        
        
        Property.Group browns = Property.Group.create("Browns", Color.BROWN, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[0], properties[1]);
        
        Property.Group blues = Property.Group.create("Blues", Color.BLUE, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[2], properties[3], properties[4]);
        
        Property.Group purples = Property.Group.create("Purples", Color.PURPLE,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[5], properties[6], properties[7]);

         Property.Group oranges = Property.Group.create("Oranges", Color.ORANGE,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[8], properties[9], properties[10]);
         
         Property.Group yellows = Property.Group.create("Yellows", Color.YELLOW,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[11], properties[12], properties[13]);
         
         Property.Group reds = Property.Group.create("Reds", Color.RED,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[14], properties[15], properties[16]);
         
         Property.Group greens = Property.Group.create("Greens", Color.GREEN,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[17], properties[18], properties[19]);
         
         Property.Group deepBlues = Property.Group.create("Deep Blues", Color.NAVY,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[20], properties[21]);
        
        Player p = new Player();

        property_tycoon.model.BoardPosition[] positions = new property_tycoon.model.BoardPosition[] {
            new property_tycoon.model.PropertyPosition(properties[0]),
            new property_tycoon.model.PropertyPosition(properties[1]),
            new property_tycoon.model.PropertyPosition(properties[2]),
            new property_tycoon.model.PropertyPosition(properties[3]),
            new property_tycoon.model.PropertyPosition(properties[4]),
            new property_tycoon.model.PropertyPosition(properties[5]),
            new property_tycoon.model.PropertyPosition(properties[6]),
            new property_tycoon.model.PropertyPosition(properties[7]),
            new property_tycoon.model.PropertyPosition(properties[8]),
            new property_tycoon.model.PropertyPosition(properties[9]),
            new property_tycoon.model.PropertyPosition(properties[10]),
            new property_tycoon.model.PropertyPosition(properties[11]),
            new property_tycoon.model.PropertyPosition(properties[12]),
            new property_tycoon.model.PropertyPosition(properties[13]),
            new property_tycoon.model.PropertyPosition(properties[14]),
            new property_tycoon.model.PropertyPosition(properties[15]),
            new property_tycoon.model.PropertyPosition(properties[16]),
            new property_tycoon.model.PropertyPosition(properties[17]),
            new property_tycoon.model.PropertyPosition(properties[18]),
            new property_tycoon.model.PropertyPosition(properties[19]),
            new property_tycoon.model.PropertyPosition(properties[20]),
            new property_tycoon.model.PropertyPosition(properties[21]),
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

        Label selected = new Label("No property selected");
        
        board = new Board(b);
        board.setOnMouseClicked(e -> selected.setText(
            board.getSelectedPosition() instanceof PropertyPositionView 
                ? ((PropertyPositionView)board.getSelectedPosition()).getModel().getDescription() + " selected" : "No property selected" ));
        
        BorderPane bp = new BorderPane(board);
        
        Button buy = new Button("Buy");
        buy.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { p.buy(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});
        
        Button sell = new Button("Sell");
        sell.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { p.sell(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});
        
        HBox buttonBar = new HBox(selected, buy, sell);
        bp.setBottom(buttonBar);
        
        ScrollPane sp = new ScrollPane(bp);
        

        Scene scene = new Scene(sp);

        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }
}
