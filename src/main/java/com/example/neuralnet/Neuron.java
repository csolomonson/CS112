package com.example.neuralnet;

import com.example.neuralnet.activation.ActivationFunction;
import com.example.neuralnet.activation.Linear;

import java.io.Serializable;
import java.util.Arrays;


/**
 * Single neuron. Calculates weighted sum of a set of other neurons, adds a bias, and applies an ActivationFunction.
 * @author Cole Solomonson
 */
public class Neuron implements Cloneable, Serializable {
    private double bias;
    private double[] weights;
    private Neuron[] inputs;
    private ActivationFunction af;

    // calculated, not set
    private double activation;
    private int numInputs;

    /**
     * Full constructor. Set inputs, weights, and bias
     *
     * @param inputs  Array on Neurons that feed into this Neuron
     * @param weights Array of doubles, one weight for each input Neuron
     * @param bias    Constant bias to add to each weighted sum
     **/
    public Neuron(Neuron[] inputs, double[] weights, double bias) {
        setInputs(inputs);
        setWeights(weights);
        setBias(bias);
        this.af = new Linear();
    }

    /**
     * Default constructor; Neuron with no inputs, no weights, and a bias of 0.
     **/
    public Neuron() {
        this(new Neuron[0], new double[0], 0);
    }

    /**
     * Copy constructor: creates a deep copy of a specified Neuron object
     *
     * @param other Neuron to instantiate a deep copy of
     **/
    public Neuron(Neuron other) {
        this(other.getInputs().clone(), other.getWeights().clone(), other.getBias());
    }

    /**
     * Calculates the weighted sum of every input, adds the bias, and sets the
     * result to the activation instance variable
     **/
    public void calculate() {
        double weightedSum = 0;
        for (int i = 0; i < numInputs; i++) {
            weightedSum += inputs[i].getOutput() * weights[i];
        }
        activation = af.func(weightedSum + bias);
        //System.out.println(activation);
    }

    /**
     * Calculates and returns the activation, based on the inputs, biases, and
     * weights.
     *
     * @return The calculated activation
     **/
    public double getOutput() {
        calculate();
        return getActivation();
    }

    /**
     * Sets the bias to the specified double
     *
     * @param bias New bias to set
     **/
    public void setBias(double bias) {
        this.bias = bias;
    }

    /**
     * Get the bias
     *
     * @return The little number added to our weighted sum in the activation
     *         calculation
     **/
    public double getBias() {
        return this.bias;
    }

    /**
     * Sets the inputs to a specified array. Also resets activations to ensure they
     * are of the same size
     *
     * @param inputs New array of Neurons
     **/
    public void setInputs(Neuron[] inputs) {
        this.inputs = inputs.clone();
        this.numInputs = inputs.length;
        this.weights = new double[numInputs];
        randomWeights();
    }

    /**
     * Get the inputs
     *
     * @return Array of each Neuron that inputs into this Neuron
     **/
    public Neuron[] getInputs() {
        return this.inputs;
    }

    /**
     * Set weights to a jolly double array. Only works if it is the same size as the
     * input array of Neurons
     *
     * @param weights Double array of weights, one for each input. Array must have
     *                the same length as inputs array
     **/
    public void setWeights(double[] weights) throws RuntimeException {
        if (weights.length != numInputs)
            throw new RuntimeException("Poop! that's not the right number of weights!");
        this.weights = weights.clone();
    }

    public void randomWeights() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 2*Math.random() -1; //random number between -1 and 1
        }
        bias = 2*Math.random() -1;
    }

    /**
     * Get the weights
     *
     * @return Double array of the weights for each input
     **/
    public double[] getWeights() {
        return this.weights;
    }

    /**
     * Gets the number of inputs
     *
     * @return Number of inputs to Neuron
     **/
    public int getNumInputs() {
        return this.numInputs;
    }

    /**
     * Get the output of the neuron without re-calculating it
     *
     * @return The current activation, as is
     **/
    public double getActivation() {
        return activation;
    }

    /**
     * Mandatory interface implementation
     *
     * @return Deep copy of this Neuron
     **/
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Neuron(this);
    }

    /**
     * My equals method (very beautiful)
     *
     * @param obj Other object to check for equality
     * @return True if obj is a Neuron with the same inputs, weights, and bias;
     *         false otherwise
     **/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Neuron))
            return false;
        Neuron other = (Neuron) obj;

        if (numInputs == other.getNumInputs() && bias == other.getBias()) {
            for (int i = 0; i < numInputs; i++) {
                if (weights[i] != other.weights[i]) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * It's a beautiful toString :)
     *
     * @return A quaint string representation, giving the size, weights, and bias
     **/
    @Override
    public String toString() {
        return String.format("Neuron with %d inputs, weights of %s, and a bias of %f", numInputs, Arrays.toString(weights),
                bias);
    }

    /**
     * Set the ActivationFunction object that is applied to the weighted sum when calculating activation
     * @param af Object that contains func() function to use
     */
    public void setActivationFunction(ActivationFunction af) {
        this.af = af;
    }
}