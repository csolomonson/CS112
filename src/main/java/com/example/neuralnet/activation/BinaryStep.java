package com.example.neuralnet.activation;

public class BinaryStep extends ActivationFunction{
    public BinaryStep() {}
    @Override
    public double func(double in) {
        if (in < 0.5) return 0;
        return 1;
    }
}
