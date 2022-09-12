package nekogochan.game.snake

import nekogochan.game.engine.Game
import nekogochan.game.snake.controller.Controller
import nekogochan.game.snake.util.IntVec2
import nekogochan.game.snake.view.View
import java.awt.Color.RED

class SnakeGame(
    val controller: Controller,
    val view: View,
) : Game {

    private val redSquarePos = IntVec2()
    private var updates = 0

    override fun update() {
        println("SnakeGame.update")
        updates++
    }

    override fun render() {
        println("SnakeGame.render")
        view.apply {
            field.clear()
            field[redSquarePos] = RED
            screenText = updates.toString()
            repaint()
        }
    }

    override fun handleInputs() {
        if (controller.left.active) { redSquarePos.x-- }
        if (controller.up.active) { redSquarePos.y-- }
        if (controller.right.active) { redSquarePos.x++ }
        if (controller.down.active) { redSquarePos.y++ }
    }
}
