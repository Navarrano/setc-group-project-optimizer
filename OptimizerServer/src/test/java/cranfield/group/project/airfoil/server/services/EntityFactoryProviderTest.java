/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import javax.persistence.EntityManagerFactory;
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
public class EntityFactoryProviderTest {
    
    public EntityFactoryProviderTest() {
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
     * Test of getInstance method, of class EntityFactoryProvider.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        EntityFactoryProvider expResult = null;
        EntityFactoryProvider result = EntityFactoryProvider.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEntityManagerFactory method, of class EntityFactoryProvider.
     */
    @Test
    public void testCreateEntityManagerFactory() {
        System.out.println("createEntityManagerFactory");
        EntityFactoryProvider instance = null;
        EntityManagerFactory expResult = null;
        EntityManagerFactory result = instance.createEntityManagerFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class EntityFactoryProvider.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        EntityFactoryProvider instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
