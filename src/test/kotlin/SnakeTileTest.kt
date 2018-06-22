import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SnakeTileTest {

    @Test
    fun testTileCreation() {
        val initX = 10
        val initY = 5
        val initDirection = Direction.UP

        val tile = SnakeTile.createTile(initX, initY, initDirection);

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)
    }

    // @TODO Replace with parametrized tests

    @Test
    fun testTileMoveLeft() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.LEFT

        val finalX = 4
        val finalY = 5
        val finalDirection = Direction.LEFT

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(initDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }

    @Test
    fun testTileMoveRight() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.RIGHT

        val finalX = 6
        val finalY = 5
        val finalDirection = Direction.RIGHT

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(initDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }

    @Test
    fun testTileMoveUp() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.UP

        val finalX = 5
        val finalY = 4
        val finalDirection = Direction.UP

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(initDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }

    @Test
    fun testTileMoveDown() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.DOWN

        val finalX = 5
        val finalY = 6
        val finalDirection = Direction.DOWN

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(initDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }

    @Test
    fun testTileMoveVerticalHorizontal() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.LEFT

        val intermediateX = 4
        val intermediateY = 5
        val intermediateDirection = Direction.UP

        val finalX = 4
        val finalY = 4
        val finalDirection = Direction.UP

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(intermediateDirection)

        assertEquals(intermediateX, tile.x)
        assertEquals(intermediateY, tile.y)
        assertEquals(intermediateDirection, tile.direction)

        tile.move(finalDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }

    @Test
    fun testTileMoveVerticalAndOppositeVertical() {
        val initX = 5
        val initY = 5
        val initDirection = Direction.LEFT

        val intermediateX = 4
        val intermediateY = 5
        val intermediateDirection = Direction.RIGHT

        val finalX = 3
        val finalY = 5
        val finalDirection = Direction.LEFT

        val tile = SnakeTile.createTile(initX, initY, initDirection)

        assertEquals(initX, tile.x)
        assertEquals(initY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(intermediateDirection)

        assertEquals(intermediateX, tile.x)
        assertEquals(intermediateY, tile.y)
        assertEquals(initDirection, tile.direction)

        tile.move(finalDirection)

        assertEquals(finalX, tile.x)
        assertEquals(finalY, tile.y)
        assertEquals(finalDirection, tile.direction)
    }
}