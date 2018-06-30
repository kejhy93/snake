import java.util.*

class Snake private constructor(val initX: Int, val initY: Int, val initDirection: Direction) {

    var x: Int = initX
        get() = synchronized(listOfTiles) { listOfTiles.firstOrNull()?.x ?: initX }

    var y: Int = initY
        get() = synchronized(listOfTiles) { listOfTiles.firstOrNull()?.y ?: initY }

    var direction: Direction = initDirection
        get() = synchronized(listOfTiles) { listOfTiles.firstOrNull()?.direction ?: Direction.LEFT }

    var listOfTiles: MutableList<SnakeTile> = LinkedList()

    var canRotate: Boolean = true

    init {
        listOfTiles.add(SnakeTile.createTile(x, y, initDirection))
    }

    companion object {
        fun createSnake(x: Int, y: Int, direction: Direction = Direction.LEFT): Snake {
            return Snake(x, y, direction)
        }
    }

    fun move() {
        synchronized(listOfTiles) {
            var currDirection = listOfTiles.first().direction
            for (tile in listOfTiles) {
                val nextDirection = tile.direction
                tile.move(currDirection)
                currDirection = nextDirection
            }
        }

        canRotate = true
    }

    fun addTile() {
        val lastTile = listOfTiles.last()
        val direction = lastTile.direction

        val positionNextTileX = calculateNextTileX(lastTile.x, direction)
        val positionNextTileY = calculateNextTileY(lastTile.y, direction)

        listOfTiles.add(SnakeTile.createTile(positionNextTileX, positionNextTileY, direction))
    }

    private fun calculateNextTileY(y: Int, direction: Direction): Int {
        return when (direction) {
            Direction.UP -> y - 1
            Direction.DOWN -> y + 1
            else -> y
        }
    }

    private fun calculateNextTileX(x: Int, direction: Direction): Int {
        return when (direction) {
            Direction.LEFT -> x + 1
            Direction.RIGHT -> x - 1
            else -> x
        }
    }

    fun turnLeft() {
        val currDirection = direction

        if (!Direction.isOpposite(currDirection, Direction.LEFT)) {
            listOfTiles.first().direction = Direction.LEFT
        }
    }

    fun turnRight() {
        val currDirection = direction

        if (!Direction.isOpposite(currDirection, Direction.RIGHT)) {
            listOfTiles.first().direction = Direction.RIGHT
        }
    }

    fun turnUp() {
        val currDirection = direction

        if (!Direction.isOpposite(currDirection, Direction.UP)) {
            listOfTiles.first().direction = Direction.UP
        }

    }

    fun turnDown() {
        val currDirection = direction

        if (!Direction.isOpposite(currDirection, Direction.DOWN)) {
            listOfTiles.first().direction = Direction.DOWN
        }
    }
}