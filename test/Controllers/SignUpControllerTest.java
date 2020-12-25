/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asena
 */
public class SignUpControllerTest {
    
    public SignUpControllerTest() {
    }

    /**
     * Test of addUser method, of class SignUpController.
     */
    
    
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        ActionEvent event = null;
        SignUpController instance = new SignUpController();
        instance.addUser(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
