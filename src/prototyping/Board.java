package prototyping;

import javafx.scene.paint.Color;

/**
 *
 * @author adam
 * Class board represents the board which the game will be played on
 */
public class Board {

    private final int width = 10, height = 10;
    //Board is made up of a 10x10 grid of tiles where the outer tiles are properties, inner tiles can be ignored
    private Tile[][] tiles; //hi

    public Board() {

        tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = new Tile();
            }
        }
        createProperties();
    }

    private void createProperties(){

        tiles[0][0].setProperty(new Property("Go", 0, Color.GREY));
        tiles[10][0].setProperty(new Property("Jail", 0, Color.GREY));
        tiles[0][10].setProperty(new Property("Go to Jail", 0, Color.GREY));
        tiles[10][10].setProperty(new Property("Free Parking", 0, Color.GREY));
        
        /*
        * here we have to assign the tiles at the edges of the board to the property that is
        * meant to be there, by using (most likely) the xml parser or (less likely) make a .txt file and read from that 
        */
    }

}
