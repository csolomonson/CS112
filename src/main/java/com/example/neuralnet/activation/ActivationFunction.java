package com.example.neuralnet.activation;

import java.io.Serializable;

public abstract class ActivationFunction implements Serializable {
    public abstract double func(double in);
}
