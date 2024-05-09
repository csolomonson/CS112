package com.example.neuralnet;

public class NeuralNetworkLayer {
    public int size;
    protected Neuron[] neurons;
    protected static int population;
    protected int id;
    NeuralNetworkLayer inputLayer;
    public NeuralNetworkLayer (Neuron[] neurons) {
        this.size = neurons.length;
        this.neurons = neurons;
        population += 1;
        id = population;

        //NeuralNetworkLayer outputLayer;
    }

    public NeuralNetworkLayer (int size) {
        Neuron[] neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = new Neuron();
        }
        this.size = size;
        this.neurons = neurons;
        population += 1;
        this.id = population;
    }
    public NeuralNetworkLayer() {
        this(new Neuron[0]);
    }

    public Neuron getNeuron(int index) {
        if (index >= 0 && index < size) {
            return neurons[index];
        }
        return null;
    }

    public Neuron[] getNeurons(){
        return neurons;
    }

    public void setInputLayer(NeuralNetworkLayer input) {
        inputLayer = input;
        for (Neuron neuron : neurons) {
            neuron.setInputs(input.getNeurons());
        }
    }

    public void update() {
        for (Neuron n : neurons) {
            n.calculate();
        }
    }

    public double[] getOutputs() {
        double[] outs = new double[size];
        for (int i = 0; i < size; i++) {
            outs[i] = neurons[i].getOutput();
        }
        return outs;
    }

    public String toString() {
        return String.format("Neural Network Layer #%d (%d neurons)", id, size);
    }

}
