package com.example.neuralnet.activation;

/**
 * Outputs zero for negative inputs
 * @author Cole Solomonson
 */
public class ReLu extends ActivationFunction {
    public ReLu(){}
    @Override
    public double func(double in) {
        return Math.max(in, 0);
    }
}
