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
    
    private PlayableCharacter()
    {
        _position = new Vertex (0, 0);
    }

    public static PlayableCharacter getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PlayableCharacter();
        }
        return INSTANCE;
    }
}
