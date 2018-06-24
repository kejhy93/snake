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

object MainKt {
    @JvmStatic fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication() {
        var snake = Snake.createSnake(10, 10)
        LwjglApplication(Runner(snake), defaultConfiguration)

        while ( true ) {
            Thread.sleep(100)
            snake.move()
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

class Runner(val snake : Snake) : KtxApplicationAdapter, KtxInputAdapter {

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


    override fun dispose() {
        super.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(0F, 0F, 0F, 0F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.run {
            projectionMatrix = camera.combined
            begin(ShapeRenderer.ShapeType.Filled)
            renderSnake(this)
            end()
        }
        super.render()
    }

    private fun renderSnake(renderer : ShapeRenderer) {
        val sizeOfBox = 10F
        for ( tile in snake.listOfTiles ) {
            val posX = tile.x*sizeOfBox
            val posY = tile.y*sizeOfBox

            renderer.color = Color.WHITE
            renderer.rect(posX, posY, sizeOfBox, sizeOfBox)
        }

//        snake.move()
        println("Snake=[${snake.x};${snake.y}]")
    }

    override fun keyDown(keycode: Int): Boolean {
        if ( keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            snake.turnLeft()
        } else if ( keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            snake.turnRight()
        } else if ( keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            snake.turnDown()
        } else if ( keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            snake.turnUp()
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