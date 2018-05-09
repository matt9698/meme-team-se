/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Random;
import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author meme-team
 */
public class Dice extends StackPane
{
    public static final int maxRoll = 6;
    public static final int minRoll = 1;
    
    public final SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();
    private int roll;
    
    public Dice(){
        
        Rectangle dice = new Rectangle(50,50, Color.WHITESMOKE);
        dice.setStroke(Color.BLACK);
        Text text = new Text();
        text.setFill(Color.BLACK);
        text.textProperty().bind(valueProperty.asString());
        setAlignment(Pos.CENTER);
        getChildren().addAll(dice, text);
    }

    public int roll(){
        
        RotateTransition rt = new RotateTransition(Duration.seconds(1), this);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setOnFinished((event) ->{
            valueProperty.set((int) (Math.random() * (maxRoll - minRoll + 1)) + minRoll);
            roll = valueProperty.get();
        });
        rt.play();
        return roll;
    }
    
    public int getRoll(){
        return roll;
    }
}
