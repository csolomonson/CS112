package com.example.neuralnet;

import java.util.Arrays;

/**
 * What a tester! I made this!
 * @author Cole Solomonson
 */
public class NetworkTester {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(3,2,2,10);
        System.out.println(Arrays.toString(nn.infer(new double[]{2.3, 4, -5})));
        Neuron[][] allNeurons = nn.getNeuronArray();
        for (Neuron[] ns: allNeurons) {
            System.out.println("=============NEW LAYER==============");
            for (Neuron n: ns) {
                System.out.println(n.toString());
            }
        }
    }
}
