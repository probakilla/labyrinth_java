package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import model.Edge.Type;

/**
 * Class used to do operation on {@link model.Graph Graphs}.
 *
 * @author Java Group
 */
public class Model
{
    public static enum Directions
    {
        EAST, WEST, NORTH, SOUTH;
    }

    private final AtomicInteger _iteration;
    private final Random _random;

    private Model()
    {
        _iteration = new AtomicInteger(1);
        _random = new Random();
    }

    private static Model INSTANCE;

    /**
     * Retrieves an instance of the Model.
     *
     * Retrieves the instance of the Model, there can be only one instance of
     * the Model at once thanks to the singleton design pattern.
     *
     * @return An unique instance of Model.
     */
    public static Model getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Model();
        }
        return INSTANCE;
    }

    /**
     * Randomly create a {@link model.Graph Graphs}.
     *
     * @param vertex the beginning of the {@link model.Graph Graphs}.
     */
    public void buildRandomPath(Vertex vertex)
    {
        Graph graph = Graph.getInstance();
        graph.addVertex(vertex);
        // Une liste aleatoire des 4 directions.
        Vector<Directions> v = new Vector<Directions>();
        for (int i = 0; i < 4; ++i)
            v.add(Directions.values()[i]);

        Directions directions[] = new Directions[4];
        int index;
        for (int i = 0; i < directions.length; ++i)
        {
            index = _random.nextInt(v.size());
            directions[i] = v.get(index);
            v.remove(index);
        }
        // Pour chacune de ces directions, on avance en profondeur d’abord.
        for (int i = 0; i < 4; ++i)
        {
            Directions dir = directions[i];
            if (vertex.inBorders(dir, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT()) && graph.doesntExist(vertex, dir))
            {
                int x = vertex.getX();
                int y = vertex.getY();
                int xt = 0, yt = 0;
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

                Vertex next = graph.getVertex(xt, yt);
                next.setNbr(_iteration.incrementAndGet());
                graph.addVertex(next);
                graph.addEdge(vertex, next, new Edge(Type.CORRIDOR));
                buildRandomPath(next);
            }
        }
    }
    
    /**
     * Construct a path from source to target according to the Manhattan algorithm, and update the value of each vertex.
     * 
     * @param source the vertex which we launch the algorithm.
     * @param target the vertex which we want to reach.
     */
    private void calculateManhattanDistance(Vertex source, Vertex target)
    {
        Graph graph = Graph.getInstance();
        Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        target.setNbr(1);
        fifo.add(target);
        while (!fifo.isEmpty())
        {
            Vertex actual = fifo.remove();
            for (Directions dir : Directions.values())
            {
                if (graph.isOpenedDoor(actual, dir))
                {
                    Vertex next = graph.getVertexByDir(actual, dir);
                    if (next.getNbr() == 0)
                    {
                        next.setNbr(actual.getNbr() + 1);
                        if (next != source)
                        {
                            fifo.add(next);
                        }
                    }
                }
            }
        }
    }

    /**
     * Method used to launch the algorithm used to order the {@link model.Enemy Enemies}
     * to follow the {@link model.PlayableCharacter Player}.
     * 
     * @param source The {@link model.Vertex Vertex} of the character who will 
     * follow.
     * @param target The {@link model.Vertex Vertex} of the character to follow.
     */
    public void launchManhattan(Vertex source, Vertex target)
    {
        Graph graph = Graph.getInstance();
        for (Vertex vertex : graph.vertexSet())
            vertex.setNbr(0);
        calculateManhattanDistance(source, target);
    }
}
