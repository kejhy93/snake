import NN.NeuralNetwork
import NN.NeuralNetworkCombiner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class NeuralNetworkCombinerTest {

    @Test
    fun testMutate() {
        val network = NeuralNetwork.createNetwork(3, 1, arrayOf(1))

        val expectedCountOfLayer = 1
        val expectedCountOfNeurons = 1
        val expectedCountOfInput = 3

        assertEquals(expectedCountOfLayer, network.layers.size)
        assertEquals(expectedCountOfNeurons, network.layers[0].size)
        assertEquals(expectedCountOfInput, network.layers[0][0].weights.size)

        val expectedValueOfFirstInput = 0.5
        val expectedValueOfSecondInput = 0.6
        val expectedValueOfThirdInput = 0.7

        val prob = 2.0

        network.layers[0][0].weights[0] = expectedValueOfFirstInput
        network.layers[0][0].weights[1] = expectedValueOfSecondInput
        network.layers[0][0].weights[2] = expectedValueOfThirdInput

        assertEquals(expectedValueOfFirstInput, network.layers[0][0].weights[0])
        assertEquals(expectedValueOfSecondInput, network.layers[0][0].weights[1])
        assertEquals(expectedValueOfThirdInput, network.layers[0][0].weights[2])

        NeuralNetworkCombiner.mutateNeuralNetwork(network, prob)

        val text = "Value before and after mutation are equals. Run test again. Probability is bitch :)"

        assertNotEquals(expectedValueOfFirstInput, network.layers[0][0].weights[0], text)
        assertNotEquals(expectedValueOfSecondInput, network.layers[0][0].weights[1], text)
        assertNotEquals(expectedValueOfThirdInput, network.layers[0][0].weights[2], text)
    }


}