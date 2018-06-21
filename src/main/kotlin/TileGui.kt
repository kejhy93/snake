import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

class TileGui ( var x : Int, var y : Int, var width : Int, var height : Int) {

    fun draw (g : Graphics) {
        var g2d = g as Graphics2D

        val xPos = IntArray(4)
        val yPos = IntArray(4)
        xPos[0] = x
        xPos[1] = x+width
        xPos[2] = x+width
        xPos[3] = x

        yPos[0] = y
        yPos[1] = y
        yPos[2] = y+height
        yPos[3] = y+height

        g2d.color = Color.BLACK
        g2d.drawPolygon(xPos, yPos, 4)
    }
}