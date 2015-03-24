/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.Workflow;
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
public class WorkflowCRUDServiceTest {
    
    public WorkflowCRUDServiceTest() {
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
     * Test of addWorkflow method, of class WorkflowCRUDService.
     */
    @Test
    public void testAddWorkflow() {
        System.out.println("addWorkflow");
        Workflow obj = null;
        WorkflowCRUDService instance = new WorkflowCRUDService();
        instance.addWorkflow(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allUsersWorkflows method, of class WorkflowCRUDService.
     */
    @Test
    public void testAllUsersWorkflows() {
        System.out.println("allUsersWorkflows");
        WorkflowCRUDService instance = new WorkflowCRUDService();
        List<Workflow> expResult = null;
        List<Workflow> result = instance.allUsersWorkflows();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorflowWithId method, of class WorkflowCRUDService.
     */
    @Test
    public void testGetWorflowWithId() {
        System.out.println("getWorflowWithId");
        Long id = null;
        WorkflowCRUDService instance = new WorkflowCRUDService();
        Workflow expResult = null;
        Workflow result = instance.getWorflowWithId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeWorkflow method, of class WorkflowCRUDService.
     */
    @Test
    public void testRemoveWorkflow() {
        System.out.println("removeWorkflow");
        Long workflowId = null;
        WorkflowCRUDService instance = new WorkflowCRUDService();
        instance.removeWorkflow(workflowId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
