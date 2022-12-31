module com.example.huffmanalgrothim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.huffmanalgrothim to javafx.fxml;
    exports com.example.huffmanalgrothim;
}