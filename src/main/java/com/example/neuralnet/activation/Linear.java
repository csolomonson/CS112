package com.example.neuralnet.activation;

/**
 * I'ts not *really* an activation function; just returns the input
 * @author Cole Solomonson
 */
public class Linear extends ActivationFunction{
    public Linear() {}
    @Override
    public double func(double in) {
        return in;
    }
}
