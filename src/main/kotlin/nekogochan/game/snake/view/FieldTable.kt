package nekogochan.game.snake.view

import nekogochan.game.snake.util.IntVec2
import java.awt.Color

interface FieldTable {
    val width: Int
    val height: Int
    var emptyCellColor: Color

    operator fun get(x: Int, y: Int): Color
    operator fun set(x: Int, y: Int, color: Color)

    operator fun set(xy: IntVec2, color: Color) {
        this[xy.x, xy.y] = color
    }

    fun clear()
    fun forEachCell(callback: (Int, Int, Color) -> Unit)
}