/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dinesh
 */
public class RegistrationScreenControllerTest {
    
    public RegistrationScreenControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of initialize method, of class RegistrationScreenController.
     */
    @Test
    public void testInitialize() {
        System.out.println("Test Setuser()");
        URL url = null;
        ResourceBundle rb = null;
        RegistrationScreenController instance = new RegistrationScreenController();
        instance.initialize(url, rb);
       // instance.setUser();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
