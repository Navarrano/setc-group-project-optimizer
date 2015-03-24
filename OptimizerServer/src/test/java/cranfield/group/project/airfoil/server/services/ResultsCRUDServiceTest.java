/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.Results;
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
public class ResultsCRUDServiceTest {
    
    public ResultsCRUDServiceTest() {
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
     * Test of addResult method, of class ResultsCRUDService.
     */
    @Test
    public void testAddResult() {
        System.out.println("addResult");
        Results obj = null;
        ResultsCRUDService instance = new ResultsCRUDService();
        instance.addResult(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastResult method, of class ResultsCRUDService.
     */
    @Test
    public void testGetLastResult() {
        System.out.println("getLastResult");
        Long workflowId = null;
        ResultsCRUDService instance = new ResultsCRUDService();
        Results expResult = null;
        Results result = instance.getLastResult(workflowId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
