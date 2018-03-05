package prototyping;

/**
 *
 * @author adam
 */
public class Player {
    
    private Token token;
    private int id;
    
    public Player(int id, Token token){
        this.id = id;
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
    
}
