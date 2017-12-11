import java.awt.event.KeyEvent;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * This class is used to start the program.
 *
 * @author Java Group
 */
public class Labyrinth extends Application
{
    
    static Controller _controller;
    
    /**
     * Main function which launch the application.
     *
     * @param args is unused.
     */
    public static void main (String args[])
    {
    	while(true)
    		launch();
    }
    
    /**
     * Function called at the start of the application which set the {@link
     * controller.Controller Controller}.
     *
     * @param primaryStage Stage used for the graphical interface.
     */
    @Override
    public void start (Stage primaryStage)
    {
        try
        {
            _controller = Controller.getInstance();
            _controller.start(primaryStage);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Stops the application.
     */
    @Override
    public void stop ()
    {
        System.exit(0);
    }
    
}