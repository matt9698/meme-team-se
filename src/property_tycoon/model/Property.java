/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
public interface Property
{
    String getDescription();
    
    Group getGroup();
    
    boolean hasOwner();
    
    Player getOwner();
    
    int getValue();
    
    int getRent();
    
    Level getLevel();
    
    boolean isImproved();
    
    boolean isMorgaged();
    
    boolean isValid();
    
    public static enum Level
    {
        UNIMPROVED,
        ONE_HOUSE,
        TWO_HOUSES,
        THREE_HOUSES,
        FOUR_HOUSES,
        ONE_HOTEL
    }
        
    public static class Group
    {
        public String getDescription()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        
        public boolean hasOwner()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    
        public Player getOwner()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        
        public Level getLevel()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        
        public int getHouseCost()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    }
}
