package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public AbstractCharacter()
    {
        _position = new Vertex(0, 0);
        _image = new ImageView();
    }

    public void setPosition(int x, int y)
    {
        _position.setX(x);
        _position.setY(y);
    }
}
