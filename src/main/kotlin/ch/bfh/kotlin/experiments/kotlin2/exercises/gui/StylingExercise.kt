/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.effect.Reflection
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.App
import tornadofx.View
import tornadofx.importStylesheet
import tornadofx.launch
import java.nio.file.Paths

class Styling : App(StylingView::class) {
    init {
        importStylesheet(Paths.get("./src/main/resources/ch/bfh/kotlin/experiments/kotlin2/exercises/gui/exercise.css"))
    }

    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 300.0
        super.start(stage)
    }
}

class StylingView : View("Fancy Tornado FX") {
    override val root: VBox by fxml()

    private val label1: Label by fxid()
    private val button1: Button by fxid()

    fun handleButton() {
        if (button1.effect != null) {
            button1.effect = null
            label1.effect = null
            button1.text = "Add Reflection"
        } else {
            label1.effect = Reflection()
            button1.effect = Reflection()
            button1.text = "Hide Reflection"
        }
    }
}

fun main(args: Array<String>) {
    launch<Styling>(args)
}