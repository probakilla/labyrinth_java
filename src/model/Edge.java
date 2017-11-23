package model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge>
{
	public enum Type
	{
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	private Type type;
	
	public Edge(Type type)
	{
		super();
		this.type = type;
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
		return type;
	}
	
	public void setType(Type type) 
	{
		this.type = type;
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
