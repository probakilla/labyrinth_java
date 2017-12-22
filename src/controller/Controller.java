package controller;


import java.util.ArrayList;
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
    private final int NB_MAX_CANDIES = NB_GRAPH_VERTICES / 4;

    private final Model _model;
    @FXML
    private final View _view;
    private final PlayableCharacter _player;
    private final ArrayList<Enemy> _enemies;
    private final AbstractCandy[] _candies;
    private final ArrayList<Door> _closedDoor;
    private Vertex _exitDoorPosition;
    private boolean _startEnemies = false, _reStartEnemies = false;
    private final int LIFE_NUMBER = 3;
    private final int WIN_SCORE = 50;
    CountDownLatch _restartSignal = new CountDownLatch(1);
    private int _difficulty = 0;
    
    private Controller()
    {
        _model = Model.getInstance();
        _view = View.getInstance();
        _player = PlayableCharacter.getInstance();
        _enemies = new ArrayList<Enemy>();
        _candies = new AbstractCandy[NB_CANDIES];
        _closedDoor = new ArrayList<Door> ();
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
            for (int i = 0; i < _enemies.size(); i++)
            {
                _enemies.get(i).setTargetX(_player.getPosition().getX());
                _enemies.get(i).setTargetY(_player.getPosition().getY());
                if (_player.collision(_enemies.get(i).getPosition()))
                {
                    int lf = _player.decrementLife();
                    _view.setLife(lf);
                    if (lf == 0)
                    {
                        // Le joueur perds.
                        _player.setScore(0);
                        _view.setScore(0);
                        _difficulty = -1;
                        reset(_difficulty);
                    }
                }
            }
            for (int i = 0; i < _closedDoor.size(); i++)
            {
                // On ouvre la porte associé.
                if (_player.collision(_closedDoor.get(i).getSwitchOn()))
                {
                    Edge door = _closedDoor.get(i).getDoor();
                    door.setType(Edge.Type.OPENED_DOOR);
                    _view.updateDoor(door, Edge.Type.OPENED_DOOR);
                }
                else if (_player.collision(_closedDoor.get(i).getSwitchOff()))
                {
                    Edge door = _closedDoor.get(i).getDoor();
                    door.setType(Edge.Type.CLOSED_DOOR);
                    _view.updateDoor(door, Edge.Type.CLOSED_DOOR);
                }
            }
            if (_player.collision(_exitDoorPosition))
            {
            	for (int i = 0; i < _enemies.size(); ++i)
            		_enemies.get(i).stopRunning();
                int score = _player.getScore() + PlayableCharacter.getLife () * WIN_SCORE;//On gagne un nombre de point proportionel au nombre de vie restantes.
                _player.setScore(score);
                _view.setScore(score);
                //Check if we the player reached the end of the game.
            	if (_difficulty == 12)
            	{
                    for (int i = 0; i < _enemies.size(); ++i)
                    {
                    	_enemies.get(i).stopRunning();
                    }
                    _view.setEndGameText(true);
                	_player.removeOnChangeListener();
                	//Once at the end the player has to relaunch the program to replay a game.
                	return;
            	}
                reset(_difficulty++);
            }
        });

        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies.add(new Enemy(_restartSignal));
            _enemies.get(i).randomizePosition();
            _view.createEnnemies(_enemies.get(i).getPosition().getX(), _enemies.get(i).getPosition().getY());
            final int idx = i;
            _enemies.get(idx).setOnChangeListener(new AbstractCharacter.OnChangeListener()
            {
                @Override
                public void changed(int x, int y)
                {
                    Platform.runLater(() ->
                    {
                        _view.updateEnemyPosition(idx, x, y);
                    });
                    if (_player.collision(_enemies.get(idx).getPosition()))
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
                                _difficulty = 0;
                                reset(_difficulty);
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
    private void reset(int difficulty)
    {
    	/*We have three parameters to increase the difficulty, so 
    	 * we increase once at a time each parameters.
    	*/
    	if (difficulty >= 0)
    		difficulty = (difficulty%3)+1;
    	switch (difficulty)
    	{
    	//The player lose so we reset the difficulty.
    	case -1:
    		NB_CLOSED_DOOR = 3;
    		Enemy.resetSpeed();
    		NB_ENEMIES = 2;
    		while (_enemies.size() > NB_ENEMIES)
    		{
    			_enemies.get(NB_ENEMIES).stopRunning();
    			_enemies.get(NB_ENEMIES).removeOnChangeListener();
    			_enemies.remove(NB_ENEMIES);
    			_view.removeEnemy(NB_ENEMIES);
    		}
    		break;
       //We increase the number of closed door.
    	case 1:
    		++NB_CLOSED_DOOR;
    		break;
        //We increase the movement speed.
    	case 2: 
    		Enemy.incrementSpeed();
    		break;
    	//We increase the number of enemies.
    	case 3:
    		++NB_ENEMIES;
    		_enemies.add(new Enemy(_restartSignal));

    		int i = _enemies.size() - 1;
            _enemies.get(i).randomizePosition();
            _view.createEnnemies(_enemies.get(i).getPosition().getX(), _enemies.get(i).getPosition().getY());
    		_enemies.get(i).stopRunning();
            _enemies.get(i).start();
            final int idx = i;
            _enemies.get(idx).setOnChangeListener(new AbstractCharacter.OnChangeListener()
            {
                @Override
                public void changed(int x, int y)
                {
                    Platform.runLater(() ->
                    {
                        _view.updateEnemyPosition(idx, x, y);
                    });
                    if (_player.collision(_enemies.get(idx).getPosition()))
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
                                _difficulty = -1;
                                reset(_difficulty);
                            });
                        }
                    }
                }
            });
    		break;
    	default:
    		break;
    	}
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
        for (i = 0; i < _enemies.size(); ++i)
        {
            _enemies.get(i).randomizePosition();
            _view.updateEnemyPosition(i, _enemies.get(i).getPosition().getX(), _enemies.get(i).getPosition().getY());
            _enemies.get(i).setTargetX(_player.getPosition().getX());
            _enemies.get(i).setTargetY(_player.getPosition().getY());
    		_enemies.get(i).stopRunning();
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
        int size = _closedDoor.size();
        for (i = 0; i < size; ++i)
        {
        	_closedDoor.remove(0);
        }
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
                    _closedDoor.add(new Door(switchOn, switchOff, door));
                }
            }
        }
        //Reset the view.
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
        for (int i = 0; i < _enemies.size(); i++)
        {
        	_enemies.get(i).setTargetX(_player.getPosition().getX());
        	_enemies.get(i).setTargetY(_player.getPosition().getY());
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
                    //NB_CLOSED_DOOR = i;
                    door.setType(Edge.Type.CORRIDOR);
                }
                else
                {
                    _view.createSwitchOn(switchOn.getX(), switchOn.getY());
                    _view.createSwitchOff(switchOff.getX(), switchOff.getY());
                    _closedDoor.add(new Door(switchOn, switchOff, door));
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
        _view.start(stage, graph, () ->{});

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
                        	for (int i = 0; i < _enemies.size(); ++i)
                        		_enemies.get(i).keepRunning();
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
                                for (int i = 0; i < _enemies.size(); i++)
									_enemies.get(i).stopRunning();
                                break;
                            case R:
                            	//To go on the default case in reset, and keep the same difficulty.
                                reset(-2);
                                break;
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
        for (int i = 0; i < _enemies.size(); i++)
        {
        	_enemies.get(i).start();
        }
    }
    /**
     * To re-launch enemies when we restart the labyrinth.
     */
      private void rePlayGame() 
      {
          _restartSignal.countDown();
          _restartSignal = new CountDownLatch(1); 
          
          for (int i = 0; i < _enemies.size(); i++)
          {
        	  _enemies.get(i).updateRestartSignal(_restartSignal);
          }
      }
}