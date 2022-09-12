package nekogochan.game.snake.controller

interface Controller {
    val left: Key
    val up: Key
    val right: Key
    val down: Key
    val enter: Key

    fun resetAll()

    interface Key {
        val active: Boolean
    }
}