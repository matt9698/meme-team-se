package property_tycoon.model;

import java.util.Arrays;

/**
 * @author Matt
 * @version 30/04/2018
 */
public final class PropertyLevel implements Comparable<PropertyLevel>
{
    private String description;
    private Group group;

    public PropertyLevel(String description)
    {
        if(description == null) {
            throw new IllegalArgumentException(
                "description should not be null.");
        }
        if(description.isEmpty()) {
            throw new IllegalArgumentException(
                "description should not be empty.");
        }
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

    /**
     * Gets the name of this level.
     *
     * @return The name of this level.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the group to which this level belongs.
     *
     * @return The group to which this level belongs,
     *         or null if this level has no group.
     */
    public Group getGroup()
    {
        return group;
    }

    /**
     * Gets the index of this level in its group,
     * indicating its relative order to other levels in its group.
     *
     * @return The index of this level level in its group.
     */
    public int getIndex()
    {
        return getGroup().getIndex(this);
    }

    /**
     * Indicates if this level is a member of a group.
     *
     * @return true if this level is a member of a group; false otherwise.
     */
    public boolean isGrouped()
    {
        return group != null;
    }

    /**
     * Indicates if this level is the highest in its group.
     *
     * @return true if this level is the highest in its group;
     *         false otherwise.
     */
    public boolean isMax()
    {
        return getGroup().isMax(this);
    }

    /**
     * Indicates if this level is the lowest in its group.
     *
     * @return true if this level is the lowest in its group;
     *         false otherwise.
     */
    public boolean isMin()
    {
        return getGroup().isMin(this);
    }

    private void setGroup(Group group)
    {
        assert group != null : "group should not be null.";

        this.group = group;
    }

    /**
     * Gets the next (higher) level in this group.
     *
     * @return The next level in this group,
     *         or null if this level is the highest.
     *
     * @throws IllegalStateException if this level has no group.
     */
    public PropertyLevel getNext()
    {
        return getGroup().getNext(this);
    }

    /**
     * Gets the previous (previous) level in this group.
     *
     * @return The previous level in this group,
     *         or null if this level is the highest.
     *
     * @throws IllegalStateException if this level has no group.
     */
    public PropertyLevel getPrevious()
    {
        return getGroup().getPrevious(this);
    }

    public static final class Group
    {
            public static final Group REGULAR_LEVELS =
            Group.create(
                true,
                new PropertyLevel("Unimproved"),
                new PropertyLevel("One House"),
                new PropertyLevel("Two Houses"),
                new PropertyLevel("Three Houses"),
                new PropertyLevel("Four Houses"),
                new PropertyLevel("One Hotel")
            );

        public static final Group STATION_LEVELS =
            Group.create(
                false,
                new PropertyLevel("One Station"),
                new PropertyLevel("Two Stations"),
                new PropertyLevel("Three Stations"),
                new PropertyLevel("Four Stations")
            );

        public static final Group UTILITY_LEVELS =
            Group.create(
                false,
                new PropertyLevel("One Utility"),
                new PropertyLevel("Two Utility")
            );
        
        public static Group create(
            boolean isImprovable, PropertyLevel... levels)
        {
            Group group = new Group(isImprovable, levels);
            for(PropertyLevel level : levels) {
                level.setGroup(group);
            }
            
            return group;
        }
        
        private boolean isImprovable;
        private PropertyLevel[] levels;

        private Group(boolean isImprovable, PropertyLevel... levels)
        {
            if(levels == null) {
                throw new IllegalArgumentException("levels should not be null.");
            }
            if(levels.length == 0) {
                throw new IllegalArgumentException("levels should not be empty.");
            }

            for(PropertyLevel level : levels) {
                if(level == null) {
                    throw new IllegalArgumentException(
                        "levels should not contain null elements.");
                }
                if(level.isGrouped()) {
                    throw new IllegalArgumentException(
                        "levels should not contain elements that are already in a group.");
                }
            }

            // Copy the array so that elements cannot
            // be subsequently modified by external code.
            this.levels = Arrays.copyOf(levels, levels.length);

            this.isImprovable = isImprovable;
        }

        /**
         * Indicates if the specified level is in this group.
         *
         * @param level the level of interest.
         * 
         * @return true if the specified level is in this group.
         *
         * @throws IllegalArgumentException if level is null.
         */
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

        /**
         * Gets the index of the specified level in this group,
         * indicating its relative order to other levels in the group.
         *
         * @param level The level of interest.
         *
         * @return The index of the specified level in this group.
         *
         * @throws IllegalArgumentException if level is null or not in this group.
         */
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
                throw new IllegalArgumentException(
                    "level should be in this group.");
            }

            return i;
        }

