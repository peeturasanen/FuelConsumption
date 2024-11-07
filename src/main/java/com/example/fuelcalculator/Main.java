package com.example.fuelcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FuelCalculator.fxml"));

            // Set default language (e.g., English)
            loader.setResources(ResourceBundle.getBundle("labels", Locale.ENGLISH));

            // Create and set the scene
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Peetu Räsänen");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Launch the JavaFX application
    }
}
