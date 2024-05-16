package com.example.neuralnet;

import com.example.neuralnet.activation.*;

import java.io.Serializable;

/**
 * Network of many layers
 * @author Cole Solomonson
 */
public class NeuralNetwork implements Serializable {
    private InputLayer inputs;
    private NeuralNetworkLayer[] hiddenLayers;
    private NeuralNetworkLayer outputLayer;
    private int inputSize;
    private int outputSize;
    private int numHiddenLayers;
    private int hiddenSize;

    private ActivationFunction activationFunction;

    /**
     * Create a network of a given shape
     * @param inputSize Number of InputNeurons in the InputLayer
     * @param numHiddenLayers number of hidden NeuralNetworkLayers to add
     * @param hiddenSize number of Neurons for each hidden layer
     * @param outputSize number of Neurons in the final layer
     */
    public NeuralNetwork(int inputSize, int numHiddenLayers, int hiddenSize, int outputSize) {
        setHyperParameters(inputSize, numHiddenLayers, hiddenSize, outputSize);
        activationFunction = new Linear();
    }

    /**
     * Create new InputLayer, hidden layers, and output layer.
     * Re-randomizes weights and biases, and ensures that everything is the right size
     */
    public void updateStructure() {
        hiddenLayers = new NeuralNetworkLayer[numHiddenLayers];

        this.inputs = new InputLayer(inputSize);
        this.outputLayer = new NeuralNetworkLayer(outputSize);

        for (int i = 0; i < numHiddenLayers; i++) {
            hiddenLayers[i] = new NeuralNetworkLayer(this.hiddenSize);
            if (i == 0) hiddenLayers[i].setInputLayer(inputs);
            else hiddenLayers[i].setInputLayer(hiddenLayers[i-1]);
        }
        outputLayer.setInputLayer(hiddenLayers[numHiddenLayers-1]);
        updateActivation();

    }

    /**
     * Set the number and size of layers
     * @param inputSize Number of InputNeurons
     * @param numHiddenLayers Number of hidden NeuralNetworkLayers
     * @param hiddenSize Number of Neurons per hidden layer
     * @param outputSize Number of Neurons in the final layer
     * @throws IllegalArgumentException Thrown if any of the inputs are too large, or less than 1
     */
    public void setHyperParameters(int inputSize, int numHiddenLayers, int hiddenSize, int outputSize) throws IllegalArgumentException{
        setInputSize(inputSize);
        setHiddenSize(hiddenSize);
        setNumHiddenLayers(numHiddenLayers);
        setOutputSize(outputSize);
        updateStructure();
    }

    /**
     * Set number of input neurons
     * @param inputSize Number of InputNeurons in the InputLayer
     * @throws IllegalArgumentException Thrown if inputSize is not between 1 and 10, inclusive
     */
    public void setInputSize(int inputSize) throws IllegalArgumentException{
        if (inputSize < 1 || inputSize > 10) throw new IllegalArgumentException("Invalid input size");
        this.inputSize = inputSize;
    }

    /**
     * Return number of inputs
     * @return Number of InputNeurons in the InputLayer
     */
    public int getInputSize() {
        return inputSize;
    }

    /**
     * Return number of outputs
     * @return Number of Neurons in the last layer
     */
    public int getOutputSize() {
        return outputSize;
    }

    /**
     * Return number of hidden layers
     * @return number of hidden layers
     */
    public int getNumHiddenLayers() {
        return numHiddenLayers;
    }

    /**
     * Return size of each hidden layer
     * @return number of Neurons in each hidden layer
     */
    public int getHiddenSize() {
        return hiddenSize;
    }

    /**
     * Change number of hidden layers
     * @param numHiddenLayers number of hidden layers
     * @throws IllegalArgumentException Thrown when value is not between 1 and 5, inclusive
     */
    public void setNumHiddenLayers(int numHiddenLayers) throws IllegalArgumentException {
        if (numHiddenLayers < 1) throw new IllegalArgumentException("Invalid number of hidden layers");
        if (numHiddenLayers > 5) throw new IllegalArgumentException("Too many hidden layers (>= 5, please)");
        this.numHiddenLayers = numHiddenLayers;
    }

    /**
     * Set size of each hidden layer
     * @param hiddenSize Number of Neurons to put in each hidden layer
     * @throws IllegalArgumentException Thrown when input is not between 1 and 10, inclusive
     */
    public void setHiddenSize(int hiddenSize) throws IllegalArgumentException{
        if (hiddenSize < 1 || hiddenSize > 10) throw new IllegalArgumentException("Invalid hidden layer size");
        this.hiddenSize = hiddenSize;
    }

    /**
     * Size of the last layer
     * @param outputSize Number of neurons to chuck into the last layer
     * @throws IllegalArgumentException Thrown when input is not between 1 and 10, inclusive
     */
    public void setOutputSize(int outputSize) throws IllegalArgumentException{
        if (outputSize < 1 || outputSize > 10) throw new IllegalArgumentException("Invalid output size");
        this.outputSize = outputSize;
    }

    /**
     * Update all the activations of all the Neurons, starting from the input
     */
    public void step() {
        for (NeuralNetworkLayer l : hiddenLayers) {
            l.update();
        }
        outputLayer.update();
    }

    /**
     * Get outputs based on given inputs
     * @param input Array of the values to set to each input
     * @return Outputs of the network
     */
    public double[] infer(double[] input) {
        this.inputs.setValues(input);
        step();
        return outputLayer.getOutputs();
    }

    /**
     * Get a 2d array representation of all the Neurons in the network, with rows corresponding to layers, and the
     * input layer at the top
     * @return Sweet 2d array representation of this network
     */
    public Neuron[][] getNeuronArray() {
        Neuron[][] arr = new Neuron[numHiddenLayers+2][];
        arr[0] = inputs.getNeurons();
        for (int i = 0; i < numHiddenLayers; i++) {
            arr[i+1] = hiddenLayers[i].getNeurons();
        }
        arr[numHiddenLayers+1] = outputLayer.getNeurons();
        return arr;
    }

    /**
     * Goes through and sets all the Neurons' activation function
     * @param activationFunctionName String name of activation function (as is written in the ChoiceBox)
     */
    public void setActivationFunction(String activationFunctionName) {
        if (activationFunctionName == null) return;
        switch (activationFunctionName) {
            case "Linear":
                this.activationFunction = new Linear();
                break;
            case "ReLu":
                this.activationFunction = new ReLu();
                break;
            case "Leaky ReLu":
                this.activationFunction = new LeakyReLu();
                break;
            case "Binary Step":
                this.activationFunction = new BinaryStep();
                break;
            case "Sigmoid":
                this.activationFunction = new Sigmoid();
                break;
        }
        updateActivation();
    }

    /**
     * Makes sure that the activation function of every Neuron is that of this network
     */
    public void updateActivation() {
        if (activationFunction == null) activationFunction = new Linear();
        for (Neuron[] l: getNeuronArray()) {
            for (Neuron n : l) {

                n.setActivationFunction(activationFunction);

            }
        }
        step();
    }

}
