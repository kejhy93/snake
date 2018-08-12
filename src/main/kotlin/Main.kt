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
    @JvmStatic fun main(args: Array<String>) {
        isBorderSolid = true

        createApplication()
    }

    private var isBorderSolid: Boolean = false

    var widthOfMap = 800F
    var heightOfMap = 600F

    var widthOfTile = 10F
    var heightOfTile = 10F

    var numberOfTilesWidth : Int = (widthOfMap/widthOfTile).toInt()
    var numberOfTilesHeight : Int = (heightOfMap/heightOfTile).toInt()

    private fun createApplication() {
        val snake = Snake.createSnake(10, 10, Direction.RIGHT)
        val bait = Bait.createBait(20, 20)

        LwjglApplication(Runner(snake, bait), defaultConfiguration)

        while ( true ) {
            Thread.sleep(100)
            if ( snake.dead )
                break

            snake.move()
            println("Snake position=[${snake.x};${snake.y}]")
            if ( snake.x == bait.x && snake.y == bait.y) {
                snake.addTile()
                bait.setPosition(Random().nextInt(30)+10, Random().nextInt(30)+10)
                println("Bait position=[${bait.x}:${bait.y}]")
            }

            if ( snake.checkCollision() ) {
                println("Collision detected")
                println("Game over")
                return
            }
            borderManipulation(snake)
        }
    }

    private fun borderManipulation(snake: Snake) {
        if (isBorderSolid) {
            if (snake.x < 0 || snake.x > numberOfTilesWidth || snake.y < 0 || snake.y > numberOfTilesHeight) {
                println("Game Over")
                snake.dead = true
            }
        } else {
            for (tile in snake.listOfTiles) {
                tile.x = Math.floorMod(tile.x, numberOfTilesWidth)
                tile.y = Math.floorMod(tile.y, numberOfTilesWidth)
            }
        }
    }

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "Test"
            configuration.width = widthOfMap.toInt()
            configuration.height = heightOfMap.toInt()

            return configuration
        }
}

class Runner(val snake : Snake, val bait : Bait) : KtxApplicationAdapter, KtxInputAdapter {

    private lateinit var font : BitmapFont
    private lateinit var batch : SpriteBatch
    private lateinit var camera : Camera
    private lateinit var renderer : ShapeRenderer

    override fun create() {
        super.create()

        font = BitmapFont()
        batch = SpriteBatch()
        renderer = ShapeRenderer()

        font.color = Color.WHITE
        camera = OrthographicCamera(MainKt.widthOfMap,MainKt.heightOfMap)
        camera.position.set(MainKt.widthOfMap/2, MainKt.heightOfMap/2, 0F)
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

        val posX = bait.x*sizeOfBox
        val posY = bait.y*sizeOfBox

        renderer.rect(posX, posY, sizeOfBox, sizeOfBox)
    }

    private fun renderSnake(renderer : ShapeRenderer) {
        val sizeOfBox = 10F
        for ( tile in snake.listOfTiles ) {
            val posX = tile.x*sizeOfBox
            val posY = tile.y*sizeOfBox

            renderer.color = Color.WHITE
            renderer.rect(posX, posY, sizeOfBox, sizeOfBox)
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        if ( !snake.canRotate ) {
            return true
        }

        if ( keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            snake.turnLeft()
            snake.canRotate = false
        } else if ( keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            snake.turnRight()
            snake.canRotate = false
        } else if ( keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            snake.turnDown()
            snake.canRotate = false
        } else if ( keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            snake.turnUp()
            snake.canRotate = false
        } else if ( keycode == Input.Keys.SPACE ) {
            snake.addTile()
        } else {

        }
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }
}