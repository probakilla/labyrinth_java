package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import model.Edge.Type;
import model.Model.Directions;
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
    private static Graph INSTANCE;
    private final static int GRID_WIDTH = 16;
    private final static int GRID_HEIGHT = 16;

    private final Vertex[][] _vertex;

    /**
     * Create a Graph base structure with {@link model.Vertex Vertices} and
     * {@link model.Edge Edges}. 
     */
    private Graph()
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
    
    public void removeGraph ()
    {
    	INSTANCE = null;
    }

    /**
     * Remove all the {@link model.Edge edges} of the {@link model.Graph graph}.
     * 
     * @param <Vertex> Class of vertices.
     * @param <Edge> Class of edges.
     * @param graph The {@link model.Graph graph} where to remove all {@link model.Edge edges}.
     */
    public static <Vertex, Edge> void removeAllEdges(Graph graph)
    {
        LinkedList<model.Edge> copy = new LinkedList<>();
        graph.edgeSet().stream().forEach((e) ->
        {
            copy.add(e);
        });
        graph.removeAllEdges(copy);
    }

    /**
     * Remove all the {@link model.Vertex vertices} of the {@link model.Graph graph}.
     * 
     * @param <Vertex> Class of vertices.
     * @param <Edge> Class of edges.
     * @param graph The {@link model.Graph graph} where to remove all {@link model.Vertex vertices}.
     */
    public static <Vertex, Edge> void removeAllVertices(Graph graph)
    {
        LinkedList<model.Vertex> copy = new LinkedList<>();
        graph.vertexSet().stream().forEach((v) ->
        {
            copy.add(v);
        });
        graph.removeAllVertices(copy);
    }  

    /**
     * Remove all {@link model.Vertex vertices} and {@link model.Edge edges} of the {@link model.Graph graph}.
     * @param <Vertex> Class of vertices.
     * @param <Edge> Class of edges.
     * @param graph The {@link model.Graph graph} where to remove all {@link model.Vertex vertices} and {@link model.Edge edges}.
     */
    public static <Vertex, Edge> void clearGraph(Graph graph)
    {
        removeAllEdges(graph);
        removeAllVertices(graph);
    }

    /**
     * Get the unique instance of the Graph.
     *
     * @return The unique instance of the graph.
     */
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
    public static int getGRIDWIDTH()
    {
        return GRID_WIDTH;
    }

    /**
     * Retrieves the height of the labyrinth.
     *
     * @return The height of the labyrinth.
     */
    public static int getGRIDHEIGHT()
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
        return !this.containsVertex(new Vertex(xt, yt));
    }
  
    public Vertex getVertex(int i, int j)
    {
        return _vertex[i][j];
    }

    /**
     * Retrieve the {@link model.Vertex Vertex} in the direction dir of the
     * actual {@link model.Vertex Vertex}.
     *
     * @param actual The {@link model.Vertex Vertex} used to search.
     * @param dir direction were we search the {@link model.Vertex Vertex}.
     * @return the specific {@link model.Vertex Vertex} if such vertices exist,
     * otherwise return null.
     */
    public Vertex getVertexByDir(Vertex actual, Directions dir)
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
     * Retrieve the {@link model.Vertex Vertex} as far possible from the origin
     * {@link model.Vertex Vertex}.
     *
     * Used to place the door as far possible from the player.
     *
     * @return The specific {@link model.Vertex Vertex}.
     */
    public Vertex getEndPath()
    {
        Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        Set<Vertex> ListVertex = this.vertexSet();
        Vertex vertex;
        for ( Iterator<Vertex> it = ListVertex.iterator(); it.hasNext();)
        {
            vertex = it.next();
            vertex.setNbr(0);
        }
        Iterator<Vertex> it = ListVertex.iterator();
        Vertex v = it.next ();
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
                {
                    next = edge.getTarget();
                }
                if (next.getNbr() == 0)
                {

                    next.setNbr(actual.getNbr() + 1);
                    if (next.getNbr() > ret.getNbr())
                    {
                        ret = next;
                    }
                    fifo.add(next);
                }
            }
        }
        return ret;
    }

    /**
     * Write the {@link model.Graph graph} in a .dot file.
     *
     * This method writes the {@link model.Graph Graph} in a .dot file in order
     * to display it with graphviz.
     */
    public void GraphToDot()
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
     * Check if there is a wall between the actual {@link model.Vertex Vertex}
     * and the {@link model.Vertex Vertex} in the
     * {@link model.Model.Directions Directions} dir.
     *
     * @param actual actual {@link model.Vertex Vertex}.
     * @param dir direction to find the target {@link model.Vertex Vertex}.
     * @return true if there is a wall, false otherwise.
     */
    public boolean isWall(Vertex actual, Directions dir)
    {
        return (this.getEdgeByDir(actual, dir) == null);
    }

    /**
     * Return an {@link model.Edge Edge} connecting the source
     * {@link model.Vertex Vertex} to a {@link model.Vertex Vertex} in the
     * {@link model.Model.Directions Directions} dir.
     *
     * @param source source {@link model.Vertex Vertex} of the
     * {@link model.Edge Edge}
     * @param dir {@link model.Model.Directions Directions} to find the target
     * {@link model.Vertex Vertex}
     * @return The specific {@link model.Edge Edge} if such edge exist,
     * otherwise return null.
     */
    public Edge getEdgeByDir(Vertex source, Directions dir)
    {
        Vertex target = this.getVertexByDir(source, dir);
        if (target == null)
        {
            return null;
        }
        return this.getEdge(source, target);
    }

    /**
     * Check if the {@link model.Edge Edge} ,in
     * {@link model.Model.Directions Directions} dir, is an opened door.
     *
     * @param actual actual {@link model.Vertex Vertex}.
     * @param dir {@link model.Model.Directions Directions} to check.
     * @return true if the {@link model.Edge Edge} is an opened door.
     */
    public boolean isOpenedDoor(Vertex actual, Directions dir)
    {
        Edge edge = this.getEdgeByDir(actual, dir);
        return (edge != null && (edge.getType() == Type.OPENED_DOOR || edge.getType() == Type.CORRIDOR));
    }

    /**
     * Check if the Edge, in {@link model.Model.Directions Directions} dir, is a
     * closed door.
     *
     * @param actual actual {@link model.Vertex Vertex}.
     * @param dir {@link model.Model.Directions Directions} to check.
     * @return true if the {@link model.Edge Edge} is a closed door or if the
     * {@link model.Edge Edge} doesn't exist.
     */
    public boolean isClosedDoor(Vertex actual, Directions dir)
    {
        Edge edge = this.getEdgeByDir(actual, dir);
        return (edge != null && edge.getType() == Type.CLOSED_DOOR);
    }

    /**
     * Retrieves the {@link model.Vertex Vertex} to place the switch to open the
     * {@link model.Edge Edge} door.
     *
     * @param door {@link model.Edge Edge} to be open by the switch
     * @return The {@link model.Vertex Vertex} where to place the switch
     */
    public Vertex setSwitchOn(Edge door)
    {
        Random rand = new Random();
        Set<Vertex> listeVertex = this.vertexSet();
        int i = 0;
        int j = 0;
        Vertex vertex;
        Graph.getInstance().GraphToDot();
        if (door.getSource().getNbr() < door.getTarget().getNbr())
        {
            vertex = door.getSource();
        }
        else
        {
            vertex = door.getTarget();
        }
        i = rand.nextInt(vertex.getNbr());
        for (Iterator<Vertex> it = listeVertex.iterator(); j <= i; j++)
        {
            vertex = it.next();
        }
        return vertex;
    }

    /**
     * Retrieves the {@link model.Vertex Vertex} to place the switch to close
     * the {@link model.Edge Edge} door.
     *
     * @param door {@link model.Edge Edge} to be open by the switch
     * @return The {@link model.Vertex Vertex} where to place the switch
     */
    public Vertex setSwitchOff(Edge door)
    {
        Random rand = new Random();
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
        {
            vertex = door.getSource();
        }
        else
        {
            vertex = door.getTarget();
        }
        i = rand.nextInt((GRID_HEIGHT * GRID_WIDTH) - vertex.getNbr()) + vertex.getNbr();
        for (Iterator<Vertex> it = listeVertex.iterator(); j <= i && it.hasNext(); j++)
        {
            vertex = it.next();
        }
        return vertex;

    }

    /**
     * Set the type of the {@link model.Edge Edge} to CLOSED_DOOR.
     *
     * @param edge the {@link model.Edge Edge} to be changed.
     * @return The modified {@link model.Edge Edge}.
     */
    private Edge closedDoor(Edge edge)
    {
        edge.setType(Edge.Type.CLOSED_DOOR);
        return edge;
    }

    /**
     * Close randomly a door from the graph.
     *
     * @return The {@link model.Edge Edge} containing the recently close door.
     */
    public Edge closeDoorRandom()
    {
        Edge edge = null;
        //Try to get a door that is not already Closed.
        do
        {
            edge = this.randomEdge();
        }
        while (edge.getType() == Type.CLOSED_DOOR);
        return closedDoor(edge);
    }

    /**
     * Retrieves a random {@link model.Edge Edge} from the graph
     *
     * @return The random {@link model.Edge Edge}.
     */
    private Edge randomEdge()
    {
        Random rand = new Random();
        Set<Edge> listeEdge = this.edgeSet();
        int randEdge = rand.nextInt(listeEdge.size() - 1) + 1;
        Iterator<Edge> it = listeEdge.iterator();
        Edge edge = it.next();
        for (int i = 0; i < randEdge; ++i)
        {
            edge = it.next();
        }
        return edge;
    }

    /**
     * Change the type of a random {@link model.Edge Edge}, from the graph, to
     * OPENNED_DOOR.
     */
    public void openDoorRandom()
    {
        int i;
        Random rand = new Random();
        for (i = 0; i < 1000; ++i)
        {
            Vertex source = this.randomVertex();
            if (source != null)
            {
                Directions dir = Directions.values()[rand.nextInt(Directions.values().length)];
                if (isWall(source, dir))
                {
                    Vertex target = this.getVertexByDir(source, dir);
                    if (target != null)
                    {
                        Edge edge = this.getEdge(source, target);
                        if (edge == null)
                        {
                            this.addEdge(source, target, new Edge(Type.OPENED_DOOR));
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves a random {@link model.Vertex Vertex} from the graph.
     *
     * @return The random {@link model.Vertex Vertex}.
     */
    private Vertex randomVertex()
    {
        Random rand = new Random();
        Set<Vertex> listeVertex = this.vertexSet();
        int randVertex = rand.nextInt(listeVertex.size());
        Iterator<Vertex> it = listeVertex.iterator();
        Vertex vertex = it.next();
        for (int i = 0; i < randVertex; ++i)
        {
            vertex = it.next();
        }
        return vertex;
    }

}
