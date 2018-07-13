import NN.NeuralNetwork
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NeuralNetworkTest {

    @Test
    fun creationOfNetwork() {
        val countOfInput = 4
        val countOfLayers = 3

        val countOfNeuronsInFirstLayer = 3
        val countOfNeuronsInSecondLayer = 5
        val countOfNeuronsInThirdLayer = 4

        val countOfNeuronsInEachLayer = arrayOf(countOfNeuronsInFirstLayer, countOfNeuronsInSecondLayer, countOfNeuronsInThirdLayer)

        val network = NeuralNetwork.createNetwork(countOfInput, countOfLayers, countOfNeuronsInEachLayer)

        val createdNetwork = network.layers

        assertEquals(countOfLayers, createdNetwork.size)
        assertEquals(countOfNeuronsInFirstLayer, createdNetwork[0].size)
        assertEquals(countOfNeuronsInSecondLayer, createdNetwork[1].size)
        assertEquals(countOfNeuronsInThirdLayer, createdNetwork[2].size)
    }

    @Test
    fun creationOfNetwork2() {
        val countOfInput = 9
        val countOfLayers = 5

        val countOfNeuronsInFirstLayer = 5
        val countOfNeuronsInSecondLayer = 1
        val countOfNeuronsInThirdLayer = 9
        val countOfNeuronsInFourthLayer = 2
        val countOfNeuronsInFifthLayer = 1

        val countOfNeuronsInEachLayer = arrayOf(countOfNeuronsInFirstLayer, countOfNeuronsInSecondLayer, countOfNeuronsInThirdLayer, countOfNeuronsInFourthLayer, countOfNeuronsInFifthLayer)

        val network = NeuralNetwork.createNetwork(countOfInput, countOfLayers, countOfNeuronsInEachLayer)

        val createdNetwork = network.layers

        assertEquals(countOfLayers, createdNetwork.size)
        assertEquals(countOfNeuronsInFirstLayer, createdNetwork[0].size)
        assertEquals(countOfNeuronsInSecondLayer, createdNetwork[1].size)
        assertEquals(countOfNeuronsInThirdLayer, createdNetwork[2].size)
        assertEquals(countOfNeuronsInFourthLayer, createdNetwork[3].size)
        assertEquals(countOfNeuronsInFifthLayer, createdNetwork[4].size)
    }

    @Test
    fun testNetworkStructureClone() {
        val inputSize = 5
        val countOfLayer = 8
        val listOfNeuronSize = arrayOf(3, 4, 5, 6, 7, 8, 9, 10)

        val network = NeuralNetwork.createNetwork(inputSize, countOfLayer, listOfNeuronSize)
        val clonedNetwork = network.clone()

        assertEquals(inputSize, clonedNetwork.inputSize)
        assertEquals(countOfLayer, clonedNetwork.countOfLayers)

        val resultListWithNeuronsCounts = Array(countOfLayer, { _ -> 0})
        for ( (index,layer) in clonedNetwork.layers.withIndex()) {
            resultListWithNeuronsCounts[index] = layer.size
        }

        assertEquals(listOfNeuronSize.size, resultListWithNeuronsCounts.size)
        for ( (index,_) in listOfNeuronSize.withIndex()) {
            assertEquals(listOfNeuronSize[index], resultListWithNeuronsCounts[index])
        }
    }


    @Test
    fun testNetworkClone() {
        val inputSize = 5
        val countOfLayer = 1
        val listOfNeuronSize = arrayOf(1)

        val network = NeuralNetwork.createNetwork(inputSize, countOfLayer, listOfNeuronSize)

        val firstInput = 0.1
        val secondInput = 0.2
        val thirdInput = 0.3
        val fourthInput = 0.8
        val fifthInput = 0.9

        network.layers[0][0].weights[0] = firstInput
        network.layers[0][0].weights[1] = secondInput
        network.layers[0][0].weights[2] = thirdInput
        network.layers[0][0].weights[3] = fourthInput
        network.layers[0][0].weights[4] = fifthInput

        val clonedNetwork = network.clone()

        assertEquals(inputSize, clonedNetwork.inputSize)
        assertEquals(countOfLayer, clonedNetwork.countOfLayers)

        val resultListWithNeuronsCounts = Array(countOfLayer, { _ -> 0})
        for ( (index,layer) in clonedNetwork.layers.withIndex()) {
            resultListWithNeuronsCounts[index] = layer.size
        }

        assertEquals(listOfNeuronSize.size, resultListWithNeuronsCounts.size)
        for ( (index,_) in listOfNeuronSize.withIndex()) {
            assertEquals(listOfNeuronSize[index], resultListWithNeuronsCounts[index])
        }

        for ( (index,weight) in network.layers[0][0].weights.withIndex()) {
            assertEquals(network.layers[0][0].weights[index], weight)
        }
    }
}