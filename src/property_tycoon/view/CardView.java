package property_tycoon.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import property_tycoon.model.Card;
import static property_tycoon.view.PreGameMenu.defaultColor;

/**
 *
 * @author ob81
 */


public class CardView extends Stage
{
    private final Card model;
    
    public CardView(Card model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;
        
        setTitle(String.format("%s - Card Details", model.getDescription()));
        setScene(buildScene());
        this.setResizable(false);
    }
    
    private Scene buildScene()
    {
        Label cardName = new Label(model.getGroup().getDescription());
        cardName.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        
        VBox details = new VBox( cardName, new Label (model.getDescription()));
        
        
        details.setSpacing(20);
        details.setMinSize(200, 200);
        details.setPadding(new Insets(40));
        details.setAlignment(Pos.CENTER);
        
        BorderPane core = new BorderPane(details);
        core.setBackground(new Background(new BackgroundFill(defaultColor,
            CornerRadii.EMPTY, Insets.EMPTY)));
        details.setStyle("-fx-border-color:BLACK; -fx-border-width: 5;");
        
        
        return new Scene(core);
    }
}
