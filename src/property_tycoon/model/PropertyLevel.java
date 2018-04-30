package property_tycoon.model;

/**
 * @author Matt
 * @version 27/04/2018
 */
public class PropertyLevel implements Comparable<PropertyLevel>
{
    private String description;
    private PropertyLevelGroup group;

    public PropertyLevel(String description)
    {
        // Check argument
        if(description == null) {
            throw new IllegalArgumentException(
                "description should not be null.");
        }

        if(description.isEmpty()) {
            throw new IllegalArgumentException(
                "description should not be empty.");
        }

        // Assign fields
        this.description = description;
        group = null;
    }

    @Override
    public int compareTo(PropertyLevel level)
    {
        if(!getGroup().contains(level)) {
            throw new IllegalArgumentException(
                "level is not in the same group.");
        }

        return Integer.compare(this.getIndex(), level.getIndex());
    }

    public PropertyLevel getNext()
    {
        return getGroup().getNext(this);
    }

    public PropertyLevel getPrevious()
    {
        return getGroup().getPrevious(this);
    }

    public PropertyLevelGroup getGroup()
    {
        if (!isGrouped()) {
            throw new IllegalStateException("PropertyLevel has no group.");
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

    public boolean isGrouped()
    {
        return group != null;
    }

    public String getDescription()
    {
        return description;
    }

    public int getIndex()
    {
        return getGroup().getIndex(this);
    }

    public boolean isMax()
    {
        return getGroup().isMax(this);
    }

    public boolean isMin()
    {
        return getGroup().isMin(this);
    }
}
