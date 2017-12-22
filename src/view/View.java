package view;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Door;
import model.Edge;
import model.Vertex;
import model.Enemy;
import model.Graph;
import model.PlayableCharacter;

/**
 * This class manage the display of the project.
 *
 * @author Java Group
 */
public class View
{

    public static final int SPAN = 4;
    public static final int WALL = 2;
    public static final int CELL = 9;
    public static final Paint WALL_COLOR = Color.CHOCOLATE;
    public static final Paint SCENE_COLOR = Color.WHITESMOKE;

    private Stage _stage;
    private Scene _scene;
    private final Pane _pane;

    private final ImageView _player, _door;
    private final ArrayList<ImageView> _iv_enemies, _iv_candies, _iv_switch_on, _iv_switch_off;
    private Label _score, _life, endgame;
    private static final String DOOR_PATH = "/utils/door_open.png";

    private View()
    {
        _pane = new Pane();
        _iv_enemies = new ArrayList<ImageView>();
        _iv_candies = new ArrayList<ImageView>();
        _iv_switch_on = new ArrayList<ImageView>();
        _iv_switch_off = new ArrayList<ImageView>();

        Image imD = new Image("file:" + System.getProperty("user.dir") + DOOR_PATH);
        _door = new ImageView(imD);

        Image imP = new Image("file:" + System.getProperty("user.dir") + PlayableCharacter.getImgPath());
        _player = new ImageView(imP);
    }

    private static View INSTANCE;

