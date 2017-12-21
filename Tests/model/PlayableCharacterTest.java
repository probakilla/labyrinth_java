package model;

import junit.framework.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Java Group
 *
 */
public class PlayableCharacterTest extends TestCase
{
    private PlayableCharacter _character;
    private final int NB_TEST = 100;

    public PlayableCharacterTest (String name)
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
        _character = PlayableCharacter.getInstance();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    @Override
    public void tearDown () throws Exception
    {
        super.tearDown();
        _character = null;
    }

    /**
     * Test method for {@link model.PlayableCharacter#getInstance()}
     */
    @Test
    public void testPlayableCharacter ()
    {
        assertNotNull(_character);
    }

    /**
     * Test method for {@link model.AbstractCharacter#getType()}
     */
    @Test
    public void testGetType ()
    {
        assertEquals(1, _character.getType());
    }

    /**
     * Test method for {@link model.AbstractCharacter#getPosition()}
     */
    @Test
    public void testGetPosition ()
    {
        assertEquals(0, _character.getPosition().getX());
        assertEquals(0, _character.getPosition().getY());
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
            _character.randomizePosition();
            assertTrue(_character.getPosition().getX() >= 0);
            assertTrue(_character.getPosition().getX() < Graph.getGRIDWIDTH());
            assertTrue(_character.getPosition().getY() >= 0);
            assertTrue(_character.getPosition().getY() < Graph.getGRIDHEIGHT());
        }
    }

    /**
     * Test method for {@link model.PlayableCharacter#getLife()}.
     */
    @Test
    public void testGetLife ()
    {
        assertEquals(3, PlayableCharacter.getLife());
    }

    /**
     * Test method for {@link model.PlayableCharacter#setLife(int)}.
     */
    @Test
    public void testSetLife ()
    {
        int i;
        for (i = 0; i < NB_TEST; ++i)
        {
            PlayableCharacter.setLife(i);
            assertEquals(i, PlayableCharacter.getLife());
        }
    }

    /**
     * Test method for {@link model.PlayableCharacter#decrementLife()}.
     */
    @Test
    public void testDecrementLife ()
    {
        PlayableCharacter.setLife(3);
        _character.decrementLife();
        assertEquals(2, PlayableCharacter.getLife());
    }

    /**
     * Test method for {@link model.PlayableCharacter#getImgPath()}.
     */
    @Test
    public void testGetImgPath ()
    {
        assertEquals("/utils/player.png", PlayableCharacter.getImgPath());
    }

    /**
     * Test method for {@link model.PlayableCharacter#getScore()}.
     */
    @Test
    public void testGetScore ()
    {
        assertEquals(0, _character.getScore());
    }

    /**
     * Test method for
     * {@link model.PlayableCharacter#increaseScore(model.Candy)}.
     */
    @Test
    public void testIncreaseScore ()
    {
        Candy candy = new Cherry(0, 0);
        _character.increaseScore(candy);
        assertEquals(10, _character.getScore());
        
        candy = new RoundCandy(0, 0);
        _character.increaseScore(candy);
        assertEquals(15, _character.getScore());
        
        candy = new RedCandy(0, 0);
        _character.increaseScore(candy);
        assertEquals(17, _character.getScore());
        
        candy = new PinkCandy(0, 0);
        _character.increaseScore(candy);
        assertEquals(20, _character.getScore());
    }

    /**
     * Test method for {@link model.PlayableCharacter#collision(model.Vertex)}.
     */
    @Test
    public void testCollision ()
    {
        _character.setPosition(0, 0);
        Vertex v = new Vertex(0, 0);
        assertTrue(_character.collision(v));
    }

}
