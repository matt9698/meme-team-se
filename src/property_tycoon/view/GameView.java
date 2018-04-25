/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import property_tycoon.model.Property;

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
        Property.Group g = new PropertyPosition.Group(
            "Yellow", 
            Color.web("ff0000"),
            150, 
            p1, p2);
        
        ((GridPane)root).add(PropertyPosition.create(p1), 0, 0);
        ((GridPane)root).add(PropertyPosition.create(p2), 1, 0);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Property Tycoon");
        stage.setScene(scene);
        stage.show();
    }
}
