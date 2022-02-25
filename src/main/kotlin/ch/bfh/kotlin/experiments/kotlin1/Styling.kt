package ch.bfh.kotlin.experiments

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import tornadofx.*


fun main(args: Array<String>) {
    launch<Styling>(args)
}

class Styling : App(StylingView::class) {
    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 500.0
        super.start(stage)
    }
}

class StylingView : View("Look at this Styling") {

    override val root = vbox(20, alignment = Pos.CENTER) {
        label("Whoa, text") {
            style {
                textFill = Color.WHITE
                backgroundColor += Color.BLACK
                fontSize = 40.px

            }
        }
        label {
            text = "Whoa, other Text set different"
            style {
                fontSize = 5.px
            }
        }
        spacer {

        }
        label {
            text = "Whoa, even more Text"
        }
    }

}