package model;

import javafx.scene.image.Image;

/**
 * Class used to define the playable character.
 *
 * @author Java Group
 */
public class PlayableCharacter extends AbstractCharacter
{

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
}
