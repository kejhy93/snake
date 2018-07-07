enum class Direction(index: Int) {
    LEFT(0), RIGHT(1), UP(2), DOWN(3), UNKNOWN(4);

    companion object {
        fun isOpposite(first: Direction, second: Direction): Boolean = first == getOpposite(second)

        private fun getOpposite(direction: Direction): Direction =
                when (direction) {
                    LEFT -> RIGHT
                    RIGHT -> LEFT
                    UP -> DOWN
                    DOWN -> UP
                    UNKNOWN -> UNKNOWN
                }

        fun mapIndexToDirection(index: Int): Direction {
            when (index) {
                0 -> LEFT
                1 -> RIGHT
                2 -> UP
                3 -> DOWN
                else -> throw IllegalArgumentException("Unknown direction")
            }
            return UNKNOWN
        }
    }
}