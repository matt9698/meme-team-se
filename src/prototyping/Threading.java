/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package prototyping;

import prototyping.xml.XmlParsing;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author matth
 */
public class Threading extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws InterruptedException
    {
        Thread backgroundLoader = new Thread(() -> {
            try {
                XmlParsing.main(null);
            }
            catch (Exception e) {
                
            }
        });
        
        // Load background XML data in parallel
        backgroundLoader.start();

        // Display the GUI
        stage.setTitle("Property Tycoon");
        stage.show();
        
        // Wait for background data to finish loading
        backgroundLoader.join();
        
        // Use data somehow
    }
}
