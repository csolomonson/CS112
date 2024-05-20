
# Neural Network Visualizer

This program displays a visual demonstration of a neural network, a foundational tool in Machine Learning. Every neuron is represented with a colored circle, with each neuron inputting data into the layer immedietly to its left. Red color represents negative values, while blue color represents positive values. The lines between the neurons signify the weights, or the value that the last neural output is multiplied by before being input into the following neuron. Finally, the color of the border around each neuron represents that neuron's bias, which is the fixed tunable value added to the weighted sum of the inputs.


## Inspiration
I've wanted to implement something like this since I saw a YouTube video by 3Blue1Brown on the topic of neural networks: https://youtu.be/aircAruvnKk?si=PwnfRAjDPmNQ8UKi. I originally wanted to implement backpropagation, as is discussed in a later video of the same series, but that was cut in the interest of time.
## UML
 ![UML diagram](https://raw.githubusercontent.com/csolomonson/CS112/master/images/Neural%20Network%20UML.png)
Here is a complete Unified Modeling Language diagram for the backend of this project. The cells in gray represent classes for the training of the network that I did not actually implement. All of these classes culminate in the NeuralNetwork class, which is capable of receiving inputs, passing them through the entire structure of the network, and displaying the outputs. The network is comprised of NeuralNetworkLayers, which in turn are comprised of Neurons. Special classes are used for the input layer to allow direct input of data. The network also applies an ActivationFunction to each neural output before passing it to the next layer to allow for non-linearity.

## Wireframe
![Application wireframe](https://raw.githubusercontent.com/csolomonson/CS112/master/images/UD2.drawio.png)

This is the layout of the application. On the left side is the actual visualization, and controls are on the right panel. There are controls for the size and shape of the network, the ActivationFunction that is used, and options to randomize, load, and save networks. Saving and loading the network involves writing to and reading from text files saved to the user's file system. 

## Demo
![Application demo](https://raw.githubusercontent.com/csolomonson/CS112/master/images/nndemo.gif)
Here is the final project being used. The inputs (the initially gray circles on the far left) can be controlled by scrolling the mouse wheel while hovering the cursor on top of them. This in turn changes the activation of all the later neurons in the network.
