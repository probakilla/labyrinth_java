package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import org.jgrapht.graph.SimpleGraph;

import model.Model.Directions;

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

    private final static int GRID_WIDTH = 16;
    private final static int GRID_HEIGHT = 16;
    private final static int MAX_OBSERVERS = 10;

    private Edge _edge;
    private final Vertex[][] _vertex;

    /**
     * Create a Graph base structure with {@link model.Vertex Vertices} and
     * {@link model.Edge Edges}.
     */
    public Graph ()
    {
        super(Edge.class);
        _vertex = new Vertex[GRID_WIDTH][GRID_HEIGHT];
        int i, j, nbr;
        nbr = 0;
        for (i = 0; i < GRID_HEIGHT; ++i)
        {
            for (j = 0; j < GRID_WIDTH; ++j)
            {
                _vertex[j][i] = new Vertex(j, i, nbr);
                nbr++;
            }
        }
    }

    /**
     * Retrieves the width of the labyrinth.
     *
     * @return The width of the labyrinth.
     */
    public static int getGRIDWIDTH ()
    {
        return GRID_WIDTH;
    }

    /**
     * Retrieves the height of the labyrinth.
     *
     * @return The height of the labyrinth.
     */
    public static int getGRIDHEIGHT ()
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
    public boolean doesntExist (Vertex v, Model.Directions dir)
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
     * Retrieves a {@link model.Vertex Vertex} locate in specific coordinates.
     *
     * @param i Abscissa of the wanted {@link model.Vertex Vertex}.
     * @param j Ordinate of the wanted {@link model.Vertex Vertex}.
     * @return The {@link model.Vertex Vertex} locate in i, j coordinates.
     */
    public Vertex getVertex (int i, int j)
    {
        return _vertex[i][j];
    }

    public Vertex getVertexByDir (Vertex actual, Directions dir)
    {
        // TODO Auto-generated method stub
        if (!this.doesntExist(actual, dir))
        {
            int xt = 0, yt = 0;
            int x = actual.getX();
            int y = actual.getY();
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
            return getVertex(xt, yt);
        }
        return null;
    }

    /**
     * Write the graph in a .dot file.
     *
     * This method writes the {@link model.Graph Graph} in a .dot file in order
     * to display it with graphviz.
     */
    public void GraphToDot ()
    {
        Vertex v = new Vertex(0, 0, 0);
        //_graph = new Graph();
        this.addVertex(v);
        //buildRandomPath(v);
        PrintWriter writer;
        try
        {
            writer = new PrintWriter("graph.dot");
            writer.println("graph path {");
            Set<Vertex> v1 = this.vertexSet();
            int i = 0;
            int j;
            for (Iterator<Vertex> it = v1.iterator(); it.hasNext(); i++)
            {
                Vertex from = it.next();
                j = 0;
                for (Iterator<Vertex> it1 = v1.iterator(); it1.hasNext(); j++)
                {
                    Vertex to = it1.next();
                    while (j < i)
                    {
                        ++j;
                        to = it1.next();
                    }
                    if (this.containsEdge(from, to))
                    {
                        writer.print(from.getNbr());
                        writer.print(" -- ");
                        writer.println(to.getNbr());
                    }

                }
            }
            writer.println("}");
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
