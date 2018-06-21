import java.awt.Color
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel

class GameWindow : JFrame() {

    init {
        createUI("Snake")
    }

    private fun createUI(title: String) {

        setTitle(title)

        add(SnakeWindow())

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(300, 200)
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }

    fun run() {

    }
}

class SnakeWindow : JPanel() {
    var objectsToDraw : MutableList<TileGui> = ArrayList()

    init {
        createUI()
    }

    private fun createUI() {
        setSize(300, 200)
        background = Color.WHITE

        var tile = TileGui(0,0,100,100)

        addTile(tile)
        isVisible = true
    }

    private fun addTile(tile : TileGui) {
        objectsToDraw.add(tile)
        repaint()
    }

    override fun paintComponents(g: Graphics?) {
        super.paintComponents(g)
        for ( tile in objectsToDraw) {
            tile.draw(g!!)
        }
    }
}