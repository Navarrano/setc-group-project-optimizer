/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cranfield.group.project.airfoil.server.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Kat
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({cranfield.group.project.airfoil.server.entities.WorkflowTest.class, cranfield.group.project.airfoil.server.entities.AstralUserTest.class, cranfield.group.project.airfoil.server.entities.ResultsTest.class, cranfield.group.project.airfoil.server.entities.AbstractEntityObjectTest.class, cranfield.group.project.airfoil.server.entities.LogsTest.class})
public class EntitiesSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
