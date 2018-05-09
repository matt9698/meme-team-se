/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import property_tycoon.model.Game;

public class GameView extends Stage
{
    private Game model;
    private BoardView board;

    public GameView(Game model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;

        setTitle("Property Tycoon");
        setFullScreen(true);
        setScene(buildScene());
    }

    public Game getModel()
    {
        return model;
    }

    private Scene buildScene()
    {
        Label selected = new Label("No property selected");
        board = new BoardView(model.getBoard());
        board.setRotate(270);
        board.setOnMouseClicked(
            e -> selected.setText(
                board.getSelectedPosition() instanceof PropertyPositionView
                    ? ((PropertyPositionView)board.getSelectedPosition()).getModel().getDescription() + " selected"
                    : "No property selected" ));

        BorderPane bp = new BorderPane(board);

        Button buy = new Button("buy");
        buy.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { model.getCurrentPlayer().buy(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});

        HBox buttonBar = new HBox(selected, buy);
        bp.setBottom(buttonBar);

        PlayerView pv = new PlayerView(model.getCurrentPlayer());
        bp.setRight(pv);

        ScrollPane sp = new ScrollPane(bp);
        return new Scene(sp);
    }
}
