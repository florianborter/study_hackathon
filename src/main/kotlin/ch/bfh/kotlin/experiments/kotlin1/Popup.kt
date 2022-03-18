package ch.bfh.kotlin.experiments.kotlin1

import javafx.geometry.Pos
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

fun main(args: Array<String>) {
    launch<Popup>(args)
}

class Popup : App(PopupMainView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class PopupMainView : View("Popup") {
    override val root = hbox(alignment = Pos.CENTER) {
        button("Open Popup") {
            action {
                find<PopupFragment>().openModal(stageStyle = StageStyle.UTILITY) //https://edvin.gitbooks.io/tornadofx-guide/content/part1/3_Components.html search for "popups"
            }
        }
    }
}

class PopupFragment : Fragment() {//a view is a singleton, a fragment is a specific type of view designed to have multiple instances
    override val root = label {
        text = "guguseli, ds isch dr text im popup"
        paddingAll = 10
    }
}