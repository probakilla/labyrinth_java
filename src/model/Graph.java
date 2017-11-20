package model;

import org.jgrapht.graph.SimpleGraph;

public class Graph extends SimpleGraph<Vertex, Edge>
{
	// On ne sait pas ce que c'est
	private static final long serialVersionUID = 1L;
	
	private Edge _edge;
	private Vertex [][] _vertex;
	private static int GRID_WIDTH = 16;
	private static int GRID_HEIGHT = 16; 

	public Graph () 
	{
		super (Edge.class);
		_vertex = new Vertex [GRID_WIDTH][GRID_HEIGHT];
		int i, j;
		for (i = 0; i < GRID_HEIGHT; ++i)
			for (j = 0; j < GRID_WIDTH; ++j)
				_vertex [j][i] = new Vertex (j, i);
	}
	
	public void buildGraph ()
	{
		this.addEdge(_vertex[0][0], _vertex[0][1]);
	}
}