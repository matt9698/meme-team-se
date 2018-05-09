/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import property_tycoon.control.ComputerController;
import property_tycoon.control.HumanController;
import property_tycoon.model.DefaultGameData;
import property_tycoon.model.Game;
import property_tycoon.model.Player;

/**
 *
 * @author adam
 */
public class PreGameMenu extends Application
{

    public static int WIDTH = 800, HEIGHT = 800;
    public static Color defaultColor = Color.web("#cbe8ba");
    private Stage stage;
    //private MainMenu mainMenu = new MainMenu();

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setTitle("Property Tycoon");
        stage.setResizable(true);
        stage.setScene(createScene(stage));
        stage.show();
    }

    /**
     * buttons: start, choose game mode, add player
     * display: current players
     */
    private Scene createScene(Stage stage)
    {
        //BorderPane
        BorderPane bp = new BorderPane();
        bp.setBackground(new Background(new BackgroundFill(defaultColor,
            CornerRadii.EMPTY, Insets.EMPTY)));

        //Top pane
        Image img = new Image("logo.png");
        ImageView imgView = new ImageView(img);
        HBox hbox = new HBox(imgView);
        hbox.setMinSize(100, 100);
        hbox.setAlignment(Pos.CENTER);

        //Bottom pane
        Button goBackButton = new Button("Back to menu");
        goBackButton.setOnMouseClicked((t) -> {
            stage.hide();
            try {
                MainMenu mainMenu = new MainMenu();
                mainMenu.start(stage);
            }
            catch(Exception ex) {
            }
        });
        goBackButton.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        goBackButton.setMinSize(100, 40);
        goBackButton.setOnMouseEntered((t) -> {
            goBackButton.setBackground(new Background(new BackgroundFill(
                Color.web(
                    "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        goBackButton.setOnMouseExited((t) -> {
            goBackButton.setBackground(new Background(new BackgroundFill(
                Color.web(
                    "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });
        
        Label gameTypeLabel = new Label(("Game Type: "));
        ComboBox comboBox3 = new ComboBox();
        comboBox3.getItems().add("Full");
        comboBox3.getItems().add("Abridged");
        comboBox3.getSelectionModel().select(0);

        Button startButton = new Button("Start");
        startButton.setBackground(new Background(new BackgroundFill(Color.web(
            "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        startButton.setMinSize(100, 40);
        startButton.setOnMouseEntered((t) -> {
            startButton.setBackground(new Background(new BackgroundFill(
                Color.web(
                    "#91e4fb"),
                new CornerRadii(10), Insets.EMPTY)));
        });
        startButton.setOnMouseExited((t) -> {
            startButton.setBackground(new Background(new BackgroundFill(
                Color.web(
                    "#3d8af7"), new CornerRadii(10), Insets.EMPTY)));
        });
        
        
        Insets insets = new Insets(20);
        HBox bottomHBox = new HBox(goBackButton, gameTypeLabel, comboBox3, startButton);
        bottomHBox.setMinSize(100, 50);
        bottomHBox.setAlignment(Pos.CENTER_LEFT);
        bottomHBox.setPadding(insets);
        bottomHBox.setSpacing(20);
        bottomHBox.setPadding(new Insets(80));
        bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
        
        

        //Center Pane
        
        Font font = new Font("font.tff", 30);
        Font font2 = new Font("font.tff", 60);

        PlayerConfig[] configs = new PlayerConfig[6];
        for(int i = 0; i < configs.length; i++) {
            configs[i] = new PlayerConfig(i+1);
            configs[i].setSpacing(20);
            configs[i].setAlignment(Pos.CENTER);
        }
        
        //create players upon the start button being clicked
        startButton.setOnMouseClicked((t) -> {
            
            int numPlayers = 0;
            for (PlayerConfig config: configs)
            {
                if (config.playerType.getValue() != "No Player")
                {
                    numPlayers++;
                }
            }
            
            Player[] players = new Player[numPlayers];
            Color[] chosenColors = new Color[numPlayers];
            int index = 0;
            for (PlayerConfig config: configs)
            {
                if (config.playerType.getValue() == "Human")
                {
//                    if (!checkNamesValid(configs))
//                    {
//                        createNameError();
//                    }
                    
                    Player player = new Player(config.name.getText(), config.color.getSelectionModel().getSelectedItem(), new HumanController());
                    players[index] = player;
                    chosenColors[index] = config.color.getSelectionModel().getSelectedItem();
                    index++;
                    System.out.println(player.getDescription());
                    System.out.println(player.getColor().toString());
                }
                
                else if (config.playerType.getValue() == "Computer")
                {
                    Player player = new Player(config.name.getText(), config.color.getSelectionModel().getSelectedItem(), new ComputerController());
                    players[index] = player;
                    chosenColors[index] = config.color.getSelectionModel().getSelectedItem();
                    index++;
                    System.out.println(player.getDescription());
                    System.out.println(player.getColor().toString());
                }
            }
            if (checkColorEquality(chosenColors))
            {
                createColorError();
            }
            else
            {
                stage.hide();
                GameView gameview = new GameView(new Game(players, new DefaultGameData()));
                gameview.showAndWait();
            }
            
        });
        
        VBox vbox = new VBox(configs);
        vbox.setMinSize(WIDTH - 100, 100);
        vbox.setStyle("-fx-border-color:GREY; -fx-border-width: 1; -fx-border-radius: 5;");
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);
//        vbox.setBorder(new Border(new BorderStroke(Color.BLACK,
//            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        //Right pane
        //Color picked = comboBox2.getSelectionModel().getSelectedItem();
        
        //rightTopVBox.setPadding(new Insets(300, 80, 80, 80));
        //TODO: make buttons for each colour and save colour upon button press

//        hbox.setAlignment(Pos.CENTER);
//        hbox.setPadding(insets);
//        hbox.setSpacing(30);
//        bp.setBackground(new Background(new BackgroundFill(Color.GREEN,
//            CornerRadii.EMPTY, Insets.EMPTY)));
        //Scene
        Scene scene = new Scene(bp);
        bp.setBottom(bottomHBox);
        bp.setTop(hbox);
        bp.setCenter(vbox);
        //bp.setRight(vbox2);
//        bp.setPadding(insets);
        //bp.setAlignment(bp.getTop(), Pos.CENTER);
        return scene;
    }
    
    //checks if two of the chosen colours by players are the same
    private boolean checkColorEquality(Color[] colors)
    {
        for (int i = 0; i < colors.length; i++)
        {
            for (int j = i+1; j < colors.length; j++)
            {
                if (colors[i] == colors[j])
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    //create an error window incase two or more chosen colors are the same
    private void createColorError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Resolve Duplicate Colors");
        alert.setContentText("Two or more players have chosen the same colour. Please choose differently!");
        alert.showAndWait();
    }
    
    private boolean checkNamesValid(PlayerConfig[] configs)
    {
        for (PlayerConfig c: configs)
        {
            if (c.name.getText().trim().isEmpty())
            {
                return false;
            }
        }
        return true;
    }
    
    private void createNameError()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Resolve Empty Name Fields");
        alert.setContentText("Atleast one player has an invalid player name. Please choose enter valid names!");
        alert.showAndWait();
    }

    private void setColorCircle(ActionEvent e, int i)
    {
        ComboBox<Color> combo = (ComboBox)e.getSource();
        

    }
}
