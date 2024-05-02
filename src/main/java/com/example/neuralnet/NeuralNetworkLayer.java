package com.example.neuralnet;

public class NeuralNetworkLayer {
    public int size;
    private Neuron[] neurons;
    private static int population;
    private int id;
    NeuralNetworkLayer inputLayer;
    public NeuralNetworkLayer (Neuron[] neurons) {
        this.size = neurons.length;
        this.neurons = neurons;
        population += 1;
        id = population;

        //NeuralNetworkLayer outputLayer;
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

    public String toString() {
        return String.format("Neural Network Layer #%d (%d neurons)", id, size);
    }

}
