/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import java.util.Collections;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tis_fx.UserDAOImpl;

/**
 *
 * @author HP
 */
public class SignInController {

    @FXML
    private Button buttonSignIn;

    @FXML
    private TextField signInUsername;

    @FXML
    private PasswordField signInPassword;

    @FXML
    void signIn(ActionEvent event) {
       UserDAOImpl dAOImpl = new UserDAOImpl();
       List<User> userList = dAOImpl.getAllUsers();
       List<String> passwordList = Collections.EMPTY_LIST;
       
       
      // System.out.println("Password: " + passwordList.get(0));
       
       boolean flag = false;
       
        System.out.println("My Input: "+signInUsername.getText());
       
       for(User user: userList){
           System.out.println("Username: "+user.getName());
           
           if(signInUsername.getText().equals(user.getName())){
               flag = true;
               System.out.println("User name matched");
           }
       }
       if (flag){
           passwordList = dAOImpl.checkPassword(signInUsername.getText());
           if(signInPassword.getText().equals(passwordList.get(0))){
              showErrorMessage("Welcome");  
           }
           else{
             showErrorMessage("Your password is wrong, please write again!"); 
           }
       } else {
           showErrorMessage("Your username or password is wrong, please write again!"); 
       }
       
       signInUsername.setText("");
       signInPassword.setText("");
       
        

    }

    private void showErrorMessage(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
