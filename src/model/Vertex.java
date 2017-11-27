package model;

import model.Model.Directions;

public class Vertex
{

    private int _x, _y, _nbr;

    public int getNbr()
    {
        return _nbr;
    }

    public void setNbr(int nbr)
    {
        _nbr = nbr;
    }

    public Vertex(int x, int y, int nbr)
    {
        _x = x;
        _y = y;
        _nbr = nbr;
    }

    public int getX()
    {
        return _x;
    }

    public void setX(int x)
    {
        _x = x;
    }

    public int getY()
    {
        return _y;
    }

    public void setY(int y)
    {
        _y = y;
    }

    /**
     * Compare two vertices according to their x, y and nbr
     *
     * @param v The vertex to compare
     * @return 0 if they are equals else 1
     */
    public int compareTo(Vertex v)
    {
        if (v.getX() == _x && v.getY() == _y && _nbr == v.getNbr())
        {
            return 0;
        }
        return 1;
    }

    /**
     * @return True if two vertices have the same x and y else false
     */
    //Pour pouvoir utiliser containsVertex qui utilise cette méthode
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

    //Pour pouvoir utiliser equals sans problème
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
     * @return false if we go off the labyrinth.
     */
    public boolean inBorders(Directions dir, int width, int height)
    {
        switch (dir)
        {
            case NORTH:
                return ((_y - 1 >= 0));
            case SOUTH:
                return ((_y + 1 < height));
            case EAST:
                return ((_x + 1 < width));
            case WEST:
                return ((_x - 1 >= 0));
        }
        return true;
    }

}
