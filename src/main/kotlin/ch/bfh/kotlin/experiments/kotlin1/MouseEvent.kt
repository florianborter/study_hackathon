package ch.bfh.kotlin.experiments

import javafx.event.EventType
import javafx.geometry.Pos
import javafx.scene.control.Control
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import tornadofx.*
import java.beans.EventHandler
import kotlin.random.Random

fun main(args: Array<String>) {
    launch<MouseEventr>(args)
}

class MouseEventr : App(Clicker::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class Clicker : View("Click") {
    override val root = vbox(alignment = Pos.CENTER) {
        addEventHandler(MouseEvent.MOUSE_CLICKED, MHandler())
    }

    inner class MHandler : javafx.event.EventHandler<MouseEvent> {

        override fun handle(p0: MouseEvent?) {
            val color: Color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            var pressed = p0?.pickResult?.intersectedNode?.style {
                backgroundColor += color
            }
        }
    }
}
