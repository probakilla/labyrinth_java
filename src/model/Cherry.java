package model;

/**
 * Cherry implement the interface {@link model.Candy Candy} and has a type
 * equals to 10.
 *
 * @author Java Group
 */
public class Cherry extends AbstractCandy
{

    /**
     * Create an instance of PinkCandy with specific coordinates.
     *
     * @param x The Abscissa of the {@link model.Candy Candy}.
     * @param y The Ordinate of the {@link model.Candy Candy}.
     */
    public Cherry(int x, int y)
    {
        super(x, y);
        _imgPath = "/utils/candy-3.png";
        _type = 10;
    }

}
