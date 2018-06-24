import java.util.*

class Snake private constructor(val initX : Int, val initY : Int) {

    var x : Int = initX
        get() = listOfTiles.firstOrNull()?.x?:initX

    var y : Int = initY
        get() = listOfTiles.firstOrNull()?.y?:initY

    var direction : Direction = Direction.LEFT
        get() = listOfTiles.firstOrNull()?.direction?:Direction.LEFT

    var listOfTiles : MutableList<SnakeTile> = LinkedList()

    init {
        listOfTiles.add(SnakeTile.createTile(x, y, Direction.RIGHT))
    }

    companion object {
        fun createSnake(x : Int, y : Int ) : Snake {
            return Snake(x, y)
        }
    }

    fun move() {
        var currDirection = listOfTiles.first().direction
        for ( tile in listOfTiles ) {
            var nextDirection = tile.direction
            tile.move(currDirection)
            currDirection = nextDirection
        }
    }

    fun addTile() {
        var lastTile = listOfTiles.last()
        var direction = lastTile.direction

        var positionNextTileX = calculateNextTileX(lastTile.x, direction)
        var positionNextTileY = calculateNextTileY(lastTile.y, direction)

        listOfTiles.add(SnakeTile.createTile(positionNextTileX, positionNextTileY, direction))
    }

    private fun calculateNextTileY(y: Int, direction: Direction): Int {
        return when ( direction ) {
            Direction.UP -> y-1
            Direction.DOWN -> y+1
            else -> y
        }
    }

    private fun calculateNextTileX(x : Int, direction: Direction): Int {
        return when ( direction ) {
            Direction.LEFT -> x+1
            Direction.RIGHT -> x-1
            else -> x
        }
    }

    fun turnLeft() {
        val currDirection = direction

        if ( !Direction.isOpposite(currDirection, Direction.LEFT)) {
            listOfTiles.first().direction = Direction.LEFT
        }
    }
    fun turnRight() {
        val currDirection = direction

        if  ( !Direction.isOpposite(currDirection, Direction.RIGHT)) {
            listOfTiles.first().direction = Direction.RIGHT
        }
    }
    fun turnUp() {
        val currDirection = direction

        if ( !Direction.isOpposite(currDirection, Direction.UP)) {
            listOfTiles.first().direction = Direction.UP
        }
    }
    fun turnDown() {
        val currDirection = direction

        if ( !Direction.isOpposite(currDirection, Direction.DOWN)) {
            listOfTiles.first().direction = Direction.DOWN
        }
    }
}