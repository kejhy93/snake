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

        var snake = Snake.createSnake(initX, initY, initDirection)

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

        var snake = Snake.createSnake(initX, initY, initDirection)

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

    @Test
    fun testCollisionDetection() {
        val initialX = 5
        val initialY = 5
        val initialDirection = Direction.LEFT

        val snake = Snake.createSnake(initialX, initialY, initialDirection)
        snake.addTile()
        snake.addTile()
        snake.addTile()
        snake.addTile()

        snake.turnUp()
        snake.move()
        assertEquals(false, snake.checkCollision())

        val turnUpAndMoveX = initialX
        val turnUpAndMoveY = initialY + 1
        val turnUpAndMoveDirection = Direction.UP

        assertEquals(turnUpAndMoveX, snake.x)
        assertEquals(turnUpAndMoveY, snake.y)
        assertEquals(turnUpAndMoveDirection, snake.direction)

        snake.turnRight()
        snake.move()
        assertEquals(false, snake.checkCollision())

        val turnRightAndMoveX = turnUpAndMoveX+1
        val turnRightAndMoveY = turnUpAndMoveY
        val turnRightAndMoveDirection = Direction.RIGHT

        assertEquals(turnRightAndMoveX, snake.x)
        assertEquals(turnRightAndMoveY, snake.y)
        assertEquals(turnRightAndMoveDirection, snake.direction)

        assertEquals(false, snake.checkCollision())

        snake.turnDown()
        snake.move()

        val turnDownAndMoveX = turnRightAndMoveX
        val turnDownAndMoveY = turnRightAndMoveY-1
        val turnDownAndMoveDirection = Direction.DOWN

        assertEquals(turnDownAndMoveX, snake.x)
        assertEquals(turnDownAndMoveY, snake.y)
        assertEquals(turnDownAndMoveDirection, snake.direction)

        assertEquals(true, snake.checkCollision())
    }
}