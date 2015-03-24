/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.controllers;

import cranfield.group.project.airfoil.server.entities.Workflow;
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
public class AirfoilCalculatorTest {
    
    public AirfoilCalculatorTest() {
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
     * Test of optimize method, of class AirfoilCalculator.
     */
    @Test
    public void testOptimize_3args() {
        System.out.println("optimize");
        double b = 0.0;
        double c = 0.0;
        double stepSize = 0.0;
        AirfoilCalculator instance = null;
        double[] expResult = null;
        double[] result = instance.optimize(b, c, stepSize);
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of optimize method, of class AirfoilCalculator.
     */
    @Test
    public void testOptimize_5args() {
        System.out.println("optimize");
        double b = 0.0;
        double c = 0.0;
        int iterations = 0;
        int iterationsOffset = 0;
        Workflow workflow = null;
        AirfoilCalculator instance = null;
        instance.optimize(b, c, iterations, iterationsOffset, workflow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of optimize method, of class AirfoilCalculator.
     */
    @Test
    public void testOptimize_4args() {
        System.out.println("optimize");
        double b = 0.0;
        double c = 0.0;
        int iterations = 0;
        Workflow workflowObj = null;
        AirfoilCalculator instance = null;
        instance.optimize(b, c, iterations, workflowObj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
