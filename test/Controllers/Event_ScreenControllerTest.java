/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class Event_ScreenControllerTest {
    
    public Event_ScreenControllerTest() {
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
     * Test of addToCart method, of class Event_ScreenController.
     */
    @Test
    public void testAddToCart() {
        System.out.println("addToCart");
        int e = 0;
        int add = 0;
        Event_ScreenController instance = new Event_ScreenController();
        instance.addToCart(e, add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class Event_ScreenController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        Event_ScreenController instance = new Event_ScreenController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeScreenMain method, of class Event_ScreenController.
     */
    @Test
    public void testCloseScreenMain() {
        System.out.println("closeScreenMain");
        Event_ScreenController instance = new Event_ScreenController();
        instance.closeScreenMain();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNumTickets method, of class Event_ScreenController.
     */
    @Test
    public void testCheckNumTickets() {
        System.out.println("checkNumTickets");
        TextField numberField = null;
        Event getevent = null;
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(numberField, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
