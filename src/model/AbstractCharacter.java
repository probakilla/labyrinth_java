package model;

import exceptions.WrongMoveException;
import java.util.Random;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Model.Directions;

/**
 * Pattern used for all Characters and objects in the labyrinth.
 *
 * @author Java Group
 */
public abstract class AbstractCharacter extends Thread
{
    protected int _type;
    protected Vertex _position;
    protected Image _imageFile;
    protected ImageView _imageDisp;
    protected OnChangeListener onChangeListener;

    /**
     * Constructor of AbstractCharacter.
     * 
     * Set the position of the AbstractCharacter with specific coordinates.
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
     * Retrieves the type of the character : 
     * -1 is for the {@link model.Enemy Enemy}
     * 1 is for the {@link model.PlayableCharacter}.
     * @return The type.
     */
    public int getType ()
    {
        return _type;
    }
    
    public ImageView getImage ()
    {
        return _imageDisp;
    }
    
    public Vertex getVertex() {
    	return _position;
    }
    
    /**
     * Change the position of the character to specific location.
     *
     * @param x The target location abscissa.
     * @param y The target location ordinate.
     */
    public void setPosition(int x, int y)
    {
        _position.setX(x);
        _position.setY(y);
        //Controller.getInstance().refreshAbstractCharacter(_position.getX(), _position.getY());
    }
    
    private void outOfBounds(Vertex v, Directions dir) throws WrongMoveException
    {
        if (v.getY() < 0 || v.getY() > Graph.getGRIDHEIGHT())
            throw new  WrongMoveException ("Je ne peux pas sortir du labyrinth.");
        else if (v.getX() < 0 || v.getX() > Graph.getGRIDWIDTH())
            throw new  WrongMoveException ("Je ne peux pas sortir du labyrinth.");
        if (Graph.getInstance().isWall (v, dir))
            throw new  WrongMoveException ("Il y a un mur par ici.");
    }

    /**
     * Increment the ordinate of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void up() throws WrongMoveException
    {
        Vertex newPos = new Vertex (0, 0);
        _position.copy(newPos);
        newPos.setY(_position.getY() - 1);
        try
        {
            outOfBounds(newPos, Directions.NORTH);
            newPos.copy(_position);
            System.out.println("Je vais en haut, nouvelle position : " + _position.toString());
        } catch (WrongMoveException e)
        {
            throw e;
        }
        if(onChangeListener != null)
        onChangeListener.changed(_position.getX(), _position.getY());
    }

    /**
     * Decrement the ordinate of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void down() throws WrongMoveException
    {
        Vertex newPos = new Vertex (0, 0);
        _position.copy(newPos);
        newPos.setY(_position.getY() + 1);
        try
        {
            outOfBounds(newPos, Directions.SOUTH);
            newPos.copy(_position);
            System.out.println("Je vais en haut, nouvelle position : " + _position.toString());
        } catch (WrongMoveException e)
        {
            throw e;
        }
        if(onChangeListener != null)
        onChangeListener.changed(_position.getX(), _position.getY());
    }

    /**
     * Decrement the abscissa of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void left() throws WrongMoveException
    {
        Vertex newPos = new Vertex (0, 0);
        _position.copy(newPos);
        newPos.setX(_position.getX() - 1);
        try
        {
            outOfBounds(newPos, Directions.WEST);
            newPos.copy(_position);
            System.out.println("Je vais en haut, nouvelle position : " + _position.toString());
        } catch (WrongMoveException e)
        {
            throw e;
        }
        if(onChangeListener != null)
        onChangeListener.changed(_position.getX(), _position.getY());
    }

    /**
     * Increment the abscissa of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void right() throws WrongMoveException
    {
        Vertex newPos = _position;
        newPos.setX(_position.getX() + 1);
        try
        {
            outOfBounds(newPos, Directions.EAST);
            newPos.copy(_position);
            System.out.println("Je vais en haut, nouvelle position : " + _position.toString());
        } catch (WrongMoveException e)
        {
            throw e;
        }
        if(onChangeListener != null)
        onChangeListener.changed(_position.getX(), _position.getY());
    }

    /**
     * Generate and set a random position for the character.
     */
    public void randomizePosition()
    {
        Random rand = new Random();
        int min = 0;
        int maxWidth = Graph.getGRIDWIDTH() - 1;
        int maxHeight = Graph.getGRIDHEIGHT() - 1;
        // Formule trouvée sur internet pour générer des nombres entre min et
        // max inclus.
        setPosition(rand.nextInt(maxWidth - min + 1) + min, rand.nextInt(maxHeight - min + 1) + min);
        /*_position.setX(rand.nextInt(maxWidth - min + 1) + min);
        _position.setY(rand.nextInt(maxHeight - min + 1) + min);*/
    }
    
    
    
    public void setOnChangeListener(OnChangeListener onChangeListener) {
    	this.onChangeListener = onChangeListener;
    }
    
    public interface OnChangeListener{
    	void changed(int x, int y);
    }

}
