/**
 *
 */
package model;

import java.util.concurrent.CountDownLatch;
import junit.framework.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Java Group
 *
 */
public class EnemyTest extends TestCase
{
    private Enemy _enemy;
    private final int NB_TEST = 100;

    public EnemyTest (String name)
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
        _enemy = new Enemy(new CountDownLatch(0));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    @Override
    public void tearDown () throws Exception
    {
        _enemy = null;
    }
    
    /**
     * Test method for {@link model.Enemy#Enemy(CountDownLatch)}.
     */
    @Test
    public void testEnemy ()
    {
        assertNotNull (_enemy);
    }
    
    
    /**
     * Test method for {@link model.AbstractCharacter#getType()}
     */
    @Test
    public void testGetType ()
    {
        assertEquals(-1, _enemy.getType());
    }

    /**
     * Test method for {@link model.AbstractCharacter#getPosition()}
     */
    @Test
    public void testGetPosition ()
    {
        assertEquals(0, _enemy.getPosition().getX());
        assertEquals(0, _enemy.getPosition().getY());
    }

    /**
     * Test method for {@link model.AbstractCharacter#randomizePostion()}
     */
    @Test
    public void testRandomizePosition ()
    {
        int i;
        for (i = 0; i < NB_TEST; ++i)
        {
            _enemy.randomizePosition();
            assertTrue(_enemy.getPosition().getX() >= 0);
            assertTrue(_enemy.getPosition().getX() < Graph.getGRIDWIDTH());
            assertTrue(_enemy.getPosition().getY() >= 0);
            assertTrue(_enemy.getPosition().getY() < Graph.getGRIDHEIGHT());
        }
    }
    
    /**
     * Test method for {@link model.Enemy#getTargetX(int)}.
     */
    @Test
    public void testGetTargetX ()
    {
        assertEquals(-1, _enemy.getTargetX());
    }

    /**
     * Test method for {@link model.Enemy#getTargetY(int)}.
     */
    @Test
    public void testGetTargetY ()
    {
        assertEquals(-1, _enemy.getTargetY());
    }

    /**
     * Test method for {@link model.Enemy#setTargetX()}.
     */
    @Test
    public void testSetTargetX ()
    {
        int i;
        for (i = 0; i < NB_TEST; ++i)
        {
            _enemy.setTargetX(i);
            assertEquals(i, _enemy.getTargetX());
        }
    }

    /**
     * Test method for {@link model.Enemy#setTargetY()}.
     */
    @Test
    public void testSetTargetY ()
    {
        int i;
        for (i = 0; i < NB_TEST; ++i)
        {
            _enemy.setTargetY(i);
            assertEquals(i, _enemy.getTargetY());
        }
    }

    /**
     * Test method for {@link model.Enemy#getImgPath()}.
     */
    @Test
    public void testGetImgPath ()
    {
        assertEquals("/utils/bad.png", Enemy.getImgPath());
    }

    /**
     * Test method for {@link model.Enemy#getRunning()}.
     */
    @Test
    public void testGetRunning ()
    {
        assertEquals(0, _enemy.getRunning());
    }
    
    /**
     * Test method for {@link model.Enemy#keepRunning()}.
     */
    @Test
    public void testKeepRunning ()
    {
        _enemy.keepRunning();
        assertEquals(1, _enemy.getRunning());
    }
    
    /**
     * Test method for {@link model.Enemy#stopRunning()}.
     */
    @Test
    public void testStopRunning ()
    {
        _enemy.stopRunning();
        assertEquals(0, _enemy.getRunning());        
    }

    /**
     * Test method for {@link model.Enemy#getNextStep()}.
     */
    @Test
    public void testGetNextStep ()
    {
        assertNotNull(_enemy.getNextStep());
    }
}
