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

            val random = Random()

            for ( (indexLayer,layer) in first.layers.withIndex()) {
                for ( (indexNeuron,neuron) in layer.withIndex()) {
                    for ( (indexWeight,weight) in neuron.weights.withIndex()) {
                        if ( prob > random.nextDouble() ) {
                            val tmp = firstCrossOveredNetwork.layers[indexLayer][indexNeuron].weights[indexWeight]
                            firstCrossOveredNetwork.layers[indexLayer][indexNeuron].weights[indexWeight] = secondCrossOveredNetwork.layers[indexLayer][indexNeuron].weights[indexWeight]
                            secondCrossOveredNetwork.layers[indexLayer][indexNeuron].weights[indexWeight] = tmp
                        }
                    }
                }
            }

            return listOf(firstCrossOveredNetwork, secondCrossOveredNetwork)
        }
    }
}