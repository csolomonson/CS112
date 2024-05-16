package com.example.neuralnet.activation;

/**
 * ActivationFunction that returns 1 for inputs above 0.5, and 0 otherwise
 * @author Cole Solomonson
 */
public class BinaryStep extends ActivationFunction{
    public BinaryStep() {}
    @Override
    public double func(double in) {
        if (in < 0.5) return 0;
        return 1;
    }
}
