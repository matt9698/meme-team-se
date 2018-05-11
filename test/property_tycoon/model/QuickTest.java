/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import javafx.scene.paint.Color;
import property_tycoon.control.HumanController;

/**
 *
 * @author matth
 */
public class QuickTest
{
    public static void main(String[] args)
    {
        GameData data = new DefaultGameData();
        BoardFactory bf = new DefaultBoardFactory(
            data.getProperties(), 
            data.getStations(),
            data.getUtilities(), 
            data.getCardGroups());
        
        Player[] p = { new Player("Matt", Color.BLUE, new HumanController()) };
        
        Board b = bf.build(p);
        
        b.moveSequential(p[0], 12);
    }
}
