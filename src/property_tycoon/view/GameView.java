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
import property_tycoon.model.BoardPosition;
import property_tycoon.model.Card;
import property_tycoon.model.CardAction;
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
    private int propertyIndex = 0, utilityIndex = 22, stationIndex = 24;
    private Player[] players;

    @Override
    public void start(Stage stage) throws IOException
    {
        createPlayers();
        createProperties();
        createPropertyGroups();
        createCards();
        createCardGroups();
        createBoardPositions();
        
         
        

        property_tycoon.model.Board b = new property_tycoon.model.Board(positions, players);

        Label selected = new Label("No property selected");
        
        board = new BoardView(b);
        board.setRotate(270);
        board.setOnMouseClicked(e -> selected.setText(
            board.getSelectedPosition() instanceof PropertyPositionView 
                ? ((PropertyPositionView)board.getSelectedPosition()).getModel().getDescription() + " selected" : "No property selected" ));
        
        BorderPane bp = new BorderPane(board);
        
        Button buy = new Button("Buy");
        buy.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { players[0].buy(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});
        
        Button sell = new Button("Sell");
        sell.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { players[0].sell(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});
        
        HBox buttonBar = new HBox(selected, buy, sell);
        bp.setBottom(buttonBar);
        
        ScrollPane sp = new ScrollPane(bp);
        

        Scene scene = new Scene(sp);

        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }

    private property_tycoon.model.PropertyPosition addnextProperty()
    {
        property_tycoon.model.PropertyPosition nextProperty = new property_tycoon.model.PropertyPosition(properties[propertyIndex]);
        propertyIndex++;
        return nextProperty;
    }
    
    private property_tycoon.model.PropertyPosition addnextStation()
    {
        property_tycoon.model.PropertyPosition nextSation = new property_tycoon.model.PropertyPosition(properties[stationIndex]);
        stationIndex++;
        return nextSation;
    }
    
    private property_tycoon.model.PropertyPosition addnextUtility()
    {
        property_tycoon.model.PropertyPosition nextUtility = new property_tycoon.model.PropertyPosition(properties[utilityIndex]);
        utilityIndex++;
        return nextUtility;
    }

    private void createBoardPositions()
    {
        property_tycoon.model.CornerPosition go = new property_tycoon.model.CornerPosition(CornerType.GO);
        property_tycoon.model.CornerPosition jail = new property_tycoon.model.CornerPosition(CornerType.JAIL);
        property_tycoon.model.CornerPosition freeParking = new property_tycoon.model.CornerPosition(CornerType.FREE_PARKING);
        property_tycoon.model.CornerPosition goToJail = new property_tycoon.model.CornerPosition(CornerType.GO_TO_JAIL);
        
        
        positions = new property_tycoon.model.BoardPosition[] {
            
            
            //Row one
            go, addnextProperty(), potLuck, addnextProperty(), new property_tycoon.model.TaxPosition(),
            addnextStation(), addnextProperty(), opportunityKnocks, addnextProperty(), addnextProperty(),
            
            //Row two
            jail, addnextProperty(), addnextUtility(), addnextProperty(), addnextProperty(),
            addnextStation(), addnextProperty(), potLuck, addnextProperty(), addnextProperty(),
            
            //Row three
            freeParking, addnextProperty(), opportunityKnocks, addnextProperty(), addnextProperty(),
            addnextStation(), addnextProperty(), addnextProperty(), addnextUtility(), addnextProperty(),
            
            //Row four
            goToJail, addnextProperty(), addnextProperty(), potLuck, addnextProperty(),
            addnextStation(), opportunityKnocks, addnextProperty(), new property_tycoon.model.TaxPosition(), addnextProperty(),
            
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

    private void createPlayers()
    {
        Player p1 = new Player();
        Player p2 = new Player();
        players = new Player[] { p1, p2 };
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
        
         Property.Group utilities = Property.Group.create("Utilities",
             Color.BLACK, Group.UTILITY_LEVELS, 150, properties[22], properties[23]);
         
          Property.Group stations = Property.Group.create("Stations",
             Color.BLACK, Group.STATION_LEVELS, 150, properties[24], properties[25], properties[26], properties[27]);
    }
}
