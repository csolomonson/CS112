package com.example.neuralnet;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class NeuronController {
    @FXML
    private Label message;
    @FXML
    private TextField inNum;
    static int inputs=2;
    @FXML
    private TextField hiddenNum;
    static int numHidden=1;
    @FXML
    private TextField hiddenSize;
    static int sizeHidden=4;
    @FXML
    private TextField numOut;
    static int outputs=4;
    @FXML
    private ChoiceBox<String> activationFunctionChooser;
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
    @FXML
    private BorderPane bp;

    static NeuralNetwork nn;

    private Stage primaryStage;

    @FXML
    public void initialize() {
        if (nn == null) nn  = new NeuralNetwork(inputs, numHidden, sizeHidden, outputs);
        activationFunctionChooser.getItems().addAll("Linear", "ReLu", "Leaky ReLu", "Sigmoid", "Binary Step");
        showNetwork();

    }
    @FXML
    public void updateStructure(Event event) {

        String in = inNum.getText();
        String out = numOut.getText();
        String nh = hiddenNum.getText();
        String sh = hiddenSize.getText();
        nn.setActivationFunction(activationFunctionChooser.getValue());

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

    @FXML
    public void updateActFunc(Event event) {
        nn.setActivationFunction(activationFunctionChooser.getValue());
        showNetwork();
    }

    public void showNetwork() {
        inputNeurons.getChildren().clear();
        Neuron[][] arr = nn.getNeuronArray();
        for (int i = 0; i < inputs; i++) {
            InputNeuron n = (InputNeuron) arr[0][i];
            double x = n.getOutput();
            Circle c = new Circle(12);
            c.setFill(findColor(x));
            c.setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent scrollEvent) {
                    System.out.println(scrollEvent.getDeltaY());
                    n.setValue(n.getOutput() + scrollEvent.getDeltaY()*0.001);
                    showNetwork();
                }
            });
            inputNeurons.getChildren().add(c);
        }
        hiddenLayers.getChildren().clear();
        hiddenLayers.setSpacing(12);
        for (int i = 1; i < arr.length; i++) {
            VBox layer = new VBox();
            Pane weights = new Pane();
            layer.setPrefWidth(25);
            layer.setSpacing(5);
            layer.setAlignment(Pos.CENTER);
            double layerSize = 24 * arr[i].length + 5 * (arr[i].length - 1);
            double lastLayerSize = 24 * arr[i-1].length + 5 * (arr[i-1].length - 1);
            for (int j = 0; j < arr[i].length; j++) {
                Neuron n = arr[i][j];
                Circle c = new Circle(12);
                c.setFill(findColor(n.getOutput()));
                c.setStroke(findColor(n.getBias()));
                layer.getChildren().add(c);
                for (int k = 0; k < n.getWeights().length; k++) {
                    Line w = new Line();
                    w.setStroke(findColor(n.getWeights()[k]));
                    w.setLayoutY(180);
                    w.setStartX(-12);
                    w.setEndX(12);
                    w.setEndY(12 + 29*j-layerSize/2);
                    w.setStartY(12 + 29*k-lastLayerSize/2);
                    weights.getChildren().add(w);
                }
            }
            hiddenLayers.getChildren().add(weights);
            hiddenLayers.getChildren().add(layer);
        }
    }

    public Color findColor(double x) {
        x = Math.max(Math.min(1,x),-1);
        int r = (int) (127.5 - 127.5*x);
        int b = (int) (127.5 + 127.5*x);
        int g = (int) (127.5 - 127.5*x*x);
        //System.out.println(x);
        return Color.rgb(r,g,b);
    }

    @FXML
    public void saveNN(ActionEvent e) {
        primaryStage = (Stage) bp.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        fileChooser.setTitle("Choose a file to save to");
        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        try {
            selectedFile.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(selectedFile));
            out.writeObject(nn);
            out.close();
        } catch (IOException ie) {
            message.setText("IO exception!");
        }
    }

    @FXML
    public void loadNN(ActionEvent e) throws IOException {
        primaryStage = (Stage) bp.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        fileChooser.setTitle("Choose a file to open");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile));
            nn = (NeuralNetwork) in.readObject();
            in.close();

            inputs = nn.getInputSize();
            outputs = nn.getOutputSize();
            numHidden = nn.getNumHiddenLayers();
            sizeHidden = nn.getHiddenSize();

            inNum.setText(Integer.toString(inputs));
            hiddenNum.setText(Integer.toString(numHidden));
            hiddenSize.setText(Integer.toString(sizeHidden));
            numOut.setText(Integer.toString(outputs));

            showNetwork();


        } catch (ClassNotFoundException c) {
            message.setText("That file don't work");
        }




    }


}