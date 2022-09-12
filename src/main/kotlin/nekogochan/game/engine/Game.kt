package nekogochan.game.engine

interface Game {
    fun update()
    fun render()
    fun handleInputs()
}
