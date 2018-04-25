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
    
    public String getDescription()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public int getHouseCost()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public boolean isOwned()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public Player getOwner()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public PropertyLevel getHighestLevel()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public PropertyLevel getLowestLevel()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public PropertyType getType()
    {
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public List<Property> getProperties()
    {
        return Collections.unmodifiableList(Arrays.asList(properties));
    }
}
