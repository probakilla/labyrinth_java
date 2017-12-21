package model;

/**
 * Class used to define the playable character.
 *
 * TYPE is the "id" of the class and it's used to manage collisions. When an
 * enemy and a player collide, the addition of their type should be 0. If this
 * is a candy, for a collision with a {@link model.Candy Candy}, it can be any
 * positive integer.
 *
 * @author Java Groupbutton_close.png
 */
public class PlayableCharacter extends AbstractCharacter
{
    private int _score;
    private static PlayableCharacter INSTANCE;
    private static int _life = 3;
    private static final String IMG_PATH = "/utils/player.png";

    private PlayableCharacter()
    {
        super(0, 0);
        _type = 1;
        _score = 0;
    }

    /**
     * Retrieves an instance of PlayableCharacter.
     *
     * Retrieves an instance of PlayableCharacter. There can be only one
     * PlayableCharacter at once thanks to the singleton design pattern.
     *
     * @return The unique instance of PlayableCharacter.
     */
    public static PlayableCharacter getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PlayableCharacter();
        }
        return INSTANCE;
    }

    /**
     *
     * @return the _life
     */
    public static int getLife()
    {
        return _life;
    }

    /**
     * Retrieves the score of the player.
     *
     * @return The score of the player.
     */
    public int getScore()
    {
        return _score;
    }

    /**
     * Retrieves the string Corresponding to the path of the
     * image used for the display of the Player.
     *
     * @return the path of the image of the character.
     */
    public static String getImgPath()
    {
        return IMG_PATH;
    }

    /**
     * Set the player's life.
     *
     * @param life The number of life to be set.
     */
    public static void setLife(int life)
    {
        _life = life;
    }
    
    /**
     * Set the player's score.
     * 
     * @param score the score to give to the player.
     */
    public void setScore(int score)
    {
        _score = score;
    }

    /**
     * Decrement the player's life, and check if the _life is positive.
     *
     * @return True if _life is positive after decrementation, otherwise false.
     */
    public int decrementLife()
    {
        if (_life == 0)
        {
            return _life;
        }
        return --_life;
    }

    /**
     * Increase the score of the player in function of the
     * {@link model.Candy Candy} he get.
     *
     * @param c The {@link model.Candy Candy} that the player gather.
     */
    public void increaseScore(Candy c)
    {
        _score += c.getType();
    }

    /**
     * Retrieves a boolean corresponding if the player is colliding with 
     * another object.
     * 
     * @param v The position of the other object.
     * @return Retrieves true if the player collides with the object, otherwise
     * retrieves false.
     */
    public boolean collision(Vertex v)
    {
        return _position.equals(v);
    }
}
