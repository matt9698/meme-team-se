/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author meme-team
 */
public class MainMenu extends Application
{

    public static int WIDTH = 500, HEIGHT = 500;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setTitle("Property Tycoon");
        stage.setResizable(false);
        stage.setScene(mainMenu(stage));
        stage.show();
    }

    private Scene mainMenu(Stage stage)
    {
        
        Button newGameButton = new Button("New Game");
        newGameButton.setOnMouseClicked((t) -> {
            stage.hide();
            try {
                PreGameMenu preGameMenu = new PreGameMenu();
                preGameMenu.start(stage);
            }
            catch(Exception ex) {
            }
        });
        
        Button settingsButton = new Button("Settings");
        settingsButton.setOnMouseClicked((t) -> {
            System.out.println("Settings button pressed");  
        });
        
        Button exitButton = new Button("Exit");
        exitButton.setOnMouseClicked((t) -> {
            System.exit(0);
        });
        
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(newGameButton);
        buttons.add(settingsButton);
        buttons.add(exitButton);
        
        Background defaultBackground = new Background(new BackgroundFill(Color.web("#3d8af7"), new CornerRadii(5), Insets.EMPTY));
        for(Button button : buttons) {
            button.setBackground(defaultBackground);
            button.setMinSize(200, 80);
            button.setStyle(String.format("-fx-font-size: %dpx;", (int)(0.25 * button.getMinWidth())));
            button.setFont(new Font("font.ttf", 40));
            button.setOnMouseEntered((t) -> {
                button.setBackground(new Background(new BackgroundFill(Color.web("#91e4fb"), new CornerRadii(5), Insets.EMPTY)));
            });
            button.setOnMouseExited((t) -> {
                button.setBackground(defaultBackground);
            });
        }
        
        
        HBox hbox = new HBox(buttons.get(0), buttons.get(1), buttons.get(2));
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(90));
        hbox.setSpacing(30);
        
        BorderPane bp = new BorderPane();
        bp.setBackground(new Background(new BackgroundFill(Color.web("#cbe8ba"),CornerRadii.EMPTY, Insets.EMPTY)));
        bp.setBottom(hbox);
        bp.setTop(new ImageView(new Image("logo.png")));
        bp.setPadding(new Insets(90));
        bp.setAlignment(bp.getTop(), Pos.CENTER);
        
        Scene scene = new Scene(bp);
        return scene;
    }

}
