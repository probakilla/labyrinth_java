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

    // TODO : Changer c'te bite
    private static final long serialVersionUID = 1L;

    private Edge _edge;
    private Vertex[][] _vertex;

    private static int GRID_WIDTH = 16;
    private static int GRID_HEIGHT = 16;

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

    public int getGRIDWIDTH()
    {
        return GRID_WIDTH;
    }

    public int getGRIDHEIGHT()
    {
        return GRID_HEIGHT;
    }

    public void buildGraph()
    {
        this.addEdge(_vertex[0][0], _vertex[0][1]);
    }

    /**
     * Check if the vertex exists and it's direction.
     *
     * @param v vertex to check.
     * @param dir direction to check.
     * @return true if both exists.
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

    public Vertex getVertex(int i, int j)
    {
        return _vertex[i][j];
    }
}
