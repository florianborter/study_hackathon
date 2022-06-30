package ch.bfh.kotlin.experiments.kotlin2.exercises

/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*
import kotlin.math.cos
import kotlin.math.sin


class TreeFactal : App(TreeFactalView::class) {
    override fun start(stage: Stage) {
        stage.width = 550.0
        stage.height = 450.0
        super.start(stage)
    }
}

class TreeFactalView: View("Tree Factal") {
    override val root = stackpane {
       canvas(500.0,400.0) {
           val gc = this.graphicsContext2D
           gc.stroke = Color.GREEN
           // TODO change len and angle to get different trees
           drawTree(gc, width / 2, height, 100.0, 90.0)
       }
    }

    private fun drawTree(
        gc: GraphicsContext,
        x0: Double,
        y0: Double,
        len: Double,
        angle: Double
    ) {
        if (len > 2) {
            val x1 = x0 + len * cos(Math.toRadians(angle))
            val y1 = y0 - len * sin(Math.toRadians(angle))
            gc.strokeLine(x0, y0, x1, y1)
            drawTree(gc, x1, y1, len * 0.75, angle + 30)
            drawTree(gc, x1, y1, len * 0.66, angle - 50)
        }
    }
}

fun main(args: Array<String>) {
    launch<TreeFactal>(args)
}