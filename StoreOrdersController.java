package com.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller for the Store Orders view
 * @author Jeeva Ramasamy, Parth Patel
 */
public class StoreOrdersController {
    @FXML
    private ComboBox<Integer> orderNumbers = new ComboBox<>();
    @FXML
    private TextField orderTotal;
    @FXML
    private ListView<String> pizzaList;

    private static final int FIRST_ELEMENT = 0;

    /**
     * Initializes the view
     */
    @FXML
    protected void initialize() {
        ArrayList<Order> orders = StoreOrders.getOrders();
        if (orders.isEmpty()) {
            return;
        }
        for (Order order : orders) {
            orderNumbers.getItems().add(order.getOrderNumber());
        }
        orderNumbers.setValue(orders.get(FIRST_ELEMENT).getOrderNumber());
        DecimalFormat money = new DecimalFormat("#0.00");
        orderTotal.setText(money.format(
                orders.get(FIRST_ELEMENT).getOrderTotal()));
        for (Pizza pizza : orders.get(FIRST_ELEMENT).getPizzas()) {
            pizzaList.getItems().add(pizza.toString());
        }
    }

    /**
     * Returns to the Main Menu view
     */
    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainMenuView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(loader.load(), 505, 440);
            stage.setTitle("RU Pizza <Main Menu>");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Main Menu view");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Changes the order shown when a new order is selected
     */
    @FXML
    protected void onOrderSelect(ActionEvent event) {
        if (orderNumbers.getItems().isEmpty()) {
            return;
        }
        ArrayList<Order> orders = StoreOrders.getOrders();
        int index = FIRST_ELEMENT;
        if (orderNumbers.getValue() != null) {
            int curr_order = orderNumbers.getValue();
            for (int i = FIRST_ELEMENT; i < orders.size(); ++i) {
                if (orders.get(i).getOrderNumber() == curr_order) {
                    index = i;
                }
            }
        }
        orderNumbers.setValue(orders.get(index).getOrderNumber());
        DecimalFormat money = new DecimalFormat("#0.00");
        orderTotal.setText(money.format(
                orders.get(index).getOrderTotal()));
        pizzaList.getItems().clear();
        for (Pizza pizza : orders.get(index).getPizzas()) {
            pizzaList.getItems().add(pizza.toString());
        }
    }

    /**
     * Cancels an order
     */
    @FXML
    protected void onCancelOrderButtonClick(ActionEvent event) {
        ArrayList<Order> orders = StoreOrders.getOrders();
        if (orders.isEmpty()) {
            return;
        }
        int index = FIRST_ELEMENT, curr_order = orderNumbers.getValue();
        for (int i = FIRST_ELEMENT; i < orders.size(); ++i) {
            if (orders.get(i).getOrderNumber() == curr_order) {
                index = i;
            }
        }
        orders.remove(index);
        orderNumbers.getItems().remove(index);
        if (orders.isEmpty()) {
            orderTotal.setText("");
            pizzaList.getItems().clear();
            return;
        }
        orderNumbers.setValue(orders.get(FIRST_ELEMENT).getOrderNumber());
    }

    /**
     * Exports the store's orders to a file
     */
    @FXML
    protected void onExportStoreOrdersButtonClick(ActionEvent event) {
        try {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Open Directory for the Export");
            Stage stage = new Stage();
            File selectedDirectory = chooser.showDialog(stage);
            if (selectedDirectory != null) {
                File file = new File(
                        selectedDirectory.getAbsolutePath() + "/orders.txt");
                StoreOrders.export(file);
            }
        }
        catch (Exception exp) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error exporting store orders");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
}
