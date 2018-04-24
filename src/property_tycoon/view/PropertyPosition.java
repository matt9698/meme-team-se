/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import property_tycoon.model.Property;

/**
 * FXML Controller class
 *
 * @author matth
 */
public class PropertyPosition extends BorderPane
{   
    private static final String FXML_PATH = "PropertyPosition.fxml";

    private Property p;
    
    public static PropertyPosition create(Property p)
    {
        // Check arguments
        if(!(p.getGroup() instanceof Group)) {
            throw new IllegalArgumentException(
                "p.getGroup() should return an instance of"
                    + " property_tycoon.view.PropertyPosition$Group");
        }
        
        // Create instance
        PropertyPosition pos = new PropertyPosition(p);
        
        // Initialise instance from FMXL
        URL fxmlPath = pos.getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "PropertyPosition.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setRoot(pos);
        loader.setController(pos);

        try {
            loader.load();
        }
        catch(IOException e) {
            throw new UncheckedIOException(
                String.format(
                    "PropertyPosition.FXML_PATH resource %s could not be loaded.",
                    FXML_PATH),
                e);
        }

        return pos;
    }

    private PropertyPosition(Property p)
    {
        this.p = p;
    }
    
    private String getDescription()
    {
        return p.getDescription();
    }
    
    private int getValue()
    {
        return p.getValue();
    }
    
    public static class Group extends Property.Group
    {
        private Color c;
        
        public Group(String description, Color color, int houseCost, Property... properties)
        {
            super(description, houseCost, properties);
            
            // Check arguments
            if(color == null) {
                throw new IllegalArgumentException("color should not be null.");
            }
            
            // Assign fields
            c = color;
        }
        
        public Color getColor()
        {
            return c;
        }
    }
}
