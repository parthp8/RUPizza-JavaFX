package com.pizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.text.DecimalFormat;

import java.io.IOException;

/**
 * Controller for the Specialty Pizza view
 * @author Jeeva Ramasamy, Parth Patel
 */
public class SpecialtyController {

    @FXML
    private ComboBox<String> specialtyList;
    @FXML
    private ImageView image;
    @FXML
    private ListView<String> toppingList;
    @FXML
    private TextField sauce, price;
    @FXML
    private RadioButton medium, large;
    @FXML
    private CheckBox extraSauce, extraCheese;

    private static final int EXTRA_SAUCE_PRICE = 1;
    private static final int EXTRA_CHEESE_PRICE = 1;

    /**
     * Initializes the view
     */
    @FXML
    protected void initialize() {
        ObservableList<String> specialties =
                FXCollections.observableArrayList("Deluxe", "Supreme",
                        "Meatzza", "Seafood", "Pepperoni");
        specialtyList.setItems(specialties);
        specialtyList.setValue("Deluxe");
        image.setImage(new Image(getClass().
                getResource("Deluxe.png").toExternalForm()));
        sauce.setText(Specialty.DELUXE.getSauce().toString());
        ObservableList<String> toppings =
                FXCollections.observableArrayList(
                        Specialty.DELUXE.getToppingList());
        toppingList.setItems(toppings);
        DecimalFormat money = new DecimalFormat("#0.00");
        price.setText(money.format(Specialty.DELUXE.getPrice()));
    }

    /**
     * Returns user to the main menu
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
     * Adjusts user's current view based on selected options
     * @param event
     */
    @FXML
    protected void onSpecialtySelect(ActionEvent event){
        String selection = specialtyList.getValue();
        Specialty specialty = getSpecialty(selection);
        image.setImage(new Image(getClass().
                getResource(selection + ".png").toExternalForm()));
        sauce.setText(specialty.getSauce().toString());
        ObservableList<String> toppings =
                FXCollections.observableArrayList(
                        specialty.getToppingList());
        toppingList.setItems(toppings);
        DecimalFormat money = new DecimalFormat("#0.00");
        price.setText(money.format(specialty.getPrice()
                + getPriceIncrease()));
    }

    /**
     * Adjusts pizza price based on selected options
     * @param event
     */
    @FXML
    protected void onOptionSelect(ActionEvent event) {
        String selection = specialtyList.getValue();
        Specialty specialty = getSpecialty(selection);
        DecimalFormat money = new DecimalFormat("#0.00");
        price.setText(money.format(specialty.getPrice()
                + getPriceIncrease()));
    }

    /**
     * Adds the pizza to the order
     * @param event
     */
    @FXML
    protected void onAddToOrderButtonClick(ActionEvent event)  {
        String selection = specialtyList.getValue();
        Pizza pizza = PizzaMaker.createPizza(selection);
        if (medium.isSelected()) {
            pizza.setSize(Size.MEDIUM);
        }
        else if (large.isSelected()) {
            pizza.setSize(Size.LARGE);
        }
        else {
            pizza.setSize(Size.SMALL);
        }
        if (extraSauce.isSelected()) {
            pizza.setExtraSauce();
        }
        if (extraCheese.isSelected()) {
            pizza.setExtraCheese();
        }
        CurrentOrderController.addPizza(pizza);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Pizza Added");
        alert.setContentText("Your pizza has been added to your order.");
        alert.showAndWait();
    }

    /**
     * Returns the Specialty object based on the selection
     * @param selection the selected option
     * @return specialty
     */
    private Specialty getSpecialty(String selection) {
        switch (selection) {
            case "Supreme":
                return Specialty.SUPREME;
            case "Meatzza":
                return Specialty.MEATZZA;
            case "Seafood":
                return Specialty.SEAFOOD;
            case "Pepperoni":
                return Specialty.PEPPERONI;
            default:
                return Specialty.DELUXE;
        }
    }

    /**
     * Returns the price increase of the pizza based on the size and extras
     * @return price_increase
     */
    private double getPriceIncrease() {
        double price_increase = 0;
        if (medium.isSelected()) {
            price_increase += Size.MEDIUM.getPriceIncrease();
        }
        else if (large.isSelected()) {
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
}
