/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.control;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import property_tycoon.model.Board;
import property_tycoon.model.BoardPosition;
import property_tycoon.model.Card;
import property_tycoon.model.CornerPosition;
import property_tycoon.model.CornerPosition.CornerType;
import property_tycoon.model.Dice;
import property_tycoon.model.Player;
import property_tycoon.model.PropertyPosition;
import property_tycoon.model.TaxPosition;

/**
 * @author meme-team
 * @version 09/05/2018
 */
public class HumanController extends Player.Controller
{

    private SimpleIntegerProperty target = new SimpleIntegerProperty();

    private Board board;
    private int diceValue;

    @Override
    public void takeTurn(Board board)
    {

        this.board = board;
        rollDice();
    }

    private void diceClick(Dice dice1, Dice dice2)
    {
        dice1.roll();
        dice2.roll();
        dice1.setOnMouseClicked(null);
        dice2.setOnMouseClicked(null);
    }

    private void rollDice()
    {
        Stage stage = new Stage();
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        Label label = new Label("Click the dice to roll!");
        label.setFont(new Font("font.ttf", 30));

        Button goButton = new Button("Start");
        goButton.setFont(new Font("font.ttf", 25));
        goButton.setOnMouseClicked(eh -> {
            stage.hide();
            movePlayer(dice1.getRoll(), dice2.getRoll());
            
        });

        HBox hBox = new HBox(dice1, dice2, goButton);
        dice1.setOnMouseClicked(eh -> {
            diceClick(dice1,dice2);
        });
        dice2.setOnMouseClicked(eh -> {
            diceClick(dice1,dice2);
        });
        

        hBox.setSpacing(30);
        hBox.setAlignment(Pos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(20));
        pane.setBackground(new Background(new BackgroundFill(Color.TURQUOISE,
            CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setTop(label);
        pane.setCenter(hBox);

        stage.setScene(new Scene(pane));
        stage.setMinWidth(50);
        stage.setMinHeight(50);
        stage.show();
    }

    private void movePlayer(int roll1, int roll2)
    {
        diceValue = roll1 + roll2;
        System.out.println("moving player " + diceValue + " spaces");
        
        BoardPosition destination = board.getPosition(board.getIndex(board.getPosition(getPlayer())) + diceValue);

        board.moveForward(getPlayer(), destination);
        takeAction();
        if(roll1 == roll2) {
            takeTurn(board);
        }
    }

    private void takeAction()
    {

        BoardPosition position = board.getPosition(getPlayer());

        //if you land on a property
        if(position instanceof PropertyPosition) {
            //if it has an owner
            if(((PropertyPosition)position).isOwned()) {
                //if you are not the owner
                if(((PropertyPosition)position).getOwner() != getPlayer()) {
                    getPlayer().payRent(
                        ((PropertyPosition)position).getProperty(), diceValue);
                }
            }
            else {
                //New popup to ask if buying or auctioning
            }
        }
        else if(position instanceof TaxPosition) {
            //New popup to display paying tax
        }
        else if(position instanceof Card.Group) {
            //New popup to display drawing a new card
            getPlayer().draw((Card.Group)position);
        }
        else if(position instanceof CornerPosition) {
            if(((CornerPosition)position).getType() == CornerType.GO) {
                //pay 200
            }
            else if(((CornerPosition)position).getType()
                == CornerType.FREE_PARKING) {
                //collect all paid tax
            }
            else if(((CornerPosition)position).getType()
                == CornerType.GO_TO_JAIL) {
                //board.moveDirect(player, position.);
            }
        }
    }

}
