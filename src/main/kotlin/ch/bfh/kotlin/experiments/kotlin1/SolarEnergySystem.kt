package ch.bfh.kotlin.experiments.kotlin1

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.stage.Stage
import tornadofx.*

fun main(args: Array<String>) {
    launch<SolarSystem>(args)
}

class SolarEnergySystem(val capacity: Double, val area: Double, var chargingLevel: Double) {

    val historyList = mutableListOf<HistoryItem>()

    fun sunshine(energy: Double) {
        chargingLevel += energy
        if (chargingLevel > capacity) {
            chargingLevel = capacity
            throw EnergyException("charginglevel exceeds capacity, energy gets lost")
        }
    }

    fun consume(energy: Double) {
        if (energy > chargingLevel) {
            throw EnergyException("Not enough energy in battery")
        }

        chargingLevel -= energy
    }

    fun getHistory(): List<HistoryItem> {
        return historyList.reversed().take(5).toList()
    }

    override fun toString(): String {
        return "SolarEnergySystem(capacity=$capacity, area=$area, chargingLevel=$chargingLevel)"
    }

}

class EnergyException(message: String) : Exception(message)

class SolarSystem : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class MainView : View("My Solar Energy System") {
    val solarEnergySystem = SolarEnergySystem(capacity = 100.0, area = 20.0, chargingLevel = 40.0)
    var quantityField = TextField()

    override val root = hbox(alignment = Pos.CENTER) {
        val vbox = form {
            label {
                text = "Solar Energy System - Capacity: ${solarEnergySystem.capacity} Area: ${solarEnergySystem.area}"
                style {
                    fontSize = 15.px
                }
            }
            label {
                text = "Charging level: ${solarEnergySystem.chargingLevel}"
                style {
                    fontSize = 15.px
                }
            }
            form {
                fieldset {
                    field("Quantity: ") {
                        quantityField = textfield()
                    }
                }

                hbox {
                    button("sunshine") {
                        action {
                            val amount = quantityField.text.toDouble()
                            try {
                                solarEnergySystem.sunshine(amount)
                                solarEnergySystem.historyList.add(HistoryItem("sunshine + $amount", Status.NORMAL, solarEnergySystem))
                            } catch (e: EnergyException) {
                                solarEnergySystem.historyList.add(HistoryItem("sunshine + $amount", Status.EXCEPTION, solarEnergySystem))
                            }
                        }
                    }
                    button("consume") {
                        action {
                            val amount = quantityField.text.toDouble()
                            try {
                                solarEnergySystem.consume(amount)
                                solarEnergySystem.historyList.add(HistoryItem("consume + $amount", Status.NORMAL, solarEnergySystem))
                            } catch (e: EnergyException) {
                                solarEnergySystem.historyList.add(HistoryItem("consume + $amount", Status.EXCEPTION, solarEnergySystem))
                            }
                        }
                    }
                    style {
                        spacing = 10.px
                    }
                }
            }

            spacer()

            for (item in solarEnergySystem.getHistory()) {
                label(item.toString())
            }
        }
    }
}

enum class Status {
    NORMAL, EXCEPTION
}

class HistoryItem(val methodNameAndParam: String, val status: Status, val solarEnergySystem: SolarEnergySystem) {
    override fun toString(): String {
        return "[$status] $methodNameAndParam: (new charging level= ${solarEnergySystem.chargingLevel})"
    }
}