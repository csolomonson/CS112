package com.example.neuralnet.activation;

/**
 * A smooth function with a range between 0 and 1. Inputs of large negative numbers are close to 0, and large
 * positive numbers are close to 1
 * @author Cole Solomonson
 */
public class Sigmoid extends ActivationFunction{
    public Sigmoid(){}
    @Override
    public double func(double in) {
        return 1/(1+Math.pow(Math.E,-in));
    }
}
