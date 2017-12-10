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
public class ModelTest {
    
    public ModelTest() {
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
     * Test of getInstance method, of class Model.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Model expResult = null;
        Model result = Model.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildRandomPath method, of class Model.
     */
    @Test
    public void testBuildRandomPath() {
        System.out.println("buildRandomPath");
        Vertex vertex = null;
        Model instance = null;
        instance.buildRandomPath(vertex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of launchManhattan method, of class Model.
     */
    @Test
    public void testLaunchManhattan() {
        System.out.println("launchManhattan");
        Vertex source = null;
        Vertex target = null;
        Model instance = null;
        instance.launchManhattan(source, target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGraph method, of class Model.
     */
    @Test
    public void testGetGraph() {
        System.out.println("getGraph");
        Model instance = null;
        Graph expResult = null;
        Graph result = instance.getGraph();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
