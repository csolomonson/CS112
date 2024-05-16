package com.example.neuralnet.activation;

/**
 * It's like a ReLu, but negative values are simply scaled down instead of eliminated
 * @author Cole Solomonson
 */
public class LeakyReLu extends ActivationFunction {
    public LeakyReLu(){}
    @Override
    public double func(double in) {
        return Math.max(in, 0.01 * in);
    }
}
