package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sound.sampled.BooleanControl;

import model.Edge.Type;
import model.Model.Directions;

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

	private static final boolean Boolean = false;;

    private final AtomicInteger _iteration;
    private final Random _random;
    private final Graph _graph;
    private int _cycle;

    private Model ()
    {
        _iteration = new AtomicInteger(1);
        _graph = Graph.getInstance();
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
    	
    	Vertex vertex1 = null;
    	_cycle = 0;
    	
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

                Vertex next = _graph.getVertex(xt, yt);
                next.setNbr(_iteration.incrementAndGet());
                _graph.addVertex(next);
                _graph.addEdge(vertex, next, new Edge(Type.CORRIDOR));
                buildRandomPath(next);
            }
        }
    }

    private void calculateManhattanDistance (Vertex source, Vertex target)
    {
        Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        target.setNbr(1);
        fifo.add(target);
        while (!fifo.isEmpty())
        {
            Vertex actual = fifo.remove();
            for (Directions dir : Directions.values())
            {
                if (_graph.isOpenedDoor(actual, dir))
                {
                    Vertex next = _graph.getVertexByDir(actual, dir);
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


    public void launchManhattan (Vertex source, Vertex target)
    {
        for (Vertex vertex : _graph.vertexSet())
        {
            vertex.setNbr(0);
        }
        System.out.println(_graph.getVertex(15, 15).getNbr());
        calculateManhattanDistance(source, target);
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

	public void buildCycleV(int nbCycle) 
	{
		Random rand = new Random();
		int xsimple = rand.nextInt(15-1)+1;
		int ysimple = rand.nextInt(14-1)+1;
		for (int i=0; i<nbCycle; i++) {
			
			while(!_graph.addEdge(_graph.getVertex(xsimple, ysimple), _graph.getVertex(xsimple, ysimple+1), new Edge(Type.OPENED_DOOR)))
			{
				xsimple = rand.nextInt(15-1)+1;
				ysimple = rand.nextInt(14-1)+1;		
			}
			xsimple = rand.nextInt(15-1)+1;
			ysimple = rand.nextInt(14-1)+1;
			//System.out.println(_graph.getVertex(xsimple, ysimple).toString());
		}
	}
		
	public void buildCycleH(int nbCycle) {
		Random rand = new Random();
		int xsimple = rand.nextInt(14-1)+1;
		int ysimple = rand.nextInt(15-1)+1;
		
		for (int i=0; i<nbCycle; i++) 
		{
			//Si il n'a pas put rajouter l'arrête on retente avec d'autre coordonnées jusqu'a ce qu'il y arrive.
			while(!_graph.addEdge(_graph.getVertex(xsimple, ysimple), _graph.getVertex(xsimple+1, ysimple), new Edge(Type.OPENED_DOOR)))
			{
				xsimple = rand.nextInt(14-1)+1;
				ysimple = rand.nextInt(15-1)+1;
			
			}
			xsimple = rand.nextInt(14-1)+1;
			ysimple = rand.nextInt(15-1)+1;
			System.out.println(_graph.getVertex(xsimple, ysimple).toString());

		}  	
	}
}
