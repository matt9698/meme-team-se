/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.layout.GridPane;
import property_tycoon.model.Property;
import property_tycoon.view.builder.PropertyTycoonBuilderFactory;

/**
 * Control representing a property position on the game board.
 *
 * @author Matt
 * @version 01/05/2018
 */
 public class Board extends GridPane
 {
    public static final String FXML_PATH = "Board.fxml";

    public static Board create(property_tycoon.model.Board model)
    {
        Board board = new Board(model);

        // Initialise from FMXL
        URL fxmlPath = board.getClass().getResource(FXML_PATH);
        assert fxmlPath != null :
            String.format(
                "Board.FXML_PATH resource %s was not found.",
                FXML_PATH);

        FXMLLoader loader = new FXMLLoader(fxmlPath);
        loader.setBuilderFactory(new PropertyTycoonBuilderFactory());
        loader.setRoot(board);
        loader.setController(board);

        try {
            loader.load();
        }
        catch(LoadException e) {
            throw new UncheckedIOException(
                String.format(
                    "Board.FXML_PATH resource %s could not be loaded."
                    + "\nThe file may be corrupt, of an incorrect format or contain syntax errors.",
                    FXML_PATH),
                e);
        }
        catch(IOException e) {
            throw new UncheckedIOException(
                String.format(
                    "Board.FXML_PATH resource %s could not be loaded.",
                    FXML_PATH),
                e);
        }

        return board;
    }

    private property_tycoon.model.Board model;
    private BoardPosition[] positions;
    private ReadOnlyListWrapper<BoardPosition> positions_wrapper;

    public Board(property_tycoon.model.Board model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;

        positions = new BoardPosition[model.getPositionCount()];
        for(int i = 0; i < model.getPositionCount(); i++) {
            if(model.getPosition(i)
                instanceof Property) {
                    positions[i] = new PropertyPosition(
                        (Property)model.getPosition(i));
                }
        }
        
        positions_wrapper = new ReadOnlyListWrapper<BoardPosition>(FXCollections.observableArrayList(positions));
    }

    public ReadOnlyListProperty<BoardPosition> positionsProperty()
    {
        return positions_wrapper.getReadOnlyProperty();
    }

    public BoardPosition getPosition(int index)
    {
        return positionsProperty().get(index);
    }
 }
