package prototyping.old;

import java.util.ArrayList;

/**
 *
 * @author adam
 */
public class Player {
    
    private Token token;
    private ArrayList<Property> ownedProperties;
    private int id;
    
    public Player(int id, Token token){
        ownedProperties = new ArrayList<>();
        this.id = id;
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
    
    public void addProperty(Property p){
        ownedProperties.add(p);
    }
    
    public ArrayList<Property> getProperties(){
        return ownedProperties;
    }
    
}
