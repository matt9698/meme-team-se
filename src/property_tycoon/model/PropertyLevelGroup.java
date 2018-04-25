 package property_tycoon.model;

/**
 *
 * @author mm933
 */
public class PropertyLevelGroup
{
    private PropertyLevel[] levels;
    
    public PropertyLevelGroup(PropertyLevel... levels)
    {
        this.levels = levels;
        
        for(int i = 0; i < levels.length; i++) {
            levels[i].setGroup(this);
            levels[i].setIndex(i);
            
            if(i > 0) {
                levels[i].setPrevious(levels[i - 1]);
            }            
            if(i < levels.length - 1) {
                levels[i].setNext(levels[i + 1]);
            }        
        }
    }
    
    public PropertyLevel getMax()
    {
        return levels[levels.length - 1];
    }
    
    public PropertyLevel getMin()
    {
        return levels[0];
    }
}
