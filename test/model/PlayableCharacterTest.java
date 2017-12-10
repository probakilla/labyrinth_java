/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillaume
 */
public class PlayableCharacterTest {
    
    public PlayableCharacterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class PlayableCharacter.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PlayableCharacter expResult = null;
        PlayableCharacter result = PlayableCharacter.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLife method, of class PlayableCharacter.
     */
    @Test
    public void testGetLife() {
        System.out.println("getLife");
        PlayableCharacter instance = null;
        int expResult = 0;
        int result = instance.getLife();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decrementLife method, of class PlayableCharacter.
     */
    @Test
    public void testDecrementLife() {
        System.out.println("decrementLife");
        PlayableCharacter instance = null;
        int expResult = 0;
        int result = instance.decrementLife();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImgPath method, of class PlayableCharacter.
     */
    @Test
    public void testGetImgPath() {
        System.out.println("getImgPath");
        String expResult = "";
        String result = PlayableCharacter.getImgPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increaseScore method, of class PlayableCharacter.
     */
    @Test
    public void testIncreaseScore() {
        System.out.println("increaseScore");
        Candy c = null;
        PlayableCharacter instance = null;
        instance.increaseScore(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class PlayableCharacter.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        PlayableCharacter instance = null;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collision method, of class PlayableCharacter.
     */
    @Test
    public void testCollision() {
        System.out.println("collision");
        Vertex v = null;
        PlayableCharacter instance = null;
        boolean expResult = false;
        boolean result = instance.collision(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
