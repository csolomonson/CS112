package com.example.neuralnet;

import com.example.neuralnet.activation.ActivationFunction;

import java.io.Serializable;

/**
 * Full layer of Neurons. Connects to an input layer, and calculates activation for every Neuron
 * @author Cole Solomonson
 */
public class NeuralNetworkLayer implements Serializable {
    public int size;
    protected Neuron[] neurons;
    protected static int population;
    protected int id;
    NeuralNetworkLayer inputLayer;

    /**
     * Create a layer out of an array of Neurons
     * @param neurons Array of Neurons that will comprise the layer
     */
    public NeuralNetworkLayer (Neuron[] neurons) {
        this.size = neurons.length;
        this.neurons = neurons;
        population += 1;
        id = population;

        //NeuralNetworkLayer outputLayer;
    }

    /**
     * Creates a layer of new Neurons of a given size, with random weights and biases
     * @param size Number of Neurons in this layer
     */
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

    /**
     * Creates an empty layer with no Neurons.
     */
    public NeuralNetworkLayer() {
        this(new Neuron[0]);
    }

    /**
     * Get the array of all the Neurons in the layer
     * @return array of all Neurons in the layer
     */
    public Neuron[] getNeurons(){
        return neurons;
    }

    /**
     * Set the layer of inputs, which will act as inputs to every Neuron in this layer
     * @param input Layer of Neurons to act as inputs to this layer
     */
    public void setInputLayer(NeuralNetworkLayer input) {
        inputLayer = input;
        for (Neuron neuron : neurons) {
            neuron.setInputs(input.getNeurons());
        }
    }

    /**
     * Recalculate the activation of every Neuron, based on current input activations
     */
    public void update() {
        for (Neuron n : neurons) {
            n.calculate();
        }
    }

    /**
     * Get all of the activations of the Neurons in this layer
     * @return Array of double activations for every Neuron
     */
    public double[] getOutputs() {
        double[] outs = new double[size];
        for (int i = 0; i < size; i++) {
            outs[i] = neurons[i].getOutput();
        }
        return outs;
    }

    /**
     * Represent the layer with id and number of neurons
     * @return String representation containing id and number of neurons
     */
    public String toString() {
        return String.format("Neural Network Layer #%d (%d neurons)", id, size);
    }

}
