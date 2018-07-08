package NN

import Direction
import java.util.*

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

            network.layers = List(countOfLayers,
                    { layer -> List(countOfNeuronsInEachLayer[layer], { Neuron(countOfInputInEachLayer[layer]) }) })

            return network
        }
    }

    fun calculateNextMove(input: List<Double>): Direction {

        var inputForLayer = input
        var outputLayer: List<Double> = listOf(0.0, 0.0, 0.0, 0.0)

        for ((index, _) in layers.withIndex()) {
            outputLayer = calculateLayer(index, inputForLayer)
            inputForLayer = outputLayer
        }

        var indexWithHighestProb = 0
        var highestProb = 0.0

        for ((index, prob) in outputLayer.withIndex()) {
            if (highestProb < prob) {
                highestProb = prob
                indexWithHighestProb = index
            }
        }

        for ( (index,prob) in outputLayer.withIndex()) {
            println("$index.th probability $prob")
        }

        val finalDirection = Direction.mapIndexToDirection(indexWithHighestProb)
        println("Highest prob=$highestProb, Index=$indexWithHighestProb, finalDirection=$finalDirection")

        return finalDirection
    }

    fun calculateLayer(layer: Int, input: List<Double>): List<Double> {
        val list: MutableList<Double> = ArrayList()

        for (index in 0 until layers[layer].size) list.add(index, layers[layer][index].calculateOutput(input))

        return list
    }
}

class Neuron(private val inputSize: Int) {
    private var weights: Array<Double>

    init {
        val random = Random()
        weights = Array(inputSize, { random.nextDouble() })
    }

    fun calculateOutput(input: List<Double>): Double {

        if (input.size != inputSize) {
            println("Size of input set and weight set does not match, expected=${inputSize},actual=${input.size}")
            return 0.0
        }

        var res = 0.0
        for (index in 0 until inputSize) {
            res += weights[index] * input[index]
        }

        return Math.tanh(res)
    }
}