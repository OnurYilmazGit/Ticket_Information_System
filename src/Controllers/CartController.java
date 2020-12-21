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
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tis_fx.EventDAOImpl;
import tis_fx.ReservationDAOImpl;
import tis_fx.UserName;
/**
 *
 * @author Asena
 */
public class CartController extends Event_ScreenController implements Initializable{
    
    @FXML
    private Button buttonApprove;
   
    @FXML
    private TableView<Event> CartView;
  //  HashMap cart=new HashMap<Integer, Integer>();
    
   
    EventDAOImpl eventDAOImpl = new EventDAOImpl();
    ArrayList<Event> selectedEvents =new ArrayList <Event> ();
    List<Event> allEvents = eventDAOImpl.getAllEvents();
    ReservationDAOImpl dAOImpl = new ReservationDAOImpl();
    @FXML
    private void approved(MouseEvent e)   {
        for(Event ev:selectedEvents){
            Reservation res = new Reservation(UserName.getInstance().getUser(), ev.getId());
            dAOImpl.insertReservation(res);
        }
        closeScreenOpenMain(e);
    } 
    
        @FXML
    void closeScreen(MouseEvent event) {
            closeScreenOpenMain(event);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //draw the cart
        //columns on the cart view
        System.out.println("initializable in cart");
        
        
        TableColumn eventName = new TableColumn("Event");
        TableColumn numTickets = new TableColumn("Number of Tickets");
        TableColumn cancelEvent = new TableColumn("Cancel Event");
        
        CartView.getColumns().addAll(eventName, numTickets, cancelEvent);
        
        
       
        
        //print the cart but the cart is null, why
        HashMap <Integer,Integer> subCart=getCartMap();
        subCart.forEach((key,value)->{
            System.out.println(String.valueOf(key)+ " - "+ String.valueOf(value));
            System.out.println(" ");
                        if(subCart.get(key)>0){
                for(int i=0;i<allEvents.size();i++){
                    if(allEvents.get(i).getId()==key){
                        allEvents.get(i).setSelectedNum(subCart.get(key));
                        selectedEvents.add(allEvents.get(i));
                        System.out.println("item selected");
                    }else{
                        System.out.println("item not selected");
                    }
                }
        }});
        ObservableList<Event> oListSelected = FXCollections.observableArrayList(selectedEvents);
        System.out.println("oList:");
        for(int i=0; i<oListSelected.size();i++){
            System.out.println("item"+i+ " ="+oListSelected.get(i).getName());
            System.out.println("");
        }
        
    //prpperty value factories
    eventName.setCellValueFactory(new PropertyValueFactory<Event,String>("name"));
    numTickets.setCellValueFactory(new PropertyValueFactory <>("selectedNum"));
    cancelEvent.setCellValueFactory(new PropertyValueFactory<>("cancelButton"));
    
    //set numTickets
   /* numTickets.setCellFactory(param-> new TableCell (){
   
        subCart.get(e)
            
            HBox pane = new HBox();
            
            for(int i=0;i<oListSelected.size();i++){
                for(int j=0;j<subCart.size();j++){
                    if(subCart.keySet().contains(oListSelected.get(i).getId())){
                        numTicks=(subCart.get(j);
                    }
                }
            }
            pane.getChildren().addAll(numTicks);
            setGraphic(pane);
        }
    });*/
    //add cancelEvent buttons for each event in the cart
    cancelEvent.setCellFactory(param -> new TableCell<Event,Event>(){
            @Override
            protected void updateItem(Event eventT,boolean empty) {
                
                super.updateItem(eventT, empty);

                HBox pane = new HBox();
                Button cancelButton = new Button("Cancel");
                pane.getChildren().addAll(cancelButton);
                setGraphic(pane);
                
                cancelButton.setOnAction(event -> {
                Event getevent = getTableView().getItems().get(getIndex());

                try{
                    subCart.remove(getevent.getId());
                    System.out.println("removed:"+getevent.getId());
                    //print the subCart so that we can see
                    subCart.forEach((key,value)->{
                    System.out.println(String.valueOf(key)+ " - "+ String.valueOf(value));
                    System.out.println(" ");
                    });
                    //renew itself since an event is removed
                    
                }
                catch(Exception errorType){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Error");
                    alert.showAndWait();
                }
            });
                
               
        }  
    });
    
    System.out.println("hello");    
    CartView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    CartView.setItems(oListSelected);
    }
    
    private void closeScreenOpenMain(MouseEvent e){
            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Main_Page.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Event Screen");
            stage.setScene(scene);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }
    }
}
