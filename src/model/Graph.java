package model;

import org.jgrapht.graph.SimpleGraph;

import model.Edge.Type;

public class Graph extends SimpleGraph<Vertex, Edge>
{
	private static final long serialVersionUID = 1L;
	
	private Edge _edge;
	private Vertex [][] _vertex;
	private int GRID_WIDTH = 16;
	private int GRID_HEIGHT = 16; 

	
	public Graph () 
	{
		super (Edge.class);
		_vertex = new Vertex [GRID_WIDTH][GRID_HEIGHT];
		int i, j;
		for (i = 0; i < GRID_HEIGHT; ++i)
			for (j = 0; j < GRID_WIDTH; ++j)
				_vertex [j][i] = new Vertex (j, i, i + j);
		_edge = new Edge (Type.CORRIDOR);
	}
	
	public int getGRIDWIDTH() 
	{
		return GRID_WIDTH;
	}

	public int getGRIDHEIGHT() 
	{
		return GRID_HEIGHT;
	}
	
	public void buildGraph ()
	{
		this.addEdge(_vertex[0][0], _vertex[0][1]);
	}
	
	public boolean doesntExist (Vertex v, Model.Directions dir) {
		return false;
	}
}