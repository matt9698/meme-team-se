package property_tycoon.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author mm933
 */
public class PropertyGroup
{
    private Property[] properties;
    private String description;
    private int houseCost, hotelCost;
    private PropertyType type;

    public PropertyGroup(String description, int houseCost, int hotelCost,
        PropertyType type)
    {
        this.description = description;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public int getHouseCost()
    {
        return houseCost;
    }

    public int getHotelCost()
    {

        return hotelCost;
    }

    public boolean isOwned()
    {
        if(!properties[0].isOwned()) {
            return false;
        }

        Player expectedOwner = properties[0].getOwner();
        int i = 1;
        while(i < properties.length
            && properties[i].isOwned()
            && properties[i].getOwner() == expectedOwner) {
            i++;
        }

        return i == properties.length;
    }

    public Player getOwner()
    {
        if(!isOwned()) {
            throw new IllegalStateException("Group has no owner.");
        }

        return properties[0].getOwner();
    }

    public PropertyLevel getHighestLevel()
    {
        return properties[properties.length].getLevel();
    }

    public PropertyLevel getLowestLevel()
    {
        return properties[0].getLevel();
    }

    public PropertyType getType()
    {
        return type;
    }

    public List<Property> getProperties()
    {
        return Collections.unmodifiableList(Arrays.asList(properties));
    }
}
