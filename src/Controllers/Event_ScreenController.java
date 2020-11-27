/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import tis_fx.EventDAOImpl;

public class Event_ScreenController implements Initializable {
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        EventDAOImpl eventDAOImpl = new EventDAOImpl();
        
        for(Models.Event e : eventDAOImpl.getAllEvents()){
            System.out.println(e.getName() + " - " + e.getType() + " - " + e.getLocation() + " - " + 
                    e.getStarTime() + " - " + e.getDate().toString() + " - " + e.getPrice() + " - " + e.getAvailableTickets());
        }
   

    }
}
