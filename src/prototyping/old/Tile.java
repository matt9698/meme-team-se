package prototyping.old;

/**
 *
 * @author adam
 * Class Tile represents a square on the game board
 */
public class Tile {
    
    //The property on the tile
    private Property property;
    
    public void setProperty(Property property){
        this.property = property;
    }
    
    public Property getProperty(){
        return property;
    }
}
