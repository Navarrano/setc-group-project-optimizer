/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.Logs;
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
public class LogsCRUDServiceTest {
    
    public LogsCRUDServiceTest() {
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
     * Test of addEventLog method, of class LogsCRUDService.
     */
    @Test
    public void testAddEventLog() {
        System.out.println("addEventLog");
        Logs obj = null;
        LogsCRUDService instance = new LogsCRUDService();
        instance.addEventLog(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
