package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.stage.Stage
import tornadofx.*

class CircleApp : App(CircleView::class) {
    override fun start(stage: Stage) {
        stage.width = 350.0
        stage.height = 350.0
        super.start(stage)
    }
}

class CircleView : View("Dynamic Circles") {
    private val numbers = mutableListOf("1", "2", "3").asObservable()
    override val root = borderpane {
        top {
            vbox(alignment = Pos.CENTER, spacing = 10.0) {
                this.padding = Insets(10.0)
                button("Add") {
                    action { numbers.add((numbers.size + 1).toString()) }
                }
                button("Remove") {
                    action { if (!numbers.isEmpty()) numbers.remove(numbers.last()) }
                }
            }
        }
        center {
            flowpane {
                this.padding = Insets(10.0)
                children.bind(numbers) {
                    stackpane {
                        circle(50.0, 50.0, 50.0) {
                            style { fill = Color.PALEVIOLETRED }
                        }
                        label(it) {
                            style {
                                this.fontWeight = FontWeight.BOLD
                            }
                        }
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    launch<CircleApp>(args)
}