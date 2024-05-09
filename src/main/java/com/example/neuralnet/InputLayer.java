package com.example.neuralnet;

public class InputLayer extends NeuralNetworkLayer{
    public InputLayer(InputNeuron[] neurons) {
        super(neurons);
    }
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

    public void setValues(double[] inputs) {
        if (inputs.length != size) return;
        for (int i = 0; i < size; i++) {
            InputNeuron n = (InputNeuron) getNeurons()[i];
            n.setValue(inputs[i]);
        }
    }
}
