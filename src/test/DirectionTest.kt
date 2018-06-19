package test

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DirectionTest {

    @Test
    fun oppositeDirection() {
        val left = Direction.LEFT
        val right = Direction.RIGHT
        val up = Direction.UP
        val down = Direction.DOWN

        assertTrue { Direction.isOpposite(left, right) }
        assertTrue { Direction.isOpposite(right, left) }
        assertTrue { Direction.isOpposite(up, down) }
        assertTrue { Direction.isOpposite(down, up) }

        assertFalse { Direction.isOpposite(left, up) }
        assertFalse { Direction.isOpposite(left, down) }
        assertFalse { Direction.isOpposite(right, up) }
        assertFalse { Direction.isOpposite(right, down) }
        assertFalse { Direction.isOpposite(up, left) }
        assertFalse { Direction.isOpposite(up, right) }
        assertFalse { Direction.isOpposite(down, left) }
        assertFalse { Direction.isOpposite(down, right) }
    }
}