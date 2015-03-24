/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kat
 */
public class LogsTest {
    
    public LogsTest() {
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
     * Test of getId method, of class Logs.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Logs instance = new Logs();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Logs.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Logs instance = new Logs();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class Logs.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Logs instance = new Logs();
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class Logs.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "";
        Logs instance = new Logs();
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogType method, of class Logs.
     */
    @Test
    public void testGetLogType() {
        System.out.println("getLogType");
        Logs instance = new Logs();
        String expResult = "";
        String result = instance.getLogType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogType method, of class Logs.
     */
    @Test
    public void testSetLogType() {
        System.out.println("setLogType");
        String logType = "";
        Logs instance = new Logs();
        instance.setLogType(logType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogSubject method, of class Logs.
     */
    @Test
    public void testGetLogSubject() {
        System.out.println("getLogSubject");
        Logs instance = new Logs();
        String expResult = "";
        String result = instance.getLogSubject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogSubject method, of class Logs.
     */
    @Test
    public void testSetLogSubject() {
        System.out.println("setLogSubject");
        String logSubject = "";
        Logs instance = new Logs();
        instance.setLogSubject(logSubject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
