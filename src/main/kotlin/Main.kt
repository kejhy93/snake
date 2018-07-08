import NN.NeuralNetwork
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxInputAdapter
import java.util.*

object MainKt {
    @JvmStatic
    fun main(args: Array<String>) {
        val singlePlayer = true

        if ( singlePlayer ) {
            createApplicationSingleSnake()
        } else {
            createApplicationMultipleSnakes(500)
        }
    }

    private fun createApplicationMultipleSnakes(countOfSnakes: Int) {
        val snakes = MutableList(countOfSnakes, { index -> Snake.createSnake(10, 10, index) })
        val baits = MutableList(countOfSnakes, { Bait.createBait(20, 20) })

        var neuralNetworks = MutableList(countOfSnakes, { NeuralNetwork.createNetwork(1, 2, arrayOf(2,4)) })

        LwjglApplication(Runner(snakes, baits), defaultConfiguration)

        while (true) {
            Thread.sleep(100)

            for ( (index,network) in neuralNetworks.withIndex() ) {
                val nnInput = createNNInput(baits[index], snakes[index])

                val direction = network.calculateNextMove(nnInput)
                snakes[index].turn(direction)
            }

            for ((index, snake) in snakes.withIndex()) {
                snake.move()
                println("Snake id=${snake.id} direction=${snake.direction}")

                if (snake.x == baits[index].x && snake.y == baits[index].y) {
                    snake.addTile()
                    snake.incrementScore()
                    baits[index].setPosition(Random().nextInt(30) + 10, Random().nextInt(30) + 10)
                    println("Bait position=[${baits[index].x}:${baits[index].y}]")
                }
            }
        }
    }

    private fun createApplicationSingleSnake() {
        val snake = Snake.createSnake(10, 10)
        val bait = Bait.createBait(20, 20)

        LwjglApplication(Runner(MutableList(1, { snake }), MutableList(1, { bait })), defaultConfiguration)

        while (true) {
            Thread.sleep(100)
            snake.move()

            if (snake.x == bait.x && snake.y == bait.y) {
                snake.addTile()
                bait.setPosition(Random().nextInt(30) + 10, Random().nextInt(30) + 10)
                println("Bait position=[${bait.x}:${bait.y}]")
            }
        }
    }

    private fun createNNInput(bait: Bait, snake: Snake): List<Double> {
        // @TODO Move to grid class, get input from game world
        // [ LEFT_TOP_TILE, TOP_TILE, RIGHT_TOP_TILE,
        // LEFT_TILE, RIGHT_TILE,
        // LEFT_BOTTOM_TILE, BOTTOM_TILE, RIGHT_BOTTOM_TILE ]

        val distance = calculateDistanceToBait(bait, snake)

        return listOf(distance)
    }

    private fun calculateDistanceToBait(bait: Bait, snake: Snake): Double = Math.hypot((bait.x - snake.x).toDouble(), (bait.y - snake.y).toDouble())

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "Test"
            configuration.width = 800
            configuration.height = 600

            return configuration
        }
}

class Runner(private var snakes: MutableList<Snake>, private val baits: MutableList<Bait>) : KtxApplicationAdapter, KtxInputAdapter {

    lateinit var font: BitmapFont
    lateinit var batch: SpriteBatch
    lateinit var camera: Camera
    lateinit var renderer: ShapeRenderer

    override fun create() {
        super.create()

        font = BitmapFont()
        batch = SpriteBatch()
        renderer = ShapeRenderer()

        font.color = Color.WHITE
        camera = OrthographicCamera(800F, 600F)
        camera.position.set(400F, 400F, 0F)
        camera.update()

        Gdx.input.inputProcessor = this
    }

    override fun render() {
        Gdx.gl.glClearColor(0F, 0F, 0F, 0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.run {
            projectionMatrix = camera.combined
            begin(ShapeRenderer.ShapeType.Filled)
            renderSnake(this)
            renderBait(this)
            end()
        }
        super.render()
    }

    private fun renderBait(renderer: ShapeRenderer) {
        val sizeOfBox = 10F
        renderer.color = Color.RED

        for (bait in baits) {
            val posX = bait.x * sizeOfBox
            val posY = bait.y * sizeOfBox

            renderer.rect(posX, posY, sizeOfBox, sizeOfBox)
        }
    }

    private fun renderSnake(renderer: ShapeRenderer) {
        val sizeOfBox = 10F
        for (snake in snakes) {
            for (tile in snake.listOfTiles) {
                val posX = tile.x * sizeOfBox
                val posY = tile.y * sizeOfBox

                renderer.color = Color.WHITE
                renderer.rect(posX, posY, sizeOfBox, sizeOfBox)
            }
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        if (snakes.size < 1 || snakes.size > 1)
            return true

        var snake = snakes[0]

        if (!snake.canRotate) {
            return true
        }

        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            snake.turnLeft()
            snake.canRotate = false
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            snake.turnRight()
            snake.canRotate = false
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            snake.turnDown()
            snake.canRotate = false
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            snake.turnUp()
            snake.canRotate = false
        } else if (keycode == Input.Keys.SPACE) {
            snake.addTile()
        } else {

        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }
}