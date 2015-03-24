/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.entities;

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
public class WorkflowTest {
    
    public WorkflowTest() {
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
     * Test of getId method, of class Workflow.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Workflow instance = new Workflow();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Workflow.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Workflow instance = new Workflow();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNbIterations method, of class Workflow.
     */
    @Test
    public void testGetNbIterations() {
        System.out.println("getNbIterations");
        Workflow instance = new Workflow();
        int expResult = 0;
        int result = instance.getNbIterations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNbIterations method, of class Workflow.
     */
    @Test
    public void testSetNbIterations() {
        System.out.println("setNbIterations");
        int nbIterations = 0;
        Workflow instance = new Workflow();
        instance.setNbIterations(nbIterations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinDragCoef method, of class Workflow.
     */
    @Test
    public void testGetMinDragCoef() {
        System.out.println("getMinDragCoef");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getMinDragCoef();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinDragCoef method, of class Workflow.
     */
    @Test
    public void testSetMinDragCoef() {
        System.out.println("setMinDragCoef");
        double minDragCoef = 0.0;
        Workflow instance = new Workflow();
        instance.setMinDragCoef(minDragCoef);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAeroplaneMass method, of class Workflow.
     */
    @Test
    public void testGetAeroplaneMass() {
        System.out.println("getAeroplaneMass");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getAeroplaneMass();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAeroplaneMass method, of class Workflow.
     */
    @Test
    public void testSetAeroplaneMass() {
        System.out.println("setAeroplaneMass");
        double aeroplaneMass = 0.0;
        Workflow instance = new Workflow();
        instance.setAeroplaneMass(aeroplaneMass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxLiftCoef method, of class Workflow.
     */
    @Test
    public void testGetMaxLiftCoef() {
        System.out.println("getMaxLiftCoef");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getMaxLiftCoef();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxLiftCoef method, of class Workflow.
     */
    @Test
    public void testSetMaxLiftCoef() {
        System.out.println("setMaxLiftCoef");
        double maxLiftCoef = 0.0;
        Workflow instance = new Workflow();
        instance.setMaxLiftCoef(maxLiftCoef);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAirSpeed method, of class Workflow.
     */
    @Test
    public void testGetAirSpeed() {
        System.out.println("getAirSpeed");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getAirSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAirSpeed method, of class Workflow.
     */
    @Test
    public void testSetAirSpeed() {
        System.out.println("setAirSpeed");
        double airSpeed = 0.0;
        Workflow instance = new Workflow();
        instance.setAirSpeed(airSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinAirSpeed method, of class Workflow.
     */
    @Test
    public void testGetMinAirSpeed() {
        System.out.println("getMinAirSpeed");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getMinAirSpeed();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinAirSpeed method, of class Workflow.
     */
    @Test
    public void testSetMinAirSpeed() {
        System.out.println("setMinAirSpeed");
        double minAirSpeed = 0.0;
        Workflow instance = new Workflow();
        instance.setMinAirSpeed(minAirSpeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAngle method, of class Workflow.
     */
    @Test
    public void testGetAngle() {
        System.out.println("getAngle");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getAngle();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAngle method, of class Workflow.
     */
    @Test
    public void testSetAngle() {
        System.out.println("setAngle");
        double angle = 0.0;
        Workflow instance = new Workflow();
        instance.setAngle(angle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChord method, of class Workflow.
     */
    @Test
    public void testGetChord() {
        System.out.println("getChord");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getChord();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChord method, of class Workflow.
     */
    @Test
    public void testSetChord() {
        System.out.println("setChord");
        double chord = 0.0;
        Workflow instance = new Workflow();
        instance.setChord(chord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpan method, of class Workflow.
     */
    @Test
    public void testGetSpan() {
        System.out.println("getSpan");
        Workflow instance = new Workflow();
        double expResult = 0.0;
        double result = instance.getSpan();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpan method, of class Workflow.
     */
    @Test
    public void testSetSpan() {
        System.out.println("setSpan");
        double span = 0.0;
        Workflow instance = new Workflow();
        instance.setSpan(span);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Workflow.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Workflow instance = new Workflow();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Workflow.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Workflow instance = new Workflow();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResults method, of class Workflow.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        Workflow instance = new Workflow();
        List<Results> expResult = null;
        List<Results> result = instance.getResults();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResults method, of class Workflow.
     */
    @Test
    public void testSetResults() {
        System.out.println("setResults");
        List<Results> results = null;
        Workflow instance = new Workflow();
        instance.setResults(results);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreator method, of class Workflow.
     */
    @Test
    public void testGetCreator() {
        System.out.println("getCreator");
        Workflow instance = new Workflow();
        AstralUser expResult = null;
        AstralUser result = instance.getCreator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreator method, of class Workflow.
     */
    @Test
    public void testSetCreator() {
        System.out.println("setCreator");
        AstralUser creator = null;
        Workflow instance = new Workflow();
        instance.setCreator(creator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
