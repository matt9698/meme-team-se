/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 * @author Matt
 * @version 01/05/2018
 */
public class Board
{
    private BoardPosition[] positions;
    private Player[] players;
    private int[] playerPositionMap;

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

    public BoardPosition getNext(BoardPosition position)
    {
        return getPosition((getIndex(position) + 1) % getPositionCount());
    }

    public BoardPosition getPrevious(BoardPosition position)
    {
        return getPosition((getIndex(position) - 1) % getPositionCount());
    }

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
            throw new IllegalArgumentException("postion is not on this board.");
        }

        return i;
    }

    public BoardPosition getPosition(int index)
    {
        if(index < 0 || index >= positions.length) {
            throw new IndexOutOfBoundsException(
                "position index is out of bounds.");
        }

        return positions[index];
    }

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

    public int getPositionCount()
    {
        return positions.length;
    }
    
    public Player[] getPlayers(){
        return players;
    }
}
