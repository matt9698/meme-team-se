/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

public interface BoardFactory
{
    int getPropertyCount();

    int getStationCount();

    int getUtilityCount();

    int getCardGroupCount();

    Board build(Player[] players);
}
