class Grid private constructor (var width : Int, var height : Int) {

    var grid : Array<Array<GridTile>> = Array(height, { _ -> Array(width, { _ -> GridTile.EMPTY})})
        private set

    companion object {
        fun createGrid ( width : Int, height : Int ) : Grid {
            return Grid(width, height)
        }
    }

    fun getTileAt( x : Int, y : Int ) : GridTile {
        if ( x in 0..(width - 1) && y in 0..(height-1)) {
            return grid[x][y]
        }
        throw IllegalArgumentException("Index out of bound")
    }

    fun setTileAt ( x : Int,y : Int, newTile : GridTile) {
        if ( x in 0..(width - 1) && y in 0..(height-1)) {
            grid[x][y] = newTile
        } else {
            throw IllegalArgumentException("Index out of bound")
        }
    }
}

enum class GridTile {
    EMPTY, BORDER, FOOD;
}