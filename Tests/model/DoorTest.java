/**
 * 
 */
package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.Edge.Type;

/**
 * @author Java Group
 *
 */
public class DoorTest extends TestCase
{

	private Vertex _switchOn, _switchOff;
	private Edge _edge;
	private Door _door;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		super.setUp();
		_switchOn = new Vertex (0, 0);
		_switchOff = new Vertex (0, 0);
		_edge = new Edge(Type.CLOSED_DOOR);
		_door = new Door (_switchOn, _switchOff, _edge);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		_switchOn = null;
		_switchOff = null;
		_edge = null;
		_door = null;
	}

	/**
	 * Test method for {@link model.Door#Door(model.Vertex, model.Vertex, model.Edge)}.
	 */
	@Test
	public void testDoor() 
	{
		assertNotNull(_door);
	}

	/**
	 * Test method for {@link model.Door#getDoor()}.
	 */
	@Test
	public void testGetDoor() 
	{
		assertEquals(_edge, _door.getDoor());
	}

	/**
	 * Test method for {@link model.Door#getSwitchOnPath()}.
	 */
	@Test
	public void testGetSwitchOnPath() 
	{
		assertEquals("/utils/button_open.png", Door.getSwitchOnPath());
	}

	/**
	 * Test method for {@link model.Door#getSwitchOffPath()}.
	 */
	@Test
	public void testGetSwitchOffPath() 
	{
		assertEquals("/utils/button_close.png", Door.getSwitchOffPath());
	}

	/**
	 * Test method for {@link model.Door#getSwitchOn()}.
	 */
	@Test
	public void testGetSwitchOn() 
	{
		assertEquals(_switchOn, _door.getSwitchOn());
	}

	/**
	 * Test method for {@link model.Door#getSwitchOff()}.
	 */
	@Test
	public void testGetSwitchOff() 
	{
		assertEquals(_switchOff, _door.getSwitchOff());
	}

}
