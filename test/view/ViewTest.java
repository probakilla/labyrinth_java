/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Edge;
import model.Graph;
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
public class ViewTest {
    
    public ViewTest() {
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
     * Test of getInstance method, of class View.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        View expResult = null;
        View result = View.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class View.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage stage = null;
        Graph g = null;
        View instance = null;
        instance.start(stage, g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawFrame method, of class View.
     */
    @Test
    public void testDrawFrame() {
        System.out.println("drawFrame");
        Stage stage = null;
        int nbrX = 0;
        int nbrY = 0;
        View instance = null;
        instance.drawFrame(stage, nbrX, nbrY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawWall method, of class View.
     */
    @Test
    public void testDrawWall() {
        System.out.println("drawWall");
        int xs = 0;
        int ys = 0;
        int xt = 0;
        int yt = 0;
        Paint color = null;
        View instance = null;
        instance.drawWall(xs, ys, xt, yt, color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawGraph method, of class View.
     */
    @Test
    public void testDrawGraph() {
        System.out.println("drawGraph");
        Graph g = null;
        View instance = null;
        instance.drawGraph(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEnnemies method, of class View.
     */
    @Test
    public void testCreateEnnemies() {
        System.out.println("createEnnemies");
        int x = 0;
        int y = 0;
        View instance = null;
        instance.createEnnemies(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCandy method, of class View.
     */
    @Test
    public void testCreateCandy() {
        System.out.println("createCandy");
        int x = 0;
        int y = 0;
        String imgPath = "";
        View instance = null;
        instance.createCandy(x, y, imgPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCandy method, of class View.
     */
    @Test
    public void testRemoveCandy() {
        System.out.println("removeCandy");
        int idx = 0;
        View instance = null;
        instance.removeCandy(idx);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPlayable method, of class View.
     */
    @Test
    public void testCreatePlayable() {
        System.out.println("createPlayable");
        View instance = null;
        instance.createPlayable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDoor method, of class View.
     */
    @Test
    public void testCreateDoor() {
        System.out.println("createDoor");
        int x = 0;
        int y = 0;
        View instance = null;
        instance.createDoor(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEnemyPosition method, of class View.
     */
    @Test
    public void testUpdateEnemyPosition() {
        System.out.println("updateEnemyPosition");
        int i = 0;
        int x = 0;
        int y = 0;
        View instance = null;
        instance.updateEnemyPosition(i, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerPosition method, of class View.
     */
    @Test
    public void testUpdatePlayerPosition() {
        System.out.println("updatePlayerPosition");
        int x = 0;
        int y = 0;
        View instance = null;
        instance.updatePlayerPosition(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSwitchOn method, of class View.
     */
    @Test
    public void testCreateSwitchOn() {
        System.out.println("createSwitchOn");
        int x = 0;
        int y = 0;
        View instance = null;
        instance.createSwitchOn(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSwitchOff method, of class View.
     */
    @Test
    public void testCreateSwitchOff() {
        System.out.println("createSwitchOff");
        int x = 0;
        int y = 0;
        View instance = null;
        instance.createSwitchOff(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScore method, of class View.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        int sc = 0;
        View instance = null;
        instance.setScore(sc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLife method, of class View.
     */
    @Test
    public void testSetLife() {
        System.out.println("setLife");
        int lf = 0;
        View instance = null;
        instance.setLife(lf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndGameText method, of class View.
     */
    @Test
    public void testSetEndGameText() {
        System.out.println("setEndGameText");
        boolean win = false;
        View instance = null;
        instance.setEndGameText(win);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDoor method, of class View.
     */
    @Test
    public void testUpdateDoor() {
        System.out.println("updateDoor");
        Edge door = null;
        Edge.Type status = null;
        View instance = null;
        instance.updateDoor(door, status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printRules method, of class View.
     */
    @Test
    public void testPrintRules() {
        System.out.println("printRules");
        View instance = null;
        instance.printRules();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
