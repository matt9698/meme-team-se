/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package property_tycoon.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author adam
 */
public class PreGameMenu extends Application
{

    public static int WIDTH = 500, HEIGHT = 900;
    public static Color defaultColor = Color.web("#cbe8ba");
    private Stage stage;
    //private MainMenu mainMenu = new MainMenu();

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setTitle("Property Tycoon");
        stage.setResizable(false);
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
        comboBox3.setDisable(true);

        Button button2 = new Button("Start");
        Insets insets = new Insets(20);
        HBox bottomHBox = new HBox(goBackButton, gameTypeLabel, comboBox3, button2);
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
        }
        
        
        
        VBox vbox = new VBox(configs);
        vbox.setMinSize(WIDTH - 100, 100);
        vbox.setPadding(insets);
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

    private void setColorCircle(ActionEvent e, int i)
    {
        ComboBox<Color> combo = (ComboBox)e.getSource();
        

    }
}
