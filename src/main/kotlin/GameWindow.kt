import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.graphics.use

class ExampleScreen : KtxScreen {
    // Notice no `lateinit var` - ExampleScreen has no create()
    // method and is constructed after LibGDX is fully initiated
    // in ExampleGame.create method.
    val font = BitmapFont()
    val batch = SpriteBatch().apply {
        color = Color.WHITE
    }

    override fun render(delta: Float) {
        batch.use {
            font.draw(it, "Hello Kotlin!", 100f, 100f)
        }
    }

    override fun dispose() {
        // Will be automatically disposed of by the game instance.
        font.dispose()
        batch.dispose()
    }
}

/** ApplicationListener implementation. */
class ExampleGame : KtxGame<Screen>() {
    override fun create() {
        // Registering ExampleScreen in the game object: it will be
        // accessible through ExampleScreen class:
        addScreen(ExampleScreen())
        // Changing current screen to the registered instance of the
        // ExampleScreen class:
        setScreen<ExampleScreen>()
    }
}

class MyInputListener : KtxInputAdapter {
    // Implementation of all ApplicationListener methods is optional. Handle the events you plan on supporting.

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        // Handle mouse click...
        return true
    }
}