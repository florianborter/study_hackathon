/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.stage.Stage
import tornadofx.*


class Buttons : App(BView::class) {
    override fun start(stage: Stage) {
        stage.width = 300.0
        stage.height = 200.0
        super.start(stage)
    }
}

class BView : View("Click me Old style") {
    private var l: Label by singleAssign()
    override val root = vbox(spacing = 20, alignment = Pos.CENTER) {
        paddingAll = 10.0
        l = label("Waiting ...")
        button("Click me") { action { l.text = "You clicked !!!" } }
    }
}

fun main(args: Array<String>) {
    launch<Buttons>(args)
}