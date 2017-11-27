package model;

import javafx.scene.image.Image;

/**
 * Class used to define an ennemy.
 *
 * @author Java Group
 */
public class Ennemy extends AbstractCharacter
{
    
    public Ennemy(int x, int y)
    {
        super();
        _position.setX(x);
        _position.setY(y);
        _imageFile = new Image ("../../bad.png");
        _image.setImage(_imageFile);
    }
}
