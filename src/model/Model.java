package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

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

    private int _iteration;
    private Random _random;
    private Graph _graph;

    private Model()
    {
        _iteration = 0;
    }

    private static Model INSTANCE;

    /**
     * Retrives an instance of the Model.
     * 
     * Retrives the instance of the Model, there can be only one instance
     * of the Model at once thanks to the singleton design pattern.
     * @return An unique instance of Model.
     */
    public static Model getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new Model();
        return INSTANCE;
    }

    /**
     * Randomly create a {@link model.Graph Graphs}.
     *
     * @param vertex the beginning of the {@link model.Graph Graphs}.
     */
    public void buildRandomPath(Vertex vertex)
    {
        //une liste aleatoire des 4 directions	
        Vector<Directions> v = new Vector<Directions>();
        for (int i = 0; i < 4; ++i)
        {
            v.add(Directions.values()[i]);
        }

        Directions directions[] = new Directions[4];
        int index;
        Random random = new Random();
        for (int i = 0; i < directions.length; ++i)
        {
            index = random.nextInt(v.size());
            directions[i] = v.get(index);
            v.remove(index);
        }
        // pour chacune de ces directions, on avance en profondeur d’abord
        for (int i = 0; i < 4; ++i)
        {
            Directions dir = directions[i];
            if (vertex.inBorders(dir, _graph.getGRIDWIDTH(), _graph.getGRIDHEIGHT()) && _graph.doesntExist(vertex, dir))
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
                Vertex next = new Vertex(xt, yt, _iteration++);
                _graph.addVertex(next);
                _graph.addEdge(vertex, next, new Edge(Type.CORRIDOR));
                buildRandomPath(next);
            }
        }
    }

    /**
     * Write the graph in a .dot file.
     * 
     * This method writes the {@link model.Graph Graph} in a .dot file in order 
     * to display it with graphviz.
     */
    public void GraphToDot()
    {
        Vertex v = new Vertex(0, 0, _iteration++);
        _graph = new Graph();
        _graph.addVertex(v);
        buildRandomPath(v);
        PrintWriter writer;
        try
        {
            writer = new PrintWriter("graph.dot");
            writer.println("graph path {");
            Set<Vertex> v1 = _graph.vertexSet();
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
                    if (_graph.containsEdge(from, to))
                    {
                        writer.print(from.getNbr());
                        writer.print(" -- ");
                        writer.println(to.getNbr());
                    }

                }
            }
            writer.println("}");
            writer.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
