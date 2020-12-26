/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import junit.framework.Assert;
import org.junit.Test;



/**
 *
 * @author Asena
 */
    /**
* Test of addUser method, of class SignUpController.
*/

public class SignUpControllerTest {
    
    public SignUpControllerTest() {
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
    public void testEmptyUsername(){
       System.out.println("Empty Username");
       SignUpController instance = new SignUpController();
       assertFalse(instance.addUser("", "kahraman", true));
        
    }
    
    @Test
    public void testEmptyPassword(){
       System.out.println("Empty Password");
       SignUpController instance = new SignUpController();
       assertFalse(instance.addUser("kahraman", "", true));
        
    }

    @Test
    public void testLongPassword() {
        System.out.println("Long Password");        
        SignUpController instance = new SignUpController();
        String password= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String username="asenaa";
        assertFalse(instance.addUser(username,password,true));
        

    }
  
    @Test
    public void testLongUsername() {
        System.out.println("Long Username");        
        SignUpController instance = new SignUpController();
        String username= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String password="ase";
        assertFalse(instance.addUser(username,password,true));
        
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
    }
     
    @Test
    public void testValidUser(){
       System.out.println("Valid User");        
       SignUpController instance = new SignUpController();
       assertTrue(instance.addUser("ecemmmm", "ecemmmm", true));       
    }    

    @Test
    public void testExistingUser(){
       System.out.println("Existing User");        
       SignUpController instance = new SignUpController();
       assertFalse(instance.addUser("muratcankilicci", "ecemmmm", true));       
    }     
    
   @Test
    public void testCaseSensitive(){
       System.out.println("CaseSensitive User");        
       SignUpController instance = new SignUpController();
       assertFalse(instance.addUser("ASE", "ecemmmm", true));       
    }     
    
    /**
     * Test of addUser method, of class SignUpController.
//     */
//    @org.junit.Test
//    public void testAddUser_ActionEvent() {
//        System.out.println("addUser");
//        ActionEvent event = null;
//        SignUpController instance = new SignUpController();
//        instance.addUser(event);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addUser method, of class SignUpController.
//     */
//    @org.junit.Test
//    public void testAddUser_3args() {
//        System.out.println("addUser");
//        String username = "";
//        String password = "";
//        boolean isStudent = false;
//        SignUpController instance = new SignUpController();
//        boolean expResult = false;
//        boolean result = instance.addUser(username, password, isStudent);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
