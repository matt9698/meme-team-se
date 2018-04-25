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

    private Board board;
    private Player[] players;
    private Property[] properties;

    public Game()
    {

        init();
    }

    private void init()
    {

        initPlayers();
        initProperties();
        initBoard();
    }

    private void initPlayers()
    {

        int numOfPlayers = 2; //set this variable to the amount of players specified before the game is created 
        players = new Player[numOfPlayers];
        players[0] = new Player("Player one");
        players[1] = new Player("Player two");
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
