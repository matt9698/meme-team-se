/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;

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
    
    private Game model;
    
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Property Tycoon");
        stage.show();
    }
}
