/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author Matt
 */
 public class Board
 {
    private final Position[] positions;

    public Board()
    {
        positions = new Position[41];
    }

    public void moveForward(Player p, int toPosition)
    {

    }

    public void moveDirect(Player p, int toPosition)
    {
        
    }

    public static interface Position
    {
        String getDescription();
    }
 }
