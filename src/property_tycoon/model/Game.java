/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.model;

/**
 *
 * @author adam
 */
public class Game
{

    private GameType gameType;
    private Board board;
    public static Player[] players;
    private Property[] properties;

    public Game(Player[] players, GameType gameType)
    {
        this.players = players;
        this.gameType = gameType;
        init();
    }

    private void init()
    {
        initProperties();
        initBoard();
    }

    private void initProperties()
    {

        properties = new PropertyImpl[41];
        //read from the xml file and initialize properties
    }

    private void initBoard()
    {

        board = new Board(players, properties);
    }

}
