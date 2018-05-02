/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.beans.PropertyChangeEvent;
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
import javafx.scene.layout.CornerRadii;
import property_tycoon.model.PropertyPosition;

/**
 * Control representing a property position on the game board.
 *
 * @author Matt
 * @version 02/05/2018
 */
public class PropertyPositionView extends BoardPositionView
{
    private static final String FXML_PATH = "PropertyPosition.fxml";

    private final Background bannerBackground;
    private final PropertyPosition model;

    private ReadOnlyBooleanWrapper mortgaged;
    private ReadOnlyBooleanWrapper owned;
    private ReadOnlyStringWrapper owner;

    public PropertyPositionView(PropertyPosition model)
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

        mortgaged = new ReadOnlyBooleanWrapper(model.isMortgaged());
        owned = new ReadOnlyBooleanWrapper(model.isOwned());
        owner = new ReadOnlyStringWrapper(
            model.isOwned() ? model.getOwner().getDescription() : "");

        model.addPropertyChangeListener(e -> update(e));

        // Initialise from FMXL
        URL fxmlPath = getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "PropertyPositionView.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        }
        catch(LoadException e) {
            throw new UncheckedIOException(
                String.format(
                    "PropertyPositionView.FXML_PATH resource %s could not be loaded."
                    + "\nThe file may be corrupt, of an incorrect format or contain syntax errors.",
                    FXML_PATH),
                e);
        }
        catch(IOException e) {
            throw new UncheckedIOException(
                String.format(
                    "PropertyPositionView.FXML_PATH resource %s could not be loaded.",
                    FXML_PATH),
                e);
        }
    }

    public Background getBannerBackground()
    {
        return bannerBackground;
    }

    public String getDescription()
    {
        return model.getDescription();
    }

    @Override
    public PropertyPosition getModel()
    {
        return model;
    }

    public boolean getMortgaged()
    {
        return mortgaged.get();
    }

    public String getOwner()
    {
        return owner.get();
    }
    
    public int getPrice()
    {
        return model.getPrice();
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

    private void update(PropertyChangeEvent e)
    {
        switch(e.getPropertyName()) {
            case "owned":
                owned.set((Boolean)e.getNewValue());
                break;
            case "owner":
                owner.set(
                    model.isOwned() ? model.getOwner().getDescription() : "");
                break;
            case "mortgaged":
                mortgaged.set((Boolean)e.getNewValue());
                break;
        }
    }
}
