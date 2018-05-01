/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view.builder;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.BuilderFactory;
import property_tycoon.view.PropertyPosition;

/**
 * @author Matt
 * @version 01/05/2018
 */
 public class PropertyTycoonBuilderFactory implements PropertyTycoonBuilderFactory
 {
    private BuilderFactory default = new JavaFXBuilderFactory();

    @Override
    public Builder<?> getBuilder(Class<?> type)
    {
        if(type == PropertyPosition.class) {
            return new PropertyPositionBuilder();
        }
        else {
            // Type is unrecognised so use default BuilderFactory
            return default.getBuilder(type);
        }
    }
 }
