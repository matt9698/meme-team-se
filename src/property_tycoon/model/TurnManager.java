/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author adam
 */
public class TurnManager
{
    /**
     * 
     * @param map the map showing what value each player rolled on their dice
     * @return a player array ordered by descending order of value rolled on dice
     */
    public static Player[] decideTurnOrder(Map<Player, Integer> map) {


        ArrayList<Player> list = new ArrayList<>(map.keySet());

        Comparator<Player> cmp = (Player p1, Player p2) -> {
            Integer roll1 = map.get(p1);
            Integer roll2 = map.get(p2);
            return roll1.compareTo(roll2);
        };
        Collections.sort(list, Collections.reverseOrder(cmp));

        Player[] turnOrder = new Player[list.size()];
        for (int i = 0; i < turnOrder.length; i++){
            turnOrder[i] = list.get(i);
        }
        return turnOrder;
        
    }
}
