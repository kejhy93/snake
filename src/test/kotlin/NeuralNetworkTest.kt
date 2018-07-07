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
}