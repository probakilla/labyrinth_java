package controller;

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

public class Controller {

	private static Controller INSTANCE;
	private final int NB_ENEMIES = 2, NB_CANDIES = 10;

	private final Model _model;
	private final View _view;
	private final PlayableCharacter _player;
	private final Enemy[] _enemies;
	private final AbstractCandy[] _candies;
	private Vertex door_position;
	private int _nb_opened_door = 10;
	private int _nb_closed_door = 3;
	
	private Controller() {
		_model = Model.getInstance();
		_view = View.getInstance();
		_player = PlayableCharacter.getInstance();
		_enemies = new Enemy[NB_ENEMIES];
		_candies = new AbstractCandy[NB_CANDIES];

		_player.setOnChangeListener(new AbstractCharacter.OnChangeListener() {

			@Override
			public void changed(int x, int y) {
				_view.updatePlayerPosition(x, y);
				for (int i = 0; i < NB_CANDIES; i++) {
					if (_candies[i] != null && _player.collision(_candies[i].getPosition())) {
						_player.increaseScore(_candies [i]);
						_view.setScore(_player.getScore());
						_view.removeCandy(i);
						_candies[i] = null;				
					}
				}
				for (int i = 0; i < NB_ENEMIES; i++) {
					_enemies[i].set_targetX(_player.getPosition().getX());
					_enemies[i].set_targetY(_player.getPosition().getY());
					if (_player.collision(_enemies[i].getPosition())) {
						if (_player.decrementLife())
							_view.setEndGameText(false);
					}
				}

				if (_player.collision(door_position))
					_view.setEndGameText(true);
			}
		});

		for (int i = 0; i < NB_ENEMIES; i++) {
			_enemies[i] = new Enemy();
			_enemies[i].randomizePosition();
			_view.createEnnemies(_enemies[i].getPosition().getX(), _enemies[i].getPosition().getY());

			_enemies[i].start();
			final int idx = i;
			_enemies[i].setOnChangeListener(new AbstractCharacter.OnChangeListener() {
				@Override
				public void changed(int x, int y) {
					_view.updateEnemyPosition(idx, x, y);
					if (_player.collision(_enemies[idx].getPosition())) {
						if (!_player.decrementLife())
							_view.setEndGameText(false);
					}

				}
			});
		}

		for (int i = 0; i < NB_CANDIES; i++) {
			_candies[i] = (AbstractCandy) CandyFactory.getCandy(0, 0, 0);
			_view.createCandy(_candies[i].getPosition().getX(), _candies[i].getPosition().getY(),
					_candies[i].getImgPath());
		}

	}

	/**
	 * Retrieves the instance of the Controller.
	 *
	 * Retrieves the instance of the Controller, there can be only one instance of
	 * the Controller at once thanks to the singleton design pattern.
	 *
	 * @return Instance of the Controller
	 */
	public static Controller getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Controller();
		}
		return INSTANCE;
	}

	/**
	 * Return the model used in the Controller.
	 *
	 * @return model
	 */
	public Model getModel() {
		return _model;
	}

	/**
	 * Start the Controller.
	 *
	 * @param stage Stage where the display will be managed.
	 */
	public void start(Stage stage) {
		_model.buildRandomPath(new Vertex(0, 0, 0));
		Graph graph = _model.getGraph ();
		door_position = graph.getEndPath();
		_view.createDoor(door_position.getX(), door_position.getY());
		_view.createPlayable();
		for (int i = 0; i < NB_ENEMIES; i++) {
			_enemies[i].set_targetX(_player.getPosition().getX());
			_enemies[i].set_targetY(_player.getPosition().getY());
		}
		for (int i = 0; i < _nb_opened_door; ++i)
			graph.openDoorRandom();
		for (int i = 0; i < _nb_closed_door; ++i)
		{
			Edge door = _model.getGraph().closeDoorRandom();
			Vertex switchOn = graph.setSwitchOn(door);
			_view.createSwitchOn(switchOn.getX(), switchOn.getY());
			Vertex switchOff = graph.setSwitchOff(door);
			_view.createSwitchOff(switchOff.getX(), switchOff.getY());
		}
		_model.getGraph().GraphToDot();
		_view.start(stage, _model.getGraph());
		_view.printRules();

		// Gestion du mouvement du joueur.
		EventHandler handler;
		handler = (EventHandler) new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getClass() == KeyEvent.class) {
					KeyEvent e = (KeyEvent) event;
					if (null != e.getCode()) {
						switch (e.getCode()) {
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
							for (int i = 0; i < NB_ENEMIES; i++) {
								_enemies[i].stopRunning();
							}
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
}
