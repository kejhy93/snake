import javax.swing.JComponent
import javax.swing.SwingUtilities

fun main(args:Array<String>) {
    val snake = Snake.createSnake(0,0)
    val grid = Grid.createGrid(5, 5)

    var window = GameWindow()
    SwingUtilities.invokeLater { window::run }
}