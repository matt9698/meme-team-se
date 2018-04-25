/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import property_tycoon.model.Property;

/**
 * Control representing a property position on the game board.
 * 
 * @author Matt
 * @version 25/04/2018
 */
public class PropertyPosition extends BorderPane
{
    private static final String FXML_PATH = "PropertyPosition.fxml";

    /**
     * Creates and returns an instance of a <code>PropertyPosition</code>
     * with the specified underlying property model. The property model should already
     * be a member of a property group that is an instance of 
     * or an instance of a subclass of <code>PropertyPosition$Group</code>
     * 
     * @param model the underlying property model.
     * 
     * @return A new <code>PropertyPosition</code> instance.
     * 
     * @throws IllegalArgumentException if model is null, does not have a group
     * or its group is not an instance of or an instance of a subclass of PropertyPosition$Group/
     */
    public static PropertyPosition create(Property model)
    {
        // Check arguments
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        
        if(!model.isGrouped()) {
            throw new IllegalArgumentException("model should have a group.");
        }

        if(!(model.getGroup() instanceof Group)) {
            throw new IllegalArgumentException(
                "model.getGroup() should return an instance of"
                + " property_tycoon.view.PropertyPosition$Group");
        }

        // Create instance
        PropertyPosition p = new PropertyPosition(model);

        // Initialise instance from FMXL
        URL fxmlPath = p.getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "PropertyPosition.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setRoot(p);
        loader.setController(p);

        try {
            loader.load();
        }
        catch(LoadException e) {
            throw new UncheckedIOException(
                String.format(
                    "PropertyPosition.FXML_PATH resource %s could not be loaded."
                        + "\nThe file may be corrupt, of an incorrect format or contain syntax errors.",
                    FXML_PATH),
                e);
        }
        catch(IOException e) {
            throw new UncheckedIOException(
                String.format(
                    "PropertyPosition.FXML_PATH resource %s could not be loaded.",
                    FXML_PATH),
                e);
        }

        return p;
    }

    private final Property model;
    
    private final Background bannerBackground;

    private PropertyPosition(Property model)
    {
        this.model = model;
        this.bannerBackground = new Background(
            new BackgroundFill(
                ((Group)model.getGroup()).getColor(),
                CornerRadii.EMPTY,
                Insets.EMPTY));
    }

    /**
     * Gets the text to be displayed by this property position.
     * 
     * @return The text to be displayed by this property position.
     */
    public String getText()
    {
        StringBuilder sb = new StringBuilder(model.getDescription());

        if(model.isMortgaged()) {
            sb.append("\n(Mortgaged)");
        }

        if(model.isOwned()) {
            sb.append("\n");
            sb.append(model.getOwner().getDescription());
        }
        else {
            sb.append("\nFor Sale");
        }

        sb.append("\n£");
        sb.append(model.getValue());

        return sb.toString();
    }
    
    /**
     * Gets the banner background to be displayed by this property position.
     * 
     * @return The banner background to be displayed by this property position.
     */
    public Background getBannerBackground()
    {
        return bannerBackground;
    }

    /**
     * Represents a group of properties with an associated colour.
     * This class extends <code>model.Property$Group</code>
     * simply to add an associated colour to the group.
     * Any properties used with the enclosing class 
     * <code>PropertyPosition</code> must use an instance of 
     * or an instance of a subclass of this class 
     * in order for the necessary colour data to be accessible.
     */
    public static class Group extends Property.Group
    {
        private Color c;

        /**
         * Creates a new instance of PropertyPosition$Group with the specified
         * description (name), colour, houseCost and properties.
         * The properties should not already be a member of another group.
         * 
         * @param description The name of the group.
         * @param color The colour of the group.
         * @param houseCost The house cost of this group.
         * @param properties The properties to be placed in this group.
         * 
         * @throws IllegalArgumentException if description, color 
         * or properties is null, if description or properties is empty, 
         * if properties contains null elements 
         * or elements that are already in a group, 
         * or if houseCost is not positive.
         */
        public Group(String description, Color color, int houseCost,
            Property... properties)
        {
            super(description, houseCost, properties);

            // Check arguments
            if(color == null) {
                throw new IllegalArgumentException("color should not be null.");
            }

            // Assign fields
            c = color;
        }

        /**
         * Gets the colour of this group.
         * 
         * @return the colour of this group.
         */
        public Color getColor()
        {
            return c;
        }
    }
}
