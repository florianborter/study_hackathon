package ch.bfh.kotlin.experiments

import javafx.geometry.Pos
import javafx.stage.Stage
import tornadofx.*

class Playground : App(PlaygroundMainView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class PlaygroundMainView : View("Playground") {
    override val root = hbox(alignment = Pos.CENTER) {
        label("playground")
    }
}

fun main(args: Array<String>) {
    launch<Playground>(args)
    playInPlayground(args)
}

fun playInPlayground(args: Array<String>) {
    println("you are playing in the Playground :D")
}