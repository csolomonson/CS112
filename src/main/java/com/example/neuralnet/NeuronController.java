package com.example.neuralnet;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class NeuronController {
    @FXML
    private Label message;
    @FXML
    private TextField inNum;
    static int inputs=1;
    @FXML
    private TextField hiddenNum;
    static int numHidden=1;
    @FXML
    private TextField hiddenSize;
    static int sizeHidden=2;
    @FXML
    private TextField numOut;
    static int outputs=1;
    @FXML
    private ComboBox<String> actFuncChooser;
    @FXML
    private Button randomButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button uploadButton;
    @FXML
    private VBox inputNeurons;
    @FXML
    private HBox hiddenLayers;
    @FXML
    private VBox outputNeurons;
    @FXML
    private Pane outputWeights;

    static NeuralNetwork nn;


    @FXML
    public void updateStructure(Event event) {
        if (nn == null) nn  = new NeuralNetwork(inputs, numHidden, sizeHidden, outputs);
        String in = inNum.getText();
        String out = numOut.getText();
        String nh = hiddenNum.getText();
        String sh = hiddenSize.getText();

        try {
            inputs = Integer.parseInt(in);
            outputs = Integer.parseInt(out);
            numHidden = Integer.parseInt(nh);
            sizeHidden = Integer.parseInt(sh);

            nn.setHyperParameters(inputs,numHidden,sizeHidden,outputs);
            message.setText("");
            showNetwork();
        } catch (NumberFormatException nfe) {
            message.setText("Non-numerical inputs detected");
        } catch (IllegalArgumentException iae) {
            message.setText(iae.getMessage());
        }

    }

    public void showNetwork() {
        inputNeurons.getChildren().clear();
        for (int i = 0; i < inputs; i++) {
            inputNeurons.getChildren().add(new Circle(12));
        }
    }


}