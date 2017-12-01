package model;

import javafx.scene.image.Image;

/**
 * Class used to define the playable character.
 *
 * TYPE is the "id" of the class and it's used to manage collisions.
 * When an enemy and a player collide, the addition of their type should be 0.
 * If this is a candy, it will be something else, but positive.
 * @author Java Group
 */
public class PlayableCharacter extends AbstractCharacter
{
    private final static int TYPE = 1;
    private static PlayableCharacter INSTANCE;

    private PlayableCharacter ()
    {
        super();
        _imageFile = new Image("file:../../utils/player.png");
        _image.setImage(_imageFile);
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
  
    public int type ()
    {
        return TYPE;
    }
}
