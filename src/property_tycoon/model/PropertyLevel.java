package property_tycoon.model;

/**
 *
 * @author mm933
 */
public class PropertyLevel implements Comparable<PropertyLevel>
{

    private PropertyLevelGroup group;
    private String description;
    private int index = -1;
    private PropertyLevel next, previous;

    public PropertyLevel(String description)
    {
        this.description = description;
    }

    @Override
    public int compareTo(PropertyLevel level)
    {
        return Integer.compare(this.index, level.index);
    }

    public void setNext(PropertyLevel level)
    {
        if(isMax()) {
            throw new IllegalStateException("This level is the highest level");
        }
        
        next = level;
    }
    
    public PropertyLevel getNext()
    {
        if(isMax()) {
            throw new IllegalStateException("This level is the highest level");
        }
        return next;
    }

    public void setPrevious(PropertyLevel level)
    {
        if(isMin()) {
            throw new IllegalStateException("This level is the lowest level");
        }
        previous = level;
    }

    public PropertyLevel getPrevious()
    {
        if(isMin()) {
            throw new IllegalStateException("This level is the lowest level");
        }
        return previous;
    }
    
    public PropertyLevelGroup getGroup()
    {
        if (!isGrouped()){
            throw new IllegalStateException("PropertyLevel does not have a group");
        }
        return group;
    }

    public void setGroup(PropertyLevelGroup group)
    {
        if(isGrouped()) {
            throw new IllegalStateException("PropertyLevel already has a group.");
        }
        
        this.group = group;
    }
    
    public void setIndex(int i)
    {
        if (index != -1) {
            throw new IllegalStateException("PropertyLevel already has an index.");
        }
        
        if(index < 0) {
            throw new IllegalArgumentException("index should not be negative.");
        }
        
        index = i;
    }

    public boolean isGrouped()
    {
        return group != null;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean isMax()
    {
        return this == group.getMax();
    }

    public boolean isMin()
    {
        return this == group.getMin();
    }
}
