package NN

import Direction
import java.util.LinkedList
import java.util.ArrayList

class NeuralNetwork private constructor(val inputSize: Int, val countOfLayers: Int) {

    var layers: List<List<Neuron>> = ArrayList()

    companion object {
        fun createNetwork(inputSize: Int, countOfLayers: Int, countOfNeuronsInEachLayer: Array<Int>): NeuralNetwork {
            val network = NeuralNetwork(inputSize, countOfLayers)

            val countOfInputInEachLayer: MutableList<Int> = LinkedList()
            countOfInputInEachLayer.add(inputSize)

            for (count in countOfNeuronsInEachLayer.sliceArray(0 until countOfNeuronsInEachLayer.size - 1)) {
                countOfInputInEachLayer.add(count)
            }

            network.layers = List ( countOfLayers,
                    { layer -> List(countOfNeuronsInEachLayer[layer], { Neuron(countOfInputInEachLayer[layer])})})

            return network
        }
    }

    fun calculateNextMove(input: List<Double>): Direction {

        var inputForLayer = input
        var outputOfLayer = calculateLayer(1, inputForLayer)



        return Direction.RIGHT
    }

    fun calculateLayer(layer: Int, input: List<Double>): List<Double> {
        val list: MutableList<Double> = ArrayList()

        for (index in 0 until layers[layer].size) {
            list.add(index, layers[layer][index].calculateOutput(input))
        }

        return list
    }
}

fun main(args: Array<String>) {
    println("Neural Network - test")

    val inputSize = 3
    val countOfLayers = 3
    val countOfNeuronsInEachLayer = arrayOf(3,5,4)

    val network = NeuralNetwork.createNetwork(inputSize, countOfLayers, countOfNeuronsInEachLayer)
}

class Neuron(val inputSize: Int) {
    var weights: List<Double> = ArrayList(inputSize)

    fun calculateOutput(input: List<Double>): Double {

        if (input.size != inputSize) {
            println("Size of input set and weight set does not match")
            return 0.0
        }

        var res = 0.0
        for (index in 0 until inputSize) {
            res += weights[index] * input[index]
        }

        return res
    }
}