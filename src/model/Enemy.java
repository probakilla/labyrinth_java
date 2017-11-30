package model;

import javafx.scene.image.Image;

/**
 * Class used to define an enemy.
 *
 * @author Java Group
 */
public class Enemy extends AbstractCharacter
{

    /**
     * Constructor of Enemy.
     *
     * Retrieves an instance of Enemy with specific coordinates.
     *
     * @param x Abscissa in the labyrinth of the Enemy.
     * @param y Ordinate in the labyrinth of the Enemy.
     */
    public Enemy (int x, int y)
    {
        super();
        _position.setX(x);
        _position.setY(y);
        _imageFile = new Image("../../bad.png");
        _image.setImage(_imageFile);
    }
}
