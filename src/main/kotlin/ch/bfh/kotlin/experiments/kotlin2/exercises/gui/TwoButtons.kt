package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.stage.Stage
import tornadofx.App
import tornadofx.View
import tornadofx.importStylesheet
import tornadofx.launch
import java.nio.file.Paths

class TwoButtons : App(TwoButtonsView::class) {
    init {
        importStylesheet(Paths.get("./src/main/resources/twobuttons.css"))
    }

    override fun start(stage: Stage) {
        stage.width = 300.0 //θ (1)
        stage.height = 200.0 //θ(1)
        super.start(stage)
    }
}

class TwoButtonsView : View("Two Buttons") {
    var val1 = 0 //θ(1)
    var val2 = 0 //θ(1)
    override val root: HBox by fxml("TwoButtonsView.fxml") //θ(?)
    //override val root: HBox by fxml("TwoButtonsView2.fxml")

    private val label1: Label by fxid() //θ(?)
    private val label2: Label by fxid() //θ(?)

    fun button1Clicked() {
        val1++ //θ(1)
        label1.text = "A = $val1" //θ(1) -> may be unprecise
    }

    fun button2Clicked() {
        val2++ //θ(1)
        label2.text = "B = $val2" //θ(1) -> may be unprecise
    }
}


fun main(args: Array<String>) {
    launch<TwoButtons>(args) //θ(1)
}
