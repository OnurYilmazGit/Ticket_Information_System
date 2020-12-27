/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author onur_yilmaz
 */
public class SignInControllerTest {
    
    public SignInControllerTest() {
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
     * Test of signIn method, of class SignInController.
     */


    /**
     * Test of signIn method, of class SignInController.
     */
    @Test
    public void test_right_username_and_right_password() {
        System.out.println("signIn");
        String username_ = "onur";
        String password_ = "1234";
        SignInController instance = new SignInController();
        boolean expResult = true;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    } 
    @Test
    public void test_wrong_username_and__right_password() {
        System.out.println("signIn");
        String username_ = "on";
        String password_ = "1234";
        SignInController instance = new SignInController();
        boolean expResult = false;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    } 
    @Test
    public void test_wrong_username_and__wrong_password() {
        System.out.println("signIn");
        String username_ = "on";
        String password_ = "12";
        SignInController instance = new SignInController();
        boolean expResult = false;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    }
    @Test
    public void test_empty_username_and_empty_password() {
        System.out.println("signIn");
        String username_ = "";
        String password_ = "";
        SignInController instance = new SignInController();
        boolean expResult = false;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    }
    @Test
    public void test_empty_username_and_a_password() {
        System.out.println("signIn");
        String username_ = "";
        String password_ = "1234";
        SignInController instance = new SignInController();
        boolean expResult = false;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    }  
    @Test
    public void test_a_username_and_empty_password() {
        System.out.println("signIn");
        String username_ = "onur";
        String password_ = "";
        SignInController instance = new SignInController();
        boolean expResult = false;
        boolean result = instance.signIn(username_, password_);
        assertEquals(expResult, result);
    }  
    
}
