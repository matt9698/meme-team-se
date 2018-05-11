/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;



/**
 *
 * @author meme-team
 */
public class CornerPosition implements property_tycoon.model.BoardPosition
{

    @Override
    public void land(Player player)
    {
    }

    @Override
    public void step(Player player)
    {
    }
    
    public enum CornerType {
        GO, JAIL, GO_TO_JAIL, FREE_PARKING;
    }
    
    public static CornerPosition create(CornerType type){
        return new CornerPosition(type);
    }
    
    private CornerType type;
    
    public CornerPosition(CornerType type){
        
        this.type = type;
    }
    
    public CornerType getType(){
        return type;
    }
}
