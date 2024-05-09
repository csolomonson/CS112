package com.example.neuralnet;

public class NeuralNetwork {
    private InputLayer inputs;
    private NeuralNetworkLayer[] hiddenLayers;
    private NeuralNetworkLayer outputLayer;
    private int inputSize;
    private int outputSize;
    private int numHiddenLayers;
    private int hiddenSize;

    public NeuralNetwork(int inputSize, int numHiddenLayers, int hiddenSize, int outputSize) {
        setHyperParameters(inputSize, numHiddenLayers, hiddenSize, outputSize);
    }

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
    }

    public void setHyperParameters(int inputSize, int numHiddenLayers, int hiddenSize, int outputSize) throws IllegalArgumentException{
        setInputSize(inputSize);
        setHiddenSize(hiddenSize);
        setNumHiddenLayers(numHiddenLayers);
        setOutputSize(outputSize);
        updateStructure();
    }

    public void setInputSize(int inputSize) throws IllegalArgumentException{
        if (inputSize < 1) throw new IllegalArgumentException("Invalid input size");
        this.inputSize = inputSize;
    }
    public void setNumHiddenLayers(int numHiddenLayers) {
        if (numHiddenLayers < 1) throw new IllegalArgumentException("Invalid number of hidden layers");
        this.numHiddenLayers = numHiddenLayers;
    }
    public void setHiddenSize(int hiddenSize) throws IllegalArgumentException{
        if (hiddenSize < 1) throw new IllegalArgumentException("Invalid hidden layer size");
        this.hiddenSize = hiddenSize;
    }
    public void setOutputSize(int outputSize) throws IllegalArgumentException{
        if (outputSize < 1) throw new IllegalArgumentException("Invalid output size");
        this.outputSize = outputSize;
    }

    public void step() {
        for (NeuralNetworkLayer l : hiddenLayers) {
            l.update();
        }
        outputLayer.update();
    }

    public double[] infer(double[] input) {
        this.inputs.setValues(input);
        step();
        return outputLayer.getOutputs();
    }

    public Neuron[][] getNeuronArray() {
        Neuron[][] arr = new Neuron[numHiddenLayers+2][];
        arr[0] = inputs.getNeurons();
        for (int i = 0; i < numHiddenLayers; i++) {
            arr[i+1] = hiddenLayers[i].getNeurons();
        }
        arr[numHiddenLayers+1] = outputLayer.getNeurons();
        return arr;
    }

}
