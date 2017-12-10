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
public class DoorTest {
    
    public DoorTest() {
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
     * Test of getDoor method, of class Door.
     */
    @Test
    public void testGetDoor() {
        System.out.println("getDoor");
        Door instance = null;
        Edge expResult = null;
        Edge result = instance.getDoor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSwitchOnPath method, of class Door.
     */
    @Test
    public void testGetSwitchOnPath() {
        System.out.println("getSwitchOnPath");
        String expResult = "";
        String result = Door.getSwitchOnPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSwitchOffPath method, of class Door.
     */
    @Test
    public void testGetSwitchOffPath() {
        System.out.println("getSwitchOffPath");
        String expResult = "";
        String result = Door.getSwitchOffPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_doorPath method, of class Door.
     */
    @Test
    public void testGet_doorPath() {
        System.out.println("get_doorPath");
        String expResult = "";
        String result = Door.get_doorPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSwitchOn method, of class Door.
     */
    @Test
    public void testGetSwitchOn() {
        System.out.println("getSwitchOn");
        Door instance = null;
        Vertex expResult = null;
        Vertex result = instance.getSwitchOn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSwitchOff method, of class Door.
     */
    @Test
    public void testGetSwitchOff() {
        System.out.println("getSwitchOff");
        Door instance = null;
        Vertex expResult = null;
        Vertex result = instance.getSwitchOff();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
