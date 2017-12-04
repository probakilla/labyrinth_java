package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract Class of {@link model.Candy Candy}.
 * 
 * @author Java Group
 */
public abstract class  AbstractCandy implements Candy
{
    protected int _type;
    protected final Vertex _position;
    protected Image _imageFile;
    protected ImageView _imageDisp;
    
    /**
     * Constructor of AbstractCandy.
     * 
     * Set the position of the {@link model.Candy Candy} with specific 
     * coordinates.
     * @param x Abscissa of the {@link model.Candy Candy}.
     * @param y Ordinate of the {@link model.Candy Candy}.
     */
    public AbstractCandy (int x, int y)
    {
        _position = new Vertex (x, y);
    }
    
    @Override
    public int getType ()
    {
        return _type;
    }
  
    @Override
    public Vertex getPosition ()
    {
       return _position;
    }
        
    /**
     * Retrieves the image of the {@link model.Candy Candy}.
     * 
     * @return The {@link javafx.scene.image.ImageView ImageView} of the 
     * {@link model.Candy Candy}.
     */
    public ImageView getImage ()
    {
        return _imageDisp;
    }
}
