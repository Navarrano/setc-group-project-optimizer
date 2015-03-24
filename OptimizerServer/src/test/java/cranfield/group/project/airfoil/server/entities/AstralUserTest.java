/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.entities;

import java.sql.Timestamp;
import java.util.List;
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
public class AstralUserTest {
    
    public AstralUserTest() {
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
     * Test of getId method, of class AstralUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AstralUser instance = new AstralUser();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AstralUser.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        AstralUser instance = new AstralUser();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class AstralUser.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        AstralUser instance = new AstralUser();
        String expResult = "";
        String result = instance.getLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class AstralUser.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        AstralUser instance = new AstralUser();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLast_connection_date method, of class AstralUser.
     */
    @Test
    public void testGetLast_connection_date() {
        System.out.println("getLast_connection_date");
        AstralUser instance = new AstralUser();
        Timestamp expResult = null;
        Timestamp result = instance.getLast_connection_date();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLast_connection_date method, of class AstralUser.
     */
    @Test
    public void testSetLast_connection_date() {
        System.out.println("setLast_connection_date");
        Timestamp last_connection_date = null;
        AstralUser instance = new AstralUser();
        instance.setLast_connection_date(last_connection_date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNb_connections method, of class AstralUser.
     */
    @Test
    public void testGetNb_connections() {
        System.out.println("getNb_connections");
        AstralUser instance = new AstralUser();
        int expResult = 0;
        int result = instance.getNb_connections();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNb_connections method, of class AstralUser.
     */
    @Test
    public void testSetNb_connections() {
        System.out.println("setNb_connections");
        int nb_connections = 0;
        AstralUser instance = new AstralUser();
        instance.setNb_connections(nb_connections);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorkflows method, of class AstralUser.
     */
    @Test
    public void testGetWorkflows() {
        System.out.println("getWorkflows");
        AstralUser instance = new AstralUser();
        List<Workflow> expResult = null;
        List<Workflow> result = instance.getWorkflows();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWorkflows method, of class AstralUser.
     */
    @Test
    public void testSetWorkflows() {
        System.out.println("setWorkflows");
        List<Workflow> workflows = null;
        AstralUser instance = new AstralUser();
        instance.setWorkflows(workflows);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNextWorkflow method, of class AstralUser.
     */
    @Test
    public void testSetNextWorkflow() {
        System.out.println("setNextWorkflow");
        Workflow workflow = null;
        AstralUser instance = new AstralUser();
        instance.setNextWorkflow(workflow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
