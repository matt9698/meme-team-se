/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

import java.util.Arrays;

public class Game
{
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 6;

    private Board board;
    private Player[] players;
    
    private int currentPlayer;
    
    public Game(Player[] players, GameData data)
    {
        this(
            players, 
            data.getProperties(), 
            data.getStations(), 
            data.getUtilities(), 
            data.getCardGroups());
    }

    public Game(
        Player[] players,
        Property[] properties,
        Property[] stations,
        Property[] utilities,
        Card.Group[] cardGroups)
    {
        if(players == null) {
            throw new IllegalArgumentException("players should not be null.");
        }
        if(players.length < MIN_PLAYER_COUNT
            || players.length > MAX_PLAYER_COUNT)
        {
            throw new IllegalArgumentException(
                String.format(
                    "players should contain between %d and %d elements (inclusive).",
                    MIN_PLAYER_COUNT,
                    MAX_PLAYER_COUNT));
        }

        for(Player player : players) {
            if(player == null) {
                throw new IllegalArgumentException(
                    "players should not contain null elements.");
            }
        }

        // Copy the array so that elements cannot
        // be subsequently modified by external code.
        this.players = Arrays.copyOf(players, players.length);

        BoardFactory bf = new DefaultBoardFactory(
            properties, stations, utilities, cardGroups);
        board = bf.build(players);
    }

    public Board getBoard()
    {
        return board;
    }

    public Player getCurrentPlayer()
    {
        return players[currentPlayer];
    }
    
    public void incrementPlayerIndex()
    {
        currentPlayer = (currentPlayer + 1) % players.length;
    }
    
    public void nextTurn()
    {
        getCurrentPlayer().getController().takeTurn();
        incrementPlayerIndex();
    }
}
