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
            
            if(i > 0) {
                levels[i].setPrevious(levels[i - 1]);
            }            
            if(i < levels.length - 1) {
                levels[i].setNext(levels[i + 1]);
            }        
        }
    }
}
