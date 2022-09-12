package nekogochan.game.snake.view

interface Field {
    val table: FieldTable
    val effeсts: MutableSet<Effect>
}