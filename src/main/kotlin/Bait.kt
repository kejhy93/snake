class Bait private constructor(initX : Int, initY : Int) {
    var x : Int = 0
    var y : Int = 0

    init {
        x = initX
        y = initY
    }

    companion object {
        fun createBait(initX : Int, initY : Int ) : Bait {
            return Bait(initX, initY)
        }
    }

    fun setPosition(nextX: Int, nextY: Int) {
        x = nextX
        y = nextY
    }
}