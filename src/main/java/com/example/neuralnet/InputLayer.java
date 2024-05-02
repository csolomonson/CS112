package com.example.neuralnet;

public class InputLayer extends NeuralNetworkLayer{
    public InputLayer(InputNeuron[] neurons) {
        super(neurons);
    }

    public void setValues(double[] inputs) {
        if (inputs.length != size) return;
        for (int i = 0; i < size; i++) {
            InputNeuron n = (InputNeuron) getNeurons()[i];
            n.setValue(inputs[i]);
        }
    }
}
