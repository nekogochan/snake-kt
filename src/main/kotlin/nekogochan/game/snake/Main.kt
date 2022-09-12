package nekogochan.game.snake

import nekogochan.game.engine.TimeDrivenEngine
import nekogochan.game.snake.controller.SwingKeyboardController
import nekogochan.game.snake.util.IntVec2
import nekogochan.game.snake.view.swing.SwingFieldTable
import nekogochan.game.snake.view.swing.SwingView
import java.awt.Color.BLACK

fun main() {
    // create game
    val controller = SwingKeyboardController()
    val view = SwingView(
        field = SwingFieldTable(IntVec2(20), BLACK),
        windowSize = IntVec2(600)
    )
    val game = SnakeGame(
        view = view,
        controller = controller
    )

    // create engine
    val fps = 20
    val ups = 5
    val engine = TimeDrivenEngine(
        game = game,
        renderDt = fps / 1000,
        updateDt = ups / 1000
    )

    // init engine
    view.onDispose = engine::stop

    // start game
    view.show()
    engine.start()
}