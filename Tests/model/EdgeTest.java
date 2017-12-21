/**
 * 
 */
package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Edge.Type;

/**
 * @author Java Group
 *
 */
public class EdgeTest 
{
	private Edge _corridor;
	private Edge _closedDoor;
	private Edge _openedDoor;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		_corridor = new Edge (Type.CORRIDOR);
		_closedDoor = new Edge (Type.CLOSED_DOOR);
		_openedDoor = new Edge (Type.OPENED_DOOR);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		_corridor = null;
		_closedDoor = null;
		_openedDoor = null;
	}

	/**
	 * Test method for {@link model.Edge#Edge(model.Edge.Type)}.
	 */
	@Test
	public void testEdge() 
	{
		assertNotNull(_corridor);
		assertNotNull(_closedDoor);
		assertNotNull(_openedDoor);
		
	}

	/**
	 * Test method for {@link model.Edge#getType()}.
	 */
	@Test
	public void testGetType() 
	{
		assertEquals(Type.CORRIDOR, _corridor.getType());
		assertEquals(Type.CLOSED_DOOR, _closedDoor.getType());
		assertEquals(Type.OPENED_DOOR, _openedDoor.getType());
	}

	/**
	 * Test method for {@link model.Edge#setType(model.Edge.Type)}.
	 */
	@Test
	public void testSetType() 
	{
		_closedDoor.setType(Type.CORRIDOR);
		assertEquals(Type.CORRIDOR, _closedDoor.getType());
		_closedDoor.setType(Type.OPENED_DOOR);
		assertEquals(Type.OPENED_DOOR, _closedDoor.getType());
		_openedDoor.setType(Type.CLOSED_DOOR);
		assertEquals(Type.CLOSED_DOOR,_openedDoor.getType());
	}
}
