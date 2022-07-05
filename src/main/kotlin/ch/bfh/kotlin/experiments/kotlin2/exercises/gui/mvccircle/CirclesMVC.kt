package ch.bfh.kotlin.experiments.kotlin2.exercises.gui.mvccircle

/** Programming 2 with Kotlin - FS 19/20,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import kotlin.math.abs
import kotlin.math.sqrt

class CircleApp : App(CircleView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 500.0
        super.start(stage)
    }
}

class CircleView : View("Circle Drawer"), PropertyChangeListener {
    override val root: HBox by fxml("Circles.fxml")
    private val fieldX: TextField by fxid()
    private val fieldY: TextField by fxid()
    private val fieldR: TextField by fxid()
    private val canvas: Canvas by fxid()
    private val message: Label by fxid()

    // Model
    private val circle = CircleModel2(100.0,100.0,50.0)

    private var centerX = 0.0
    private var centerY = 0.0


    init {
        circle.addObserver(this)
        fieldX.text = circle.centerX.toString()
        fieldX.filterInput { it.controlNewText.isDouble() }
        fieldY.text = circle.centerY.toString()
        fieldY.filterInput { it.controlNewText.isDouble() }
        fieldR.text = circle.radius.toString()
        fieldR.filterInput { it.controlNewText.isDouble() && (it.controlNewText.toDouble() < 300) }

        drawCircle(circle.centerX, circle.centerY, circle.radius)
        message.text = "X = ${circle.centerX} Y = ${circle.centerY} Radius = ${circle.radius}"
    }

    fun setX() {
        println("setX")
        circle.centerX = fieldX.text.toDouble()
    }

    fun setY() {
        circle.centerY = fieldY.text.toDouble()
    }

    fun setR() {
        circle.radius = fieldR.text.toDouble()
    }

    fun onMousePressed(event: MouseEvent) {
        // remember the values
       centerX = event.x
       centerY = event.y
        println("MousePressed: ${circle.centerX},${circle.centerY}")
    }

    fun onMouseReleased(event: MouseEvent) {
        println("MouseReleased: ${event.x},${event.y}")

        val a: Double = abs(centerX - event.x)
        val b: Double = abs(centerY - event.y)

        circle.centerX = centerX
        circle.centerY = centerY
        circle.radius = sqrt(a * a + b * b)
    }


    private fun drawCircle(x: Double, y: Double, r: Double) {
        val gc = canvas.graphicsContext2D
        gc.clearRect(0.0, 0.0, canvas.width, canvas.height)
        gc.fill = Color.YELLOWGREEN
        gc.fillOval(x - r, y - r, 2 * r, 2 * r)
    }

    override fun propertyChange(evt: PropertyChangeEvent?) {
        drawCircle(circle.centerX, circle.centerY, circle.radius)
        message.text = "X = ${circle.centerX} Y = ${circle.centerY} Radius = ${circle.radius}"
        fieldX.text = circle.centerX.toString()
        fieldY.text = circle.centerY.toString()
        fieldR.text = circle.radius.toString()
    }
}

fun main(args: Array<String>) {
    launch<CircleApp>(args)
}