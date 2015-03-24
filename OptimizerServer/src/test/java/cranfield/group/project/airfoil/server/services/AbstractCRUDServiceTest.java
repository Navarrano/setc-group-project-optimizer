/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.AbstractEntityObject;
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
public class AbstractCRUDServiceTest {
    
    public AbstractCRUDServiceTest() {
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
     * Test of find method, of class AbstractCRUDService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Object id = null;
        AbstractCRUDService instance = new AbstractCRUDServiceImpl();
        AbstractEntityObject expResult = null;
        AbstractEntityObject result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of persist method, of class AbstractCRUDService.
     */
    @Test
    public void testPersist() {
        System.out.println("persist");
        AbstractCRUDService instance = new AbstractCRUDServiceImpl();
        AbstractEntityObject expResult = null;
        AbstractEntityObject result = instance.persist(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractCRUDServiceImpl extends AbstractCRUDService {
    }
    
}
