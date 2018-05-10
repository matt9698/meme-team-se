/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

public interface GameData
{
    Property[] getProperties();

    Property[] getStations();

    Property[] getUtilities();

    Card.Group[] getCardGroups();
}
