/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Event;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tis_fx.HoldEvent;

import tis_fx.EventDAOImpl;
import tis_fx.HoldCart;
import tis_fx.UserName;

public class Event_ScreenController implements Initializable {

    @FXML
    private TableView<Event> tableView;
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField search;
    EventDAOImpl eventDAOImpl = new EventDAOImpl();

    TableColumn nameCol = new TableColumn("Name");
    TableColumn typeCol = new TableColumn("Type");
    TableColumn locationCol = new TableColumn("Location");
    TableColumn startTimeCol = new TableColumn("Start Time");
    TableColumn dateCol = new TableColumn("Date");
    TableColumn priceCol = new TableColumn("Price");
    TableColumn availableTicketsCol = new TableColumn("Available Tickets");
    TableColumn addItem = new TableColumn("Add Event");

    //for test cases
    TextField numberField;
    Button addButton;

    //private HashMap<Integer, Integer> cart = HoldCart.getInstance().getCart();
    HashMap<Integer, Integer> cart = new HashMap();

    public void addToCart(int e, int add) {
        cart.put(e, cart.get(e) + add);
        HoldCart.getInstance().setCart(cart);
    }

    @FXML
    private void showCart(MouseEvent e) {
        //  HoldCart.getInstance().setCart(cart);
        HoldEvent.getInstance().setEvent(e);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Cart.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("CART");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException error) {
            error.printStackTrace();
        }

