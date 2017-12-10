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
public class VertexTest {
    
    public VertexTest() {
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
     * Test of setX method, of class Vertex.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 0;
        Vertex instance = new Vertex();
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Vertex.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        int y = 0;
        Vertex instance = new Vertex();
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNbr method, of class Vertex.
     */
    @Test
    public void testGetNbr() {
        System.out.println("getNbr");
        Vertex instance = new Vertex();
        int expResult = 0;
        int result = instance.getNbr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNbr method, of class Vertex.
     */
    @Test
    public void testSetNbr() {
        System.out.println("setNbr");
        int nbr = 0;
        Vertex instance = new Vertex();
        instance.setNbr(nbr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getX method, of class Vertex.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Vertex instance = new Vertex();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Vertex.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Vertex instance = new Vertex();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Vertex.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Vertex v = null;
        Vertex instance = new Vertex();
        int expResult = 0;
        int result = instance.compareTo(v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Vertex instance = new Vertex();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Vertex.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vertex instance = new Vertex();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inBorders method, of class Vertex.
     */
    @Test
    public void testInBorders() {
        System.out.println("inBorders");
        Model.Directions dir = null;
        int width = 0;
        int height = 0;
        Vertex instance = new Vertex();
        boolean expResult = false;
        boolean result = instance.inBorders(dir, width, height);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Vertex.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vertex instance = new Vertex();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class Vertex.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Object o = null;
        Vertex instance = new Vertex();
        instance.copy(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
