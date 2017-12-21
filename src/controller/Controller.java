package controller;


import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
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
    private final int NB_GRAPH_VERTICES = Graph.getGRIDWIDTH() * Graph.getGRIDHEIGHT();
    private int NB_ENEMIES = 2, NB_CANDIES = 10, NB_OPENED_DOOR = 10, NB_CLOSED_DOOR = 3;
    private final int NB_MAX_CANDIES = NB_GRAPH_VERTICES / 4, NB_MAX_CLOSED_DOOR = NB_GRAPH_VERTICES / 2;

    private final Model _model;
    @FXML
    private final View _view;
    private final PlayableCharacter _player;
    private final Enemy[] _enemies;
    private final AbstractCandy[] _candies;
    private final Door[] _closedDoor;
    private Vertex _exitDoorPosition;
    private boolean _startEnemies = false, _reStartEnemies = false;
    private final int LIFE_NUMBER = 3;
    private final int WIN_SCORE = 50;
    CountDownLatch _restartSignal = new CountDownLatch(1);
    
    private Controller()
    {
        _model = Model.getInstance();
        _view = View.getInstance();
        _player = PlayableCharacter.getInstance();
        _enemies = new Enemy[NB_ENEMIES];
        _candies = new AbstractCandy[NB_CANDIES];
        _closedDoor = new Door[NB_CLOSED_DOOR];
        _player.setOnChangeListener((int x, int y) ->
        {
            _view.updatePlayerPosition(x, y);
            for (int i = 0; i < NB_CANDIES; i++)
            {
                if (_candies[i] != null && _player.collision(_candies[i].getPosition()))
                {
                    _player.increaseScore(_candies[i]);
                    _view.setScore(_player.getScore());
                    _view.removeCandy(i);
                    _candies[i] = null;
                }
            }
            for (int i = 0; i < NB_ENEMIES; i++)
            {
                _enemies[i].setTargetX(_player.getPosition().getX());
                _enemies[i].setTargetY(_player.getPosition().getY());
                if (_player.collision(_enemies[i].getPosition()))
                {
                    int lf = _player.decrementLife();
                    _view.setLife(lf);
                    if (lf == 0)
                    {
                        //   _view.setEndGameText(false);
                        // Le joueur perds.
                        _player.setScore(0);
                        _view.setScore(0);
                        reset();
                    }
                }
            }
            for (int i = 0; i < NB_CLOSED_DOOR && i <= NB_MAX_CLOSED_DOOR; i++)
            {
                // On ouvre la porte associé.
                if (_player.collision(_closedDoor[i].getSwitchOn()))
                {
                    Edge door = _closedDoor[i].getDoor();
                    door.setType(Edge.Type.OPENED_DOOR);
                    _view.updateDoor(door, Edge.Type.OPENED_DOOR);
                }
                else if (_player.collision(_closedDoor[i].getSwitchOff()))
                {
                    Edge door = _closedDoor[i].getDoor();
                    door.setType(Edge.Type.CLOSED_DOOR);
                    _view.updateDoor(door, Edge.Type.CLOSED_DOOR);
                }
            }
            if (_player.collision(_exitDoorPosition))
            {
                for (int i = 0; i < NB_ENEMIES; i++)
                {
                    _enemies[i].stopRunning();
                }
                // Le joueur gagne.
                //_view.setEndGameText(true);
                int score = _player.getScore() + _player.getLife () * WIN_SCORE;//On gagne un nombre de point proportionel au nombre de vie restantes.
                _player.setScore(score);
                _view.setScore(score);
                reset();
            }
        });

        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i] = new Enemy(_restartSignal);
            _enemies[i].randomizePosition();
            _view.createEnnemies(_enemies[i].getPosition().getX(), _enemies[i].getPosition().getY());
            final int idx = i;
            _enemies[i].setOnChangeListener(new AbstractCharacter.OnChangeListener()
            {
                @Override
                public void changed(int x, int y)
                {
                    Platform.runLater(() ->
                    {
                        _view.updateEnemyPosition(idx, x, y);
                    });
                    if (_player.collision(_enemies[idx].getPosition()))
                    {
                        int lf = _player.decrementLife();
                        Platform.runLater(() ->
                        {
                            /* 
                             * Les threads ne peuvent pas modifier les aspects 
                             * graphiques,
                             */
                            _view.setLife(lf);
                        });
                        if (lf == 0)
                        {
                            // Le joueur perds.
                            int score = 0;
                            _player.setScore(score);
                            Platform.runLater(() ->
                            {
                                _view.setScore(score);
                                reset();
                            });
                        }
                    }
                }
            });
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
    public static Controller getInstance()
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
    public Model getModel()
    {
        return _model;
    }

    /**
     * Reset the level.
     */
    private void reset()
    {
        int i, j; // cpt boucles.

        // Re-création du graph et de sa porte de sortie.      
        Graph graph = Graph.getInstance();
        Graph.clearGraph(graph);
        _model.buildRandomPath(new Vertex(0, 0, 0));
        _exitDoorPosition = graph.getEndPath();
        _view.updateDoorPosition(_exitDoorPosition.getX(), _exitDoorPosition.getY());
        // Positions objets du labyrinthe.
        _player.setPosition(0, 0);
        PlayableCharacter.setLife(LIFE_NUMBER);
        _view.updatePlayerPosition(0, 0);
        _view.setLife(LIFE_NUMBER);
        // Réinitialsation des ennemies.
        _reStartEnemies = true;
        for (i = 0; i < NB_ENEMIES; ++i)
        {
            _enemies[i].randomizePosition();
            _view.updateEnemyPosition(i, _enemies[i].getPosition().getX(), _enemies[i].getPosition().getY());
             _enemies[i].setTargetX(_player.getPosition().getX());
            _enemies[i].setTargetY(_player.getPosition().getY());
            _enemies[i].stopRunning();
        }

        _view.removeAllCandies();
        // Initilisation des Candies.
        for (i = 0; i < NB_CANDIES && i < NB_MAX_CANDIES; i++)
        {
            //Si on à déjà placé le maximum de bonbons possible on arrếte.
            AbstractCandy candy = (AbstractCandy) CandyFactory.getCandy();
            //On essaye de placer autant de fois qu'il y a de cases dans le graphe, si on échoue on n'arrête.
            for (j = 0; j < NB_GRAPH_VERTICES; ++j)
            {
                if (AbstractCandy.correctCandyPosition(_candies, candy, _exitDoorPosition))
                {
                    break;
                }
                candy = (AbstractCandy) CandyFactory.getCandy();
            }
            if (!AbstractCandy.correctCandyPosition(_candies, candy, _exitDoorPosition))
            {
                NB_CANDIES = i;
                break;
            }
            _candies[i] = candy;
            _view.createCandy(_candies[i].getPosition().getX(), _candies[i].getPosition().getY(),
                    _candies[i].getImgPath());
        }

        // Reset des portes.
        for (i = 0; i < NB_OPENED_DOOR; ++i)
        {
            graph.openDoorRandom();
        }

        // Reset les portes fermées.
        _view.removeAllSwitchOff();
        _view.removeAllSwitchOn();
        for (i = 0; i < NB_CLOSED_DOOR; ++i)
        {
            Edge door = graph.closeDoorRandom();
            Vertex switchOn = graph.setSwitchOn(door);
            // On essaye de placer l'interrupteur 300 fois, si on échoue on n'arrête.
            for (j = 0; j < 300; ++j)
            {
                if (Door.correctSwitchPosition(_closedDoor, _candies, switchOn, _exitDoorPosition))
                {
                    break;
                }
                switchOn = graph.setSwitchOn(door);
            }
            if (!Door.correctSwitchPosition(_closedDoor, _candies, switchOn, _exitDoorPosition))
            {
                //On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
                door.setType(Edge.Type.CORRIDOR);
                NB_CLOSED_DOOR = i;
            }
            else
            {
                Vertex switchOff = graph.setSwitchOff(door);
                // On essaye de placer l'interrupteur 300 fois, si on échoue on n'arrête.
                for (j = 0; j < 300; ++j)
                {
                    if (Door.correctSwitchPosition(_closedDoor, _candies, switchOff, _exitDoorPosition))
                    {
                        break;
                    }
                    switchOff = graph.setSwitchOff(door);
                }
                if (!Door.correctSwitchPosition(_closedDoor, _candies, switchOff, _exitDoorPosition))
                {
                    //On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
                    NB_CLOSED_DOOR = i;
                    door.setType(Edge.Type.CORRIDOR);
                }
                else
                {
                    _view.createSwitchOn(switchOn.getX(), switchOn.getY());
                    _view.createSwitchOff(switchOff.getX(), switchOff.getY());
                    _closedDoor[i] = new Door(switchOn, switchOff, door);
                }
            }
        }
        _view.resetFrame();
        _view.drawGraph(graph);
    }

    /**
     * Set the window with starting parameters.
     */
    private void init()
    {
        _model.buildRandomPath(new Vertex(0, 0, 0));
        Graph graph = Graph.getInstance();
        _exitDoorPosition = graph.getEndPath();
        _view.createDoor(_exitDoorPosition.getX(), _exitDoorPosition.getY());
        _view.createPlayable();

        // Initilisation des Candies.
        for (int i = 0; i < NB_CANDIES && i < NB_MAX_CANDIES; i++)
        {
            //Si on à déjà placé le maximum de bonbons possible on arrếte.
            AbstractCandy candy = (AbstractCandy) CandyFactory.getCandy();
            int j;
            //On essaye de placer autant de fois qu'il y a de cases dans le graphe, si on échoue on n'arrête.
            for (j = 0; j < NB_GRAPH_VERTICES; ++j)
            {
                if (AbstractCandy.correctCandyPosition(_candies, candy, _exitDoorPosition))
                {
                    break;
                }
                candy = (AbstractCandy) CandyFactory.getCandy();
            }
            if (!AbstractCandy.correctCandyPosition(_candies, candy, _exitDoorPosition))
            {
                NB_CANDIES = i;
                break;
            }
            _candies[i] = candy;
            _view.createCandy(_candies[i].getPosition().getX(), _candies[i].getPosition().getY(),
                _candies[i].getImgPath());
        }

        // Initialisation des Ennemies.
        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i].setTargetX(_player.getPosition().getX());
            _enemies[i].setTargetY(_player.getPosition().getY());
        }

        // Ouvre des murs.
        for (int i = 0; i < NB_OPENED_DOOR; ++i)
        {
            graph.openDoorRandom();
        }
        // Place les portes fermées.
        for (int i = 0; i < NB_CLOSED_DOOR; ++i)
        {
            Edge door = graph.closeDoorRandom();
            Vertex switchOn = graph.setSwitchOn(door);
            int j;
            // On essaye de placer l'interrupteur 1000 fois, si on échoue on n'arrête.
            for (j = 0; j < 1000; ++j)
            {
                if (Door.correctSwitchPosition(_closedDoor, _candies, switchOn, _exitDoorPosition))
                {
                    break;
                }
                switchOn = graph.setSwitchOn(door);
            }
            if (!Door.correctSwitchPosition(_closedDoor, _candies, switchOn, _exitDoorPosition))
            {
                //On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
                door.setType(Edge.Type.CORRIDOR);
                NB_CLOSED_DOOR = i;
            }
            else
            {
                Vertex switchOff = graph.setSwitchOff(door);
                // On essaye de placer l'interrupteur 1000 fois, si on échoue on n'arrête.
                for (j = 0; j < 1000; ++j)
                {
                    if (Door.correctSwitchPosition(_closedDoor, _candies, switchOff, _exitDoorPosition))
                    {
                        break;
                    }
                    switchOff = graph.setSwitchOff(door);
                }
                if (!Door.correctSwitchPosition(_closedDoor, _candies, switchOff, _exitDoorPosition))
                {
                    //On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
                    NB_CLOSED_DOOR = i;
                    door.setType(Edge.Type.CORRIDOR);
                }
                else
                {
                    _view.createSwitchOn(switchOn.getX(), switchOn.getY());
                    _view.createSwitchOff(switchOff.getX(), switchOff.getY());
                    _closedDoor[i] = new Door(switchOn, switchOff, door);
                }
            }
        }
    }

    /**
     * Start the Controller.
     *
     * @param stage The stage where we display all content.
     */
    public void start(Stage stage)
    {
        init();
        Graph graph = Graph.getInstance();
        _view.start(stage, graph, () ->
        {
            //playGame();
        });

        // Gestion du mouvement du joueur.
        EventHandler handler;
        handler = (EventHandler) new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                if (event.getClass() == KeyEvent.class)
                {
                    KeyEvent e = (KeyEvent) event;
                    if (null != e.getCode())
                    {
                        if (!_startEnemies)
                        {
                            _startEnemies = true;
                            playGame();
                        }
                        else if (_reStartEnemies)
                        {
                            
                            for (int i = 0; i < NB_ENEMIES; i++)
                            {
                                _enemies[i].keepRunning();
                               //System.out.println(_enemies[i].getRunning ());     
                            }
                            _reStartEnemies = false;
                            rePlayGame ();
                        }
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
                            case R:
                                reset();
                            default:
                                break;
                        }

                    }
                }
                event.consume();
            }
        };
        stage.addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    /**
     * Start the enemies.
     */
    private void playGame()
    {
        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i].start();
        }
    }
    /**
     * To re-launch enemies when we restart the labyrinth.
     */
      private void rePlayGame() 
      {
          _restartSignal.countDown();
          _restartSignal = new CountDownLatch(1); 
          
          for (int i = 0; i < NB_ENEMIES; i++)
          {
              _enemies[i].updateRestartSignal(_restartSignal);
          }
      }
}