    /**
     * Retrieves an instance of View.
     *
     * Retrieves the instance of the View, there can be only one instance of the
     * View at once thanks to the singleton design pattern.
     *
     * @return Instance of View.
     */
    public static View getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new View();
        }
        return INSTANCE;
    }

    /**
     * Start the View.
     *
     * @param stage Stage used for the graphical interface.
     * @param g the {@link model.Graph Graph} to display.
     * @param playing Listener to launch the game.
     */
    public void start(Stage stage, Graph g, final OnPlayListener playing)
    {
        new MenuView(new OnPlayListener()
        {

            @Override
            public void play()
            {
                _stage = stage;

                drawFrame(stage, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT());
                drawGraph(g);
                stage.show();
                playing.play();
            }
        }).start(stage);
    }

    /**
     * Reset all display of the labyrinth.
     */
    public void resetFrame()
    {
        int x, y;
        for (x = 0; x < Graph.getGRIDWIDTH(); x++)
        {
            for (y = 0; y < Graph.getGRIDHEIGHT(); y++)
            {
                if (x + 1 < Graph.getGRIDWIDTH())
                {
                    drawWall(x, y, x + 1, y, SCENE_COLOR);
                }

                if (y + 1 < Graph.getGRIDHEIGHT())
                {
                    drawWall(x, y, x, y + 1, SCENE_COLOR);
                }
            }
        }
    }

    /**
     * Draw the labyrinth basis.
     *
     * Draw the main structure of the labyrinth (e.g borders of the labyrinth).
     *
     * @param stage Window where we draw the labyrinth.
     * @param nbrX Height of the labyrinth.
     * @param nbrY Width of the labyrinth.
     */
    public void drawFrame(Stage stage, int nbrX, int nbrY)
    {
        _scene = new Scene(_pane, ((WALL + CELL) * nbrX + WALL) * SPAN, ((WALL + CELL) * nbrY + WALL) * SPAN + 50);

        _scene.setFill(SCENE_COLOR);
        stage.setScene(_scene);

        Rectangle square = new Rectangle(0, 0, SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALL_COLOR);
        _pane.getChildren().add(square);

        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)), SPAN * (nbrX * (CELL + WALL) + WALL), WALL * SPAN);
        square.setFill(WALL_COLOR);
        _pane.getChildren().add(square);

        square = new Rectangle(0, 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        _pane.getChildren().add(square);

        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0, WALL * SPAN, SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        _pane.getChildren().add(square);

        for (int x = 0; x < nbrX - 1; ++x)
        {
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for (int y = 0; y < nbrY - 1; ++y)
            {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
                square.setFill(WALL_COLOR);
                _pane.getChildren().add(square);
            }
        }

        _score = new Label();
        _score.setLayoutX((((WALL + CELL) * nbrX + WALL) * SPAN) / 3 - 100);
        _score.setLayoutY(((WALL + CELL) * nbrY + WALL) * SPAN + 10);
        _score.setFont(new Font("Serif", 20));
        _score.setText("Votre score : 0pt");
        _pane.getChildren().add(_score);

        _life = new Label();
        _life.setLayoutX((((WALL + CELL) * nbrX + WALL) * SPAN) - 100);
        _life.setLayoutY(((WALL + CELL) * nbrY + WALL) * SPAN + 10);
        _life.setFont(new Font("Serif", 20));
        _life.setText("Vie : " + PlayableCharacter.getLife());
        _pane.getChildren().add(_life);

        endgame = new Label();
        endgame.setLayoutX((((WALL + CELL) * nbrX + WALL) * SPAN) / 2 - 100);
        endgame.setLayoutY((((WALL + CELL) * nbrY + WALL) * SPAN) / 2);
        endgame.setFont(new Font("Serif", 50));
        endgame.setStyle("-fx-background: #FFFFFF;");
        endgame.setVisible(false);
        _pane.getChildren().add(endgame);
    }

    /**
     * Draw a line between two points.
     *
     * This method is used to build walls in the labyrinth. Draw a line between
     * two points given in parameters (with their coordinates). Color of the
     * wall is also set by this method.
     *
     * @param xs abscissa of the first point.
     * @param ys Ordinate of the first point.
     * @param xt Abscissa of the second point.
     * @param yt Ordinate of the second point.
     * @param color Color of the wall.
     */
    public void drawWall(int xs, int ys, int xt, int yt, Paint color)
    {
        int x = 0, y = 0, xspan = 0, yspan = 0;
        if (ys == yt)
        {
            x = ((WALL + CELL) + (WALL + CELL) * ((int) (xs + xt) / 2)) * SPAN;
            y = (WALL + ys * (WALL + CELL)) * SPAN;
            xspan = WALL * SPAN;
            yspan = CELL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            _pane.getChildren().add(square);
        }
        else if (xs == xt)
        {
            x = (WALL + xs * (WALL + CELL)) * SPAN;
            y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt) / 2)) * SPAN;
            xspan = CELL * SPAN;
            yspan = WALL * SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            _pane.getChildren().add(square);
        }
    }
    
    /**
     * Draw the structure of the labyrinth according to the graph which is linked. Call several times drawWall.
     * @param g The graph which describe the labyrinth.
     */
    public void drawGraph(Graph g)
    {
        Edge e;
        for (int x = 0; x < Graph.getGRIDWIDTH(); x++)
        {
            for (int y = 0; y < Graph.getGRIDHEIGHT(); y++)
            {
                if (x + 1 < Graph.getGRIDWIDTH())
                {
                    e = g.getEdge(g.getVertex(x, y), g.getVertex(x + 1, y));
                    if (e == null || (e.getType() != Edge.Type.CORRIDOR))
                    {
                        drawWall(x, y, x + 1, y, Color.CHOCOLATE);
                        if (e != null && (e.getType() == Edge.Type.OPENED_DOOR))
                        {
                            drawWall(x, y, x + 1, y, SCENE_COLOR);
                        }
                        else if (e != null && (e.getType() == Edge.Type.CLOSED_DOOR))
                        {
                            drawWall(x, y, x + 1, y, Color.BLUE);
                        }
                    }
                }

                if (y + 1 < Graph.getGRIDHEIGHT())
                {
                    e = g.getEdge(g.getVertex(x, y), g.getVertex(x, y + 1));
                    if (e == null || (e.getType() != Edge.Type.CORRIDOR))
                    {
                        drawWall(x, y, x, y + 1, Color.CHOCOLATE);
                        if (e != null && (e.getType() == Edge.Type.OPENED_DOOR))
                        {
                            drawWall(x, y, x, y + 1, SCENE_COLOR);
                        }
                        else if (e != null && (e.getType() == Edge.Type.CLOSED_DOOR))
                        {
                            drawWall(x, y, x, y + 1, Color.BLUE);
                        }
                    }

                }
            }
        }
    }
    
    /**
     * Create and add to the panel the sprite of an enemy in the labyrinth.
     * @param x The Abscissa of the enemy.
     * @param y The Ordinate of the enemy.
     */
    public void createEnnemies(int x, int y)
    {
        Image im = new Image("file:" + System.getProperty("user.dir") + Enemy.getImgPath());
        ImageView iv = new ImageView(im);

        _pane.getChildren().add(iv);
        setImageViewPosition(iv, x, y);
        iv.toFront();
        _iv_enemies.add(iv);
    }
    
    /**
     * Create and add to the panel the sprite of a candy, which is different according to imgPath.
     * @param x The Abscissa of the candy.
     * @param y The Ordinate of the candy.
     * @param imgPath The name of the path of the sprite of the candy.
     */
    public void createCandy(int x, int y, String imgPath)
    {
        Image im = new Image("file:" + System.getProperty("user.dir") + imgPath);
        ImageView iv = new ImageView(im);
        setImageViewPosition(iv, x, y);
        _pane.getChildren().add(iv);
        _iv_candies.add(iv);
    }
    /**
     * remove the sprite of an existing candy in the panel.
     * @param idx The index on the set.
     */
    public void removeCandy(int idx)
    {
        _pane.getChildren().remove(_iv_candies.get(idx));
        _iv_candies.set(idx, null);
    }

    /**
     * Remove all candies in the array list.
     */
    public void removeAllCandies()
    {
        for (int i = 0; i < _iv_candies.size(); i++)
        {
            _pane.getChildren().remove(_iv_candies.get(i));
        }
        _iv_candies.clear();
    }

    /**
     * Remove all enemies in the array list.
     */
    public void removeAllEnemies()
    {
        for (int i = 0; i < _iv_enemies.size(); i++)
        {
            removeEnemy(i);
        }
    	_iv_enemies.clear();
    }
    
    /**
     * Remove the specific enemy in the array list.
     * @param i The enemies' number in the array list.
     */
    public void removeEnemy (int i)
    {

    	_pane.getChildren().remove(_iv_enemies.get(i));
    	_iv_enemies.remove(i);
    }

    /**
     * Remove all switch on in the array list.
     */
    public void removeAllSwitchOn()
    {
        for (int i = 0; i < _iv_switch_on.size(); i++)
        {
            _pane.getChildren().remove(_iv_switch_on.get(i));
        }
        _iv_switch_on.clear();
    }

    /**
     * Remove all switch off in the array list.
     */
    public void removeAllSwitchOff()
    {
        for (int i = 0; i < _iv_switch_off.size(); i++)
        {
            _pane.getChildren().remove(_iv_switch_off.get(i));
        }
        _iv_switch_off.clear();
    }

    /**
     * Clean all content on the stage.
     */
    public void clean()
    {
        removeAllCandies();
        removeAllEnemies();
        removeAllSwitchOff();
        removeAllSwitchOn();

        _pane.getChildren().remove(_player);
        _pane.getChildren().remove(_door);
    }

    /**
     * Create a playable character.
     */
    public void createPlayable()
    {
        _pane.getChildren().add(_player);
        setImageViewPosition(_player, 0, 0);
    }

    /**
     * Create and add the exit door in the panel.
     * @param x The Abscissa of the door.
     * @param y The Ordinate of the door.
     */
    public void createDoor(int x, int y)
    {
        _pane.getChildren().add(_door);
        setImageViewPosition(_door, x, y);
    }

    /**
     * Change the location of the finish door.
     * 
     * @param x The Abscissa of the door.
     * @param y The Ordinate of the door.
     */
    public void updateDoorPosition(int x, int y)
    {
        setImageViewPosition(_door, x, y);
    }

    /**
     * Change the position of the sprite of an enemy.
     * 
     * @param i The index on the enemies set.
     * @param x The Abscissa of the enemy.
     * @param y The Ordinate of the enemy.
     */
    public void updateEnemyPosition(int i, int x, int y)
    {
        setImageViewPosition(_iv_enemies.get(i), x, y);
        _iv_enemies.get(i).toFront();
    }

    /**
     * Change the position of the sprite of the player.
     * 
     * @param x The Abscissa of the player.
     * @param y The Ordinate of the player.
     */
    public void updatePlayerPosition(int x, int y)
    {
        setImageViewPosition(_player, x, y);
    }

    /**
     * Create and add the sprite of an on-off switch (on) on the panel.
     * 
     * @param x The Abscissa of the on-off switch.
     * @param y The Ordinate of the on-off switch.
     */
    public void createSwitchOn(int x, int y)
    {
        Image im = new Image("file:" + System.getProperty("user.dir") + Door.getSwitchOnPath());
        ImageView iv = new ImageView(im);

        _pane.getChildren().add(iv);
        setImageViewPosition(iv, x, y);
        _iv_switch_on.add(iv);
    }

    /**
     * Create and add the sprite of an on-off switch (off) on the panel.
     * 
     * @param x The Abscissa of the on-off switch.
     * @param y The Ordinate of the on-off switch.
     */
    public void createSwitchOff(int x, int y)
    {
        Image im = new Image("file:" + System.getProperty("user.dir") + Door.getSwitchOffPath());
        ImageView iv = new ImageView(im);

        _pane.getChildren().add(iv);
        setImageViewPosition(iv, x, y);
        _iv_switch_off.add(iv);
    }

    /**
     * Change the position of a sprite.
     * 
     * @param iv The sprite.
     * @param x The Abscissa of the sprite.
     * @param y The Ordinate of the sprite.
     */
    private void setImageViewPosition(ImageView iv, int x, int y)
    {
        iv.setX((int) ((WALL + x * (WALL + CELL)) * SPAN));
        iv.setY((int) ((WALL + y * (WALL + CELL)) * SPAN));
    }

    /**
     * Update the score view.
     * 
     * @param sc The player's score.
     */
    public void setScore(int sc)
    {
        _score.setText("Votre score : " + sc + "pts");
    }

    /**
     * Update the life view.
     * 
     * @param lf The player's life.
     */
    public void setLife(int lf)
    {
        _life.setText("Vie : " + lf);
    }
    /**
	 * Update the view when the player finishes the level.
	 * 
	 * @param win The status of the game at its end.
	 */
    public void setEndGameText(boolean win)
    {
        Platform.runLater(() ->
        {
            endgame.setTextFill(Color.web(win ? "#00E676" : "#F44336"));

            endgame.setText(win ? "Bravo !" : "Game Over !");
            endgame.setStyle("-fx-background-color: black;");
            endgame.toFront();

            endgame.setVisible(true);
        });
    }
    
    /**
     * Update the color of a door according to its type (closed or opened).
     * 
     * @param door The edge which is situated the door.
     * @param status The status of the door (closed or opened).
     */
    public void updateDoor(Edge door, Edge.Type status)
    {
        Vertex source = door.getSource();
        Vertex target = door.getTarget();
        if (status == Edge.Type.CLOSED_DOOR)
        {
            drawWall(source.getX(), source.getY(), target.getX(), target.getY(), Color.BLUE);
        }
        else if (status == Edge.Type.OPENED_DOOR)
        {
            drawWall(source.getX(), source.getY(), target.getX(), target.getY(), SCENE_COLOR);
        }
    }

    /**
     * @return the _stage
     */
    public Stage getStage()
    {
        return _stage;
    }

    /**
     * 
     * @author Ordi
     *
     */
    public interface OnPlayListener
    {
        void play();
    }

}
