package controller;

import java.util.Random;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.*;
import model.Edge.Type;
import view.*;

/**
 * Link between views and models.
 *
 * @author Java Group
 */
public class Controller
{

	private static Controller INSTANCE;
	private int NB_GRAPH_VERTICES = Graph.getGRIDWIDTH()*Graph.getGRIDHEIGHT();
	private int NB_ENEMIES = 2, NB_CANDIES = 10, NB_OPENED_DOOR = 10, NB_CLOSED_DOOR = 3;
	private final int NB_MAX_CANDIES = NB_GRAPH_VERTICES/4, NB_MAX_CLOSED_DOOR = NB_GRAPH_VERTICES/2;

	private final Model _model;
	@FXML
	private final View _view;
	private final PlayableCharacter _player;
	private final Enemy[] _enemies;
	private final AbstractCandy[] _candies;
	private final Door[] _closed_door;
	private Vertex exit_door_position;
	private boolean startEnemies = false;

	private Controller()
	{
		_model = Model.getInstance();
		_view = View.getInstance();
		_player = PlayableCharacter.getInstance();
		_enemies = new Enemy[NB_ENEMIES];
		_candies = new AbstractCandy[NB_CANDIES];
		_closed_door = new Door[NB_CLOSED_DOOR];
		_player.setOnChangeListener(new AbstractCharacter.OnChangeListener()
		{

			@Override
			public void changed(int x, int y)
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
					_enemies[i].set_targetX(_player.getPosition().getX());
					_enemies[i].set_targetY(_player.getPosition().getY());
					if (_player.collision(_enemies[i].getPosition()))
					{
						int lf = _player.decrementLife();
						_view.setLife(lf);
						if (lf == 0)
						{
							_view.setEndGameText(false);
						}
					}
				}
				for (int i = 0; i < NB_CLOSED_DOOR && i <= NB_MAX_CLOSED_DOOR; i++)
				{
					// On ouvre la porte associé.
					if (_player.collision(_closed_door[i].getSwitchOn()))
					{
						Edge door = _closed_door[i].getDoor();
						door.setType(Edge.Type.OPENED_DOOR);
						_view.updateDoor(door, Edge.Type.OPENED_DOOR);
					}
					else if (_player.collision(_closed_door[i].getSwitchOff()))
					{
						Edge door = _closed_door[i].getDoor();
						door.setType(Edge.Type.CLOSED_DOOR);
						_view.updateDoor(door, Edge.Type.CLOSED_DOOR);
					}
				}
				if (_player.collision(exit_door_position))
				{
					for (int i = 0; i < NB_ENEMIES; i++)
					{
						_enemies[i].stopRunning();
					}
					_view.setEndGameText(true);
				}
			}
		});

		for (int i = 0; i < NB_ENEMIES; i++)
		{
			_enemies[i] = new Enemy();
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
							_view.setEndGameText(false);
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
     * Start the Controller.
     *
     * @param stage Stage where the display will be managed.
     */
    public void start(Stage stage)
    {
        _model.buildRandomPath(new Vertex(0, 0, 0));
        Graph graph = _model.getGraph();
        exit_door_position = graph.getEndPath();
        _view.createDoor(exit_door_position.getX(), exit_door_position.getY());
        _view.createPlayable();
		for (int i = 0; i < NB_CANDIES && i < NB_MAX_CANDIES; i++)
		{
			//Si on à déjà placé le maximum de bonbons possible on arrếte.
			AbstractCandy candy = (AbstractCandy) CandyFactory.getCandy();
			int j;
			//On essaye de placer autant de fois qu'il y a de cases dans le graphe, si on échoue on n'arrête.
			for (j = 0; j < NB_GRAPH_VERTICES; ++j)
			{
				if (AbstractCandy.correctCandyPosition(_candies, exit_door_position, candy))
				{
					break;
				}
				candy = (AbstractCandy) CandyFactory.getCandy();
			}
			if (!AbstractCandy.correctCandyPosition(_candies, exit_door_position, candy))
			{
				NB_CANDIES = i;
				break;
			}
			_candies[i] = candy;
			_view.createCandy(_candies[i].getPosition().getX(), _candies[i].getPosition().getY(),
					_candies[i].getImgPath());
		}
        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i].set_targetX(_player.getPosition().getX());
            _enemies[i].set_targetY(_player.getPosition().getY());
        }
        for (int i = 0; i < NB_OPENED_DOOR; ++i)
        {
            graph.openDoorRandom();
        }
        for (int i = 0; i < NB_CLOSED_DOOR; ++i)
        {
            Edge door = graph.closeDoorRandom();
            Vertex switchOn = graph.setSwitchOn(door);
            int j;
            //On essaye de placer l'interrupteur 1000 fois, si on échoue on n'arrête.
            for (j = 0; j < 1000; ++j)
            {
            	 if (Door.correctSwitchPosition( _closed_door, exit_door_position, _candies, switchOn))
                 {
                     break;
                 }
                 switchOn = graph.setSwitchOn(door);
            }
            if (!Door.correctSwitchPosition( _closed_door, exit_door_position, _candies, switchOn))
            {
            	//On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
            	door.setType(Edge.Type.CORRIDOR);
            	NB_CLOSED_DOOR = i;
            }
            else
            {
            	Vertex switchOff = graph.setSwitchOff(door);
            	//On essaye de placer l'interrupteur 1000 fois, si on échoue on n'arrête.
            	for (j = 0; j < 1000; ++j)
            	{
            		if (Door.correctSwitchPosition( _closed_door, exit_door_position, _candies, switchOff))
            		{
            			break;
            		}
            		switchOff = graph.setSwitchOff(door);
            	}
            	if (!Door.correctSwitchPosition( _closed_door, exit_door_position, _candies, switchOff))
            	{
            		//On a pas réussi à fermer toute les portes donc on remet l'arrête comme avant et on met à jour la variable NB_CLOSED_DOOR.
            		NB_CLOSED_DOOR = i;
            		door.setType(Edge.Type.CORRIDOR);
            	}
            	else
            	{
            		_view.createSwitchOn(switchOn.getX(), switchOn.getY());
            		_view.createSwitchOff(switchOff.getX(), switchOff.getY());
            		_closed_door[i] = new Door(switchOn, switchOff, door);
            	}
            }
        }
        graph.GraphToDot();
        _view.start(stage, graph, new View.OnPlayListener()
        {
            @Override
            public void play()
            {
                //playGame();
            }
        });
        _view.printRules();

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
                        if (!startEnemies)
                        {
                            startEnemies = true;
                            playGame();
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
                            	 _view.removeAllCandies();
                            	 _view.removeAllEnemies();
                            	 _view.removeAllSwitchOn();
                            	 _view.removeAllSwitchOff();
                            	 for (int i = 0; i < NB_CANDIES; i++)
                            		 _candies[i] = null;
                            	 for (int i = 0; i < NB_CANDIES; i++) {
                            		AbstractCandy candy = (AbstractCandy) CandyFactory.getCandy();
                                     //On essaye de placer autant de fois qu'il y a de cases dans le graphe, si on échoue on n'arrête.
                                 	int j;
                                 	for (j = 0; j < NB_GRAPH_VERTICES; ++j)
                                     {
                                 		if (AbstractCandy.correctCandyPosition(_candies, exit_door_position, candy))
                                 			break;
                                 		candy = (AbstractCandy) CandyFactory.getCandy();	
                                 	if (AbstractCandy.correctCandyPosition(_candies, exit_door_position, candy))
                                 	{
                                 		NB_CANDIES = i;
                                 		break;                                 		
                                 	}

                                     _candies[i] = candy;           
                                     _view.createCandy(_candies[i].getPosition().getX(), _candies[i].getPosition().getY(),
                                     _candies[i].getImgPath());
                                 }
                            	 startEnemies = false;
                            	 for (i = 0; i < NB_ENEMIES; i++) {
                            		 _enemies[i].randomizePosition();
                                     _view.createEnnemies(_enemies[i].getPosition().getX(), _enemies[i].getPosition().getY());
                                 }
                            	 
                                 //for (int i = 0; i < NB_OPENED_DOOR; ++i)
                                     //graph.openDoorRandom();
                                 for (i = 0; i < NB_CLOSED_DOOR; ++i)
                                 {
                                     Edge door = graph.closeDoorRandom();
                                     Vertex switchOn = graph.setSwitchOn(door);
                                     _view.createSwitchOn(switchOn.getX(), switchOn.getY());
                                     Vertex switchOff = graph.setSwitchOff(door);
                                     _view.createSwitchOff(switchOff.getX(), switchOff.getY());
                                     //_closed_door[i] = new Door (switchOn, switchOff, door);
                                 }
                            	 
                            	 _view.setScore(0);
                                 _view.setLife(3);
                                 _player.setScore(0);
                                 PlayableCharacter.setLife(3);
                                 _player.setPosition(0, 0);
                                 _view.updatePlayerPosition(0, 0);

                                 break;
                            	 }
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

    private void playGame()
    {
        for (int i = 0; i < NB_ENEMIES; i++)
        {
            _enemies[i].start();
        }
    }
}
