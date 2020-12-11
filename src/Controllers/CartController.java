/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tis_fx.EventDAOImpl;
/**
 *
 * @author Asena
 */
public class CartController implements Initializable{
    
    @FXML
    private Button buttonApprove;
    
    @FXML
    private void approved(MouseEvent e)   {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Main_Page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        } 
        catch (IOException ex) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }

    } 
    
    
    
    @FXML
    private TableView<Event> CartView;
    
    HashMap cart=new HashMap<Integer, Integer>();
    
    @FXML
    EventDAOImpl eventDAOImpl = new EventDAOImpl();
    
    //draw the cart
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //draw the cart
        //columns on the cart view
        TableColumn eventName = new TableColumn("Event");
        TableColumn numTickets = new TableColumn("Number of Tickets");
        TableColumn cancelEvent = new TableColumn("");
        
        CartView.getColumns().addAll(eventName, numTickets, cancelEvent);
        
        //prpperty value factories
        eventName.setCellValueFactory(new PropertyValueFactory<Event,String>("eventName"));
        numTickets.setCellValueFactory(new PropertyValueFactory<Event,String>("numTickets"));
        cancelEvent.setCellValueFactory(new PropertyValueFactory<>("cancelButton"));
        
        //add cancelEvent buttons for each event in the cart
        cancelEvent.setCellFactory(param -> new TableCell<Event,Event>(){
            protected void cancelEvent(Event eventT) {
                HBox pane = new HBox();
                Button cancelButton = new Button("Cancel");
                TextField numberField = new TextField(); //delete dis what even is dis     
                pane.getChildren().addAll(cancelButton);
                setGraphic(pane);

                cancelButton.setOnAction(event -> {
                Event getevent = getTableView().getItems().get(getIndex());

                try{
                    cart.remove(getevent.getId());
                    System.out.println("removed:"+getevent.getId());
                    //print the cart so that we can see
                    cart.forEach((key,value)->{
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
    }
}
