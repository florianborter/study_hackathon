package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.beans.binding.Bindings
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*


class ThreeCircleApp : App(ThreeCircleView::class) {
    override fun start(stage: Stage) {
        stage.width = 300.0
        stage.height = 300.0
        super.start(stage)
    }
}

class ThreeCircleView : View("Three Circles") {
    override val root = pane {
        circle {
            fill = Color.BLUE
            centerXProperty().bind(Bindings.divide(this@pane.widthProperty(), 4))
            //centerXProperty().addListener { it -> println(it) }
            centerYProperty().bind(Bindings.divide(this@pane.heightProperty(), 2))
            radiusProperty()
                .bind(
                    Bindings.min(
                        this@pane.widthProperty().divide(4),
                        Bindings.divide(this@pane.heightProperty(), 2)
                    )
                )


        }
        circle {
            fill = Color.BLUEVIOLET
            centerXProperty().bind(Bindings.divide(this@pane.widthProperty(), 2))
            //centerXProperty().addListener { it -> println(it) }
            centerYProperty().bind(Bindings.divide(this@pane.heightProperty(), 2))
            radiusProperty()
                .bind(
                    Bindings.min(
                        this@pane.widthProperty().divide(4),
                        Bindings.divide(this@pane.heightProperty(), 2)
                    )
                )

        }
        circle {
            fill = Color.VIOLET
            centerXProperty().bind(
                Bindings.add(
                    Bindings.divide(this@pane.widthProperty(), 2),
                    Bindings.divide(this@pane.widthProperty(), 4)
                )
            )
            centerYProperty().bind(Bindings.divide(this@pane.heightProperty(), 2))
            radiusProperty()
                .bind(
                    Bindings.min(
                        this@pane.widthProperty().divide(4),
                        Bindings.divide(this@pane.heightProperty(), 2)
                    )
                )
            //radiusProperty().addListener { it -> println(it) }
        }
    }
}

fun main(args: Array<String>) {
    launch<ThreeCircleApp>(args)
}