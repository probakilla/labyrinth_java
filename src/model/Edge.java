package model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge>
{
	/**
	 * Keep the compiler happy.
	 */
	private static final long serialVersionUID = 1L;

	public enum Type 
	{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	private Type _type;
	
	public Edge(Type type) 
	{
		super();
		_type = type;
	}
	
	public Vertex getSource() 
	{
		return (Vertex) super.getSource();
	}
	
	public Vertex getTarget() 
	{
		return (Vertex) super.getTarget();
	}
	
	public Type getType()
	{
		return _type;
	}
	
	public void setType(Type type) 
	{
		_type = type;
	}

	@Override
	public int compareTo(Edge o)
	{
		int source = this.getSource().compareTo((o).getSource());
		if (source!=0)
			return source;
		else 
		{
			return this.getTarget().compareTo((o).getTarget());
		}
	}
}