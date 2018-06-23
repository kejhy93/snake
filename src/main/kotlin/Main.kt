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

    private fun createApplication() = LwjglApplication(Runner(), defaultConfiguration)

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "Test"
            configuration.width = 800
            configuration.height = 600

            return configuration
        }
}

class Runner : KtxApplicationAdapter, KtxInputAdapter {

    lateinit var font : BitmapFont
    lateinit var batch : SpriteBatch
    lateinit var camera : Camera
    lateinit var renderer : ShapeRenderer

    var xPos : Float = 0F
    var delta : Float = 0F

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
            color = Color.WHITE
            rect(calculateXPos(), 200F, 10F, 10F)
            end()
        }
        super.render()
    }

    private fun calculateXPos(): Float {
        xPos += delta
        return xPos
    }

    override fun keyDown(keycode: Int): Boolean {
        delta = if ( keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            -1F
        } else if ( keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            1F
        } else {
            delta
        }

        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }
}