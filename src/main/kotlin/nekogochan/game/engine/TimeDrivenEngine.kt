package nekogochan.game.engine

import kotlin.math.max

class TimeDrivenEngine(
    private var game: Game,
    private val updateDt: Int,
    private val renderDt: Int,
) : Engine {

    private var updateTs: Long = 0
    private var renderTs: Long = 0
    private var running = false

    private fun ts() = System.currentTimeMillis()

    private fun sync() {
        val maxTs = max(updateTs, renderTs)
        while (ts() < maxTs) {
            Thread.sleep(1)
        }
    }

    override fun start() {
        running = true
        while (running) {
            handleUpdate()
            handleRender()
            sync()
        }
    }

    private fun handleUpdate() {
        val ts = ts()
        if (updateTs < ts) {
            game.handleInputs()
            game.update()
            updateTs = ts + updateDt
        }
    }

    private fun handleRender() {
        val ts = ts()
        if (renderTs < ts) {
            game.render()
            renderTs = ts + renderDt
        }
    }

    override fun stop() {
        running = false
    }

    override fun setGame(game: Game) {
        this.game = game
    }
}