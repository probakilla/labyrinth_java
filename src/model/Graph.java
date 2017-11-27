package model;

import org.jgrapht.graph.SimpleGraph;

/**
 * Graph used to display the labyrinth.
 *
 * This graph will set the global map of the labyrinth.
 *
 * @author Java Group
 */
public class Graph extends SimpleGraph<Vertex, Edge>
{
    private static final long serialVersionUID = 1L;

    private Edge _edge;
    private final Vertex[][] _vertex;

    private final static int GRID_WIDTH = 16;
    private final static int GRID_HEIGHT = 16;

    /**
     * Create a Graph base structure with {@link model.Vertex Vertices} and 
     * {@link model.Edge Edges}.
     */
    public Graph()
    {
        super(Edge.class);
        _vertex = new Vertex[GRID_WIDTH][GRID_HEIGHT];
        int i, j;
        for (i = 0; i < GRID_HEIGHT; ++i)
        {
            for (j = 0; j < GRID_WIDTH; ++j)
            {
                _vertex[j][i] = new Vertex(j, i, i + j);
            }
        }
    }
    
    /**
     * Retrives the width of the labyrinth.
     * 
     * @return The width of the labyrinth.
     */
    public int getGRIDWIDTH()
    {
        return GRID_WIDTH;
    }
    
    /**
     * Retrives the height of the labyrinth.
     * 
     * @return The height of the labyrinth.
     */
    public int getGRIDHEIGHT()
    {
        return GRID_HEIGHT;
    }
    
    /**
     * Check if the vertex exists in the Graph.
     *
     * @param v vertex to check.
     * @param dir direction to check.
     * @return true if both exists, false in the other case.
     */
    public boolean doesntExist(Vertex v, Model.Directions dir)
    {
        int xt = 0, yt = 0;
        int x = v.getX();
        int y = v.getY();
        switch (dir)
        {
            case NORTH:
                xt = x;
                yt = y - 1;
                break;
            case SOUTH:
                xt = x;
                yt = y + 1;
                break;
            case EAST:
                xt = x + 1;
                yt = y;
                break;
            case WEST:
                xt = x - 1;
                yt = y;
                break;
        }
        return !this.containsVertex(new Vertex(xt, yt, v.getNbr() + 1));
    }

    /**
     * Retrives a {@link model.Vertex Vertex} locate in specific coordinates.
     * 
     * @param i Abcsissa of the wanted {@link model.Vertex Vertex}.
     * @param j Ordinate of the wanted {@link model.Vertex Vertex}.
     * @return The {@link model.Vertex Vertex} locate in i, j coordinates.
     */
    public Vertex getVertex(int i, int j)
    {
        return _vertex[i][j];
    }
}
