module com.example.testroutefinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testroutefinder to javafx.fxml;
    exports com.example.testroutefinder;
}