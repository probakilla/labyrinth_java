package controller;

import exceptions.WrongMoveException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
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
    private final int NB_ENEMIES = 3;

    private final Model _model;
    private final View _view;
    private final PlayableCharacter _player;
    private final Enemy[] _enemies;

    private Controller ()
    {
        _model = Model.getInstance();
        _view = View.getInstance();
        _player = PlayableCharacter.getInstance();
        _enemies = new Enemy[NB_ENEMIES];

        int i;
        for (i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i] = new Enemy();
            _enemies[i].randomizePosition();
        }
    }

    /**
     * Retrieves the instance of the Controller.
     *
     * Retrieves the instance of the Controller, there can be only one instance
     * of the Controller at once thanks to the singleton design pattern.
     *
     * @return Instance of the Controller
     */
    public static Controller getInstance ()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    /**
     * Return the model used in the Controller.
     *
     * @return model
     */
    public Model getModel ()
    {
        return _model;
    }

    /**
     * Start the Controller.
     *
     * @param stage Stage where the display will be managed.
     */
    public void start (Stage stage)
    {
        _model.buildRandomPath(new Vertex(0, 0, 0));
        _model.getGraph().GraphToDot();
        _model.launchManhattan(_model.getGraph().getVertex(0, 0), _model.getGraph().getVertex(15, 15));
        _view.start(stage, _model.getGraph());
        _view.printRules();

        int i;
        for (i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i].start();
        }
        // Gestion du mouvement du joueur.
        EventHandler handler;
        handler = (EventHandler) new EventHandler()
        {
            @Override
            public void handle (Event event)
            {
                if (event.getClass() == KeyEvent.class)
                {
                    KeyEvent e = (KeyEvent) event;
                    try
                    {
                        if (null != e.getCode())
                        {
                            switch (e.getCode())
                            {
                                case UP:
                                    _player.up();
                                    break;
                                case DOWN:
                                    _player.down();
                                    break;
                                case LEFT:
                                    _player.left();
                                    break;
                                case RIGHT:
                                    _player.right();
                                    break;
                                case Q:
                                    System.out.println("Vous quittez le jeu.");
                                    System.exit(0);
                                    break;
                                case ESCAPE:
                                    System.out.println("Vous quittez le jeu.");
                                    System.exit(0);
                                    break;
                                // Touches de test.
                                case S:
                                    System.out.println("Vous arrêtez les ennemis");
                                    for (int i = 0; i < NB_ENEMIES; i++)
                                    {
                                        _enemies[i].stopRunning();
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    catch (WrongMoveException exception)
                    {
                        exception.printMessage();
                    }
                }

                event.consume();
            }
        };
        stage.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }
}
