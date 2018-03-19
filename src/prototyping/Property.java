package prototyping;

import javafx.scene.paint.Color;

/**
 *
 * @author adam
 */
public class Property {
    
    private String name;
    private int cost;
    private Color color;
    private boolean owned;
   
    public Property(String name, int cost, Color color){
        this.name = name;
        this.cost = cost;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Color getColor() {
        return color;
    }
    
    public void setOwned(Player player){
        owned = true;
        player.addProperty(this);
    }
    
    public Boolean isOwned(){
        return owned;
    }
    
    
    
    
}
