package model;

import java.util.Random;
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
	protected static String _imgPath = "/utils/bad.png";
    
    public int get_targetX() {
		return _targetX;
	}

	public void set_targetX(int _target) {
		this._targetX = _target;
	}
	
    public int get_targetY() {
		return _targetY;
	}

	public void set_targetY(int _target) {
		this._targetY = _target;
	}

	/**
     * Constructor of Enemy.
     *
     * The coordinates of the Enemy are set to [0,0]. The call of this
     * constructor should be followed by randomizePosition().
     */
    public Enemy ()
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
    public Enemy (int x, int y)
    {
        super(x, y);
        _type = -1;
        _running = 0;
    }
    
    /**
   	 * @return the _imgPath
   	 */
   	public static String getImgPath() {
   		return _imgPath;
   	}
    
    public void stopRunning ()
    {
    	_running = 0;
    }

    public int getNextStep(){
    	int x = 0;
    	int y = 0;
    	int xt = 0;
    	int yt = 0;
    	int nb = 1000;
    	int idx = 0;
    	//System.out.println("on est au "+this.getPosition());
    	for (int i = 0; i < 4; ++i)
    	{

    		switch (i)
    		{
    		case 0:
    			x = 0;
    			y = -1;
    			break;
    		case 1:
    			x = 0;
    			y = 1;
    			break;
    		case 2:
    			x = 1;
    			y = 0;
    			break;
    		case 3:
    			x = -1;
    			y = 0;
    			break;
    		}
    		//System.out.println("possibilité:"+Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y));
    		if ((this.getPosition().getX()+x >=0 && this.getPosition().getX()+x < Model.getInstance().getGraph().getGRIDWIDTH() 
    				&& this.getPosition().getY()+y >= 0 && this.getPosition().getY()+y < Model.getInstance().getGraph().getGRIDHEIGHT()) 
    				&& !Model.getInstance().getGraph().doesntExist(Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y)) 
    				&& nb >= Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y).getNbr()
    				&& Model.getInstance().getGraph().isOpenedDoor(this.getPosition(), Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y)))
    		{
    			//System.out.println("= "+Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y));
    			xt = x;
    			yt = y;
    			idx = i;
    			nb = Model.getInstance().getGraph().getVertex(this.getPosition().getX()+x, this.getPosition().getY()+y).getNbr();
    		}
    	}
    	//System.out.println("on va vers "+idx+" "+Model.getInstance().getGraph().getVertex(this.getPosition().getX()+xt, this.getPosition().getY()+yt));
    	return idx;
    }

    @Override
    public void run ()
    {
    	_running = 1;
    	while (_running == 1)
    	{
    		//System.out.println(this.get_targetX()+" "+this.get_targetY());
    		Model.getInstance().launchManhattan(this.getPosition(), Model.getInstance().getGraph().getVertex(this.get_targetX(), this.get_targetY()));
    		switch (this.getNextStep())
    		{
    		case 0:
    			this.up();
    			break;
    		case 1:
    			this.down();
    			break;
    		case 2:
    			this.right();
    			break;
    		case 3:
    			this.left();
    			break;
    		default:
    			break;
    		}
    		try
    		{
    			Enemy.sleep(1000);
    		}
    		catch (InterruptedException ex)
    		{
    			Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
    		}
    	}
    }
        	
}
