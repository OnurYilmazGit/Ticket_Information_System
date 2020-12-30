/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Event;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tis_fx.ReservationDAOImpl;
import tis_fx.UserName;

/**
 *
 * @author baris
 */
public class MyReservationPageControllerTest {
    
    List<Integer> reservedEvents_ = new ArrayList<>();
    
    public MyReservationPageControllerTest() {
      
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
    
    @Test
    public void emptyCart(){
        MyReservationPageController instance = new MyReservationPageController();
        instance.reservedEvents = reservedEvents_;
        assertTrue(instance.dbToView());
    }
}
