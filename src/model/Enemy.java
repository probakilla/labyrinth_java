package model;

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
    private static String _imgPath = "/utils/bad.png";
    // Time in ms between each enemie's move.
    private final int _sleepTime = 1000;

    /**
     * Retrieves the target of the Enemy.
     *
     * @return The target of the Enemy.
     */
    public int get_targetX()
    {
        return _targetX;
    }

    /**
     * Set the target's abscissa of the Enemy.
     *
     * @param target The target abscissa of the Enemy.
     */
    public void set_targetX(int target)
    {
        _targetX = target;
    }

    /**
     * Retrieves the target's ordinate of the Enemy.
     *
     * @param target The target's ordinate of the Enemy.
     */
    public int get_targetY()
    {
        return _targetY;
    }

    /**
     * Set the target's ordinate of the Enemy.
     *
     * @param target The target's ordinate of the Enemy.
     */
    public void set_targetY(int _target)
    {
        this._targetY = _target;
    }

    /**
     * Constructor of Enemy.
     *
     * The coordinates of the Enemy are set to [0,0]. The call of this
     * constructor should be followed by randomizePosition().
     */
    public Enemy()
    {
        super(0, 0);
        _type = -1;
        _running = 0;
    }

    /**
     * Constructor of Enemy.
     *
     * Retrieves an instance of Enemy with specific coordinates.
     *
     * @param x Abscissa in the labyrinth of the Enemy.
     * @param y Ordinate in the labyrinth of the Enemy.
     */
    public Enemy(int x, int y)
    {
        super(x, y);
        _type = -1;
        _running = 0;
    }

    /**
     * @return the _imgPath
     */
    public static String getImgPath()
    {
        return _imgPath;
    }

    /**
     * Set _running at 0.
     *
     * This will stop enemies, be careful once stopped enemies can't be
     * unstopped.
     */
    public void stopRunning()
    {
        _running = 0;
    }

    /**
     * Return the direction of the next enemies move.
     *
     * @return An integer corresponding of the direction of the move (0 = NORTH, 1 =
     * East, 2 = South, 3 = West).
     */
    public Directions getNextStep()
    {
        int x = 0;
        int y = 0;
        int nb = 1000;
        //System.out.println("on est au "+this.getPosition());
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
            if ((this.getPosition().getX() + x >= 0 && this.getPosition().getX() + x < Model.getInstance().getGraph().getGRIDWIDTH()
                && this.getPosition().getY() + y >= 0 && this.getPosition().getY() + y < Model.getInstance().getGraph().getGRIDHEIGHT())
                && !Model.getInstance().getGraph().doesntExist(Model.getInstance().getGraph().getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y))
                && nb >= Model.getInstance().getGraph().getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr()
                && Model.getInstance().getGraph().isOpenedDoor(this.getPosition(), dir)
                && Model.getInstance().getGraph().getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr() != 0)
            {
                nb = Model.getInstance().getGraph().getVertex(this.getPosition().getX() + x, this.getPosition().getY() + y).getNbr();
                ret = dir;
            }
        }
        return ret;
    }

    @Override
    public void run()
    {
        _running = 1;
        while (_running == 1)
        {
            Model.getInstance().launchManhattan(this.getPosition(), Model.getInstance().getGraph().getVertex(this.get_targetX(), this.get_targetY()));
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
    }

}
