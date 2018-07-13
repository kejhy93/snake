package NN

import java.util.*

class NeuralNetworkCombiner {
    companion object {
        fun mutateNeuralNetwork(network : NeuralNetwork, prob : Double) : NeuralNetwork {
            val random = Random()

            for ( layer in network.layers) {
                for ( neuron in layer ) {
                    for ( (index,_) in neuron.weights.withIndex() ) {
                        if ( prob > random.nextDouble()) {
                            neuron.weights[index] = random.nextDouble()
                        }
                    }
                }
            }
            return network
        }

        fun crossOverNeuralNetworks ( first : NeuralNetwork, second : NeuralNetwork, prob : Double ) : List<NeuralNetwork> {
            val firstCrossOveredNetwork = first.clone()
            val secondCrossOveredNetwork = second.clone()

            for ( (index,layer) in first.layers.withIndex()) {
                
            }

            return listOf()
        }
    }
}