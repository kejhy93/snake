import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GridTest {

    @Test
    fun testGridCreation() {
        val initSize = 5
        var grid = Grid.createGrid(initSize, initSize)

        assertEquals(initSize, grid.grid.size)
        assertEquals(initSize, grid.grid[0].size)

        val initWidth = 5
        val initHeight = 3

        grid = Grid.createGrid(initWidth, initHeight)

        assertEquals(initHeight, grid.grid.size)
        assertEquals(initWidth, grid.grid[0].size)
    }

    @Test
    fun testSettingGridTile() {
        val initWidht = 5
        val initHeight = 4

        val grid = Grid.createGrid(initWidht, initHeight)
        grid.setTileAt(1, 1, GridTile.BORDER)
        grid.setTileAt(2, 1, GridTile.BORDER)
        grid.setTileAt(1, 2, GridTile.BORDER)

        assertEquals(GridTile.EMPTY, grid.getTileAt(0, 0))
        assertEquals(GridTile.EMPTY, grid.getTileAt(2,2))
        assertEquals(GridTile.EMPTY, grid.getTileAt(3,3))

        assertEquals(GridTile.BORDER, grid.getTileAt(1,1))
        assertEquals(GridTile.BORDER, grid.getTileAt(1,2))
        assertEquals(GridTile.BORDER, grid.getTileAt(2,1))
    }
}