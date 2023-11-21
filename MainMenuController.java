package com.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Main Menu view
 * @author Jeeva Ramasamy, Parth Patel
 */
public class MainMenuController {

    /**
     * Opens the Speciality Pizza view
     */
    @FXML
    protected void onSpecialtyButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("SpecialtyView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(loader.load(), 505, 440);
            stage.setTitle("Order Speciality Pizza");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Specialty Pizza view");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Opens the Build Your Own Pizza view
     */
    @FXML
    protected void onBuildPizzaButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("BuildPizzaView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(loader.load(), 505, 440);
            stage.setTitle("Customize your Pizza");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Build Your Own Pizza view");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }

    }

    /**
     Opens the Speciality view
     */
    @FXML
    protected void onCurrentOrderButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("CurrentOrderView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(loader.load(), 505, 440);
            stage.setTitle("Order Details");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Current Order view");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Opens the Store Orders view
     */
    @FXML
    protected void onStoreOrdersButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("StoreOrdersView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(loader.load(), 505, 440);
            stage.setTitle("Store Orders");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Store Orders view");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }
}