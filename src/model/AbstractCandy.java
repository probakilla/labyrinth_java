package model;

/**
 * Abstract Class of {@link model.Candy Candy}.
 *
 * @author Java Group
 */
public abstract class AbstractCandy implements Candy
{
    protected int _type;
    protected String _imgPath;
    protected final Vertex _position;

    /**
     * Constructor of AbstractCandy.
     *
     * Set the position of the {@link model.Candy Candy} with specific
     * coordinates.
     *
     * @param x Abscissa of the {@link model.Candy Candy}.
     * @param y Ordinate of the {@link model.Candy Candy}.
     */
    public AbstractCandy(int x, int y)
    {
        _position = new Vertex(x, y);
    }

    @Override
    public int getType()
    {
        return _type;
    }

    @Override
    public Vertex getPosition()
    {
        return _position;
    }

    /**
     * Retrieves the path of the to the image of the {@link model.Candy Candy}.
     *
     * @return The path to the image.
     */
    public String getImgPath()
    {
        return _imgPath;
    }
    

    /**
     * Check if the position of the {@link model.Candy Candy} we want to put is
     * not already used by another {@link model.Candy Candy}, or the {@link model.PlayableCharacter player} or the exit door.
     * 
     * @param candyList The array containing all {@link model.Candy Candies}.
     * @param candy The {@link model.Candy Candy} to be tested.
     * @param exitDoor The {@link model.Vertex vertex} of the exit door.
     * @return True, if there is no other {@link model.Candy Candy}, or {@link model.PlayableCharacter player} or exit door at the same position, otherwise return false.
     */
    public static boolean correctCandyPosition(AbstractCandy[] candyList, AbstractCandy candy, Vertex exitDoor)
    {
    	Vertex candyPosition = candy.getPosition();
       if (candyPosition.equals(new Vertex (0, 0)) || candyPosition.equals(exitDoor))
        		return false;
        int i;
        for (i = 0; i < candyList.length; i++)
        {
        	if (candyList[i] == null)
        		return true;
            if (candyPosition.equals(candyList[i].getPosition()))
                return false;
        }
        return true;
    }
}
