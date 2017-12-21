package model;

import junit.framework.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Java Group
 */
public class CandyTest extends TestCase
{
    private Candy _cherry, _round, _red, _pink;
    private CandyFactory _factory;
    private final int NB_TEST = 100;

    public CandyTest (String name)
    {
        super(name);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    @Override
    public void setUp () throws Exception
    {
        super.setUp();
        _cherry = new Cherry(0, 0);
        _round = new RoundCandy(0, 0);
        _red = new RedCandy(0, 0);
        _pink = new PinkCandy(0, 0);
        _factory = new CandyFactory();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    @Override
    public void tearDown () throws Exception
    {
        super.tearDown();
        _cherry = null;
        _round = null;
        _red = null;
        _pink = null;
        _factory = null;
    }
    
    /**
     * Test method for {@link model.AbstractCandy#AbstractCandy(int,int)}
     */
    @Test
    public void testConstrutors ()
    {
        assertNotNull(_cherry);
        assertNotNull(_round);
        assertNotNull(_red);
        assertNotNull(_pink);
        assertNotNull(_factory);
    }
    
    /**
     * Test method for {@link model.AbstractCandy#getType()}
     */
    @Test
    public void testGetType ()
    {
        assertEquals(10, _cherry.getType());
        assertEquals(5, _round.getType());
        assertEquals(2, _red.getType());
        assertEquals(3, _pink.getType());
    }
    
    
    /**
     * Test method for {@link model.AbstractCandy#getPosition()}
     */
    @Test
    public void testGetPosition ()
    {
        assertEquals(0, _cherry.getPosition().getX());
        assertEquals(0, _round.getPosition().getX());
        assertEquals(0, _red.getPosition().getX());
        assertEquals(0, _pink.getPosition().getX());
        assertEquals(0, _cherry.getPosition().getY());
        assertEquals(0, _round.getPosition().getY());
        assertEquals(0, _red.getPosition().getY());
        assertEquals(0, _pink.getPosition().getY());
    }
    
    /**
     * Test method for {@link model.AbstractCandy#getImgPath()}.
     */
    @Test
    public void testGetImgPath ()
    {
        AbstractCandy cherry = (AbstractCandy) _cherry;
        AbstractCandy round = (AbstractCandy) _round;
        AbstractCandy red = (AbstractCandy) _red;
        AbstractCandy pink = (AbstractCandy) _pink;
        assertEquals("/utils/candy-3.png", cherry.getImgPath());
        assertEquals("/utils/candy-4.png", round.getImgPath());
        assertEquals("/utils/candy-1.png", red.getImgPath());
        assertEquals("/utils/candy-2.png", pink.getImgPath());
    }
}
