/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dinesh
 */
public class AddressTest {
    
    public AddressTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getStreetAddress method, of class Contact.
     */
    @Test
    public void DISABLED_testGetStreetAddress() {
        System.out.println("getStreetAddress");
        Contact instance = new Contact();
         String expResult = "4919 coldwater";
        instance.setStreetAddress(expResult);
       
        String result = instance.getStreetAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of setStreetAddress method, of class Contact.
     */
    @Test
    public void DISABLED_testSetStreetAddress() {
        System.out.println("setStreetAddress");
        String streetAddress = "4919 coldwater";
        Contact instance = new Contact();
        instance.setStreetAddress(streetAddress);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of getApt method, of class Contact.
     */
    @Test
    public void DISABLED_testGetApt() {
        System.out.println("getApt");
        Contact instance = new Contact();
        String expResult = "1";
        instance.setApt(expResult);
        String result = instance.getApt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of setApt method, of class Contact.
     */
    @Test
    public void DISABLED_testSetApt() {
        System.out.println("setApt");
        String apt = "1";
        Contact instance = new Contact();
        instance.setApt(apt);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getCity method, of class Contact.
     */
    @Test
    public void DISABLED_testGetCity() {
        System.out.println("getCity");
        Contact instance = new Contact();
        String expResult = "Sherman Oaks";
        instance.setCity(expResult);
        String result = instance.getCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setCity method, of class Contact.
     */
    @Test
    public void DISABLED_testSetCity() {
        System.out.println("setCity");
        String city = "Sherman Oaks";
        Contact instance = new Contact();
        instance.setCity(city);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getZip method, of class Contact.
     */
    @Test
    public void DISABLED_testGetZip() {
        System.out.println("getZip");
        Contact instance = new Contact();
        String expResult = "91423";
        instance.setZip(expResult);
        String result = instance.getZip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setZip method, of class Contact.
     */
    @Test
    public void DISABLED_testSetZip() {
        System.out.println("setZip");
        String zip = "91423";
        Contact instance = new Contact();
        instance.setZip(zip);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Contact.
     */
    @Test
    public void DISABLED_testGetState() {
        System.out.println("getState");
        Contact instance = new Contact();
         String expResult = "CA";
        instance.setState(expResult);
       
        String result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Contact.
     */
    @Test
    public void DISABLED_testSetState() {
        System.out.println("setState");
        String state = "CA";
        Contact instance = new Contact();
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class Contact.
     */
    @Test
    public void DISABLED_testGetCountry() {
        System.out.println("getCountry");
        Contact instance = new Contact();
        String expResult = "USA";
        instance.setCountry(expResult);
        String result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class Contact.
     */
    @Test
    public void DISABLED_testSetCountry() {
        System.out.println("setCountry");
        String country = "USA";
        Contact instance = new Contact();
        instance.setCountry(country);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Address.
     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        Contact instance = new Contact();
//        int expResult = 0;
//        int result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }
    
}
