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
public class EnemyTest {
    
    public EnemyTest() {
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
     * Test of get_targetX method, of class Enemy.
     */
    @Test
    public void testGet_targetX() {
        System.out.println("get_targetX");
        Enemy instance = new Enemy();
        int expResult = 0;
        int result = instance.get_targetX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_targetX method, of class Enemy.
     */
    @Test
    public void testSet_targetX() {
        System.out.println("set_targetX");
        int _target = 0;
        Enemy instance = new Enemy();
        instance.set_targetX(_target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_targetY method, of class Enemy.
     */
    @Test
    public void testGet_targetY() {
        System.out.println("get_targetY");
        Enemy instance = new Enemy();
        int expResult = 0;
        int result = instance.get_targetY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_targetY method, of class Enemy.
     */
    @Test
    public void testSet_targetY() {
        System.out.println("set_targetY");
        int _target = 0;
        Enemy instance = new Enemy();
        instance.set_targetY(_target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImgPath method, of class Enemy.
     */
    @Test
    public void testGetImgPath() {
        System.out.println("getImgPath");
        String expResult = "";
        String result = Enemy.getImgPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopRunning method, of class Enemy.
     */
    @Test
    public void testStopRunning() {
        System.out.println("stopRunning");
        Enemy instance = new Enemy();
        instance.stopRunning();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextStep method, of class Enemy.
     */
    @Test
    public void testGetNextStep() {
        System.out.println("getNextStep");
        Enemy instance = new Enemy();
        Model.Directions expResult = null;
        Model.Directions result = instance.getNextStep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Enemy.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Enemy instance = new Enemy();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
