/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.control;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import property_tycoon.model.Player;

/**
 * @author meme-team
 * @version 09/05/2018
 */
public class HumanController extends Player.Controller
{
    @Override
    public void takeTurn()
    {
        Alert alert = new Alert(AlertType.INFORMATION, "Click OK to roll the dice.", ButtonType.OK);
        alert.showAndWait();
        
    }
}
