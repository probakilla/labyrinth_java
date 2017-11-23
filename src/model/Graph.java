package model;

import org.jgrapht.graph.SimpleGraph;

import model.Edge.Type;
import model.Model.Directions;

public class Graph extends SimpleGraph<Vertex, Edge>
{
	private static final long serialVersionUID = 1L;
	
	private Edge [][] _edge;
	private Vertex [][] _vertex;
	private int GRID_WIDTH = 16;
	private int GRID_HEIGHT = 16; 

	
	public Graph () {
		super (Edge.class);
		_vertex = new Vertex [GRID_WIDTH][GRID_HEIGHT];
		int i, j;
		for (i = 0; i < GRID_HEIGHT; ++i)
			for (j = 0; j < GRID_WIDTH; ++j)
				_vertex [j][i] = new Vertex (j, i, i + j);
		_edge = new Edge[GRID_WIDTH][GRID_HEIGHT];
	}
	
	public int getGRIDWIDTH() {
		return GRID_WIDTH;
	}

	public int getGRIDHEIGHT() {
		return GRID_HEIGHT;
	}
	
	public void buildGraph () {
		this.addEdge(_vertex[0][0], _vertex[0][1]);
	}
	
	
/**
 * Verifie l'existence du vertex et de la direction
 * @param v vertex à vérifier
 * @param dir direction à verifier
 * @return true si les deux éxistent sinon false
 */
	public boolean doesntExist (Vertex v, Model.Directions dir) {
		if (dir == Directions.EAST || dir == Directions.SOUTH || dir == Directions.SOUTH || dir == Directions.NORTH) {
			int i, j;
			for (i = 0; i < GRID_HEIGHT; ++i)
				for (j = 0; j < GRID_WIDTH; ++j)
					if (v.compareTo(_vertex[j][i]) == 0)
						return true;
			return false;
		}
		return false;
	}
}