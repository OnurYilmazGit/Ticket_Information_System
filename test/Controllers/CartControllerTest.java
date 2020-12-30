/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
import Models.Reservation;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tis_fx.EventDAOImpl;
import static org.junit.Assert.*;
import tis_fx.ReservationDAOImpl;
import tis_fx.UserName;

/**
 *
 * @author baris
 */
public class CartControllerTest {
    ArrayList<Event> selectedEvents_ = new ArrayList<>();
    ReservationDAOImpl dAOImpl = new ReservationDAOImpl();
    
    public CartControllerTest() {
    }

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
    
    @Test
    public void testDiscount(){
        CartController instance = new CartController();
        instance.is_user_student = true;
        
        EventDAOImpl eventDAOImpl = new EventDAOImpl();
        
        selectedEvents_.add(eventDAOImpl.getAllEvents_().get(0));
        selectedEvents_.add(eventDAOImpl.getAllEvents_().get(1));
        
        instance.selectedEvents = selectedEvents_ ;
        
        double is_student_on =  0;
        double is_student_off = 0;
        
        instance.is_user_student = true;
        is_student_on = instance.getTotalPrice();
        
        instance.is_user_student = false;
        is_student_off = instance.getTotalPrice();
        
        System.out.println(is_student_off * 0.7);
        System.out.println(is_student_on);
        
        boolean result = is_student_off * 0.7 == is_student_on;
        
        assertTrue(result);
    }
    
    @Test
    public void testApproved(){
        CartController instance = new CartController();
        EventDAOImpl eventDAOImpl = new EventDAOImpl();
        selectedEvents_.add(eventDAOImpl.getAllEvents_().get(0));
        instance.selectedEvents = selectedEvents_ ;
        Event getevent =instance.selectedEvents.get(0);
        Reservation res = new Reservation("test", getevent.getId());
        dAOImpl.insertReservation(res);
        assertTrue(dAOImpl.deleteReservedEvents(res));
    }
    
    @Test
    public void testSynchronEvent(){
        CartController instance = new CartController();
        Date date = new Date();
        Event eventFirst = new Event(0,"Oscar","Ceremonie","Ankara","12:00",date,120.0,100);
        Event eventSecond = new Event(0,"Altın Portakal","Ceremonie","Ankara","12:00",date,150.0,100);
        assertTrue(instance.is_clash(eventFirst, eventSecond));
    }
    
    
}
