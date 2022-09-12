package nekogochan.game.snake.view.swing

import nekogochan.game.snake.util.IntVec2
import nekogochan.game.snake.view.FieldTable
import nekogochan.game.snake.view.View
import java.awt.Font
import java.awt.Font.PLAIN
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.Rectangle
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants.CENTER
import javax.swing.WindowConstants.DISPOSE_ON_CLOSE


class SwingView(
    override val field: FieldTable,
    windowSize: IntVec2,
    var onDispose: () -> Unit = {},
) : View {
    override var screenText: String = ""
    private val window: JFrame
    private val image: BufferedImage
    private val gx: Graphics2D
    private val textLabel: JLabel

    init {
        // TODO: 11.09.2022 fix absolute pos
        image = BufferedImage(windowSize.x, windowSize.y, TYPE_INT_RGB)
        gx = image.createGraphics()
        window = JFrame().apply {
            layout = null
            textLabel = JLabel().apply {
                bounds = insets.toRectangle()
                horizontalAlignment = CENTER
                verticalAlignment = CENTER
                font = Font("Verdana", PLAIN, 11)
            }
            setSize(
                windowSize.x + insets.left + insets.right,
                windowSize.y + insets.top + insets.bottom
            )
            add(JLabel().apply {
                icon = ImageIcon(image)
                bounds = insets.toRectangle()
            })
            updateLabelText()
            defaultCloseOperation = DISPOSE_ON_CLOSE
            addWindowListener(object : WindowAdapter() {
                override fun windowClosed(e: WindowEvent?) {
                    onDispose()
                }
            })
        }
    }

    override fun repaint() {
        updateImage()
        updateLabelText()
        window.repaint()
    }

    override fun show() {
        window.isVisible = true
    }

    override fun dispose() {
        window.dispose()
    }

    private fun updateImage() {
        val tmp = BufferedImage(field.width, field.height, TYPE_INT_RGB)
        field.forEachCell { x, y, color ->
            tmp.setRGB(x, y, color.rgb)
        }
        image.graphics.drawImage(
            tmp.getScaledInstance(image.width, image.height, TYPE_INT_RGB),
            0,
            0,
            null
        )
    }

    private fun updateLabelText() {
        textLabel.text = "<html><p>$screenText</p></html>"
    }
}

private fun Insets.toRectangle() = Rectangle(left, top, right - left, bottom - top)
