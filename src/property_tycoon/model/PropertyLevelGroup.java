 package property_tycoon.model;

 import java.util.Arrays;

/**
 * @author Matt
 * @version 27/04/2018
 */
public class PropertyLevelGroup
{
    private PropertyLevel[] levels;

    public PropertyLevelGroup(PropertyLevel... levels)
    {
        // TODO: Organise constructor code
        // Check arguments
        if(levels == null) {
            throw new IllegalArgumentException("levels should not be null.");
        }

        if(levels.length == 0) {
            throw new IllegalArgumentException("levels should not be empty.");
        }

        // TODO: Check levels elements aren't null

        // TODO: Check levels elements aren't already in a group

        // Assign field
        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.levels = Arrays.copyOf(levels, levels.length);

        for(int i = 0; i < levels.length; i++) {
            levels[i].setGroup(this);
        }
    }

    public PropertyLevel getNext(PropertyLevel level)
    {
        if(isMax(level)) {
            throw new IllegalArgumentException(
                "level is the highest in this group.");
        }

        return levels[getIndex(level) + 1];
    }

    public PropertyLevel getPrevious(PropertyLevel level)
    {
        if(isMin(level)) {
            throw new IllegalArgumentException(
                "level is the lowest in this group.");
        }

        return levels[getIndex(level) - 1];
    }

    public boolean isMax(PropertyLevel level)
    {
        return level.equals(levels[levels.length - 1]);
    }

    public boolean isMin(PropertyLevel level)
    {
        return level.equals(levels[0]);
    }

    public int getIndex(PropertyLevel level)
    {
        if(level == null) {
            throw new IllegalArgumentException("level should not be null.");
        }

        // Search the levels array to find index of level
        int i = 0;
        while(i < levels.length && !level.equals(levels[i])) {
            i++;
        }

        if(i == levels.length) {
            // We reached the end of the levels array without finding level
            throw new IllegalArgumentException("level is not in this group.");
        }

        return i;
    }

    public PropertyLevel getLevel(int index)
    {
        if(index < 0 || index >= levels.length) {
            throw new IndexOutOfBoundsException(
                "level index is out of bounds.");
        }

        return levels[index];
    }

    public boolean contains(PropertyLevel level)
    {
        if(level == null) {
            throw new IllegalArgumentException("level should not be null.");
        }

        // Search the levels array to find index of level
        int i = 0;
        while(i < levels.length && !level.equals(levels[i])) {
            i++;
        }

        return i != levels.length;
    }

    public int getLevelCount()
    {
        return levels.length;
    }

    public PropertyLevel getMin()
    {
        return levels[0];
    }

    public PropertyLevel getMax()
    {
        return levels[levels.length - 1];
    }
    
    public boolean isImprovable()
    {
        // TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
