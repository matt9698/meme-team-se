/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import javafx.scene.paint.Color;

/**
 *
 * @author matth
 */
public class DefaultGameData implements GameData
{
    @Override
    public Card.Group[] getCardGroups()
    {
        Card[] cards = new Card[]{
            Card.create(new FakeAction(), true),
            Card.create(new FakeAction(), true)
        };
        
        Card.Group[] cardGroups = new Card.Group[] {
            Card.Group.create("Pot Luck", cards[0]),
            Card.Group.create("Opportunity Knocks", cards[1])
        };
        
        return cardGroups;
    }

    @Override
    public Property[] getProperties()
    {
        Property[] properties = new Property[] {
            //Properties
            Property.create("Crapper Street", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Gangsters Paradise", 60, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Weeping Angel", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Potts Avenue", 100, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Nardole Drive", 120, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Skywalker Drive", 140, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Wookie Hole", 140, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Rey Lane", 160, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Cooper Drive", 180, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Wolowitz Street", 180, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Penny Lane", 200, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Yue Fei Square", 220, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Mulan Rouge", 220, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Han Xin Gardens", 240, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Kirk Close", 260, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Picard Avenue", 260, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Crusher Creek", 280, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Sirat Mews", 300, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Ghengis Cresent", 300, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Ibis Cresent", 320, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Hawking Way", 350, new int[] {1, 1, 1, 1, 1, 1}),
            Property.create("Turing Heights", 400, new int[] {1, 1, 1, 1, 1, 1})
        };
        
        Property.Group.create(
            "Browns", 
            Color.SIENNA, 
            PropertyLevel.Group.REGULAR_LEVELS,
            30, 
            properties[0], 
            properties[1]);

        Property.Group.create(
            "Blues", 
            Color.LIGHTSTEELBLUE, 
            PropertyLevel.Group.REGULAR_LEVELS,
            30, 
            properties[2], 
            properties[3], 
            properties[4]);

        Property.Group.create(
            "Purples", 
            Color.PALEVIOLETRED,
            PropertyLevel.Group.REGULAR_LEVELS,
            30, 
            properties[5], 
            properties[6], 
            properties[7]);

        Property.Group.create(
           "Oranges", 
           Color.CORAL,
           PropertyLevel.Group.REGULAR_LEVELS, 
           30, 
           properties[8], 
           properties[9], 
           properties[10]);

        Property.Group.create(
           "Yellows", 
           Color.KHAKI,
           PropertyLevel.Group.REGULAR_LEVELS, 
           30, 
           properties[11], 
           properties[12], 
           properties[13]);

        Property.Group.create(
           "Reds", 
           Color.CRIMSON,
           PropertyLevel.Group.REGULAR_LEVELS, 
           30, 
           properties[14], 
           properties[15], 
           properties[16]);

        Property.Group.create(
            "Greens", 
            Color.SEAGREEN,
            PropertyLevel.Group.REGULAR_LEVELS, 
            30, 
            properties[17], 
            properties[18], 
            properties[19]);

        Property.Group.create(
            "Deep Blues", 
            Color.DARKSLATEBLUE,
            PropertyLevel.Group.REGULAR_LEVELS, 
            30, 
            properties[20], 
            properties[21]);
        
        return properties;
    }

    @Override
    public Property[] getStations()
    {
        Property[] stations = new Property[] {
            Property.createStation("Brighton Station", 200),
            Property.createStation("Hove Station", 200),
            Property.createStation("Falmer Station", 200),
            Property.createStation("Lewes Station", 200)
        };
        
        Property.Group.create(
            "Stations",
            Color.web("#bfdbae"), 
            property_tycoon.model.PropertyLevel.Group.STATION_LEVELS, 
            1, 
            stations[0],
            stations[1],
            stations[2],
            stations[3]);
             
        return stations;
    }

    @Override
    public Property[] getUtilities()
    {
        Property[] utilities = new Property[] {
            Property.createUtility("Tesla Power Co", 150),
            Property.createUtility("Edison Water", 150)
        };
        
        Property.Group.create(
            "Utilites",
            Color.web("#bfdbae"), 
            property_tycoon.model.PropertyLevel.Group.UTILITY_LEVELS, 
            1, 
            utilities[0],
            utilities[1]);
             
        return utilities;
    }
    
}
