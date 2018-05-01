/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view.builder;

import javafx.util.Builder;
import property_tycoon.model.Property;
import property_tycoon.view.PropertyPosition;

/**
 * @author Matt
 * @version 01/05/2018
 */
public class PropertyPositionBuilder implements Builder<PropertyPosition>
{
    private Property model;

    public Property getModel()
    {
        return model;
    }

    public void setModel(Property model)
    {
        this.model = model;
    }

    @Override
    public PropertyPosition build()
    {
        return PropertyPosition.create(model);
    }
}