        /**
         * Gets the level at the specified index in this group.
         *
         * @param index The index of the level in question.
         *
         * @return The level at the specified index.
         *
         * @throws IndexOutOfBoundsException if the level index is out of bounds.
         */
        public PropertyLevel getLevel(int index)
        {
            if(index < 0 || index >= levels.length) {
                throw new IndexOutOfBoundsException(
                    "level index is out of bounds.");
            }

            return levels[index];
        }

        /**
         * Gets the number of levels in this group.
         *
         * @return the number of levels in this group.
         */
        public int getLevelCount()
        {
            return levels.length;
        }

        /**
         * Gets the highest level in this group.
         *
         * @return the highest level in this group.
         */
        public PropertyLevel getMax()
        {
            return levels[levels.length - 1];
        }

        /**
         * Gets the lowest level in this group.
         *
         * @return the lowest level in this group.
         */
        public PropertyLevel getMin()
        {
            return levels[0];
        }

        /**
         * Gets the next (higher) level in this group.
         *
         * @param level the level of interest.
         * 
         * @return The next level in this group,
         *         or null if this level is the highest.
         */
        public PropertyLevel getNext(PropertyLevel level)
        {
            if(isMax(level)) {
                return null;
            }

            return levels[getIndex(level) + 1];
        }

        /**
         * Gets the previous (lower) level in this group.
         *
         * @param level the level of interest.
         * 
         * @return The previous level in this group,
         *         or null if this level is the lowest.
         */
        public PropertyLevel getPrevious(PropertyLevel level)
        {
            if(isMin(level)) {
                return null;
            }

            return levels[getIndex(level) - 1];
        }

        /**
         * Indicates if properties in property groups using this
         * level group can be upgraded and downgraded.
         *
         * @return true properties in property groups using this
         *         level group can be upgraded and downgraded;
         *         false otherwise.
         */
        public boolean isImprovable()
        {
            return isImprovable;
        }

        /**
         * Indicates if the specified level is the highest in this group.
         *
         * @param level The level of interest.
         *
         * @return true if the specified level is the highest in this group;
         *         false otherwise.
         *
         * @throws IllegalArgumentException if level is null or is not in this group.
         */
        public boolean isMax(PropertyLevel level)
        {
            if(level == null) {
                throw new IllegalArgumentException("level should not be null");
            }
            if(!contains(level)) {
                throw new IllegalArgumentException(
                    "level should be in this group.");
            }

            return level.equals(levels[levels.length - 1]);
        }

        /**
         * Indicates if the specified level is the lowest in this group.
         *
         * @param level The level of interest.
         *
         * @return true if the specified level is the lowest in this group;
         *         false otherwise.
         *
         * @throws IllegalArgumentException if level is null or is not in this group.
         */
        public boolean isMin(PropertyLevel level)
        {
            if(level == null) {
                throw new IllegalArgumentException("level should not be null");
            }
            if(!contains(level)) {
                throw new IllegalArgumentException(
                    "level should be in this group.");
            }

            return level.equals(levels[0]);
        }
    }
}
