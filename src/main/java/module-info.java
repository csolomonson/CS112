module com.example.neuralnet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.neuralnet to javafx.fxml;
    exports com.example.neuralnet;
}