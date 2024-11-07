package com.example.fuelcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Locale;

public class FuelCalculatorController {

    @FXML
    private Label titleLabel, distanceLabel, fuelLabel, resultLabel;
    @FXML
    private TextField distanceField, fuelField;
    @FXML
    private Button calculateButton;
    private ResourceBundle bundle;

    // Database connection parameters
    private static final String URL = "jdbc:mariadb://localhost:3306/fuelconsumption";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @FXML
    public void initialize() {
        // Set default language (e.g., English)
        switchToEnglish();
    }

    @FXML
    public void handleCalculate() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double fuel = Double.parseDouble(fuelField.getText());
            double consumption = (fuel / distance) * 100;

            resultLabel.setText(String.format(bundle.getString("result.label"), consumption));
            saveToDatabase(distance, fuel, consumption);
        } catch (NumberFormatException e) {
            resultLabel.setText(bundle.getString("invalid.input"));
        }
    }

    private void saveToDatabase(double distance, double fuel, double consumption) {
        String query = "INSERT INTO fuel_data (distance, fuel_used, consumption) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, distance);
            stmt.setDouble(2, fuel);
            stmt.setDouble(3, consumption);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToEnglish() {
        setLanguage("en", "US");
    }

    @FXML
    public void switchToFrench() {
        setLanguage("fr", "FR");
    }

    @FXML
    public void switchToJapanese() {
        setLanguage("ja", "JP");
    }

    @FXML
    public void switchToPersian() {
        setLanguage("fa", "IR");
    }

    private void setLanguage(String lang, String country) {
        bundle = ResourceBundle.getBundle("labels", new Locale(lang, country));

        // Update UI elements with localized text
        titleLabel.setText(bundle.getString("title"));
        distanceLabel.setText(bundle.getString("distance.label"));
        fuelLabel.setText(bundle.getString("fuel.label"));
        calculateButton.setText(bundle.getString("calculate.button"));
        resultLabel.setText("");  // Clear result label when language changes
    }
}