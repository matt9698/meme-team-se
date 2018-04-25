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
import javafx.scene.layout.BorderPane;
import property_tycoon.model.Card;

public class CardDialog extends BorderPane
{
    private static final String FXML_PATH = "CardDialog.fxml";

    public static CardDialog create(Card model)
    {
        // Check argument
        if(model == null) {
            throw new IllegalArgumentException("model should not be null");
        }

        if(!model.isGrouped()) {
            throw new IllegalArgumentException("model should have a group.");
        }

        // Create instance
        CardDialog c = new CardDialog(model);

        // Initialise instance from FXML
        URL fxmlPath = c.getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "CardDialog.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setRoot(c);
        loader.setController(c);

        try {
            loader.load();
        }
        catch(LoadException e) {
            throw new UncheckedIOException(
                String.format(
                    "CardDialog.FXML_PATH resource %s could not be loaded."
                        + "\nThe file may be corrupt, of an incorrect format or contain syntax errors.",
                    FXML_PATH),
                e);
        }
        catch(IOException e) {
            throw new UncheckedIOException(
                String.format(
                    "CardDialog.FXML_PATH resource %s could not be loaded.",
                    FXML_PATH),
                e);
        }

        return c;
    }

    private final Card model;

    private CardDialog(Card model)
    {
        this.model = model;
    }

    public String getText()
    {
        return model.getDescription();
    }
}
