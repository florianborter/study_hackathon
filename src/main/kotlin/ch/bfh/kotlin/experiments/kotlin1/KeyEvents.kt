package ch.bfh.kotlin.experiments.kotlin1

import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import tornadofx.*

class Events : App(EventsMainView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class EventsMainView : View("Events") {
    val keycode = SimpleStringProperty()
    val keychar = SimpleStringProperty()
    val text = SimpleStringProperty()
    val character = SimpleStringProperty()
    val ctrlDown = SimpleStringProperty()
    val shiftDown = SimpleStringProperty()
    val altDown = SimpleStringProperty()
    val metaDown = SimpleStringProperty()

    override val root = vbox(alignment = Pos.CENTER) {
        addEventFilter(KeyEvent.KEY_RELEASED, KeyEventHandler())
        hbox {
            label("keycode".padEnd(15))
            label(keycode)
        }
        hbox {
            label("keychar:".padEnd(15))
            label(keychar)
        }
        hbox {
            label("text:".padEnd(15))
            label(text)
        }
        hbox {
            label("character:".padEnd(15))
            label(character)
        }
        hbox {
            label("ctrlDown:".padEnd(15))
            label(ctrlDown)
        }
        hbox {
            label("shiftDown:".padEnd(15))
            label(shiftDown)
        }
        hbox {
            label("altDown:".padEnd(15))
            label(altDown)
        }
        hbox {
            label("metaDown:".padEnd(15))
            label(metaDown)
        }
    }

    override fun onDock() {
        root.requestFocus()
    }

    inner class KeyEventHandler : EventHandler<KeyEvent> {
        override fun handle(keyEvent: KeyEvent?) {
            if (keyEvent != null) {
                keycode.value = keyEvent.code.code.toString()
                keychar.value = keyEvent.character
                text.value = keyEvent.text
                character.value = keyEvent.code.char
                ctrlDown.value = keyEvent.isControlDown.toString()
                shiftDown.value = keyEvent.isShiftDown.toString()
                altDown.value = keyEvent.isAltDown.toString()
                metaDown.value = keyEvent.isMetaDown.toString()
            }
        }
    }
}

fun main(args: Array<String>) {
    launch<Events>(args)
}