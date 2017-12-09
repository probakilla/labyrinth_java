package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import org.jgrapht.graph.SimpleGraph;

import model.Edge.Type;
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
    
    private static Graph INSTANCE;
    private final static int GRID_WIDTH = 16;
    private final static int GRID_HEIGHT = 16;

    private Vertex[][] _vertex;

    /**
     * Create a Graph base structure with {@link model.Vertex Vertices} and
     * {@link model.Edge Edges}.
     */
    private Graph ()
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
    
    public static Graph getInstance()
    {
    	if (INSTANCE == null)
        {
            INSTANCE = new Graph();
        }
        return INSTANCE;
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
        return !this.containsVertex(new Vertex(xt, yt));
    }
    
    public boolean doesntExist (Vertex v)
    {
        return !this.containsVertex(v);
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
    	Vertex v = this.getVertex(1, 1);
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
            Vertex vertex;
            int i = 0;
            int j;
            for (Iterator<Vertex> it = v1.iterator(); it.hasNext(); i++)
        	{
        		vertex = it.next();
        		vertex.setNbr(i);
        	}
            i = 0;
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
     * Check if there is a wall between the actual vertex and the vertex in the directions dir.
     * 
     * @param actual actual vertex.
     * @param dir direction to find the target vertex.
     * @return true if there is a wall.
     */
    public boolean isWall(Vertex actual, Directions dir)
    {
    	return (this.getEdge(actual, dir) == null);
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

	/**
	 * Check if the Edge ,in directions dir, is an opened door.
	 * @param actual actual vertex.
	 * @param dir directions to check.
	 * @return true if the edge is an opened door.
	 */
	public boolean isOpenedDoor(Vertex actual, Directions dir)
	{
		Edge edge = this.getEdge(actual, dir);
		return (edge != null && (/*edge.getType() == Type.OPENED_DOOR ||*/ edge.getType() == Type.CORRIDOR));
	}
	
	public boolean isOpenedDoor(Vertex actual, Vertex target)
	{
		Edge edge = this.getEdge(actual, target);
		return (edge != null && (/*edge.getType() == Type.OPENED_DOOR ||*/ edge.getType() == Type.CORRIDOR));
	}
	
	/**
	 * Check if the Edge, in directions dir, is a closed door.
	 * @param actual actual vertex.
	 * @param dir directions to check.
	 * @return true if the edge is a closed door or if the edge doesn't exist.
	 */
	public boolean isClosedDoor(Vertex actual, Directions dir)
	{
		Edge edge = this.getEdge(actual, dir);
		return (edge != null && edge.getType() == Type.CLOSED_DOOR);
	}
	
	/**
	 * Retrieves the vertex to place the switch to open the edge door.
	 * @param door edge to be open by the switch
	 * @return The vertex where to place the switch
	 */
	public Vertex setSwitchOn(Edge door)
	{
		Random rand = new Random ();
		Set<Vertex> listeVertex = this.vertexSet();
		int i = 0;
		int j = 0;
        Vertex vertex;
        for (Iterator<Vertex> it = listeVertex.iterator(); it.hasNext(); i++)
    	{
    		vertex = it.next();
    		vertex.setNbr(i);
    	}
        if (door.getSource().getNbr() < door.getTarget().getNbr())
        	vertex = door.getSource();
        else
        	vertex = door.getTarget();
        i = rand.nextInt(vertex.getNbr() + 1);
        for (Iterator<Vertex> it = listeVertex.iterator(); j <= i; j++)
    	{
        	vertex = it.next();
    	}
		return vertex;
		
	}
	
	/**
	 * Retrieves the vertex to place the switch to close the edge door.
	 * @param door edge to be open by the switch
	 * @return The vertex where to place the switch
	 */
	public Vertex setSwitchOff(Edge door)
	{
		Random rand = new Random ();
		Set<Vertex> listeVertex = this.vertexSet();
		int i = 0;
		int j = 0;
        Vertex vertex;
        for (Iterator<Vertex> it = listeVertex.iterator(); it.hasNext(); i++)
    	{
    		vertex = it.next();
    		vertex.setNbr(i);
    	}
        if (door.getSource().getNbr() > door.getTarget().getNbr())
        	vertex = door.getSource();
        else
        	vertex = door.getTarget();
        i = rand.nextInt((this.getGRIDHEIGHT() * this.getGRIDWIDTH()) - vertex.getNbr() + 1);
        for (Iterator<Vertex> it = listeVertex.iterator(); j <= i; j++)
    	{
        	vertex = it.next();
    	}
		return vertex;
		
	}
	
	/**
	 * Set the type of the Edge edge to CLOSED_DOOR.
	 * 
	 * @param edge the Edge to be changed.
	 * @return The modified Edge.
	 */
	public Edge closedDoor (Edge edge)
	{
		edge.setType(Edge.Type.CLOSED_DOOR);
		return edge;
	}
	
	/**
	 * Close randomly a door from the graph.
	 * @return The Edge containing the recently close door.
	 */
	public Edge closeDoorRandom  ()
	{
		Edge edge = this.randomEdge ();
		return closedDoor (edge);
	}

	/**
	 * Retrieves a random Edge from the graph
	 * 
	 * @return The random Edge.
	 */
	private Edge randomEdge()
	{
		Random rand = new Random();
		Set<Edge> listeEdge = this.edgeSet();
		int randEdge = rand.nextInt(listeEdge.size()) + 1;
		Iterator<Edge> it = listeEdge.iterator();
		Edge edge = it.next();
		for (int i = 0; i < randEdge; ++i)
			edge = it.next();
		return edge;
	}
	
	/**
	 * Change the type of a random edge, from the graph, to OPENNED_DOOR.
	 */
	public void openDoorRandom ()
	{
		int i;
		Random rand = new Random ();
		for (i = 0; i < 1000; ++i)
		{
			Vertex source = this.randomVertex ();
			if (source != null)
			{
				Directions dir = Directions.values()[rand.nextInt(Directions.values().length)];
				if (isWall (source, dir))
				{
					Vertex target = this.getVertexByDir(source, dir);
					if (target != null)
					{
						Edge edge = this.getEdge(source, target);
						if (edge == null)
						{
							this.addEdge(source, target, new Edge (Type.OPENED_DOOR));
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Retrieves a random vertex from the graph.
	 * 
	 * @return The random vertex.
	 */
	private Vertex randomVertex() 
	{
		Random rand = new Random();
		Set<Vertex> listeVertex = this.vertexSet();
		int randVertex = rand.nextInt(listeVertex.size());
		Iterator<Vertex> it = listeVertex.iterator();
		Vertex vertex = it.next();
		for (int i = 0; i < randVertex; ++i)
			vertex = it.next();
		return vertex;
	}
	
	/**
	 * Returns a set of the closed edges contained in this graph
	 * 
	 * @return The set of the closed edges.
	 */
	public Set<Edge> allClosedDoor ()
	{
		//Set<Edge> listEdge = this.edgeSet();
		Set<Edge> listClosedEdge = new HashSet<Edge> ();
		for (Edge edge : this.edgeSet())
			if (edge.getType() == Type.CLOSED_DOOR)
				listClosedEdge.add(edge);
		return listClosedEdge;
	}

}
