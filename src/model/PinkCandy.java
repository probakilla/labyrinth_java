package model;

/**
 * PinkCandy implement the interface {@link model.Candy Candy} and has a type
 * equals to 3.
 *
 * @author Java Group
 */
public class PinkCandy extends AbstractCandy
{
    /**
     * Create an instance of PinkCandy with specific coordinates.
     *
     * @param x The Abscissa of the {@link model.Candy Candy}.
     * @param y The Ordinate of the {@link model.Candy Candy}.
     */
    public PinkCandy (int x, int y)
    {
        super(x, y);
        _imgPath = "candy-2.png";
        _type = 3;
    }

}
