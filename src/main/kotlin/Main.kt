import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.KtxApplicationAdapter
import ktx.app.KtxInputAdapter
import ktx.graphics.rect
import ktx.graphics.use
import java.util.*

object MainKt {
    @JvmStatic fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication() {
        val snake = Snake.createSnake(10, 10)
        val bait = Bait.createBait(20, 20)

        LwjglApplication(Runner(snake, bait), defaultConfiguration)

        while ( true ) {
            Thread.sleep(100)
            snake.move()

            if ( snake.x == bait.x && snake.y == bait.y) {
                snake.addTile()
                bait.setPosition(Random().nextInt(30)+10, Random().nextInt(30)+10)
                println("Bait position=[${bait.x}:${bait.y}]")
            }
        }
    }

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "Test"
            configuration.width = 800
            configuration.height = 600

            return configuration
        }
}

class Runner(val snake : Snake, val bait : Bait) : KtxApplicationAdapter, KtxInputAdapter {

    lateinit var font : BitmapFont
    lateinit var batch : SpriteBatch
    lateinit var camera : Camera
    lateinit var renderer : ShapeRenderer

    override fun create() {
        super.create()

        font = BitmapFont()
        batch = SpriteBatch()
        renderer = ShapeRenderer()

        font.color = Color.WHITE
        camera = OrthographicCamera(800F,600F)
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