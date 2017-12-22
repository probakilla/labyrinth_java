package model;

import java.util.Random;
import model.Model.Directions;

/**
 * Abstract class used for all Characters in the labyrinth.
 *
 * @author Java Group
 */
public abstract class AbstractCharacter extends Thread
{
    protected int _type;
    protected Vertex _position;
    protected OnChangeListener onChangeListener;

    /**
     * Constructor of AbstractCharacter.
     *
     * Set the position of the AbstractCharacter with specific coordinates.
     *
     * @param x Abscissa in the labyrinth of the character.
     * @param y Ordinate in the labyrinth of the character.
     */
    public AbstractCharacter(int x, int y)
    {
        _position = new Vertex(x, y);
    }

    /**
     * Retrieves the type of the Character.
     *
     * Retrieves the type of the character : -1 is for the
     * {@link model.Enemy Enemy} 1 is for the {@link model.PlayableCharacter}.
     *
     * @return The type.
     */
    public int getType()
    {
        return _type;
    }

    /**
     * Retrieves the position of the character.
     *
     * Retrieves a {@link model.Vertex Vertex} corresponding to the position of
     * the current character.
     *
     * @return A Vertex corresponding to the position.
     */
    public Vertex getPosition()
    {
        return _position;
    }

    /**
     * Change the position of the character to specific location.
     *
     * Set the position of the current character to specific location, this 
     * method will test if the coordinates are in bounds. If not, this method
     * does nothing.
     * 
     * @param x The target location abscissa.
     * @param y The target location ordinate.
     */
    public void setPosition(int x, int y)
    {
        if (x < Graph.getGRIDWIDTH() && x >= 0)       
            _position.setX(x);
        if (y < Graph.getGRIDHEIGHT() && y >= 0)
            _position.setY(y);  
    }

    private boolean validMove(Vertex v, Directions dir)
    {
        if (!v.inBorders(dir, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT()))       
            return false;       
        else if (Graph.getInstance().isWall(v, dir) || Graph.getInstance().isClosedDoor(v, dir))
            return false;
        return true;
    }

    /**
     * Decrement the ordinate of the character.
     * 
     */
    public void up()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.NORTH))
        {
            newPos.setY(newPos.getY() - 1);
            _position.copy(newPos);           
            onChangeListener.changed(newPos.getX(), newPos.getY());
        }
    }

    /**
     * Increment the ordinate of the character.
     */
    public void down()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.SOUTH))
        {
            newPos.setY(newPos.getY() + 1);
            _position.copy(newPos);         
            onChangeListener.changed(newPos.getX(), newPos.getY());
        }
    }

    /**
     * Decrement the abscissa of the character.
     */
    public void left()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.WEST))
        {
            newPos.setX(newPos.getX() - 1);
            _position.copy(newPos);
            onChangeListener.changed(newPos.getX(), newPos.getY());
        }
    }

    /**
     * Increment the abscissa of the character.
     */
    public void right()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.EAST))
        {
            newPos.setX(newPos.getX() + 1);
            _position.copy(newPos);
            onChangeListener.changed(newPos.getX(), newPos.getY());
        }
    }

    /**
     * Generate and set a random position for the character.
     */
    public void randomizePosition()
    {
        Random rand = new Random();
        // On place les ennemies à au moins 5 cases du joueur.
        int min = 5;
        int maxWidth = Graph.getGRIDWIDTH() - 1;
        int maxHeight = Graph.getGRIDHEIGHT() - 1;
        //set at a random position in the graph.
        setPosition(rand.nextInt(maxWidth - min + 1) + min, rand.nextInt(maxHeight - min + 1) + min);
    }

    /**
     * Method used to set automatically the new position of the image of 
     * characters.
     * 
     * @param onChangeListener The Listener to link.
     */
    public void setOnChangeListener(OnChangeListener onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }
    
    /**
     * Remove the listener link to the character
     * 
     * Used to delete properly the thread.
     */
    public void removeOnChangeListener()
    {
    	this.onChangeListener = null;
    }

    /**
     * Method used to notify all listeners of the new position.
     */
    public interface OnChangeListener
    {
        void changed(int x, int y);
    }

}
