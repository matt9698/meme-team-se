/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import property_tycoon.model.Property;

/**
 * Control representing a property position on the game board.
 *
 * @author Matt
 * @version 01/05/2018
 */
public class PropertyPosition extends BorderPane
{
    public static final String FXML_PATH = "PropertyPosition.fxml";

    /**
     * Creates and returns an instance of a <code>PropertyPosition</code>
     * with the specified underlying property model.
     * The property model should already be a member of a property group.
     *
     * @param model the underlying property model.
     *
     * @return A new <code>PropertyPosition</code> instance.
     *
     * @throws IllegalArgumentException if model is null or not in a group
     */
    public static PropertyPosition create(Property model)
    {
        // Create instance
        PropertyPosition position = new PropertyPosition(model);

        // Initialise instance from FMXL
        URL fxmlPath = position.getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "PropertyPosition.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setRoot(position);
        loader.setController(position);

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

        return position;
    }

    private final Background bannerBackground;
    private final Property model;

    private ReadOnlyBooleanWrapper mortgaged;
    private ReadOnlyBooleanWrapper owned;
    private ReadOnlyStringWrapper owner;

    public PropertyPosition(Property model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        if(!model.isGrouped()) {
            throw new IllegalArgumentException("model should have a group.");
        }
        this.model = model;

        this.bannerBackground = new Background(
            new BackgroundFill(
                model.getGroup().getColor(),
                CornerRadii.EMPTY,
                Insets.EMPTY));

        model.addPropertyChangeListener(e -> update());

        mortgaged = new ReadOnlyBooleanWrapper(model.isMortgaged());
        owned = new ReadOnlyBooleanWrapper(model.isOwned());
        owner = new ReadOnlyStringWrapper(
            model.isOwned() ? model.getOwner().getDescription() : "");
    }

    public Background getBannerBackground()
    {
        return bannerBackground;
    }

    public String getDescription()
    {
        return model.getDescription();
    }

    public String getOwner()
    {
        return owner.get();
    }

    public int getPrice()
    {
        return model.getPrice();
    }

    public boolean isMortgaged()
    {
        return mortgaged.get();
    }

    public boolean isOwned()
    {
        return owned.get();
    }

    public ReadOnlyBooleanProperty mortgagedProperty()
    {
        return mortgaged.getReadOnlyProperty();
    }

    public ReadOnlyBooleanProperty ownedProperty()
    {
        return owned.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty ownerProperty()
    {
        return owner.getReadOnlyProperty();
    }

    private void update()
    {
        mortgaged.set(model.isMortgaged());
        owned.set(model.isOwned());
        owner.set(model.isOwned() ? model.getOwner().getDescription() : "");
    }
}
