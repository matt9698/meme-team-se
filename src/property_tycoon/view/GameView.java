/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import property_tycoon.model.Card;
import property_tycoon.model.Player;
import property_tycoon.model.Property;
import property_tycoon.model.PropertyLevel;

/**
 *
 * @author Matt
 */
public class GameView extends Application
{
    public static final String FXML_PATH = "GameView.fxml";
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }
        
    @Override
    public void start(Stage stage) throws IOException
    {
        URL fxmlPath = getClass().getResource(FXML_PATH);
        assert fxmlPath != null : 
            String.format(
                "GameView.FXML_PATH resource %s was not found.", 
                FXML_PATH);
        
        Parent root;
        try {
            root = FXMLLoader.load(fxmlPath);
        }
        catch(IOException e) {
            System.err.println(
                "Fatal Application Error: GameView Application could not be started.");
            System.err.format(
                "GameView.FXML_PATH resource %s could not be loaded.\n\n", 
                FXML_PATH);
            throw e;
        }
        
        Property p1 = Property.create("Ollie's Bin", 1, new int[] {1,1,1,1,1,1});
        Property p2 = Property.create("T's House", 10, new int[] {1,1,1,1,1,1});
        Property.Group pg = Property.Group.create(
            "Yellow",
            Color.YELLOW,
            PropertyLevel.Group.REGULAR_LEVELS,
            150, 
            p1, p2);
        
        Button buy = new Button("Buy");
        Button sell = new Button("Sell");
        Button mort = new Button("Mortgage");
        Button unmort = new Button("Unmortgage");
        Player p = new Player();
        buy.setOnAction(e -> p.buy(p1));
        sell.setOnAction(e -> p.sell(p1));
        mort.setOnAction(e -> p.mortgage(p1));
        unmort.setOnAction(e -> p.unmortgage(p1));
        
        GridPane grid = (GridPane)root;
        
        grid.add(PropertyPosition.create(p1), 0, 0);
        grid.add(PropertyPosition.create(p2), 1, 0);
        grid.add(buy, 0, 1);
        grid.add(sell, 1, 1);
        grid.add(mort, 2, 1);
        grid.add(unmort, 3, 1);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }
    
}
