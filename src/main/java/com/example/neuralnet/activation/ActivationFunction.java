package com.example.neuralnet.activation;

import java.io.Serializable;

/**
 * A class for activation functions to apply to the weighted sum of a Neuron's inputs before outputting it to the
 * next layer.
 * @author Cole Solomonson
 */
public abstract class ActivationFunction implements Serializable {
    /**
     * The actual function that gets applied in the calculation of a Neuron's activation
     * @param in The weighted sum of the Neuron's inputs
     * @return The value to output
     */
    public abstract double func(double in);
}
