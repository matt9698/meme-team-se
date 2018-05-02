/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import property_tycoon.model.BoardPosition;
import property_tycoon.model.CornerPosition;

/**
 *
 * @author adam
 */
public class CornerPositionView extends BoardPositionView
{
    private final CornerPosition corner;

    public CornerPositionView(CornerPosition corner)
    {
        assert corner != null : "Corner should not be null";
        this.corner = corner;

        setId("background");
        getStylesheets().add(getClass().getResource("BoardPositionView.css").toExternalForm());
        // TODO: Listen for events on corner and forward them to listeners of this corner
    }

    @Override
    public BoardPosition getModel()
    {
        return corner;
    }   
}
