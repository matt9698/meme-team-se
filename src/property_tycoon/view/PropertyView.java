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
import property_tycoon.model.Property;
import property_tycoon.model.PropertyLevel;
import static property_tycoon.view.PreGameMenu.defaultColor;

public class PropertyView extends Stage
{
    private final Property model;
    
    public PropertyView(Property model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;
        
        setTitle(String.format("%s - Property Details", model.getDescription()));
        setScene(buildScene());
        this.setResizable(false);
    }
    
    private Scene buildScene()
    {
        Label propertyName = new Label(model.getDescription());
        propertyName.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        
        VBox details = new VBox(
            propertyName,
            new Label("Price: £" + model.getPrice()));
        
        PropertyLevel.Group levels = model.getGroup().getLevels();
        for(int i = 0; i < levels.getLevelCount(); i++) {
            details.getChildren().add(
                new Label(String.format(
                    "%s: £%s", 
                    levels.getLevel(i).getDescription(), 
                    model.getRentPrice(levels.getLevel(i), 0))));
        }
        
        details.setSpacing(20);
        details.setMinSize(200, 200);
        details.setPadding(new Insets(40));
        details.setAlignment(Pos.CENTER);
        
        
        
        BorderPane core = new BorderPane(details);
        core.setBackground(new Background(new BackgroundFill(defaultColor,
            CornerRadii.EMPTY, Insets.EMPTY)));
        VBox colorTab = new VBox();
        colorTab.setBackground(new Background(new BackgroundFill(model.getGroup().getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        colorTab.setMinSize(200, 50);
        colorTab.setStyle("-fx-border-color:BLACK; -fx-border-width: 5;");
        core.setTop(colorTab);
        details.setStyle("-fx-border-color:BLACK; -fx-border-width: 5;");
        
        return new Scene(core);
    }
}
