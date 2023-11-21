package com.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller for the Current Order view.
 * @author Jeeva Ramasamy, Parth Patel
 */
public class CurrentOrderController {
    @FXML
    private TextField orderNumber, subtotal, salesTax, orderTotal;
    @FXML
    private ListView<String> orderList;

    private static ArrayList<Pizza> pizzas = new ArrayList<>();
    private static final double NJ_SALES_TAX = 0.06625;

    /**
     * Initializes the view.
     */
    @FXML
    protected void initialize() {
        orderNumber.setText(String.valueOf(StoreOrders.getNextOrderNum()));
        double sub = 0;
        for (Pizza pizza : pizzas) {
            sub += pizza.price();
            orderList.getItems().add(pizza.toString());
        }
        DecimalFormat money = new DecimalFormat("#0.00");
        subtotal.setText(money.format(sub));
        salesTax.setText(money.format(sub * NJ_SALES_TAX));
        orderTotal.setText(money.format(sub + (sub * NJ_SALES_TAX)));
    }

    /**
     * Returns to the Main Menu view.
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
     * Removes the pizza from the order.
     * @param event
     */
    @FXML
    protected void onRemovePizzaButtonClick(ActionEvent event) {
        int index = orderList.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            pizzas.remove(index);
            orderList.getItems().clear();
            initialize();
        }
    }

    /**
     * Places the order.
     * @param event
     */
    @FXML
    protected void onPlaceOrderButtonClick(ActionEvent event) {
        if (!pizzas.isEmpty()) {
            Order order = new Order(StoreOrders.getNextOrderNum(),
                    Double.parseDouble(orderTotal.getText()), pizzas);
            StoreOrders.addOrder(order);
            pizzas = new ArrayList<>();
            orderList.getItems().clear();
            initialize();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Order placed");
            alert.setContentText("Your order has been placed.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No pizzas in order");
            alert.setContentText("Please add pizzas to the order before placing it.");
            alert.showAndWait();
        }
    }

    /**
     * Adds the pizza to the order.
     * @param pizza
     */
    protected static void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
}
