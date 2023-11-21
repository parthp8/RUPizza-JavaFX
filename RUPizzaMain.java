package com.pizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class for the RU Pizza application
 * @author Jeeva Ramasamy, Parth Patel
 */
public class RUPizzaMain extends Application {
    /**
     * Launches the Main Menu view of the application
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    RUPizzaMain.class.getResource("MainMenuView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 505, 440);
            stage.setTitle("RU Pizza <Main Menu>");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading Main Menu");
            alert.setContentText("Error loading Main Menu: "
                    + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Launches the application
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch();
    }
}