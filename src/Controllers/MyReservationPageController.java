/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Event;
import Models.Reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tis_fx.EventDAOImpl;
import tis_fx.ReservationDAOImpl;
import tis_fx.UserName;

public class MyReservationPageController implements Initializable {

    @FXML
    private TableView<Event> tableView;

    ReservationDAOImpl reservationDAOImpl;
    EventDAOImpl eventDAOImpl;
    
    List<Integer> reservedEvents;
    List<Integer> reservedEvents2;
    List<Event> reservedEventsInfo = Collections.EMPTY_LIST;
    List<Event> newList = Collections.EMPTY_LIST;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservationDAOImpl = new ReservationDAOImpl();
        eventDAOImpl = new EventDAOImpl();
        
        reservedEvents = reservationDAOImpl.getReservedEvents(UserName.getInstance().getUser());
        reservedEvents2 = reservedEvents.stream().distinct().collect(Collectors.toList());
        //eventDAOImpl.getReservedEventsInfo(1);
        
        dbToView();
        
        createTable();
   
        
       // reservedEventsInfo = eventDAOImpl.getReservedEventsInfo(7);
    
       // System.out.println(reservedEventsInfo.get(0).getName()+"-"+ reservedEventsInfo.get(0).getType()+"-"+reservedEventsInfo.get(0).getStarTime());
        
        /*
        for(Event e : reservedEventsInfo){
            System.out.println(e.getName()+"-"+ e.getType()+"-"+e.getStarTime());
            
        }
        */
        //ObservableList<Event> oListEvents = FXCollections.observableArrayList(tmpList);

    //    System.out.println(reservedEventsInfo.get(0).getName());
        
         
      
                
       //tableView.setItems(oListEvents);    
    }    
    
    
    public boolean dbToView(){
        try{
            if(reservedEvents.isEmpty()){
            System.out.println("Is empty");
            //createTable();
            }
            else{
                for(int i : reservedEvents2){
                    System.out.println("index:"+i);
                    reservedEventsInfo = eventDAOImpl.getReservedEventsInfo(i); 
                    System.out.println(reservedEventsInfo.get(0).getName()+"-"+ reservedEventsInfo.get(0).getType()+"-"+reservedEventsInfo.get(0).getStarTime());
                    tableView.getItems().add(reservedEventsInfo.get(0));
                }
            }
        }
        catch(Error e){
            return false;
        }
        return true;
    }
    
    private void createTable(){
        TableColumn nameCol = new TableColumn("Name");
        TableColumn typeCol = new TableColumn("Type");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn startTimeCol = new TableColumn("Start Time");
        TableColumn dateCol = new TableColumn("Date");
        
        tableView.getColumns().addAll(nameCol, typeCol, locationCol, startTimeCol, dateCol);

        nameCol.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        //nameCol.setMaxWidth(400);
        typeCol.setCellValueFactory(new PropertyValueFactory<Event, String>("type"));
        //typeCol.setMaxWidth(200);
        locationCol.setCellValueFactory(new PropertyValueFactory<Event, String>("location"));
        //locationCol.setMaxWidth(400);
        startTimeCol.setCellValueFactory(new PropertyValueFactory<Event, String>("starTime"));
        //startTimeCol.setMaxWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<Event, Date>("date"));
        // dateCol.setMaxWidth(200);
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  
    }
}
