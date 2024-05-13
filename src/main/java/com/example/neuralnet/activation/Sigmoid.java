package com.example.neuralnet.activation;

public class Sigmoid extends ActivationFunction{
    public Sigmoid(){}
    @Override
    public double func(double in) {
        return 1/(1+Math.pow(Math.E,-in));
    }
}
