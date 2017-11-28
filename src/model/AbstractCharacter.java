package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import exceptions.WrongMoveException;
import java.util.Random;

/**
 * Pattern used for all Characters and objects in the labyrinth.
 *
 * @author Java Group
 */
public class AbstractCharacter
{

    protected Image _imageFile;
    protected ImageView _image;
    protected Vertex _position;

    /**
     * Constructor of AbstractCharacter.
     */
    public AbstractCharacter ()
    {
        _position = new Vertex(0, 0);
        _image = new ImageView();
    }

    private void outOfBounds (int newPos) throws WrongMoveException
    {
        if (newPos < 0 || newPos > Graph.getGRIDHEIGHT())
        {
            throw new WrongMoveException("Wrong move");
        }
    }

    /**
     * Increment the ordinate of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void up () throws WrongMoveException
    {
        int newPos = _position.getY() + 1;
        try
        {
            outOfBounds(newPos);
            _position.setY(newPos);
        }
        catch (WrongMoveException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Decrement the ordinate of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void down () throws WrongMoveException
    {
        int newPos = _position.getY() - 1;
        try
        {
            outOfBounds(newPos);
            _position.setY(newPos);
        }
        catch (WrongMoveException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Decrement the abscissa of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void left () throws WrongMoveException
    {
        int newPos = _position.getX() - 1;
        try
        {
            outOfBounds(newPos);
            _position.setX(newPos);
        }
        catch (WrongMoveException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Increment the abscissa of the character.
     *
     * @throws exceptions.WrongMoveException in case of collision with a wall or
     * a movement out of the labyrinth.
     */
    public void right () throws WrongMoveException
    {
        int newPos = _position.getX() + 1;
        try
        {
            outOfBounds(newPos);
            _position.setX(newPos);
        }
        catch (WrongMoveException e)
        {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Change the position of the character to specific location.
     *
     * @param x The target location abscissa.
     * @param y The target location ordinate.
     */
    public void setPosition (int x, int y)
    {
        _position.setX(x);
        _position.setY(y);
    }

    /**
     * Generate and set a random position for the character.
     */
    public void randomizePosition ()
    {
        Random rand = new Random ();
        int min = 0;
        int maxWidth = Graph.getGRIDWIDTH() - 1;
        int maxHeight = Graph.getGRIDHEIGHT() - 1;
        // Formule trouvée sur internet pour générer des nombres entre min et
        // max inclus.
        _position.setX(rand.nextInt(maxWidth - min + 1) + min);
        _position.setY(rand.nextInt(maxHeight - min + 1) + min);
    }

    /**
     * Retrieves the ImageView in order to display it.
     *
     * @return Retrieves the ImageView member.
     */
    public ImageView getImage ()
    {
        return _image;
    }
}
