package com.example.neuralnet.activation;

public class ReLu extends ActivationFunction {
    public ReLu(){}
    @Override
    public double func(double in) {
        return Math.max(in, 0);
    }
}
