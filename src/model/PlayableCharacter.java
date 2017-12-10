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
    private static PlayableCharacter INSTANCE;
    private int _score;
    private int _life;

	private static String _imgPath = "/utils/player.png";


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
    public int getLife() 
	{
		return _life;
	}
	
    /**
     * Decrement the player's life, and check if the _life is positive.
     * @return True if _life is positive after decrementation, otherwise false.
     */
	public boolean decrementLife() 
	{
		return --_life == 0;	
	}
    
    
    /**
  	 * @return the _imgPath
  	 */
  	public static String getImgPath() {
  		return _imgPath;
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
     * 
     * @return The _score
     */
    public int getScore() {
	return _score;
    }

    public boolean collision(Vertex v) {
    	return _position.getX() == v.getX() && _position.getY() == v.getY();
    }
}
