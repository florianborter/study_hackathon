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

class FancyTornadoFX : App(FancyTornadoFXView::class) {
    init {
        importStylesheet(Paths.get("./src/main/resources/fancy.css"))
    }

    override fun start(stage: Stage) {
        stage.width = 400.0 //θ (1)
        stage.height = 200.0 //θ(1)
        super.start(stage)
    }
}

class FancyTornadoFXView : View("Fancy Tornado FX") {
    override val root: VBox by fxml("FancyView.fxml") //θ(?)
    //override val root: HBox by fxml("TwoButtonsView2.fxml")

    private val label: Label by fxid()
    private val button: Button by fxid()

    init {
        label.effect = Reflection()
        button.effect = Reflection()
    }

    fun buttonClicked() {
        label.effect = null
        button.effect = null
    }
}


fun main(args: Array<String>) {
    launch<FancyTornadoFX>(args) //θ(1)
}
