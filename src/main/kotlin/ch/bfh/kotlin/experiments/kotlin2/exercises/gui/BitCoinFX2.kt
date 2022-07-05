/** Programming with Kotlin,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

import ch.bfh.kotlin.experiments.kotlin2.exercises.async.BitCoinData
import ch.bfh.kotlin.experiments.kotlin2.exercises.async.getBitCoinData
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.fixedRateTimer

class BitCoinApp : App(BitCoinView::class) {
    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 250.0
        super.start(stage)
    }
}

class BitCoinView : View("BitcoinApp2") {
    private var rateEur: Label by singleAssign()
    private var rateGbp: Label by singleAssign()
    private var rateUsd: Label by singleAssign()
    private var timeStamp: Label by singleAssign()
    private var message: Label by singleAssign()

    private val history = ArrayList<BitCoinData>()

    init {
        fixedRateTimer("Timer", true, 0L, 30000) {
            getBitCoinDataAsync()
        }
    }

    override val root = borderpane {
        top {
            useMaxWidth = true
            gridpane {
                hgap = 10.0
                vgap = 10.0
                alignment = Pos.CENTER
                row {
                    //   alignment = Pos.CENTER
                    label("last updated")
                    label("USD")
                    label("GBP")
                    label("EUR")
                }
                row {
                    // alignment = Pos.CENTER
                    timeStamp = label("00:00:00")
                    rateUsd = label(10000.0.toString())
                    rateGbp = label(10000.0.toString())
                    rateEur = label(10000.0.toString())
                }
            }
        }
        center {
            useMaxWidth = true
            hbox(10, alignment = Pos.CENTER) {
                button("Update") {
                    action {
                        println("Updated: The UI is reactive.")
                        getBitCoinDataAsync()
                    }
                }
                button("Get History") {
                    action {
                        println("The UI is reactive.")
                        history.forEach { println(it) }
                    }
                }
            }
        }
        bottom {
            useMaxWidth = true
            message = label("") {
                textFill = Color.RED
            }
        }
    }

    private fun getBitCoinDataAsync() {
        lateinit var newRate: BitCoinData
        runAsync {
            newRate = getBitCoinData()
            if (history.isEmpty() || history.last() != newRate) {
                history.add(newRate)
            }
        } ui {
            updateLabels(newRate)
        } fail {
            message.textFill = Color.RED
            message.text = "ERROR: $it"
        }
    }

    private fun updateLabels(data: BitCoinData) {
        timeStamp.text = LocalDateTime.parse(data.time.updatedISO, DateTimeFormatter.ISO_DATE_TIME)
            .format(DateTimeFormatter.ISO_TIME)
        rateEur.text = String.format("%10.2f", data.bpi.EUR.rate_float)
        rateGbp.text = String.format("%10.2f", data.bpi.GBP.rate_float)
        rateUsd.text = String.format("%10.2f", data.bpi.USD.rate_float)
        message.text = ""
    }
}

fun main(args: Array<String>) {
    launch<BitCoinApp>(args)
}

