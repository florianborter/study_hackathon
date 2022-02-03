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
import jdk.jfr.Percentage
import tornadofx.*
import java.awt.Paint


fun main(args: Array<String>) {
    launch<Responsive>(args)
}

class Responsive : App(ResponsiveView::class) {
    override fun start(stage: Stage) {
        stage.width = 200.0
        stage.height = 200.0
        super.start(stage)
    }
}

class ResponsiveView : View("Look at this Styling") {


    override val root = vbox(20, alignment = Pos.CENTER) {
        spacing = 10.0
        val label1 = label("Default is min-width") {
        }
        button("MaxWidth") {
            useMaxWidth = true
        }
        label("MaxWidth with padding") {
            padding = Insets(20.0)
            useMaxWidth = true
            style {
                backgroundColor += Color.AZURE
            }
        }
        button("50% (no refresh)") {
            prefWidth = primaryStage.width/2
        }
    }

}