package model;

import java.util.Random;

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
    protected OnChangeListener onChangeListener;

    /**
     * Constructor of AbstractCharacter.
     *
     * Set the position of the AbstractCharacter with specific coordinates.
     *
     * @param x Abscissa in the labyrinth of the character.
     * @param y Ordinate in the labyrinth of the character.
     */
    public AbstractCharacter (int x, int y)
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
    public int getType ()
    {
        return _type;
    }

    public Vertex getPosition ()
    {
        return _position;
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
        //Controller.getInstance().refreshAbstractCharacter(_position.getX(), _position.getY());
    }

    private boolean validMove (Vertex v, Directions dir)
    {
//        if (v.getY() < 0 || v.getY() > Graph.getGRIDHEIGHT())
//            throw new  WrongMoveException ("Je ne peux pas sortir du labyrinth.");
//        else if (v.getX() < 0 || v.getX() > Graph.getGRIDWIDTH())
//            throw new  WrongMoveException ("Je ne peux pas sortir du labyrinth.");
//        if (Graph.getInstance().isWall (v, dir))
//            throw new  WrongMoveException ("Il y a un mur par ici.");

        // C'est un plusieur lignes pour éviter un return qui fait 69 mètres.
    	if (!v.inBorders(dir, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT()))
    		return false;
        /*if (v.getY() < 0 || v.getY() > Graph.getGRIDHEIGHT())
        {
            return false;
        }
        else if (v.getX() < 0 || v.getX() > Graph.getGRIDWIDTH())
        {
            return false;
        }*/
        else if (Graph.getInstance().isWall(v, dir))
        {
            return false;
        }
        return true;
    }

    /**
     * Increment the ordinate of the character.
     */
    public void up ()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.NORTH))
        {
        	newPos.setY(newPos.getY() - 1);
            _position.copy(newPos);
            System.out.println("HAUT   -> " + _position.toString());
        }
        else if (!validMove(newPos, Directions.NORTH))
        {
            System.out.println("NOPE" + _position.toString());
        }
    }

    /**
     * Decrement the ordinate of the character.
     */
    public void down ()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.SOUTH))
        {
        	newPos.setY(newPos.getY() + 1);
            _position.copy(newPos);
            System.out.println("BAS    -> " + _position.toString());
        }
        else if (!validMove(newPos, Directions.SOUTH))
        {
            System.out.println("NOPE" + _position.toString());
        }
    }

    /**
     * Decrement the abscissa of the character.
     */
    public void left ()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.WEST))
        {
        	newPos.setX(newPos.getX() - 1);
            _position.copy(newPos);
            System.out.println("GAUCHE -> " + _position.toString());
        }
        else if (!validMove(newPos, Directions.WEST))
        {
            System.out.println("NOPE" + _position.toString());
        }
    }

    /**
     * Increment the abscissa of the character.
     */
    public void right ()
    {
        Vertex newPos = new Vertex();
        newPos.copy(_position);

        if (validMove(newPos, Directions.EAST))
        {
        	newPos.setX(newPos.getX() + 1);
            _position.copy(newPos);
            System.out.println("DROIT  -> " + _position.toString());
        }
        else if (!validMove(newPos, Directions.EAST))
        {
            System.out.println("NOPE" + _position.toString());
        }
    }

    /**
     * Generate and set a random position for the character.
     */
    public void randomizePosition ()
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

    public void setOnChangeListener (OnChangeListener onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }

    public interface OnChangeListener
    {
        void changed (int x, int y);
    }

}
