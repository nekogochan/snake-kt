package nekogochan.game.snake.view.swing

import nekogochan.game.snake.util.IntVec2
import nekogochan.game.snake.view.FieldTable
import java.awt.Color

class SwingFieldTable(
    override val width: Int,
    override val height: Int,
    override var emptyCellColor: Color,
) : FieldTable {
    constructor(size: IntVec2, emptyCellColor: Color) : this(size.x, size.y, emptyCellColor)

    private val table: Array<Array<Color>> = Array(width) {
        Array(height) {
            emptyCellColor
        }
    }

    override fun get(x: Int, y: Int): Color {
        return table[x][y]
    }

    override fun set(x: Int, y: Int, color: Color) {
        table[x][y] = color
    }

    override fun clear() {
        forEachCell { x, y, _ ->
            table[x][y] = emptyCellColor
        }
    }

    override fun forEachCell(callback: (Int, Int, Color) -> Unit) {
        for (x in 0 until width) {
            for (y in 0 until height) {
                callback(x, y, table[x][y])
            }
        }
    }
}