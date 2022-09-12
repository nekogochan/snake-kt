package nekogochan.game.snake.view

interface View {
    val field: FieldTable
    var screenText: String
    fun repaint()
    fun show()
    fun dispose()
}