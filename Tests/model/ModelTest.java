/**
 * 
 */
package model;

import model.*;

import static org.junit.Assert.*;


import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Java Group
 *
 */
public class ModelTest {

	private Model _model;
	private Vertex _vertex, _target;
	private Graph _graph;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_model = Model.getInstance();
		_graph = Graph.getInstance();
		_vertex = new Vertex(0, 0, 0);
		_target = new Vertex(15, 15, 0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		_model = null;
		_vertex = null;
		_target = null;
		_graph.removeGraph();
	}

	/**
	 * Test method for {@link model.Model#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(_model);
	}

	/**
	 * Test method for {@link model.Model#buildRandomPath(model.Vertex)}.
	 */
	@Test
	public void testBuildRandomPath() {
		_model.buildRandomPath(_vertex);
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
        Set<Vertex> ListVertex = _graph.vertexSet();
        Vertex vertex;
        for ( Iterator<Vertex> it = ListVertex.iterator(); it.hasNext();)
        {
            vertex = it.next();
            vertex.setNbr(0);
        }
        Iterator<Vertex> it = ListVertex.iterator();
        Vertex v = it.next();
        v.setNbr(1);
        fifo.add(v);
        while (!fifo.isEmpty())
        {
            Vertex actual = fifo.remove();
            for (Edge edge : _graph.edgesOf(actual))
            {
                Vertex next = edge.getSource();
               
                if (next.equals(actual))
                {
                    next = edge.getTarget();
                }
                if (next.getNbr() == 0)
                {
                    next.setNbr(1);
                    fifo.add(next);
                }
            }
        }
        for(int i=0; i<_graph.getGRIDHEIGHT(); i++) {
        	for (int j=0; j<_graph.getGRIDWIDTH(); j++) {
        		if (i!=0 && j!=0) {
        			assertNotEquals(0, _graph.getVertex(i, j).getNbr());
        		}
        	}
        }
	}

	/**
	 * Test method for {@link model.Model#launchManhattan(model.Vertex, model.Vertex)}.
	 */
	@Test
	public void testLaunchManhattan() {
		_model.buildRandomPath(_vertex);
		
		_target = _graph.getEndPath();
		_model.launchManhattan(_vertex, _target);

		for(int i=0; i<_graph.getGRIDHEIGHT(); i++) {
        	for (int j=0; j<_graph.getGRIDWIDTH(); j++) { 
        		assertNotEquals(0, _graph.getVertex(i, j).getNbr());
        	}
        }
		assertEquals(1, _target.getNbr());
	}



}
