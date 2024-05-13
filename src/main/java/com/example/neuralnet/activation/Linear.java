package com.example.neuralnet.activation;

public class Linear extends ActivationFunction{
    public Linear() {}
    @Override
    public double func(double in) {
        return in;
    }
}
