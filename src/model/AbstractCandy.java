package model;

/**
 * Abstract Class of {@link model.Candy Candy}.
 *
 * @author Java Group
 */
public abstract class  AbstractCandy implements Candy
{
    protected int _type;
    protected final Vertex _position;
    protected String _imgPath;
    
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
     * @return the _imgPath
     */
    public String getImgPath()
    {
        return _imgPath;
    }
}
