package com.example.neuralnet;

public class InputNeuron extends Neuron{
    double input;
    @Override
    public double getOutput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }
}
