package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class used to define the playable character.
 *
 * TYPE is the "id" of the class and it's used to manage collisions. When an
 * enemy and a player collide, the addition of their type should be 0. If this
 * is a candy, for a collision with a {@link model.Candy Candy}, it can be
 * any positive integer.
 *
 * @author Java Group
 */
public class PlayableCharacter extends AbstractCharacter
{
    private static PlayableCharacter INSTANCE;
    private int _score;

    private PlayableCharacter ()
    {
        super(0, 0);
        _imageFile = new Image("file:../../utils/player.png");
        _imageDisp = new ImageView (_imageFile);
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
    public static PlayableCharacter getInstance ()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PlayableCharacter();
        }
        return INSTANCE;
    }

    /**
     * Increase the score of the player in function of the 
     * {@link model.Candy Candy} he get.
     *
     * @param c The {@link model.Candy Candy} that the player gather.
     */
    public void increaseScore (Candy c)
    {
        _score += c.getType();
    }

    public void printScore ()
    {
        System.out.println("Vous avez gagné " + _score + " points.");
    }

}
