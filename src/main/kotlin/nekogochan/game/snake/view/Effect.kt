package nekogochan.game.snake.view

import java.awt.image.BufferedImage

abstract class Effect {
    abstract fun apply(image: BufferedImage)

    override fun hashCode(): Int {
        return 0
    }

    final override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        return javaClass == other.javaClass
    }
}