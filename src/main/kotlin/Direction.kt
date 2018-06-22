enum class Direction {
    LEFT, RIGHT, UP, DOWN;

    companion object {
        fun isOpposite(first : Direction, second : Direction) : Boolean = first == getOpposite(second)

        private fun getOpposite(direction : Direction ) : Direction =
                when ( direction ) {
                    LEFT -> RIGHT
                    RIGHT -> LEFT
                    UP -> DOWN
                    DOWN -> UP
                }
    }
}