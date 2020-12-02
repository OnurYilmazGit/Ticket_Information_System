/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import tis_fx.EventDAOImpl;

public class Event_ScreenController implements Initializable {
    
    @FXML
    private TableView<Event> tableView;
    
    @FXML
    private TextField search;
    EventDAOImpl eventDAOImpl = new EventDAOImpl();
        
    List<Event> cart = new ArrayList<Event>();
    List<Event> allEvents = eventDAOImpl.getAllEvents();
    ObservableList<Event> oListEvents = FXCollections.observableArrayList(allEvents);
        
    FilteredList<Event> filter = new FilteredList(oListEvents,e->true);
    
    @FXML
    private void search(KeyEvent event) {
        search.textProperty().addListener((observable,oldValue,newValue)->{
   
            filter.setPredicate((Event events)->{
                if(newValue.isEmpty() || newValue==null ){
                    return true;
                }
                else if(events.getName().toLowerCase().contains(newValue.toLowerCase()) ||
                        events.getLocation().toLowerCase().contains(newValue.toLowerCase()) ){
                    return true;
                }
                return false;
            });
            
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }
    

    public void addToChart(List<Event> list,Event e){
        for(Event i : list){
            if(i.getId() == e.getId()){
                i.setId(i.getId()+1);
            }
            else{
                list.add(e);
            }
        }
    }

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        TableColumn nameCol = new TableColumn("Name");
        TableColumn typeCol = new TableColumn("Type");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn startTimeCol = new TableColumn("Start Time");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn availableTicketsCol = new TableColumn("Available Tickets");
        TableColumn addItem = new TableColumn("Add Item");
        
        
        tableView.getColumns().addAll(nameCol,typeCol,locationCol,startTimeCol,dateCol,priceCol,availableTicketsCol,addItem);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<Event,String>("name"));
        //nameCol.setMaxWidth(400);
        typeCol.setCellValueFactory(new PropertyValueFactory<Event,String>("type"));
        //typeCol.setMaxWidth(200);
        locationCol.setCellValueFactory(new PropertyValueFactory<Event,String>("location"));
        //locationCol.setMaxWidth(400);
        startTimeCol.setCellValueFactory(new PropertyValueFactory<Event,String>("starTime"));
        //startTimeCol.setMaxWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<Event,Date>("date"));
       // dateCol.setMaxWidth(200);
        priceCol.setCellValueFactory(new PropertyValueFactory<Event,Double>("price"));
       // priceCol.setMaxWidth(200);
        availableTicketsCol.setCellValueFactory(new PropertyValueFactory<Event,Integer>("availableTickets"));
        //availableTicketsCol.setMaxWidth(400);
        
        addItem.setCellValueFactory(new PropertyValueFactory<>("addButton"));
        
        addItem.setCellFactory(param -> new TableCell<Event,Event>(){
            private final Button addButton = new Button("Add");
       
            
            @Override
            protected void updateItem(Event eventT,boolean empty) {
                super.updateItem(eventT, empty);

                setGraphic(addButton);
                addButton.setOnAction(event -> {
                    Event getevent = getTableView().getItems().get(getIndex());
                    addToChart(cart,getevent);
                    System.out.println("WORKEDD"+getevent.getId());
                });
                
            }  
        });
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      
        tableView.setItems(oListEvents);
       
        }
}

