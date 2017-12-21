/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Edge.Type;
import model.Model.Directions;

/**
 * @author Java Group
 *
 */
public class GraphTest 
{
	private Graph _graph;
	private Vertex _vertex, _vertexBis, _vertexEnd;
	private Edge _edge, _edgeBis;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		_graph = Graph.getInstance();
		_vertex =_graph.getVertex(0, 0);
		_vertexBis = _graph.getVertex(1, 0);
		_vertexEnd = _graph.getVertex(3, 0);
		_edge = new Edge(Type.CORRIDOR);
		_edgeBis = new Edge(Type.CORRIDOR);
		_graph.addVertex(_vertex);
		_graph.addVertex(_vertexBis);
		_graph.addEdge(_vertex, _vertexBis, _edge);
		_graph.addVertex(_vertexEnd);
		_graph.addEdge(_vertexBis, _vertexEnd, _edgeBis);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		_graph.removeGraph();
		_graph = null;
		_vertex = null;
		_vertexBis = null;
		_edge = null;
	}

	/**
	 * Test method for {@link model.Graph#getInstance()}.
	 */
	@Test
	public void testGetInstance() 
	{
		assertNotNull(_graph);
	}

	/**
	 * Test method for {@link model.Graph#getGRIDWIDTH()}.
	 */
	@Test
	public void testGetGRIDWIDTH() 
	{
		assertEquals(16, Graph.getGRIDWIDTH());
	}

	/**
	 * Test method for {@link model.Graph#getGRIDHEIGHT()}.
	 */
	@Test
	public void testGetGRIDHEIGHT() 
	{
		assertEquals(16, Graph.getGRIDHEIGHT());
	}

	/**
	 * Test method for {@link model.Graph#doesntExist(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testDoesntExistVertex()
	{
		assertTrue(_graph.doesntExist(_vertex, Directions.EAST));
		assertFalse(_graph.doesntExist(_vertex, Directions.EAST));
	}

	/**
	 * Test method for {@link model.Graph#getVertex(int, int)}.
	 */
	@Test
	public void testGetVertex() 
	{
		assertEquals(_vertex, _graph.getVertex(0, 0));
	}

	/**
	 * Test method for {@link model.Graph#getVertexByDir(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testGetVertexByDir() 
	{
		assertEquals(_graph.getVertex(1, 0), _graph.getVertexByDir(_vertex, Directions.EAST));
		assertNotEquals(_graph.getVertex(2, 0), _graph.getVertexByDir(_vertex, Directions.EAST));
		_graph.addVertex(_graph.getVertex(2, 0));
		assertNotEquals(_graph.getVertex(2, 0), _graph.getVertexByDir(_vertex, Directions.EAST));
		assertNotEquals(_graph.getVertex(1, 0), _graph.getVertexByDir(_vertex, Directions.NORTH));
	}

	/**
	 * Test method for {@link model.Graph#getEndPath()}.
	 */
	@Test
	public void testGetEndPath() 
	{
		assertNotEquals(_vertexBis ,_graph.getEndPath());
		assertEquals(_vertexEnd ,_graph.getEndPath());
	}

	/**
	 * Test method for {@link model.Graph#isWall(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testIsWall() 
	{
		assertTrue(_graph.isWall(_vertexBis, Directions.EAST));
		assertFalse(_graph.isWall(_vertexBis, Directions.WEST));
	}

	/**
	 * Test method for {@link model.Graph#getEdgeByDir(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testGetEdgeByDir()
	{
		assertEquals(_edge, _graph.getEdgeByDir(_vertex, Directions.EAST));
	}

	/**
	 * Test method for {@link model.Graph#isOpenedDoor(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testIsOpenedDoor() 
	{
		assertTrue(_graph.isOpenedDoor(_vertex, Directions.EAST));
		assertFalse(_graph.isOpenedDoor(_vertex, Directions.WEST));
	}

	/**
	 * Test method for {@link model.Graph#isClosedDoor(model.Vertex, model.Model.Directions)}.
	 */
	@Test
	public void testIsClosedDoor() 
	{
		assertFalse(_graph.isClosedDoor(_vertex, Directions.EAST));
	}

	/**
	 * Test method for {@link model.Graph#setSwitchOn(model.Edge)}.
	 */
	@Test
	public void testSetSwitchOn() 
	{
		assertNotNull(_graph.setSwitchOn(_edgeBis));
	}

	/**
	 * Test method for {@link model.Graph#setSwitchOff(model.Edge)}.
	 */
	@Test
	public void testSetSwitchOff() 
	{
		assertNotNull(_graph.setSwitchOff(_edge));
	}

	/**
	 * Test method for {@link model.Graph#closeDoorRandom()}.
	 */
	@Test
	public void testCloseDoorRandom() 
	{
		assertEquals(Type.CLOSED_DOOR, _graph.closeDoorRandom().getType());
	}

}
