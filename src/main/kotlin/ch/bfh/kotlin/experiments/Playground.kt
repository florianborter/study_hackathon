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
    //launch<Playground>(args)
    playInPlayground(args)
}

fun playInPlayground(args: Array<String>) {
    println("you are playing in the Playground :D")

    val hexString = "45 00 02 54 2A 00 20 00 29 11 7A ED 0A 00 00 02 C0 A8 00 02"
    val splitHexString = hexString.split(" ")
    println("version / ihl " + splitHexString[0])
    println("Type of service" + splitHexString[1])
    println("Total length" + splitHexString[2] + splitHexString[3])
    println("Fragemnt ID" + splitHexString[4] + splitHexString[5])
    println("Fragment info" + splitHexString[6] + splitHexString[7])
    println("ttl" + splitHexString[8])
    println("protocol" + splitHexString[9])
    println("checksum" + splitHexString[10] + splitHexString[11])
    println("source address" + splitHexString[12] + splitHexString[13] + splitHexString[14] + splitHexString[15])
    println("destination address" + splitHexString[16] + splitHexString[17] + splitHexString[18] + splitHexString[19])
}