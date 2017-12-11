package model;

import java.util.Random;

import model.Model.Directions;

/**
 * Pattern used for all Characters and objects in the labyrinth.
 *
 * @author Java Group
 */
public abstract class AbstractCharacter extends Thread {
    protected int _type;
    protected Vertex _position;
    protected OnChangeListener onChangeListener;
    
    /**
     * Constructor of AbstractCharacter.
     *
     * Set the position of the AbstractCharacter with specific coordinates.
     *
     * @param x
     *            Abscissa in the labyrinth of the character.
     * @param y
     *            Ordinate in the labyrinth of the character.
     */
    public AbstractCharacter(int x, int y) {
        _position = new Vertex(x, y);
    }
    
    /**
     * Retrieves the type of the Character.
     *
     * Retrieves the type of the character : -1 is for the {@link model.Enemy Enemy}
     * 1 is for the {@link model.PlayableCharacter}.
     *
     * @return The type.
     */
    public int getType() {
        return _type;
    }
    
    public Vertex getPosition() {
        return _position;
    }
    
    /**
     * Change the position of the character to specific location.
     *
     * @param x
     *            The target location abscissa.
     * @param y
     *            The target location ordinate.
     */
    public void setPosition(int x, int y) {
        _position.setX(x);
        _position.setY(y);
    }
    
    private boolean validMove(Vertex v, Directions dir) {
        if (!v.inBorders(dir, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT())) {
            return false;
        } else if (Graph.getInstance().isWall(v, dir) || Graph.getInstance().isClosedDoor(v, dir)) {
            return false;
        }
        return true;
    }
    
    /**
     * Increment the ordinate of the character.
     */
    public void up() {
        Vertex newPos = new Vertex();
        newPos.copy(_position);
        
        if (validMove(newPos, Directions.NORTH)) {
            newPos.setY(newPos.getY() - 1);
            _position.copy(newPos);
            //System.out.println("HAUT   -> " + _position.toString());
            onChangeListener.changed(newPos.getX(), newPos.getY());
        } else if (!validMove(newPos, Directions.NORTH)) {
            //System.out.println("NOPE" + _position.toString());
        }
    }
    
    /**
     * Decrement the ordinate of the character.
     */
    public void down() {
        Vertex newPos = new Vertex();
        newPos.copy(_position);
        
        if (validMove(newPos, Directions.SOUTH)) {
            newPos.setY(newPos.getY() + 1);
            _position.copy(newPos);
            //System.out.println("BAS    -> " + _position.toString());
            onChangeListener.changed(newPos.getX(), newPos.getY());
        } else if (!validMove(newPos, Directions.SOUTH)) {
            //System.out.println("NOPE" + _position.toString());
        }
    }
    
    /**
     * Decrement the abscissa of the character.
     */
    public void left() {
        Vertex newPos = new Vertex();
        newPos.copy(_position);
        
        if (validMove(newPos, Directions.WEST)) {
            newPos.setX(newPos.getX() - 1);
            _position.copy(newPos);
            //System.out.println("GAUCHE -> " + _position.toString());
            onChangeListener.changed(newPos.getX(), newPos.getY());
        } else if (!validMove(newPos, Directions.WEST)) {
            //System.out.println("NOPE" + _position.toString());
        }
    }
    
    /**
     * Increment the abscissa of the character.
     */
    public void right() {
        Vertex newPos = new Vertex();
        newPos.copy(_position);
        
        if (validMove(newPos, Directions.EAST)) {
            newPos.setX(newPos.getX() + 1);
            _position.copy(newPos);
            //System.out.println("DROIT  -> " + _position.toString());
            onChangeListener.changed(newPos.getX(), newPos.getY());
        } else if (!validMove(newPos, Directions.EAST)) {
            //System.out.println("NOPE" + _position.toString());
        }
    }
    
    /**
     * Generate and set a random position for the character.
     */
    public void randomizePosition() {
        Random rand = new Random();
        int min = 5;//On place les ennemies à au moins 5 cases du joueur.
        int maxWidth = Graph.getGRIDWIDTH() - 1;
        int maxHeight = Graph.getGRIDHEIGHT() - 1;
        // Formule trouvée sur internet pour générer des nombres entre min et
        // max inclus.
        setPosition(rand.nextInt(maxWidth - min + 1) + min, rand.nextInt(maxHeight - min + 1) + min);
    }
    
    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }
    
    public interface OnChangeListener {
        void changed(int x, int y);
    }
    
}
