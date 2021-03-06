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
    private static int _sleepTime = 1000;//Time in ms between each enemies' move.
    private static final String IMG_PATH = "/utils/bad.png";

    CountDownLatch _restartSignal;

    /**
     * Constructor of Enemy.
     *
     * The coordinates of the Enemy are set to [0,0]. The call of this
     * constructor should be followed by randomizePosition().
     * @param restartSignal The {@link java.util.concurrent.CountDownLatch CountDownLatch} used to pause the thread.
     */
    public Enemy (CountDownLatch restartSignal)
    {
        super(0, 0);
        _type = -1;
        _running = 1;
        _restartSignal = restartSignal;
        _targetX = -1;
        _targetY = -1;
    }

    /**
     * Retrieves the targets abscissa of the Enemy.
     *
     * @return The abscissa of the target.
     */
    public int getTargetX ()
    {
        return _targetX;
    }
    
    /**
     * Retrieves the targets ordinate of the Enemy.
     *
     * @return The ordinate of the target.
     */
    public int getTargetY ()
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
    public void setTargetX (int x)
    {
        _targetX = x;
    }


    /**
     * Set the targets ordinate of the Enemy.
     *
     * @param y The target ordinate.
     */
    public void setTargetY (int y)
    {
        _targetY = y;
    }
    
    /**
     * Increase enemies' movement speed.
     * 
     * Reduce the sleeping time between to enemies' moves.
     */
    public static void incrementSpeed ()
    {
    	_sleepTime -= 100;
    }
    
    /**
    * Reset enemies' movement speed.
    * 
    * Reset the sleeping time between to enemies' moves to one second.
    */
   public static void resetSpeed ()
   {
   	_sleepTime = 1000;
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
        int nb = 1000;
        Vertex position = this.getPosition();
        Vertex nextPosition;
        Graph graph = Graph.getInstance();
        Directions ret = Directions.NORTH;
        for (Directions dir : Directions.values())
        {
            if (!graph.doesntExist(position, dir))
            {
	            nextPosition = graph.getVertexByDir(position, dir);
	            if ((nextPosition.getX() >= 0 && nextPosition.getX() < Graph.getGRIDWIDTH()
	                && nextPosition.getY() >= 0 && nextPosition.getY() < Graph.getGRIDHEIGHT())
	                && nb >= nextPosition.getNbr() && graph.isOpenedDoor(position, dir) && nextPosition.getNbr() != 0)
	            {
	                nb = nextPosition.getNbr();
	                ret = dir;
	            }
            }
        }
        return ret;
    }

    @Override
    public void run ()
    {
        while (_running == 1)
        {
            Model.getInstance().launchManhattan(this.getPosition(), Graph.getInstance().getVertex(this.getTargetX(), this.getTargetY()));
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
                //Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //We block enemies until the main thread deblock them. Used to wait until the player press any key, after a reset by the controller.
        try
        {
            _restartSignal.await();
        }
        catch (InterruptedException ex)
        {
        	System.out.println("pd");
        	Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        	return;
        }
        if (_running == 1)
        {
            run();
        }
    }

}
