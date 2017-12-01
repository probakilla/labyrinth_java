package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Red Candy implement the interface {@link model.Candy Candy} and has a type 
 * equals to 2.
 * 
 * @author Java Group
 */
public class RedCandy extends AbstractCandy
{
    /**
     * Create an instance of PinkCandy with specific coordinates.
     * 
     * @param x The Abscissa of the {@link model.Candy Candy}.
     * @param y The Ordinate of the {@link model.Candy Candy}.
     */
    public RedCandy (int x, int y) 
    {
        super (x, y);
        _imageFile = new Image("file:../../candy-2.png");
        _imageDisp = new ImageView(_imageFile);
        _type = 2;
    }

}
