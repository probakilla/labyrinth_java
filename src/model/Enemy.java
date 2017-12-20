package model;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Model.Directions;

/**
 * Class used to define an enemy.
 *
 * TYPE is the "id" of the class and it's used to manage collisions. When an
 * enemy and a player collide, the addition of their type should be 0. If this
 * is a candy, it will be something else, but positive.
 *
 * @author Java Group
 */
public class Enemy extends AbstractCharacter implements Runnable
{
    private int _running;
    private int _targetX;
    private int _targetY;
    private final int _sleepTime = 1000;//Time in ms between each enemies' move.
    private static final String IMG_PATH = "/utils/bad.png";

    // wat ?
    private static final class Lock
    {
    }
    private final Object lock = new Lock();
    CountDownLatch _restartSignal;

    /**
     * Constructor of Enemy.
     *
     * The coordinates of the Enemy are set to [0,0]. The call of this
     * constructor should be followed by randomizePosition().
     * @param restartSignal 
     */
    public Enemy (CountDownLatch restartSignal)
    {
        super(0, 0);
        _type = -1;
        _running = 0;
        _restartSignal = restartSignal;
    }

    /**
     * Retrieves the targets abscissa of the Enemy.
     *
     * @return The abscissa of the target.
     */
    public int get_targetX ()
    {
        return _targetX;
    }
    
    /**
     * Retrieves the targets ordinate of the Enemy.
     *
     * @return The ordinate of the target.
     */
    public int get_targetY ()
    {
        return _targetY;
    }

    /**
     * Retrieves the running data member.
     * 
     * @return The running member.
     */
    public int getRunning ()
    {
        return _running;
    }

    /**
     * Retrieves the path to the image of an Enemy.
     * 
     * @return The path to the image.
     */
    public static String getImgPath ()
    {
        return IMG_PATH;
    }
    
    /**
     * Set the targets abscissa of the Enemy.
     *
     * @param x The target abscissa.
     */
    public void set_targetX (int x)
    {
        _targetX = x;
    }


    /**
     * Set the targets ordinate of the Enemy.
     *
     * @param y The target ordinate.
     */
    public void set_targetY (int y)
    {
        _targetY = y;
    }

    /**
     * Set _running at 0.
     *
     * This will stop enemies.
     */
    public void stopRunning ()
    {
        _running = 0;
    }

    /**
     * Set _running at 1.
     *
     * This will restart enemies movement, if it used with rePlayGame in the
     * Controller.
     */
    public void keepRunning ()
    {
        _running = 1;
    }

    /**
     * To update the {@link java.util.concurrent.CountDownLatch restartSignal}.
     *
     * @param restartSignal The new
     * {@link java.util.concurrent.CountDownLatch restartSignal}.
     */
    public void updateRestartSignal (CountDownLatch restartSignal)
    {
        _restartSignal = restartSignal;
    }

    /**
     * Return the direction of the next enemies move.
     *
     * @return An integer corresponding of the direction of the move (0 = NORTH,
     * 1 = East, 2 = South, 3 = West).
     */
    public Directions getNextStep ()
    {
        int x = 0;
        int y = 0;
        int nb = 1000;
        Graph graph = Graph.getInstance();
        Directions ret = Directions.NORTH;
        for (Directions dir : Directions.values())
        {
            switch (dir)
            {
                case NORTH:
                    x = 0;
                    y = -1;
                    break;
                case SOUTH:
                    x = 0;
                    y = 1;
                    break;
                case EAST:
                    x = 1;
                    y = 0;
                    break;
                case WEST:
                    x = -1;
                    y = 0;
                    break;
            }
            if ((this.getPosition().getX() + x >= 0 && this.getPosition().getX() + x < Graph.getGRIDWIDTH()
                && this.getPosition().getY() + y >= 0 && this.getPosition().getY() + y < Graph.getGRIDHEIGHT())
                && !graph.doesntExist(graph.getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y))
                && nb >= graph.getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr()
                && graph.isOpenedDoor(this.getPosition(), dir)
                && graph.getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr() != 0)
            {
                nb = graph.getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr();
                ret = dir;
            }
        }
        return ret;
    }

    @Override
    public void run ()
    {
        _running = 1;
        while (_running == 1)
        {
            Model.getInstance().launchManhattan(this.getPosition(), Graph.getInstance().getVertex(this.get_targetX(), this.get_targetY()));
            switch (this.getNextStep())
            {
                case NORTH:
                    this.up();
                    break;
                case SOUTH:
                    this.down();
                    break;
                case EAST:
                    this.right();
                    break;
                case WEST:
                    this.left();
                    break;
                default:
                    break;
            }
            try
            {
                Enemy.sleep(_sleepTime);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //We block enemies until the main thread deblock them. Used to wait until the player press any key, after a reset by the controller.
        try
        {
            _restartSignal.await();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (_running == 1)
        {
            run();
        }
    }

}
