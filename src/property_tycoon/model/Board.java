/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matt
 */
public class Board
{

    private final Position[] positions;
    private Map<Player, Position> playerPositions;
    private Player[] players;
    private Property[] properties;

    public Board(Player[] players, Property[] properties)
    {
        positions = new Position[41];
        playerPositions = new HashMap<>();
        this.players = players;
        this.properties = properties;

        for(Player p : players) {
            playerPositions.put(p, positions[0]);
        }
        placePropertiesOnBoard();

    }

    private void placePropertiesOnBoard()
    {
        for (int i = 0; i < properties.length; i++) {
            //Place the properties onto the board
            //maybe have a method in the Position class called setProperty(property)
            //board.getPositions()[i].setProperty(properties[i]);
        }
    }

    public void moveForward(Player p, int steps)
    {
        //Get players position on the board in terms of the position array
        int currentPosition = getIndexOfPlayer(p);
        if (currentPosition == -1){
            System.err.println("Cannot find player on this board");
            return;
        }
        
        //Calculate the destination position
        int destination = currentPosition + steps;
        if (destination > 40){
            destination -= 40;
        }
        
        //Move the player
        playerPositions.remove(p);
        playerPositions.put(p, positions[destination]);       
    }

    public void moveForward(Player p, Position to)
    {
        playerPositions.remove(p);
        playerPositions.put(p, to);
    }

    public void moveDirect(Player p, Position to)
    {
        playerPositions.remove(p);
        playerPositions.put(p, to);
    }
    
    private int getIndexOfPlayer(Player p){
        
        Position startPosition = playerPositions.get(p);
        int current = -1;
        for (int i = 0; i < positions.length; i++){
            if (positions[i] == startPosition){
                current = i;
                break;
            }
        }
        return current;
    }

    public int getIndex(Position position)
    {
        int index = 0;
        for (int i = 0; i < positions.length; i++){
            if (positions[i] == position){
                index = i;
                break;
            }
        }
        return index;
    }

    public Position getPositionOfPlayer(Player player)
    {
        return playerPositions.get(player);
    }

    public static interface Position
    {
        String getDescription();
    }
    
    public Position[] getPositions()
    {
        return positions;
    }

    public int getBoardSize()
    {
        return positions.length;
    }
}
