package model;

import org.jgrapht.graph.DefaultEdge;

/**
 * An implementation of edges in a {@link model.Graph Graph}
 *
 * @author Java Group
 */
public class Edge extends DefaultEdge implements Comparable<Edge>
{

    /**
     * Keep the compiler happy.
     */
    private static final long serialVersionUID = 1L;

    public static enum Type
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

    /**
     * Retrieves the source of this edge.
     *
     * @return source of this edge.
     */
    @Override
    public Vertex getSource()
    {
        return (Vertex) super.getSource();
    }

    /**
     * Retrieves the target of this edge.
     *
     * @return target of this edge
     */
    @Override
    public Vertex getTarget()
    {
        return (Vertex) super.getTarget();
    }

    /**
     * Retrieves the type of this edge.
     *
     * @return type of the edge.
     */
    public Type getType()
    {
        return _type;
    }

    /**
     * Set the type of this edge.
     *
     * @param type type of the edge
     */
    public void setType(Type type)
    {
        _type = type;
    }

    @Override
    public int compareTo(Edge o)
    {
        int source = this.getSource().compareTo((o).getSource());
        if (source != 0)
        {
            return source;
        } else
        {
            return this.getTarget().compareTo((o).getTarget());
        }
    }
}
