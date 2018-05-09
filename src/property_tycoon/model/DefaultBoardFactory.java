/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;
import property_tycoon.model.CornerPosition.CornerType;

public class DefaultBoardFactory implements BoardFactory
{
    private static final int PROPERTY_COUNT = 22;
    private static final int STATION_COUNT = 4;
    private static final int UTILITY_COUNT = 2;
    private static final int CARD_GROUP_COUNT = 2;
    
    private Property[] properties;
    private Property[] stations;
    private Property[] utilities;
    
    private int propertyIndex;
    private int stationIndex;
    private int utilityIndex;
    
    private Card.Group[] cardGroups;
    
    public DefaultBoardFactory( 
        Property[] properties, 
        Property[] stations, 
        Property[] utilities,
        Card.Group[] cardGroups)
    {
        if(properties == null) {
            throw new IllegalArgumentException("properties should not be null.");
        }
        if(properties.length != PROPERTY_COUNT)
        {
            throw new IllegalArgumentException(
                String.format(
                    "properties should contain %d elements..",
                    PROPERTY_COUNT));
        }

        for(Property property : properties) {
            if(property == null) {
                throw new IllegalArgumentException(
                    "properties should not contain null elements.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.properties = Arrays.copyOf(properties, properties.length);
        
        if(stations == null) {
            throw new IllegalArgumentException("stations should not be null.");
        }
        if(stations.length != STATION_COUNT)
        {
            throw new IllegalArgumentException(
                String.format(
                    "stations should contain %d elements..",
                    STATION_COUNT));
        }

        for(Property station : stations) {
            if(station == null) {
                throw new IllegalArgumentException(
                    "stations should not contain null elements.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.stations = Arrays.copyOf(stations, stations.length);
        
        if(utilities == null) {
            throw new IllegalArgumentException("utilities should not be null.");
        }
        if(utilities.length != UTILITY_COUNT)
        {
            throw new IllegalArgumentException(
                String.format(
                    "utilitites should contain %d elements..",
                    UTILITY_COUNT));
        }

        for(Property utility : utilities) {
            if(utility == null) {
                throw new IllegalArgumentException(
                    "utilities should not contain null elements.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.utilities = Arrays.copyOf(utilities, utilities.length);
        
        if(cardGroups == null) {
            throw new IllegalArgumentException("cardGroups should not be null.");
        }
        if(cardGroups.length != CARD_GROUP_COUNT)
        {
            throw new IllegalArgumentException(
                String.format(
                    "cardGroups should contain %d elements.",
                    PROPERTY_COUNT));
        }

        for(Card.Group cardGroup : cardGroups) {
            if(cardGroup == null) {
                throw new IllegalArgumentException(
                    "cardGroups should not contain null elements.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.cardGroups = Arrays.copyOf(cardGroups, cardGroups.length);
    }
    
    @Override
    public Board build(Player[] players)
    {
        BoardPosition[] positions = new BoardPosition[] {
            // Row one
            new CornerPosition(CornerType.GO),
            getNextProperty(),
            cardGroups[0],
            getNextProperty(),
            new TaxPosition(),
            
            getNextStation(),
            getNextProperty(),
            cardGroups[1],
            getNextProperty(),
            getNextProperty(),
            
            // Row two
            new CornerPosition(CornerType.JAIL),
            getNextProperty(),
            getNextUtility(),
            getNextProperty(),
            getNextProperty(),
            
            getNextStation(),
            getNextProperty(),
            cardGroups[0],
            getNextProperty(),
            getNextProperty(),
            
            // Row three
            new CornerPosition(CornerType.FREE_PARKING),
            getNextProperty(),
            cardGroups[1],
            getNextProperty(),
            getNextProperty(),
            
            getNextStation(),
            getNextProperty(),
            getNextProperty(),
            getNextUtility(),
            getNextProperty(),
            
            // Row four
            new CornerPosition(CornerType.GO_TO_JAIL),
            getNextProperty(),
            getNextProperty(),
            cardGroups[0],
            getNextProperty(),
            
            getNextStation(),
            cardGroups[1],
            getNextProperty(),
            new TaxPosition(),
            getNextProperty()
        };
        
        return new Board(positions, players);
    }

    @Override
    public int getCardGroupCount()
    {
        return CARD_GROUP_COUNT;
    }

    @Override
    public int getPropertyCount()
    {
        return PROPERTY_COUNT;
    }

    @Override
    public int getStationCount()
    {
        return STATION_COUNT;
    }

    @Override
    public int getUtilityCount()
    {
        return UTILITY_COUNT;
    }
    
    private PropertyPosition getNextProperty()
    {
        return new PropertyPosition(properties[propertyIndex++]);
    }
    
    private PropertyPosition getNextStation()
    {
        return new PropertyPosition(stations[stationIndex++]);
    }
    
    private PropertyPosition getNextUtility()
    {
        return new PropertyPosition(utilities[utilityIndex++]);
    }
}
