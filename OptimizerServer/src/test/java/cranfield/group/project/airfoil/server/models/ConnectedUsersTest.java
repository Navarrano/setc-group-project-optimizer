/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.models;

import java.util.Iterator;
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
public class ConnectedUsersTest {
    
    public ConnectedUsersTest() {
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
     * Test of add method, of class ConnectedUsers.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String user = "";
        ConnectedUsers instance = new ConnectedUsers();
        instance.add(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ConnectedUsers.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        String user = "";
        ConnectedUsers instance = new ConnectedUsers();
        instance.remove(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class ConnectedUsers.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String user = "";
        ConnectedUsers instance = new ConnectedUsers();
        boolean expResult = false;
        boolean result = instance.contains(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class ConnectedUsers.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        ConnectedUsers instance = new ConnectedUsers();
        Iterator<String> expResult = null;
        Iterator<String> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