        /* try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/Cart.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("CART");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }*/
    }

    /*    public static HashMap<Integer, Integer> getCartMap() {
        return Event_ScreenController.cart;
    }
     */
    List<Event> allEvents = eventDAOImpl.getAllEvents();

    ObservableList<Event> oListEvents = FXCollections.observableArrayList(allEvents);

    FilteredList<Event> filter = new FilteredList(oListEvents, e -> true);

    @FXML
    private void search(KeyEvent event) {
        search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Event events) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (events.getName().toLowerCase().contains(newValue.toLowerCase())
                        || events.getLocation().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });

        });

        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
        addItem.setCellFactory(param -> new TableCell<Event, Event>() {

            @Override
            protected void updateItem(Event eventT, boolean empty) {
                super.updateItem(eventT, empty);

                if (!empty) {
                    HBox pane = new HBox();
                    Button addButton = new Button("Add");
                    numberField = new TextField();
                    numberField.setPromptText("max.10");
                    numberField.setFocusTraversable(false);
                    numberField.setMaxWidth(70);
                    pane.getChildren().addAll(numberField, addButton);
                    setGraphic(pane);

                    addButton.setOnAction(event -> {
                        Event getevent = getTableView().getItems().get(getIndex());
                        try{
                        int numberOfTicketsAdded = Integer.parseInt(numberField.getText());
                           try {
                            if (checkCorrectReq(numberOfTicketsAdded, getevent)) {
                                showDialog("The event was added your cart!");
                                addToCart(getevent.getId(), numberOfTicketsAdded);
                            } else if (checkGreaterThan10(numberOfTicketsAdded, getevent)) {
                                showDialog("You can book up to 10 tickets!");
                            } else if (checkGreaterThanAvailableTickets(numberOfTicketsAdded, getevent)) {
                                showDialog("There aren't enough tickets for this reservation.");
                            } else if (checkIsZero(numberOfTicketsAdded, getevent)) {
                                showDialog("Please provide a ticket number to make a reservation");
                            } else if (checkNegativeNumber(numberOfTicketsAdded, getevent)){
                               showDialog("Please enter a positive ticket number to make a reservation");
                            } 
                            else {
                                showDialog("Error!");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(Event_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        } catch(Exception e){
                            showDialog("Please give an integer number");
                        }
                        numberField.setText("");
                    });
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        welcomeLabel.setText(UserName.getInstance().getUser());

        for (Event iterEvent : allEvents) {
            cart.put(iterEvent.getId(), 0);
        }

        tableView.setItems(oListEvents);

        tableView.getColumns().addAll(nameCol, typeCol, locationCol, startTimeCol, dateCol, priceCol, availableTicketsCol, addItem);

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
        priceCol.setCellValueFactory(new PropertyValueFactory<Event, Double>("price"));
        // priceCol.setMaxWidth(200);
        availableTicketsCol.setCellValueFactory(new PropertyValueFactory<Event, Integer>("availableTickets"));
        //availableTicketsCol.setMaxWidth(400);

        addItem.setCellValueFactory(new PropertyValueFactory<>("addButton"));

        addItem.setCellFactory(param -> new TableCell<Event, Event>() {

            @Override
            protected void updateItem(Event eventT, boolean empty) {
                super.updateItem(eventT, empty);

                if (!empty) {
                    HBox pane = new HBox();
                    addButton = new Button("Add");
                    TextField numberField = new TextField();
                    numberField.setPromptText("max.10");
                    numberField.setFocusTraversable(false);
                    numberField.setMaxWidth(70);
                    pane.getChildren().addAll(numberField, addButton);
                    setGraphic(pane);

                    addButton.setOnAction(event -> {
                        Event getevent = getTableView().getItems().get(getIndex());
                           try{
                        int numberOfTicketsAdded = Integer.parseInt(numberField.getText());
                           try {
                            if (checkCorrectReq(numberOfTicketsAdded, getevent)) {
                                showDialog("The event was added your cart!");
                                addToCart(getevent.getId(), numberOfTicketsAdded);
                            } else if (checkGreaterThan10(numberOfTicketsAdded, getevent)) {
                                showDialog("You can book up to 10 tickets!");
                            } else if (checkGreaterThanAvailableTickets(numberOfTicketsAdded, getevent)) {
                                showDialog("There aren't enough tickets for this reservation.");
                            } else if (checkIsZero(numberOfTicketsAdded, getevent)) {
                                showDialog("Please provide a ticket number to make a reservation");
                            } else if (checkNegativeNumber(numberOfTicketsAdded, getevent)){
                               showDialog("Please enter a positive ticket number to make a reservation");
                            } 
                            else {
                                showDialog("Error!");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(Event_ScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        } catch(Exception e){
                            showDialog("Please give an integer number");
                        }
                        numberField.setText("");
                    });
                }

            }
        });

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    private boolean showDialog(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();

        return false;
    }

    void closeScreenMain() {
        System.out.println("closeScreenMain reached");
        final Node source = (Node) HoldEvent.getInstance().getEvent().getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

   /* boolean checkNumTickets(int numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        try {
            if (numberOfTicketsAdded <= 10 && numberOfTicketsAdded > 0 && numberOfTicketsAdded <= getevent.getAvailableTickets()) {

                // showDialog("The event was added your cart!");
                cart.forEach((key, value) -> {
                    System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
                    System.out.println(" ");
                    System.out.println("1111111");
                });
                status = true;
            } else if (numberOfTicketsAdded > 10) {
                //  showDialog("You can book up to 10 tickets!");
                System.out.println("22222222");
                status = false;

            } else if (numberOfTicketsAdded > getevent.getAvailableTickets()) {
                // showDialog("There aren't enough tickets for this reservation.");
                System.out.println("3333333");

                status = false;
            } else if (numberOfTicketsAdded == 0) {
                // showDialog("Please provide a ticket number to make a reservation");
                System.out.println("444444444");

                status = true;
            } else {
                System.out.println("5555");
                System.out.println(getevent.getId());
                System.out.println(getevent.getName());
                status = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }*/

    boolean checkCorrectReq(int numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        try {
            if (numberOfTicketsAdded <= 10 && numberOfTicketsAdded > 0 && numberOfTicketsAdded <= getevent.getAvailableTickets()) {
                // showDialog("The event was added your cart!");
                cart.forEach((key, value) -> {
                    System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
                    System.out.println(" ");
                    System.out.println("1111111");
                });
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    boolean checkGreaterThan10(int numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        try {
            if (numberOfTicketsAdded > 10) {
                //  showDialog("You can book up to 10 tickets!");
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    boolean checkGreaterThanAvailableTickets(int numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        try {
            if (numberOfTicketsAdded > getevent.getAvailableTickets()) {
                // showDialog("There aren't enough tickets for this reservation.");
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    boolean checkIsZero(int numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        try {
            if (numberOfTicketsAdded == 0) {
                // showDialog("Please provide a ticket number to make a reservation");
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    boolean checkNegativeNumber(int numberOfTicketsAdded, Event getevent){
        boolean status = false;
        try {
            if(numberOfTicketsAdded < 0 ){
                status = true;
            }
        } catch(Exception e){e.printStackTrace();}
        return status;
    }

    boolean checkNumTickets(String numberOfTicketsAdded, Event getevent) {
        boolean status = false;
        return status;
    }
}
