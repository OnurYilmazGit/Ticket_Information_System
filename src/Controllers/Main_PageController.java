/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Main_PageController implements Initializable {

    @FXML
    private Pane main_pane;
    private Parent fxml;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    public Main_PageController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/Views/Event_Screen.fxml"));
            main_pane.getChildren().removeAll();
            main_pane.getChildren().setAll(fxml);

            VBox vbox = FXMLLoader.load(getClass().getResource("/Views/DrawerContent.fxml"));

            drawer.setSidePane(vbox);

            for (Node node : vbox.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        switch (node.getAccessibleText()) {
                            case "main_page":
                                goToMainPage(e);
                                break;
                            case "my_resarvations":
                                goToReservationPage(e);
                                break;
                            case "log_out":
                                log_out(e);
                                break;
                        }
                    });
                }
            }

            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(Main_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void goToReservationPage(MouseEvent e) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/Views/MyReservationPage.fxml"));
            main_pane.getChildren().removeAll();
            main_pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(Main_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goToMainPage(MouseEvent e) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/Views/Event_Screen.fxml"));
            main_pane.getChildren().removeAll();
            main_pane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(Main_PageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void log_out(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Views/FXMLDocument.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

}
