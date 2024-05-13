module com.example.neuralnet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.neuralnet to javafx.fxml;
    exports com.example.neuralnet;
    exports com.example.neuralnet.activation;
    opens com.example.neuralnet.activation to javafx.fxml;
}