/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.beans.PropertyChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import property_tycoon.model.Player;
import property_tycoon.model.Property;

/**
 * Control that displays information about a player.
 *
 * @author meme-team
 * @version 02/05/2018
 */
public class PlayerView extends VBox
{
    private final Player model;
    private final ObservableList<Property> properties;
    private ListView<Property> propertiesListView;
    private Button sellButton;
    private Button mortgageButton;

    private Label cash;

    public PlayerView(Player model)
    {
        if(model == null) {
            throw new IllegalArgumentException("model should not be null.");
        }
        this.model = model;

        model.cashProperty().addListener(e -> update());
        model.getProperties().addListener((ListChangeListener)e -> update());
        model.getCards().addListener((ListChangeListener)e -> update());

        Label description = new Label(model.getDescription());
        description.setFont(Font.font("Helvetica", FontWeight.BOLD, 24d));

        cash = new Label("£" + model.getCash());
        cash.setFont(Font.font("Helvetica", 24));

        properties = FXCollections.observableArrayList(model.getProperties());
        propertiesListView = new ListView<Property>(properties);
        propertiesListView.getSelectionModel().setSelectionMode(
            SelectionMode.SINGLE);
        propertiesListView.getSelectionModel().selectedItemProperty().addListener(
            e -> selectionChange());

        sellButton = new Button("Sell");
        sellButton.setOnAction(e -> sellButtonClick());

        mortgageButton = new Button("Mortgage");
        mortgageButton.setOnAction(e -> mortgageButtonClick());

        HBox buttonBar = new HBox(sellButton, mortgageButton);

        getChildren().addAll(description, cash, propertiesListView, buttonBar);
        selectionChange();
    }

    private void mortgageButtonClick()
    {
        Property p = propertiesListView.getSelectionModel().getSelectedItem();
        if(p != null) {
            if(p.isMortgaged()) {
                model.unmortgage(p);
            }
            else {
                model.mortgage(p);
            }
        }
        selectionChange();
    }

    private void update()
    {
        // TODO: Implement properly        
        cash.setText("£" + model.getCash());
        properties.clear();
        properties.addAll(model.getProperties());
    }

    private void sellButtonClick()
    {
        Property p = propertiesListView.getSelectionModel().getSelectedItem();
        if(p != null) {
            model.sell(p);
        }
    }

    private void selectionChange()
    {
        Property p = propertiesListView.getSelectionModel().getSelectedItem();
        if(p == null) {
            sellButton.setDisable(true);
            mortgageButton.setDisable(true);
        }
        else {
            sellButton.setDisable(false);
            mortgageButton.setDisable(false);

            if(p.isMortgaged()) {
                mortgageButton.setText("Unmortgage");
            }
            else {
                mortgageButton.setText("Mortgage");
            }
        }

    }
}
