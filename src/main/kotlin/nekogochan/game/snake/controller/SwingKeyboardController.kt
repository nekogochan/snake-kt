package nekogochan.game.snake.controller

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame

class SwingKeyboardController : Controller {

    override lateinit var left: Key
    override lateinit var up: Key
    override lateinit var right: Key
    override lateinit var down: Key
    override val enter = Key(0)

    init {
        left = Key.Exclusive(0, this::up, this::right, this::down)
        up = Key.Exclusive(0, this::left, this::right, this::down)
        right = Key.Exclusive(0, this::left, this::up, this::down)
        down = Key.Exclusive(0, this::left, this::up, this::right)
    }

    private val all: Sequence<Key> get() = sequenceOf(left, up, right, down, enter)

    override fun resetAll() {
        all.forEach { it.active = false }
    }

    fun applyTo(frame: JFrame) {
        frame.addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {
            }

            override fun keyPressed(e: KeyEvent?) {
                all.find { it.code == e?.keyCode}.apply {
                    if (this != null) {
                        active = true
                    }
                }
            }

            override fun keyReleased(e: KeyEvent?) {
            }
        })
    }

    open class Key(val code: Int) : Controller.Key {
        override var active: Boolean = false

        class Exclusive(code: Int, private vararg val exclusives: () -> Key) : Key(code) {
            override var active: Boolean
                get() = super.active
                set(active) {
                    super.active = active
                    if (active) {
                        exclusives.forEach {
                            it().active = false
                        }
                    }
                }
        }
    }
}