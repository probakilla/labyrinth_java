package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
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

    private Vertex[][] _vertex;

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

    /**
     * Retrieve the vertex in the direction dir of the actual vertex.
     * 
     * @param actual vertex used to search.
     * @param dir direction were we search the vertex.
     * @return the specific vertex if such vertices exist, otherwise return null.
     */
    public Vertex getVertexByDir (Vertex actual, Directions dir)
    {
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
     * Retrieve the vertex as far possible from the origin vertex. 
     * 
     * Used to place the door as far possible from the player. 
     * @return The specific vertex.
     */
    public Vertex getEndPath ()
    {	
    	Vertex v = this.getVertex(0, 0);
    	Queue<Vertex> fifo = new ArrayDeque<Vertex>();
    	Set<Vertex> ListVertex = this.vertexSet();
    	Vertex vertex;
    	for (Iterator<Vertex> it = ListVertex.iterator(); it.hasNext();)
    	{
    		vertex = it.next();
    		vertex.setNbr(0);
    	}
    	v.setNbr(1);
    	Vertex ret = v;
    	fifo.add(v);
    	while (!fifo.isEmpty())
    	{
    		Vertex actual = fifo.remove();
    		for (Edge edge : this.edgesOf(actual))
            {
    			Vertex next = edge.getSource();
    			if (next.equals(actual))
    				next = edge.getTarget();
    			if (next.getNbr() == 0)
                {
    				
    				next.setNbr(actual.getNbr() + 1);
    				if (next.getNbr() > ret.getNbr())
    					ret = next;
    				fifo.add(next);
                }
            }
    	}
    	return ret;	
    }
    
    /**
     * Write the graph in a .dot file.
     *
     * This method writes the {@link model.Graph Graph} in a .dot file in order
     * to display it with graphviz.
     */
    public void GraphToDot ()
    {
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

    /**
     * Return an edge connecting the source vertex to a vertex in the direction dir. 
     *
     * @param source source vertex of the edge
     * @param dir direction to find the target vertex
     * @return The specific edge if such edge exist, otherwise return null.
     */
	public Edge getEdge(Vertex source, Directions dir) {
		Vertex target = this.getVertexByDir(source, dir);
		if (target == null)
			return null;
		return this.getEdge(source, target);
	}
}
