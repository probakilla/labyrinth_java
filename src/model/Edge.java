package model;

import org.jgrapht.graph.DefaultEdge;

/**
 * An implementation of edges in a {@link model.Graph Graph}
 *
 * @author Java Group
 */
public class Edge extends DefaultEdge
{
    public static enum Type
    {
        OPENED_DOOR,
        CLOSED_DOOR,
        CORRIDOR;
    };

    private Type _type;

    /**
     * Create an Edge with a specific type.
     *
     * @param type The {@link model.Edge.Type Type} of the Edge.
     */
    public Edge(Type type)
    {
        super();
        _type = type;
    }

    /**
     * Retrieves the source of this edge.
     *
     * @return Source of this edge.
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
}
