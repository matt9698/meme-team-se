/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * Represents a board.
 * An object of this class is called using arrays of board positions and players. 
 * It's directly instantiated once by the graphical user interface.
 * 
 * @author meme-team
 * @version 01/05/2018
 */
public class Board
{
    private BoardPosition[] positions;
    private Player[] players;
    private int[] playerPositionMap;

    
    /**
     * Constructs a board.
     * The passed in board positions and players become this object's board
     * positions and players.
     * 
     * @param positions This board's positions.
     * @param players This board's players.
     * 
     * @throws IllegalArgumentException if the passed in positions array is null.
     * @throws IllegalArgumentException if there are no positions in the position array.
     * @throws IllegalArgumentException if there is a null position in the position array.
     * @throws IllegalArgumentException if the passed in players array is null.
     * @throws IllegalArgumentException if there are no players in the player array.
     * @throws IllegalArgumentException if there is a null player in the player array. 
     */
    public Board(BoardPosition[] positions, Player[] players)
    {
        if(positions == null) {
            throw new IllegalArgumentException("positions should not be null.");
        }
        if(positions.length == 0) {
            throw new IllegalArgumentException("positions should not be empty.");
        }

        for(BoardPosition position : positions) {
            if(position == null) {
                throw new IllegalArgumentException(
                    "positions should not contain null elements.");
            }
        }

        this.positions = positions;

        if(players == null) {
            throw new IllegalArgumentException("players should not be null.");
        }
        if(players.length == 0) {
            throw new IllegalArgumentException("players should not be empty.");
        }

        for(Player player : players) {
            if(players == null) {
                throw new IllegalArgumentException(
                    "players should not contain null elements.");
            }
        }

        this.players = players;
    }

    public void moveForward(Player player, BoardPosition to)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveBackward(Player player, BoardPosition to)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveSequential(Player player, int by)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public void moveDirect(Player player, BoardPosition to)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public BoardPosition getStart()
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    /**
     * Returns the next board position from the passed in position.
     * 
     * @param position The original board position.
     * 
     * @return The next position on the board.
     */
    public BoardPosition getNext(BoardPosition position)
    {
        return getPosition((getIndex(position) + 1) % getPositionCount());
    }

    /**
     * Returns the previous board position from the passed in position.
     * 
     * @param position The original board position.
     * 
     * @return The previous position on the board.
     */
    public BoardPosition getPrevious(BoardPosition position)
    {
        return getPosition((getIndex(position) - 1) % getPositionCount());
    }

    /**
     * Returns the index of the passed in board position in the position array.
     * 
     * @param position The board position.
     * 
     * @return The index of position within the board position array.
     * 
     * @throws IllegalArgumentException if the passed in position is null.
     * @throws IllegalArgumentException if the position doesn't exist in the position array.
     */
    public int getIndex(BoardPosition position)
    {
        if(position == null) {
            throw new IllegalArgumentException("position should not be null.");
        }

        // Search the positions array to find index of position
        int i = 0;
        while(i < positions.length && !position.equals(positions[i])) {
            i++;
        }

        if(i == positions.length) {
            // We reached the end of the positions array without finding postion
            throw new IllegalArgumentException("position is not on this board.");
        }

        return i;
    }
    
    /**
     * Returns the position from the array located at the passed in index.
     * 
     * @param index The array index.
     * 
     * @return The position within the array at the index.
     * 
     * @throws IndexOutOfBoundsException if the index is out of bounds of the array.
     */
    public BoardPosition getPosition(int index)
    {
        if(index < 0 || index >= positions.length) {
            throw new IndexOutOfBoundsException(
                "position index is out of bounds.");
        }

        return positions[index];
    }

    /**
     * Returns the position from the array on which the passed in player is located at.
     * 
     * @param of The player.
     * 
     * @return The board position that the player is located at.
     * 
     * @throws IllegalArgumentException if the player is a null object.
     * @throws IllegalArgumentException if the passed in player is not in this object's
     *         player array.
     */
    public BoardPosition getPosition(Player of)
    {
        if(of == null) {
            throw new IllegalArgumentException("of should not be null.");
        }

        // Search the players array to find index of player
        int i = 0;
        while(i < players.length && !of.equals(players[i])) {
            i++;
        }

        if(i == players.length) {
            // We reached the end of the players array without finding the player
            throw new IllegalArgumentException("of is not on this board.");
        }

        return positions[playerPositionMap[i]];
    }

    /**
     * Returns the number of positions in this board.
     * 
     * @return The number of positions objects in the board position array.
     */
    public int getPositionCount()
    {
        return positions.length;
    }
    
    public Player[] getPlayers(){
        return players;
    }
}
