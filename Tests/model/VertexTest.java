/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.scene.traversal.Direction;

import model.Model.Directions;

/**
 * @author Java Group
 *
 */
public class VertexTest {

	Vertex _vertexEmpty;
	Vertex _vertexNb;
	Vertex _vertex;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		_vertexEmpty = new Vertex();
		_vertex = new Vertex (0, 0);
		_vertexNb = new Vertex (0, 0, 0);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		_vertexEmpty  = null;
		_vertex = null;
		_vertexNb = null;
	}

	/**
	 * Test method for {@link model.Vertex#Vertex()}.
	 */
	@Test
	public void testVertex() 
	{
		assertNotNull(_vertexEmpty);
	}

	/**
	 * Test method for {@link model.Vertex#Vertex(int, int)}.
	 */
	@Test
	public void testVertexIntInt()
	{
		assertNotNull(_vertex);
	}

	/**
	 * Test method for {@link model.Vertex#setX(int)}.
	 */
	@Test
	public void testSetX() 
	{
		_vertexEmpty.setX(10);
		assertEquals(10, _vertexEmpty.getX());
	}

	/**
	 * Test method for {@link model.Vertex#setY(int)}.
	 */
	@Test
	public void testSetY() 
	{
		_vertexEmpty.setY(100);
		assertEquals(100, _vertexEmpty.getY());
	}

	/**
	 * Test method for {@link model.Vertex#Vertex(int, int, int)}.
	 */
	@Test
	public void testVertexIntIntInt() 
	{
		assertNotNull(_vertexNb);
	}

	/**
	 * Test method for {@link model.Vertex#getNbr()}.
	 */
	@Test
	public void testGetNbr() 
	{
		assertEquals(0, _vertexNb.getNbr());
	}

	/**
	 * Test method for {@link model.Vertex#setNbr(int)}.
	 */
	@Test
	public void testSetNbr() 
	{
		_vertexNb.setNbr(42);
		assertEquals(42, _vertexNb.getNbr());
	}

	/**
	 * Test method for {@link model.Vertex#getX()}.
	 */
	@Test
	public void testGetX() 
	{
		assertEquals(0, _vertex.getX());
	}

	/**
	 * Test method for {@link model.Vertex#getY()}.
	 */
	@Test
	public void testGetY() 
	{
		assertEquals(0, _vertex.getY());
	}

	/**
	 * Test method for {@link model.Vertex#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() 
	{
		_vertexEmpty.setX(69);
		assertNotEquals(_vertex, _vertexEmpty);
		_vertexEmpty.copy(_vertex);
		assertEquals(_vertex, _vertexEmpty);
	}

	/**
	 * Test method for {@link model.Vertex#inBorders(model.Model.Directions, int, int)}.
	 */
	@Test
	public void testInBorders() 
	{
		assertFalse(_vertex.inBorders(Directions.NORTH, 16, 16));
		assertTrue(_vertex.inBorders(Directions.SOUTH, 16, 16));
	}

}
