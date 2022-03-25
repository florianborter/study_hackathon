/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.App
import tornadofx.View
import tornadofx.importStylesheet
import tornadofx.launch
import java.nio.file.Paths


class ClickMe : App(ClickMeView::class) {
    init {
        //println(System.getProperty("user.dir"))
        importStylesheet(Paths.get("./src/main/resources/clickme.css"))
    }

    override fun start(stage: Stage) {
        stage.width = 300.0
        stage.height = 200.0
        super.start(stage)
    }
}

class ClickMeView : View("Click Me") {
    override val root: VBox by fxml()

    private val label1: Label by fxid()

    fun clickMe() {
        label1.text = "You clicked !!!"
    }
}

fun main(args: Array<String>) {
    launch<ClickMe>(args)
}