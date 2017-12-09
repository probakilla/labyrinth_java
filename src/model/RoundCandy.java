package model;

/**
 * RoundCandy implement the interface {@link model.Candy Candy} and has a type
 * equals to 5.
 *
 * @author Java Group
 */
public class RoundCandy extends AbstractCandy
{

    /**
     * Create an instance of PinkCandy with specific coordinates.
     *
     * @param x The Abscissa of the {@link model.Candy Candy}.
     * @param y The Ordinate of the {@link model.Candy Candy}.
     */
    public RoundCandy (int x, int y)
    {
        super(x, y);
        _imgPath = "/utils/candy-4.png";
        _type = 5;
    }
}
