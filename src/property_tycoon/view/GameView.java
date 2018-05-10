/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import property_tycoon.model.Card;
import property_tycoon.model.Game;
import property_tycoon.model.Property;

public class GameView extends Stage
{
    private Game model;
    private BoardView board;
    private BorderPane overlay;

    public GameView(Game model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;

        setTitle("Property Tycoon");
        setScene(buildScene());
        show();
        model.nextTurn();
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
        
        board.setOnMouseClicked(
            e -> showDialog());

        BorderPane bp = new BorderPane(board);

        Button buy = new Button("buy");
        buy.setOnAction(e ->  { if(board.getSelectedPosition() instanceof PropertyPositionView) { model.getCurrentPlayer().buy(((PropertyPositionView)board.getSelectedPosition()).getModel()); }});

        HBox buttonBar = new HBox(selected, buy);
        bp.setBottom(buttonBar);

        PlayerView pv = new PlayerView(model.getCurrentPlayer());
        bp.setRight(pv);
        
        overlay = new BorderPane();
        overlay.setBackground(new Background(new BackgroundFill(
            new Color(.2, .2, .2, .75), CornerRadii.EMPTY,
            Insets.EMPTY)));
        overlay.setVisible(false);
        
        StackPane layers = new StackPane(bp, overlay);

        ScrollPane sp = new ScrollPane(layers);
        return new Scene(sp);
    }


    private void showDialog()
    {
        BoardPositionView pos = board.getSelectedPosition();
        if(pos instanceof PropertyPositionView) {
            Property model = ((PropertyPositionView)pos).getModel();
            PropertyView dialog = new PropertyView(model);
            dialog.showAndWait();
        }
        else if(pos instanceof CardPositionView) {
            Card card = ((CardPositionView)pos).getModel().draw(model.getCurrentPlayer());
            CardView dialog  = new CardView(card);
            dialog.showAndWait();
        }
    }
}
