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
import javafx.stage.StageStyle;
import property_tycoon.model.Card;
import property_tycoon.model.CornerPosition.CornerType;
import property_tycoon.model.FakeAction;
import property_tycoon.model.Player;
import property_tycoon.model.Property;
import property_tycoon.model.PropertyLevel;
import property_tycoon.model.PropertyLevel.Group;

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
    
    private BoardView board;
    private Property[] properties;
    private Card[] cards;
    private Card.Group potLuck, opportunityKnocks;
    private property_tycoon.model.BoardPosition[] positions;

    @Override
    public void start(Stage stage) throws IOException
    {
        
        createProperties();
        createPropertyGroups();
        createCards();
        createCardGroups();
        createBoardPositions();
        
         
        Player p = new Player();

        Player[] players = new Player[] { p };

        property_tycoon.model.Board b = new property_tycoon.model.Board(positions, players);

        Label selected = new Label("No property selected");
        
        board = new BoardView(b);
        board.setRotate(270);
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
        
        PlayerView pv = new PlayerView(players[0]);
        bp.setRight(pv);
        
        ScrollPane sp = new ScrollPane(bp);
        

        Scene scene = new Scene(sp);


        stage.setTitle("Property Tycoon");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    private void createBoardPositions()
    {
        positions = new property_tycoon.model.BoardPosition[] {
            
            new property_tycoon.model.CornerPosition(CornerType.GO), 
            new property_tycoon.model.PropertyPosition(properties[0]), 
            potLuck,
            new property_tycoon.model.PropertyPosition(properties[1]),
            new property_tycoon.model.TaxPosition(),
            
            
            new property_tycoon.model.PropertyPosition(properties[24]),
            new property_tycoon.model.PropertyPosition(properties[2]),
            opportunityKnocks,
            new property_tycoon.model.PropertyPosition(properties[3]),
            new property_tycoon.model.PropertyPosition(properties[4]),
            
            
            new property_tycoon.model.CornerPosition(CornerType.JAIL),
            new property_tycoon.model.PropertyPosition(properties[5]),
            new property_tycoon.model.PropertyPosition(properties[22]),
            new property_tycoon.model.PropertyPosition(properties[6]),
            new property_tycoon.model.PropertyPosition(properties[7]),
            
            
            new property_tycoon.model.PropertyPosition(properties[25]),
            new property_tycoon.model.PropertyPosition(properties[8]),
            potLuck,
            new property_tycoon.model.PropertyPosition(properties[9]),
            new property_tycoon.model.PropertyPosition(properties[10]),
            
            
            new property_tycoon.model.CornerPosition(CornerType.FREE_PARKING),
            new property_tycoon.model.PropertyPosition(properties[11]),
            opportunityKnocks,
            new property_tycoon.model.PropertyPosition(properties[12]),
            new property_tycoon.model.PropertyPosition(properties[13]),
            
            
            new property_tycoon.model.PropertyPosition(properties[26]),
            new property_tycoon.model.PropertyPosition(properties[14]),
            new property_tycoon.model.PropertyPosition(properties[15]),
            new property_tycoon.model.PropertyPosition(properties[23]),
            new property_tycoon.model.PropertyPosition(properties[16]),
            
            
            new property_tycoon.model.CornerPosition(CornerType.GO_TO_JAIL),
            new property_tycoon.model.PropertyPosition(properties[17]),
            new property_tycoon.model.PropertyPosition(properties[18]),
            potLuck,
            new property_tycoon.model.PropertyPosition(properties[19]),
            
            
            new property_tycoon.model.PropertyPosition(properties[27]),
            opportunityKnocks,
            new property_tycoon.model.PropertyPosition(properties[20]),
            new property_tycoon.model.TaxPosition(),
            new property_tycoon.model.PropertyPosition(properties[21]),
            
          };
    }

    private void createCardGroups()
    {
        potLuck = Card.Group.create("Pot Luck", cards[0]);
        opportunityKnocks = Card.Group.create("Opportunity Knocks",
            cards[1]);
    }

    private void createCards()
    {
        cards = new Card[]{
            Card.create(new FakeAction(), true),
            Card.create(new FakeAction(), true)
        };
        
    }

    private void createProperties()
    {
        properties = new Property[] {
            //Properties
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
            Property.create("Turing Heights", 400, new int[] {1, 1, 1, 1, 1, 1}),
            
            //Utilities
            Property.createUtility("Tesla Power Co", 150),
            Property.createUtility("Edison Water", 150),
            
            //Stations
            Property.createStation("Brighton Station", 200),
            Property.createStation("Hove Station", 200),
            Property.createStation("Falmer Station", 200),
            Property.createStation("Lewes Station", 200)
        };
    }

    private void createPropertyGroups()
    {
        Property.Group browns = Property.Group.create("Browns", Color.SIENNA, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[0], properties[1]);
        
        Property.Group blues = Property.Group.create("Blues", Color.LIGHTSTEELBLUE, PropertyLevel.Group.REGULAR_LEVELS,
            30, properties[2], properties[3], properties[4]);
        
        Property.Group purples = Property.Group.create("Purples", Color.PALEVIOLETRED,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[5], properties[6], properties[7]);

         Property.Group oranges = Property.Group.create("Oranges", Color.CORAL,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[8], properties[9], properties[10]);
         
         Property.Group yellows = Property.Group.create("Yellows", Color.KHAKI,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[11], properties[12], properties[13]);
         
         Property.Group reds = Property.Group.create("Reds", Color.CRIMSON,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[14], properties[15], properties[16]);
         
         Property.Group greens = Property.Group.create("Greens", Color.SEAGREEN,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[17], properties[18], properties[19]);
         
         Property.Group deepBlues = Property.Group.create("Deep Blues", Color.DARKSLATEBLUE,
           PropertyLevel.Group.REGULAR_LEVELS, 30, properties[20], properties[21]);
        
         Property.Group utilities = Property.Group.create("Utilities",
             Color.web("#bfdbae"), Group.UTILITY_LEVELS, 150, properties[22], properties[23]);
         
          Property.Group stations = Property.Group.create("Stations",
             Color.web("#bfdbae"), Group.STATION_LEVELS, 150, properties[24], properties[25], properties[26], properties[27]);
    }
}
