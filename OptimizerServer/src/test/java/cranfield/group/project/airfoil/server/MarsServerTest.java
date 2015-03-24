/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server;

import cranfield.group.project.airfoil.api.model.WorkflowDTO;
import java.util.List;
import java.util.Observable;
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
public class MarsServerTest {
    
    public MarsServerTest() {
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
     * Test of run method, of class MarsServer.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        MarsServer instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedWorkflowData method, of class MarsServer.
     */
    @Test
    public void testGetSelectedWorkflowData() {
        System.out.println("getSelectedWorkflowData");
        Long workflowId = null;
        MarsServer instance = null;
        WorkflowDTO expResult = null;
        WorkflowDTO result = instance.getSelectedWorkflowData(workflowId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorkflowList method, of class MarsServer.
     */
    @Test
    public void testGetWorkflowList() {
        System.out.println("getWorkflowList");
        MarsServer instance = null;
        List<WorkflowDTO> expResult = null;
        List<WorkflowDTO> result = instance.getWorkflowList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateConnection method, of class MarsServer.
     */
    @Test
    public void testValidateConnection() {
        System.out.println("validateConnection");
        String[] credentials = null;
        MarsServer instance = null;
        boolean expResult = false;
        boolean result = instance.validateConnection(credentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeUserInformationInDatabase method, of class MarsServer.
     */
    @Test
    public void testWriteUserInformationInDatabase() {
        System.out.println("writeUserInformationInDatabase");
        String username = "";
        MarsServer instance = null;
        instance.writeUserInformationInDatabase(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCredentials method, of class MarsServer.
     */
    @Test
    public void testCheckCredentials() {
        System.out.println("checkCredentials");
        String[] credentials = null;
        MarsServer instance = null;
        String expResult = "";
        String result = instance.checkCredentials(credentials);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MarsServer.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        MarsServer instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
