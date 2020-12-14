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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import tis_fx.EventDAOImpl;

public class Event_ScreenController implements Initializable {

    @FXML
    private TableView<Event> tableView;

    @FXML
    private TextField search;
    EventDAOImpl eventDAOImpl = new EventDAOImpl();

    @FXML
    private Pane cart_pane;
    private Parent fxml;

    @FXML
    private void showCart(MouseEvent e) {
        /*    System.out.println("ERROR");
        try {
            fxml = FXMLLoader.load(getClass().getResource("/Views/Cart.fxml"));
            cart_pane.getChildren().removeAll();
            cart_pane.getChildren().setAll(fxml);
        } 
        catch (IOException exc) {
            Logger.getLogger(Main_PageController.class.getName()).log(Level.SEVERE,"Hata var", exc);
        }
         */
        try {
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
        }
    }

    public static HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>();

    public static HashMap<Integer, Integer> getCartMap() {
        return Event_ScreenController.cart;
    }

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
    }

    public void addToCart(int e, int add) {
        cart.put(e, cart.get(e) + add);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Event iterEvent : allEvents) {
            cart.put(iterEvent.getId(), 0);
        }

        TableColumn nameCol = new TableColumn("Name");
        TableColumn typeCol = new TableColumn("Type");
        TableColumn locationCol = new TableColumn("Location");
        TableColumn startTimeCol = new TableColumn("Start Time");
        TableColumn dateCol = new TableColumn("Date");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn availableTicketsCol = new TableColumn("Available Tickets");
        TableColumn addItem = new TableColumn("Add Event");

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
                    Button addButton = new Button("Add");
                    TextField numberField = new TextField();
                    numberField.setMaxWidth(70);
                    pane.getChildren().addAll(numberField, addButton);
                    setGraphic(pane);

                    addButton.setOnAction(event -> {
                        Event getevent = getTableView().getItems().get(getIndex());

                        try {
                            int numberOfTicketsAdded = Integer.parseInt(numberField.getText());
                            if (numberOfTicketsAdded > 0 && numberOfTicketsAdded <= getevent.getAvailableTickets()) {
                                addToCart(getevent.getId(), numberOfTicketsAdded);
                                getevent.setAvailableTickets(getevent.getAvailableTickets() - numberOfTicketsAdded);
                                System.out.println(getevent.getAvailableTickets());
                                showDialog("The event was added your cart!");
                                cart.forEach((key, value) -> {
                                    System.out.println(String.valueOf(key) + " - " + String.valueOf(value));
                                    System.out.println(" ");
                                    numberField.setText("");
                                });
                            } else {
                                throw new Exception("Number should be bigger than 0 or smaller than avilable tickets");
                            }
                        } catch (Exception errorType) {
                            showDialog("Please give proper type for ticket number");
                             numberField.setText("");
                        }
                    });
                }

            }
        });

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.setItems(oListEvents);

    }

    private void showDialog(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }
}
