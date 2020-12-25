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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import java.util.stream.Collectors;
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
import tis_fx.HoldCart;
import tis_fx.ReservationDAOImpl;
import tis_fx.UserDAOImpl;
import tis_fx.UserName;

/**
 *
 * @author Asena
 */
public class CartController extends Event_ScreenController implements Initializable {

    @FXML
    private Button buttonApprove;

    @FXML
    private TableView<Event> CartView;
    //  HashMap cart=new HashMap<Integer, Integer>();

    @FXML
    private Label pricelabel;

    EventDAOImpl eventDAOImpl = new EventDAOImpl();
    ArrayList<Event> selectedEvents = new ArrayList<Event>();
    List<Event> allEvents = eventDAOImpl.getAllEvents();
    ReservationDAOImpl dAOImpl = new ReservationDAOImpl();
    UserDAOImpl userdaoimp = new UserDAOImpl();
    Event_ScreenController event_ScreenController = new Event_ScreenController();
    List<Integer> reservedEvents = dAOImpl.getReservedEvents(UserName.getInstance().getUser());
    List<Integer> reservedEvents2 = reservedEvents.stream().distinct().collect(Collectors.toList());
    List<Event> reservedEventsInfo = Collections.EMPTY_LIST;
    ObservableList<Event> oListSelected;
    double totalprice=0;
    private HashMap<Integer, Integer> cart = HoldCart.getInstance().getCart();
    byte is_user_student_b =userdaoimp.checkIfStudent(UserName.getInstance().getUser()).get(0);  
    boolean  is_user_student = is_user_student_b!=0;
    
    @FXML
    private void approved(MouseEvent e) {
        boolean is_clash = false;
         
        for (int i=0;i< CartView.getItems().size();i++) {
            Event getevent = CartView.getItems().get(i);
            Reservation res = new Reservation(UserName.getInstance().getUser(), getevent.getId());
            System.out.println("Hello hello:" +getevent.getId());
            dAOImpl.insertReservation(res);
            
            for (int j : reservedEvents2) {
                reservedEventsInfo = eventDAOImpl.getReservedEventsInfo(j);
                if (reservedEventsInfo.get(0).getStarTime().equals(getevent.getStarTime()) && reservedEventsInfo.get(0).getDate().equals(getevent.getDate())) {
                    is_clash = true;
                }
            }
        }
        
        for (int i=0;i< CartView.getItems().size();i++) {
            for (int j=0;j< CartView.getItems().size();j++) {
                Event getevent1 = CartView.getItems().get(i);
                Event getevent2 = CartView.getItems().get(j);
                if (getevent1.getId() != getevent2.getId() && getevent1.getDate().equals(getevent2.getDate()) && getevent1.getStarTime().equals(getevent2.getStarTime())) {
                    is_clash = true;
                }
            }
        }
        
        if (is_clash) {
            System.out.println("Clash?:" +is_clash);
            showDialog("You have reservations starting at the same time and date!");
        }

        cart.clear();
        HoldCart.getInstance().setCart(cart);

        event_ScreenController.closeScreenMain();
        // Approved.getInstance().setApproved(true);
        closeScreenOpenMain(e);
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
    

    @FXML
    void closeScreen(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //draw the cart
        //columns on the cart view
        System.out.println("initializable in cart");

        TableColumn eventName = new TableColumn("Event");
        TableColumn numTickets = new TableColumn("Number of Tickets");
        TableColumn cancelEvent = new TableColumn("Cancel Event");

        CartView.getColumns().addAll(eventName, numTickets, cancelEvent);

        //print the cart but the cart is null, why
        cart.forEach((key, value) -> {
            System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
            System.out.println(" ");
            if (cart.get(key) > 0) {
                for (int i = 0; i < allEvents.size(); i++) {
                    if (allEvents.get(i).getId() == key) {
                        allEvents.get(i).setSelectedNum(cart.get(key));
                        selectedEvents.add(allEvents.get(i));
                        System.out.println("item selected");
                    } else {
                        System.out.println("item not selected");
                    }
                }
            }
        });
        oListSelected = FXCollections.observableArrayList(selectedEvents);
        System.out.println("oList:");
        for (int i = 0; i < oListSelected.size(); i++) {
            System.out.println("item" + i + " =" + oListSelected.get(i).getName());
            System.out.println("");
        }

        //prpperty value factories
        eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        numTickets.setCellValueFactory(new PropertyValueFactory<>("selectedNum"));
        cancelEvent.setCellValueFactory(new PropertyValueFactory<>("cancelButton"));
       
        for(Event eventt:selectedEvents){
            totalprice+=eventt.getPrice()*cart.get(eventt.getId());
        }
        if(is_user_student){
            double totalprice_s=totalprice*0.7;
            String s= ""+totalprice_s;
            System.out.println("Hello burdayım:"+s);
            pricelabel.setText(s);
        }
        else{
            String s= ""+totalprice;
            System.out.println("Hello burdayım:"+s);
            pricelabel.setText(s);
        }
       

        cancelEvent.setCellFactory(param -> new TableCell<Event, Event>() {
            @Override
            protected void updateItem(Event eventT, boolean empty) {

                super.updateItem(eventT, empty);
                if (!empty) {
                    HBox subPane = new HBox();
                    Button cancelButton = new Button("Cancel");
                    subPane.getChildren().addAll(cancelButton);
                    setGraphic(subPane);

                    cancelButton.setOnAction(event -> {
                        Event getevent = getTableView().getItems().get(getIndex());
                        try {
                            double totalcancelled = getevent.getPrice()*cart.get(getevent.getId());
                            totalprice-=totalcancelled;
                            if(is_user_student){
                                double totalprice_s=totalprice*0.7;
                                String s= ""+totalprice_s;                               
                                pricelabel.setText(s);
                            }
                            else{
                                String s= ""+totalprice;
                                pricelabel.setText(s);
                            }
                            cart.put(getevent.getId(),0);
                            System.out.println("removed:" + getevent.getId());
                            //print the cart so that we can see
                            updateCart(cart);
                            
                            cart.forEach((key, value) -> {
                                System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
                                System.out.println(" ");  
                            });
                            

                           
                            //renew itself since an event is removed
                        } catch (Exception errorType) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText("Error");
                            alert.showAndWait();
                        }

                    });
                }

            }
        });

        System.out.println("hello");
        CartView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        CartView.setItems(oListSelected);
    }

    private void closeScreenOpenMain(MouseEvent e) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Main_Page.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Event Screen");
            stage.setScene(scene);
            stage.show();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ex);
        }
    }

    private void showDialog(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    private void updateCart(HashMap<Integer, Integer> cart) {
        oListSelected.removeAll(oListSelected);
        cart.forEach((key, value) -> {
            System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
            System.out.println(" ");
            if (cart.get(key) > 0) {
                for (int i = 0; i < allEvents.size(); i++) {
                    if (allEvents.get(i).getId() == key) {
                        allEvents.get(i).setSelectedNum(cart.get(key));
                        //selectedEvents.add(allEvents.get(i));
                        CartView.getItems().add(allEvents.get(i));
                    } else {

                    }
                }
            }
        });

    }
}
