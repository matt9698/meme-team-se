/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

/**
 * @author meme-team
 * @version 29/04/2018
 */
public class BoardPositionMapping
{
    private int row;
    private int column;
    private double rotation;

    public BoardPositionMapping(int column, int row)
    {
        this(column, row, 0d);
    }

    public BoardPositionMapping(int column, int row, double rotation)
    {
        this.column = column;
        this.row = row;
        this.rotation = rotation;
    }

    public int getColumn()
    {
        return column;
    }

    public double getRotation()
    {
        return rotation;
    }

    public int getRow()
    {
        return row;
    }
}
