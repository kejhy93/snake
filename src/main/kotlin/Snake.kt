import java.util.*

class Snake private constructor(initX : Int, initY : Int) {

    var x : Int = initX
        get() = listOfTiles.getOrNull(0)?.x?:0

    var y : Int = initY
        get() = listOfTiles.getOrNull(0)?.y?:0

    var listOfTiles : MutableList<SnakeTile> = LinkedList()

    init {
        listOfTiles.add(SnakeTile.createTile(x, y, Direction.LEFT))
    }

    companion object {
        fun createSnake(x : Int, y : Int ) : Snake {
            return Snake(x, y)
        }
    }
}