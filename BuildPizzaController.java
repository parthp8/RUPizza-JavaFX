package com.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller for the Build Pizza view
 * @author Jeeva Ramasamy, Parth Patel
 */
public class BuildPizzaController {

    @FXML
    private ComboBox<String> sizeList;
    @FXML
    private RadioButton tomato;
    @FXML
    private CheckBox extraSauce, extraCheese;
    @FXML
    private ListView<String> additionalToppings, selectedToppings;
    @FXML
    private TextField price;
    @FXML
    private Label toppingAlert;

    private static final int MAX_TOPPINGS = 7;
    private static final int FREE_TOPPINGS = 3;
    private static final double PRICE_PER_TOPPING = 1.49;
    private static final double STARTING_PRICE = 8.99;
    private static final int EXTRA_SAUCE_PRICE = 1;
    private static final int EXTRA_CHEESE_PRICE = 1;

    /**
     * Initializes the view
     */
    @FXML
    protected void initialize() {
        sizeList.getItems().addAll(Size.SMALL.toString(),
                Size.MEDIUM.toString(), Size.LARGE.toString());
        sizeList.setValue(Size.SMALL.toString());
        for (Topping topping : Topping.values()) {
            additionalToppings.getItems().add(topping.toString());
        }
        onOptionSelect(null);
    }

    /**
     * Returns to the main menu
     * @param event
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
     * Adds the selected topping to selected options list
     * @param event
     */
    @FXML
    protected void onAddButtonClick(ActionEvent event) {
        String selection = additionalToppings.
                getSelectionModel().getSelectedItem();
        if (selectedToppings.getItems().size() >= MAX_TOPPINGS) {
            toppingAlert.setVisible(true);
            return;
        }
        if (selection != null) {
            selectedToppings.getItems().add(selection);
            additionalToppings.getItems().remove(selection);
        }
        onOptionSelect(event);
    }

    /**
     * Removes the selected topping from the selected options list
     * @param event
     */
    @FXML
    protected void onRemoveButtonClick(ActionEvent event) {
        String selection = selectedToppings.
                getSelectionModel().getSelectedItem();
        if (selection != null) {
            additionalToppings.getItems().add(selection);
            selectedToppings.getItems().remove(selection);
            toppingAlert.setVisible(false);
        }
        onOptionSelect(event);
    }

    /**
     * Changes the price based on the size of the pizza
     * @param event
     */
    @FXML
    protected void onOptionSelect(ActionEvent event) {
        DecimalFormat money = new DecimalFormat("#0.00");
        price.setText(money.format(STARTING_PRICE
                + getPriceIncrease()));
    }

    /**
     * Adds the pizza to the order
     * @param event
     */
    @FXML
    protected void onAddToOrderButtonClick(ActionEvent event)  {
        if (selectedToppings.getItems().size() < FREE_TOPPINGS) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not Enough Toppings");
            alert.setContentText("You must select at least 3 toppings.");
            alert.showAndWait();
            return;
        }
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        ArrayList<Topping> toppingList = new ArrayList<Topping>();
        for (String topping : selectedToppings.getItems()) {
            for (Topping t : Topping.values()) {
                if (t.toString().equals(topping)) {
                    toppingList.add(t);
                    break;
                }
            }
        }
        pizza.setToppings(toppingList);
        applySelections(pizza);
        CurrentOrderController.addPizza(pizza);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Pizza Added");
        alert.setContentText("Your pizza has been added to your order.");
        alert.showAndWait();
    }

    /**
     * Returns the price increase of the pizza based on the size and extras
     * @return price_increase
     */
    private double getPriceIncrease() {
        double price_increase = 0;
        int currentToppings = selectedToppings.getItems().size();
        if (currentToppings > FREE_TOPPINGS) {
            price_increase += (currentToppings - FREE_TOPPINGS)
                    * PRICE_PER_TOPPING;
        }
        if (sizeList.getValue().equals(Size.MEDIUM.toString())) {
            price_increase += Size.MEDIUM.getPriceIncrease();
        }
        else if (sizeList.getValue().equals(Size.LARGE.toString())) {
            price_increase += Size.LARGE.getPriceIncrease();
        }
        if (extraSauce.isSelected()) {
            price_increase += EXTRA_SAUCE_PRICE;
        }
        if (extraCheese.isSelected()) {
            price_increase += EXTRA_CHEESE_PRICE;
        }
        return price_increase;
    }

    /**
     * Applies the selections to the pizza
     * @param pizza
     */
    private void applySelections(Pizza pizza) {
        if (sizeList.getValue().equals(Size.MEDIUM.toString())) {
            pizza.setSize(Size.MEDIUM);
        }
        else if (sizeList.getValue().equals(Size.LARGE.toString())) {
            pizza.setSize(Size.LARGE);
        }
        else {
            pizza.setSize(Size.SMALL);
        }
        if (tomato.isSelected()) {
            pizza.setSauce(Sauce.TOMATO);
        }
        else {
            pizza.setSauce(Sauce.ALFREDO);
        }
        if (extraSauce.isSelected()) {
            pizza.setExtraSauce();
        }
        if (extraCheese.isSelected()) {
            pizza.setExtraCheese();
        }
    }
}
