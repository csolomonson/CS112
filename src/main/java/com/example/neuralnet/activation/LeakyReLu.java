package com.example.neuralnet.activation;

public class LeakyReLu extends ActivationFunction {
    public LeakyReLu(){}
    @Override
    public double func(double in) {
        return Math.max(in, 0.01 * in);
    }
}
