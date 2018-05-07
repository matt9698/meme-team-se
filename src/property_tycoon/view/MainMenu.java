/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author not matt
 */
public class MainMenu extends Application
{

    private Scene mainMenuScene, gameMenuScene;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public MainMenu()
    {
    }

    @Override
    public void start(Stage stage) throws Exception
    {

        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setTitle("Property Tycoon");
        stage.setResizable(false);
        stage.setScene(mainMenu(stage));
        stage.show();
    }

    private Scene gameMenu(Stage stage){
        Image img = new Image("logo.png");
        ImageView imgView = new ImageView(img);

        Insets insets = new Insets(90);

        Button button = new Button("Create Normal");
        button.setOnMouseClicked((t) -> {
            System.out.println("button 1 pressed");                               //take 
        });
        button.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button.setMinSize(100, 40);
        button.setOnMouseEntered((t) -> {
            button.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button.setOnMouseExited((t) -> {
            button.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        Button button2 = new Button("Create Abridged");
        button2.setOnMouseClicked((t) -> {
            System.out.println("button 2 pressed");
        });
        button2.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button2.setMinSize(100, 40);
        button2.setOnMouseEntered((t) -> {
            button2.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button2.setOnMouseExited((t) -> {
            button2.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        Button button3 = new Button("Back to Main Menu");
        button3.setOnMouseClicked((t) -> {
            System.exit(0);
        });
        button3.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button3.setMinSize(100, 40);
        button3.setOnMouseEntered((t) -> {
            button3.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button3.setOnMouseExited((t) -> {
            button3.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        HBox hbox = new HBox(button, button2, button3);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(insets);
        hbox.setSpacing(30);
        BorderPane bp = new BorderPane();
        bp.setBackground(new Background(new BackgroundFill(Color.web(
            "#cbe8ba"),
            CornerRadii.EMPTY, Insets.EMPTY)));
        gameMenuScene = new Scene(bp);
        bp.setBottom(hbox);
        bp.setTop(imgView);
        bp.setPadding(insets);
        bp.setAlignment(bp.getTop(), Pos.CENTER);
        return gameMenuScene;
    }
    
    private Scene mainMenu(Stage stage)
    {
        Image img = new Image("logo.png");
        ImageView imgView = new ImageView(img);

        Insets insets = new Insets(90);

        Button button = new Button("New Game");
        button.setOnMouseClicked((t) -> {
            stage.setScene(gameMenu(stage));
        });
        button.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button.setMinSize(100, 40);
        button.setOnMouseEntered((t) -> {
            button.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button.setOnMouseExited((t) -> {
            button.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        Button button2 = new Button("Settings");
        button2.setOnMouseClicked((t) -> {
            System.out.println("button 2 pressed");                                   //TO DO, SETTINGS PAGE
        });
        button2.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button2.setMinSize(100, 40);
        button2.setOnMouseEntered((t) -> {
            button2.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button2.setOnMouseExited((t) -> {
            button2.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        Button button3 = new Button("Exit");
        button3.setOnMouseClicked((t) -> {
            System.exit(0);
        });
        button3.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        button3.setMinSize(100, 40);
        button3.setOnMouseEntered((t) -> {
            button3.setBackground(new Background(new BackgroundFill(Color.web(
                "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        button3.setOnMouseExited((t) -> {
            button3.setBackground(new Background(new BackgroundFill(Color.web(
                "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });

        HBox hbox = new HBox(button, button2, button3);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(insets);
        hbox.setSpacing(30);
        BorderPane bp = new BorderPane();
        bp.setBackground(new Background(new BackgroundFill(Color.web(
            "#cbe8ba"),
            CornerRadii.EMPTY, Insets.EMPTY)));
        mainMenuScene = new Scene(bp);
        bp.setBottom(hbox);
        bp.setTop(imgView);
        bp.setPadding(insets);
        bp.setAlignment(bp.getTop(), Pos.CENTER);
        return mainMenuScene;
    }

}
