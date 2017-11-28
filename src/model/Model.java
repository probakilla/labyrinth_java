package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
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
    };

    private AtomicInteger _iteration;
    private Random _random;
    private Graph _graph;

    private Model ()
    {
        _iteration = new AtomicInteger(1);
        _graph = new Graph();
        _random = new Random();
    }

    private static Model INSTANCE;

    /**
     * Retrieves an instance of the Model.
     *
     * Retrieves the instance of the Model, there can be only one instance of the
     * Model at once thanks to the singleton design pattern.
     *
     * @return An unique instance of Model.
     */
    public static Model getInstance ()
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
    public void buildRandomPath (Vertex vertex)
    {
        _graph.addVertex(vertex);
        //une liste aleatoire des 4 directions	
        Vector<Directions> v = new Vector<Directions>();
        for (int i = 0; i < 4; ++i)
        {
            v.add(Directions.values()[i]);
        }

        Directions directions[] = new Directions[4];
        int index;
        for (int i = 0; i < directions.length; ++i)
        {
            index = _random.nextInt(v.size());
            directions[i] = v.get(index);
            v.remove(index);
        }
        // pour chacune de ces directions, on avance en profondeur d’abord
        for (int i = 0; i < 4; ++i)
        {
            Directions dir = directions[i];
            if (vertex.inBorders(dir, Graph.getGRIDWIDTH(), Graph.getGRIDHEIGHT()) && _graph.doesntExist(vertex, dir))
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

                Vertex next = new Vertex(xt, yt, _iteration.incrementAndGet());
                _graph.addVertex(next);
                _graph.addEdge(vertex, next, new Edge(Type.CORRIDOR));
                buildRandomPath(next);
            }
        }
    }

    /**
     * Return the graph used in Model.
     *
     * @return graph
     */
    public Graph getGraph ()
    {
        return _graph;
    }
}
