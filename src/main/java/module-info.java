module FuelConsumptionCalculator {
    // Requires JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;

    // Requires Java SQL module for database operations
    requires java.sql;

    // Opens the package containing the controller to JavaFX (for FXML access)
    opens com.example.fuelcalculator to javafx.fxml;

    // Exports the main application package
    exports com.example.fuelcalculator;
}
