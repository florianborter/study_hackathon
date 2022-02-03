package ch.bfh.kotlin.experiments

import javafx.beans.Observable
import javafx.beans.property.SimpleStringProperty
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
    launch<Refresh>(args)
}

class Refresh : App(RefreshView::class) {
    override fun start(stage: Stage) {
        stage.width = 200.0
        stage.height = 200.0
        super.start(stage)
    }
}

class RefreshView : View("Look at this Styling") {
    var superImportantValue1 = "Ohh"
    var superImportantValue2 = SimpleStringProperty("Ohh")


    override val root = vbox(20, alignment = Pos.CENTER) {
        val label1 = label(superImportantValue1) {
        }
        val label2 = label {
            textProperty().bind(superImportantValue2)
        }
        button("Click 1") {
            action {
                label1.text += "h"
            }
        }
        button("Click 2") {
            action {
                superImportantValue2 += "h"
            }
        }
    }

}