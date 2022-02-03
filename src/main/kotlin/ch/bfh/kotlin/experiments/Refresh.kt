package ch.bfh.kotlin.experiments

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.stage.Stage
import tornadofx.*


fun main(args: Array<String>) {
    launch<Refresh>(args)
}

class Refresh : App(RefreshView::class) {
    override fun start(stage: Stage) {
        stage.width = 300.0
        stage.height = 500.0
        super.start(stage)
    }
}

class RefreshView : View("Refreshing view") {
    var superImportantValue1 = "Ohh"
    var superImportantValue2 = SimpleStringProperty("Ohh")
    val inputProperty = SimpleStringProperty()
    val outputProperty = inputProperty.stringBinding { dosth(it ?: "") }
    val list = mutableListOf(
        SimpleStringProperty("1"),
        SimpleStringProperty("2"),
        SimpleStringProperty("3"),
        SimpleStringProperty("4"),
        SimpleStringProperty("5")
    )
    var nextElement = 6

    override val root = vbox(20, alignment = Pos.CENTER) {
        val label1 = label(superImportantValue1) {
        }
        val label2 = label {
            textProperty().bind(superImportantValue2)
        }
        button("Click 1") {
            action {
                label1.text += "h"
            }
        }
        button("Click 2") {
            action {
                superImportantValue2 += "h"
            }
        }

        textfield(inputProperty)

        label(inputProperty)

        label {
            textProperty().bind(outputProperty)
        }

        button(inputProperty) //bind to whatever you want :D

        button("add next element") {
            action { addHistoryItem(nextElement++) }
        }

        vbox {
            //sorting first by value then by isBound for demonstration purpose
            for (valueBinding in list.sortedWith(compareBy<SimpleStringProperty> { it.value }.thenBy { it.isBound })
                .reversed().asObservable()) {
                label(valueBinding)
            }
        }
    }

    fun dosth(string: String) = "dosth with value in fun, val: '$string'"

    fun addHistoryItem(intvalue: Int) {
        for (i in 0..3) {
            list[i].value = list[i + 1].value
        }
        list[4].value = intvalue.toString()
    }

}