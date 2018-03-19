/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author matth
 */
public class GameView extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Property Tycoon");
        stage.show();
    }
}
