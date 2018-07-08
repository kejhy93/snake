import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SnakeTest {
    @Test
    fun testCreation() {
        val initX = 10
        val initY = 5

        val tile = Snake.createSnake(initX, initY)

        Assertions.assertEquals(initX, tile.x)
        Assertions.assertEquals(initY, tile.y)
    }

    @Test
    fun testMove() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.RIGHT

        val finalX = 7
        val finalY = 5
        val finalDirection = Direction.RIGHT

        var snake = Snake.createSnake(initX, initY, 0,initDirection)

        assertEquals(initX, snake.x)
        assertEquals(initY, snake.y)
        assertEquals(initDirection, snake.direction)

        snake.move()
        snake.move()

        assertEquals(finalX, snake.x)
        assertEquals(finalY, snake.y)
        assertEquals(finalDirection, snake.direction)
    }

    @Test
    fun testRotation() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.LEFT

        var snake = Snake.createSnake(initX, initY, 0,initDirection)

        assertEquals(initX, snake.x)
        assertEquals(initY, snake.y)
        assertEquals(initDirection, snake.direction)

        snake.turnUp()
        val directionUp = Direction.UP

        assertEquals(initX, snake.x)
        assertEquals(initY, snake.y)
        assertEquals(directionUp, snake.direction)

        snake.turnRight()
        val directionRight = Direction.RIGHT

        assertEquals(initX, snake.x)
        assertEquals(initY, snake.y)
        assertEquals(directionRight, snake.direction)

        snake.turnDown()
        val directionDown = Direction.DOWN

        assertEquals(initX, snake.x)
        assertEquals(initY, snake.y)
        assertEquals(directionDown, snake.direction)
    }
}