package model;

import model.Model.Directions;

/**
 * An implementation of vertices in a {@link model.Graph Graph}
 *
 * @author Java Group
 */
public class Vertex
{

    private int _x, _y, _nbr;

    /**
     * Create a Vertex with null coordinates.
     */
    public Vertex()
    {
        _x = 0;
        _y = 0;
        _nbr = -1;
    }

    /**
     * Create a Vertex with specific coordinates and a fixed number.
     *
     * Create a Vertex with x in abscissa and y in ordinate and set the number
     * of the Vertex to -1.
     *
     * @param x Abscissa of the Vertex.
     * @param y Ordinate of the Vertex.
     */
    public Vertex(int x, int y)
    {
        _x = x;
        _y = y;
        _nbr = -1;
    }

    /**
     * Setter on the abscissa of the {@link model.Vertex Vertex}.
     * 
     * @param x The abscissa of the {@link model.Vertex Vertex}.
     */
    public void setX(int x)
    {
        _x = x;
    }

    /**
     * Setter on the ordinate of the {@link model.Vertex Vertex}.
     * 
     * @param y The abscissa of the {@link model.Vertex Vertex}.
     */
    public void setY(int y)
    {
        _y = y;
    }

    /**
     * Create a Vertex with specific coordinates and a number.
     *
     * @param x Abscissa of the Vertex.
     * @param y Ordinate of the Vertex.
     * @param nbr Number of the Vertex.
     */
    public Vertex(int x, int y, int nbr)
    {
        _x = x;
        _y = y;
        _nbr = nbr;
    }

    /**
     * Retrieves the number of the {@link model.Vertex Vertex}.
     *
     * @return The number of the {@link model.Vertex Vertex}.
     */
    public int getNbr()
    {
        return _nbr;
    }

    /**
     * Setter on the number of the current {@link model.Vertex Vertex}.
     * 
     * @param nbr The number of the {@link model.Vertex Vertex}.
     */
    public void setNbr(int nbr)
    {
        _nbr = nbr;
    }

    /**
     * Retrieves the Abscissa of the {@link model.Vertex Vertex}.
     *
     * @return The Abscissa of the {@link model.Vertex Vertex}.
     */
    public int getX()
    {
        return _x;
    }

    /**
     * Retrieves the Ordinate of the {@link model.Vertex Vertex}.
     *
     * @return The Ordinate of the {@link model.Vertex Vertex}.
     */
    public int getY()
    {
        return _y;
    }

    /**
     * Compare two vertices according to their x, y and nbr.
     *
     * @param v The vertex to compare.
     * @return 0 if they are equals, 1 in the other case.
     */
    public int compareTo(Vertex v)
    {
        if (v.getX() == _x && v.getY() == _y && _nbr == v.getNbr())
            return 0;
        return 1;
    }

    /**
     * Compare two Vertices coordinates.
     *
     * @param obj The object to compare with.
     * @return True if two {@link model.Vertex Vertices} have the same x and y, 
     * false in the other case.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass()))
        {
            return false;
        }
        if (!(obj instanceof Vertex))
        {
            return false;
        }
        Vertex v = (Vertex) obj;
        return v.getX() == _x && v.getY() == _y;
    }

    @Override
    public int hashCode()
    {
        return _x - _y;
    }

    /**
     * Check if we stay in the limits of the graph when we move.
     *
     * @param dir direction of the movement.
     * @param width Width of the labyrinth.
     * @param height height of the labyrinth.
     * @return false if we go off the labyrinth, true in the other case.
     */
    public boolean inBorders(Directions dir, int width, int height)
    {
        switch (dir)
        {
            case NORTH:
                return _y - 1 >= 0;
            case SOUTH:
                return _y + 1 < height;
            case EAST:
                return _x + 1 < width;
            case WEST:
                return _x - 1 >= 0;
        }
        return false;
    }

    /**
     * Retrieves a String with coordinates.
     *
     * Retrieves a {@link java.String String} with the coordinates of the 
     * {@link model.Vertex Vertex} with the format [x-y].
     *
     * @return The {@link java.String String} with the coordinates of the 
     * {@link model.Vertex Vertex}.
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("[");
        str.append(_x).append(",").append(_y).append("]").append(" nb ").append(_nbr);
        return str.toString();
    }

    /**
     * Copy a {@link model.Vertex Vertex} into the current 
     * {@link model.Vertex Vertex}.
     * 
     * @param o The object (should be a {@link model.Vertex Vertex}) to copy.
     */
    public void copy(Object o)
    {
        if (o instanceof Vertex)
        {
            _x = ((Vertex) o).getX();
            _y = ((Vertex) o).getY();
        }
    }
}
