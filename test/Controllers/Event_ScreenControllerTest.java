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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tis_fx.EventDAOImpl;

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
    public void negativeTestCheckNumTickets() {
        System.out.println("intTestCheckNumTickets");
        Event getevent = allEvents.get(0);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(-2, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void zeroTestCheckNumTickets() {
        System.out.println("zeroTestCheckNumTickets");
        Event getevent = allEvents.get(2);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = true;
        boolean result = instance.checkNumTickets(0, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
        @Test
        public void moreThanTenTestCheckNumTickets() {
        System.out.println("moreThanTenTestCheckNumTickets");
        Event getevent = allEvents.get(1);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(11, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

        @Test
        public void charTestCheckNumTickets() {
        System.out.println("charTestCheckNumTickets");
        Event getevent = allEvents.get(2);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets('e', getevent);
        assertEquals(expResult, result);
//         TODO review the generated test code and remove the default call to fail.    
    }
    
        @Test
        public void moreThanAvailableTestCheckNumTickets() {
        System.out.println("moreThanAvailableTestCheckNumTickets");
        Event getevent = allEvents.get(1);
        int available_tickets=getevent.getAvailableTickets()+1;
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets(available_tickets, getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
        @Test
        public void stringCheckNumTickets() {
        System.out.println("StringTestCheckNumTickets");
        Event getevent = allEvents.get(2);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets("ecemmm", getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
        @Test
        public void emptyStringCheckNumTickets() {
        System.out.println("EmptyStringTestCheckNumTickets");
        Event getevent = allEvents.get(2);
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = false;
        boolean result = instance.checkNumTickets("", getevent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    } 
        
        @Test
        public void lessThanTenAvailableTestCheckNumTickets() {
        System.out.println("LessThanTenAvailableCheckNumTickets");
        Event getevent = allEvents.get(3);  //This event is going to be event which remains less than 10 available tickets in database that's why 3 for me
        Event_ScreenController instance = new Event_ScreenController();
        boolean expResult = true;
        System.out.println("geliyoooo");
        boolean result = instance.checkNumTickets(2, getevent);
        System.out.println(getevent.getId());
        System.out.println(getevent.getName());
        assertEquals(expResult, result);
//         TODO review the generated test code and remove the default call to fail.
       
    }        
        
}
    

