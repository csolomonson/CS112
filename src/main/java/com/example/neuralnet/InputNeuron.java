package com.example.neuralnet;

/**
 * Special Neuron with a settable input (instead of strictly calculating from input Neurons)
 * @author Cole Solomonson
 */
public class InputNeuron extends Neuron{
    double input;

    /**
     * Simply returns the input that was previously set
     * @return The input that was directly set
     */
    @Override
    public double getOutput() {
        return input;
    }

    /**
     * Set the input of this Neuron
     * @param input The input (and also output) of this Neuron
     */
    public void setValue(double input) {
        this.input = input;
    }
}
