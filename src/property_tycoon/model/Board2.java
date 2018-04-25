/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import property_tycoon.model.Board.Position;

/**
 *
 * @author adam
 */
public class Board2
{
    
    public static final PositionType[] POSITION_TYPES = new PositionType[41];
    
    public static final int PROPERTY_COUNT = 22;
    public static final int STATION_COUNT = 4;
    public static final int UTILITY_COUNT = 2;
    public static final int CARD_GROUP_COUNT = 2;
    
    private Property[] properties, stations, utilities;
    private Card.Group[] cards;
    private Player[] players;
    
    private Position[] positions = new Position[41];
    
    
    public Board2(Property[] properties, Property[] stations, 
        Property[] utilities, Card.Group[] cards, Player[] players)
    {
        this.players = players;
        this.properties = properties;
        this.stations = stations;
        this.utilities = utilities;
        this.cards = cards;
        
        int propertyIndex = 0;
        int stationIndex = 0;
        int utilityIndex = 0;
        for(int i = 0; i < POSITION_TYPES.length; i++) {
            switch (POSITION_TYPES[i]) {
                case REGULAR:
                   positions[i] = new PropertyPosition(properties[propertyIndex]);
                   propertyIndex++;
                   break;
                case STATION:
                   positions[i] = new PropertyPosition(properties[stationIndex]);
                   stationIndex++;
                   break;
                case UTILITY:
                   positions[i] = new PropertyPosition(properties[utilityIndex]);
                   utilityIndex++;
                   break;
            }
            
        }
        
    }
   
    
}
