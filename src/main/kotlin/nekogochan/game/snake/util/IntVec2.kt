package nekogochan.game.snake.util

data class IntVec2(var x: Int, var y: Int) {
    constructor(xy: Int) : this(xy, xy)
    constructor() : this(0)

    operator fun plusAssign(vec: IntVec2) {
        x += vec.x
        y += vec.y
    }

    operator fun plus(vec: IntVec2): IntVec2 {
        return copy().apply { plusAssign(vec) }
    }

    operator fun minusAssign(vec: IntVec2) {
        x -= vec.x
        y -= vec.y
    }

    operator fun minus(vec: IntVec2): IntVec2 {
        return copy().apply { minusAssign(vec) }
    }

    operator fun timesAssign(vec: IntVec2) {
        x *= vec.x
        y *= vec.y
    }

    operator fun times(vec: IntVec2): IntVec2 {
        return copy().apply { timesAssign(vec) }
    }

    operator fun divAssign(vec: IntVec2) {
        x /= vec.x
        y /= vec.y
    }

    operator fun div(vec: IntVec2): IntVec2 {
        return copy().apply { divAssign(vec) }
    }
}