/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tis_fx.EventDAOImpl;

/**
 *
 * @author HP
 */
public class Event_ScreenControllerTest {
     private TextField numberField;
     EventDAOImpl eventDAOImpl = new EventDAOImpl();
     private List<Event> allEvents = eventDAOImpl.getAllEvents();
    
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
     * Test of checkNumTickets method, of class Event_ScreenController.
     */
    @Test
    public void intTestCheckNumTickets() {
        System.out.println("intTestCheckNumTickets");
        Event getevent = allEvents.get(0);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(5, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void zeroTestCheckNumTickets() {
        System.out.println("zeroTestCheckNumTickets");
        Event getevent = allEvents.get(0);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(0, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
