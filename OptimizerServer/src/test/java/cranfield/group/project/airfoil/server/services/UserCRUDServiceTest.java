/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.AstralUser;
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
public class UserCRUDServiceTest {
    
    public UserCRUDServiceTest() {
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
     * Test of existsUser method, of class UserCRUDService.
     */
    @Test
    public void testExistsUser() {
        System.out.println("existsUser");
        String login = "";
        UserCRUDService instance = new UserCRUDService();
        boolean expResult = false;
        boolean result = instance.existsUser(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewUser method, of class UserCRUDService.
     */
    @Test
    public void testAddNewUser() {
        System.out.println("addNewUser");
        AstralUser obj = null;
        UserCRUDService instance = new UserCRUDService();
        instance.addNewUser(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserConnectionInformation method, of class UserCRUDService.
     */
    @Test
    public void testUpdateUserConnectionInformation() {
        System.out.println("updateUserConnectionInformation");
        AstralUser obj = null;
        UserCRUDService instance = new UserCRUDService();
        instance.updateUserConnectionInformation(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class UserCRUDService.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        String login = "";
        UserCRUDService instance = new UserCRUDService();
        Long expResult = null;
        Long result = instance.getUserId(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserObj method, of class UserCRUDService.
     */
    @Test
    public void testGetUserObj() {
        System.out.println("getUserObj");
        String login = "";
        UserCRUDService instance = new UserCRUDService();
        AstralUser expResult = null;
        AstralUser result = instance.getUserObj(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
