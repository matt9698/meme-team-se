/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

/**
 *
 * @author meme-team
 */
public class PlayerConfig extends HBox
{
    private static Color[] COLORS = new Color[] { Color.DARKKHAKI, Color.LIME, Color.web("#fef65b"), Color.CYAN, Color.VIOLET, Color.CRIMSON};
    private static String[] COLOR_NAMES = new String[] { "Khaki" , "Lime", "Butter", "Cyan", "Violet", "Crimson"};
    private static StringConverter<Color> STRING_CONVERTER = new StringConverter<Color>()
    {
        @Override
        public Color fromString(String string)
        {
            int i = 0;
            while(!COLOR_NAMES[i].equals(string)) {
                i++;
            }
            
            return COLORS[i];
        }

        @Override
        public String toString(Color t)
        {
            int i = 0;
            while(!COLORS[i].equals(t)) {
                i++;
            }
            
            return COLOR_NAMES[i];
        }
    };
    
    private int index;
    
    ComboBox<String> playerType;
    ComboBox<Color> color;
    TextField name = new TextField();
    Circle colorCircle;

    public PlayerConfig(int i)
    {
        index = i;
        playerType = new ComboBox<>();
        playerType.getItems().addAll("No Player", "Human", "AI");
        playerType.getSelectionModel().select(0);
        playerType.setOnAction (e -> {
            if (playerType.getValue() != "No Player")
            {
                name.setDisable(false);
                color.setDisable(false);
            }
            else
            {
                name.setDisable(true);
                color.setDisable(true);
            }
        });
        
        name.setDisable(true);
        
        color = new ComboBox<>();
        color.getItems().addAll(Arrays.asList(COLORS));
        color.setConverter(STRING_CONVERTER);
        color.setOnAction(e -> colorCircle.setFill(color.getSelectionModel().getSelectedItem()));
        color.setDisable(true);
        
        
        colorCircle = new Circle(10);
        colorCircle.setFill(Color.GRAY);
        
        getChildren().addAll(new Label("Player " + i), playerType, new Label("Name:"), name, new Label("Colour:"), colorCircle, color);
        
    }
}
