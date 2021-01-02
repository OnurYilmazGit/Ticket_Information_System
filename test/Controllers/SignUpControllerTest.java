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
import org.junit.*;
import junit.framework.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    
     @Test
    public void testSpecialCharecters(){
       System.out.println("Special Charecters");        
       SignUpController instance = new SignUpController();
       assertTrue(instance.addUser("ASE#", "ecemmmm", true));       
    }
   @Test
    public void testEscapeSequence(){
       System.out.println("Escape Sequence");        
       SignUpController instance = new SignUpController();
       assertTrue(instance.addUser("ase\na", "ecemmmm", true));       
    } 
    @Test
    public void testNotLatinAlphabet(){
       System.out.println("Not Latin Alphabet");        
       SignUpController instance = new SignUpController();
       assertTrue(instance.addUser("스트레이 키즈", "ecemmmm", true));       
    }
    @Test
    public void testGapControl(){
       System.out.println("Gap Control");        
       SignUpController instance = new SignUpController();
       assertFalse(instance.addUser("   muratcankilicci   ", "ecemmmm", true));    //it gives False,because muratcankilicci is already exists in our database
   
}
}
