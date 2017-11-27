package controller;

import javafx.stage.Stage;
import model.*;
import view.*;

/**
 * Link between views and models.
 *
 * @author Java Group
 */
public class Controller
{

    private static Controller INSTANCE;

    private final Model _model;
    private final View _view;

    private Controller()
    {
        _model = Model.getInstance();
        _view = View.getInstance();
    }

    /**
     * Retrives the instance of the Controller.
     *
     * Retrives the instance of the Controller, there can be only one instance
     * of the Controller at once thanks to the singleton design pattern.
     *
     * @return Instance of the Controller
     */
    public static Controller getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    /**
     * Start the Controller.
     *
     * @param stage Stage where the dispaly will be managed.
     */
    public void start(Stage stage)
    {
        _model.GraphToDot();
        _view.start(stage);
    }
}
