/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author adam
 */
public class Board2
{   
//    public static final int PROPERTY_COUNT = 22;
//    public static final int STATION_COUNT = 4;
//    public static final int UTILITY_COUNT = 2;
//    public static final int CARD_GROUP_COUNT = 2;
    
    public static final BoardPositionType[] POSITION_TYPES = new BoardPositionType[] {
        BoardPositionType.GO,
        BoardPositionType.PROPERTY,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.FINE,
        BoardPositionType.STATION,
        BoardPositionType.PROPERTY,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.VISIT_JAIL,
        BoardPositionType.PROPERTY,
        BoardPositionType.UTILITY,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.STATION,
        BoardPositionType.PROPERTY,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.FREE_PARKING,
        BoardPositionType.PROPERTY,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.STATION,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.UTILITY,
        BoardPositionType.PROPERTY,
        BoardPositionType.GO_JAIL,
        BoardPositionType.PROPERTY,
        BoardPositionType.PROPERTY,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.STATION,
        BoardPositionType.CARD,
        BoardPositionType.PROPERTY,
        BoardPositionType.FINE,
        BoardPositionType.PROPERTY,
        BoardPositionType.JAIL
    };
    
    private Card.Group[] cards;
    private Map<Player, BoardPosition> playerPositions;
    
    private BoardPosition[] positions;
    
    public Board2(
        RegularProperty[] properties, 
        StationProperty[] stations, 
        UtilityProperty[] utilities, 
        Card.Group[] decks, 
        Player[] players)
    {
        // TODO: Check arguments
        // TODO: Assign fields
        
        // Fill positions array
        positions = new BoardPosition[POSITION_TYPES.length];
        
        int propertyIndex = 0;
        int stationIndex = 0;
        int utilityIndex = 0;
        for(int i = 0; i < positions.length; i++) {
            switch(POSITION_TYPES[i]) {
                case PROPERTY:
                    positions[i] = new PropertyPosition(properties[propertyIndex]);
                    propertyIndex++;
                    break;
                case STATION:
                    positions[i] = new PropertyPosition(stations[stationIndex]);
                    stationIndex++;
                    break;
                case UTILITY:
                    positions[i] = new PropertyPosition(utilities[utilityIndex]);
                    utilityIndex++;
                    break;
            }
        }
        
        // Place players on board
        playerPositions = new HashMap<Player, BoardPosition>();
        for(Player p : players) {
            playerPositions.put(p, positions[0]);
        }
    }
}