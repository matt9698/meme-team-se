/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import property_tycoon.model.BoardPosition;
import property_tycoon.model.TaxPosition;

/**
 *
 * @author adam
 */
public class TaxPositionView extends BoardPositionView
{
    
    private TaxPosition model;
    
    public TaxPositionView(TaxPosition model){
        this.model = model;
    }
    
    @Override
    public TaxPosition getModel()
    {
        return model;
    }
    
}
