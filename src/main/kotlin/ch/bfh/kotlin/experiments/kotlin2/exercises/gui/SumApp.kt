package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.stage.Stage
import tornadofx.*

class SumApp : App(SumView::class) {
    override fun start(stage: Stage) {
        stage.width = 550.0
        stage.height = 200.0
        super.start(stage)
    }
}

class SumView : View("Sum App") {
    private var tf1: TextField by singleAssign()
    private var tf2: TextField by singleAssign()
    private var res: TextField by singleAssign()
    override val root = hbox(spacing = 10, alignment = Pos.CENTER) {
        tf1 = textfield("1") {
            this.prefColumnCount = 5
            this.filterInput { it.controlNewText.isInt() }
            this.textProperty().addListener { _ ->
                res.text = add(this.text, tf2.text)
            }
        }
        label(" + ")
        tf2 = textfield("1") {
            this.prefColumnCount = 5
            this.filterInput { it.controlNewText.isInt() }
            this.textProperty().addListener { _ ->
                res.text = add(this.text, tf1.text)
            }
        }
        label(" =  ")
        res = textfield {
            this.prefColumnCount = 10
            this.isEditable = false
        }
    }

    private fun add(s1: String, s2: String) =
        (try {
            s1.toInt()
        } catch (e: NumberFormatException) {
            0
        } +
                try {
                    s2.toInt()
                } catch (e: NumberFormatException) {
                    0
                }).toString()
}

fun main(args: Array<String>) {
    launch<SumApp>(args)
}