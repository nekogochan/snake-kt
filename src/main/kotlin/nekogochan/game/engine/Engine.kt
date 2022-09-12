package nekogochan.game.engine

interface Engine {
    fun start()
    fun stop()
    fun setGame(game: Game)
}