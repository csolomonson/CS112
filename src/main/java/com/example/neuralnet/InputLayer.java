package com.example.neuralnet;

/**
 * A layer composed entirely of InputNeurons
 * @author Cole Solomonson
 */
public class InputLayer extends NeuralNetworkLayer{

    /**
     * Make a layer of entirely InputNeurons
     * @param size number of InputNeurons to put in the layer
     */
    public InputLayer(int size) {
        Neuron[] neurons = new Neuron[size];
        for (int i = 0; i < size; i++) {
            neurons[i] = new InputNeuron();
        }
        this.size = size;
        this.neurons = neurons;
        population += 1;
        this.id = population;
    }

    /**
     * Set the inputs for all the InputNeurons
     * @param inputs array of all the values to set this layer to
     */
    public void setValues(double[] inputs) {
        if (inputs.length != size) return;
        for (int i = 0; i < size; i++) {
            InputNeuron n = (InputNeuron) getNeurons()[i];
            n.setValue(inputs[i]);
        }
    }
}
