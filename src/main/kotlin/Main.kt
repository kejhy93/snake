import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ktx.app.KtxApplicationAdapter

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

class Runner : KtxApplicationAdapter {
    override fun create() {
        super.create()
    }

    override fun render() {
        super.render()
    }
}