class SnakeTile private constructor (initX : Int, initY : Int, initDirection : Direction){

    var x : Int = initX
    var y : Int = initY
    var direction : Direction = initDirection


    companion object {
        fun createTile ( x : Int, y : Int, direction : Direction ) : SnakeTile {
            return SnakeTile( x, y, direction)
        }
    }

    /**
     * Move tile
     */
    fun move( newDirection : Direction ) {
        when (direction) {
            Direction.LEFT -> x--
            Direction.RIGHT -> x++
            Direction.UP -> y++
            Direction.DOWN -> y--
        }

        if ( !Direction.isOpposite(direction, newDirection)) {
            direction = newDirection
        }
    }
}