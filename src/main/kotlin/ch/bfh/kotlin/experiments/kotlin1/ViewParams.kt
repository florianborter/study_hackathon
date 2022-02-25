package ch.bfh.kotlin.experiments

import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.stage.Stage
import tornadofx.*

fun main(args: Array<String>) {
    launch<ViewParams>(args)
}

class ViewParams : App(ViewParamsMainView::class) {
    override fun start(stage: Stage) {
        stage.width = 600.0
        stage.height = 600.0
        super.start(stage)
    }
}

class ViewParamsMainView : View("Popup") {
    var param1TextField by singleAssign<TextField>()
    var param2TextField by singleAssign<TextField>()
    override val root = hbox(alignment = Pos.CENTER) {
        form {
            fieldset("Simple form") {
                field("Enter a Stringparam:") {
                    param1TextField = textfield { }
                }
                field("Enter a Intparam:") {
                    param2TextField = textfield { }
                }
                button("open View in current window") {
                    action {
                        replaceWith(
                            ParameterizedView(
                                param1TextField.text,
                                param2TextField.text.toInt()
                            )
                        ) //hard replacement
                    }
                }
                button("open as popup (background not blocked)") {
                    action {
                        ParameterizedView(
                            param1TextField.text,
                            param2TextField.text.toInt()
                        ).openWindow(owner = null) //other window can be closed without closing this one
                    }
                }
                button("open as popup (in foreground)") {
                    action {
                        ParameterizedView(
                            param1TextField.text,
                            param2TextField.text.toInt()
                        ).openWindow() //will be closed if owner get closed
                    }
                }
                button("open as popup (block background)") {
                    action {
                        ParameterizedView(param1TextField.text, param2TextField.text.toInt()).openModal()
                    }
                }
                button("test with byparam") {
                    action {
                        openInternalWindow<ParameterizedView>(
                            params = mapOf(
                                "param1" to param1TextField.text,
                                "param2" to param2TextField.text.toInt(),
                                "param3" to "Test"
                            )
                        )
                    }
                }
            }
        }
        paddingAll = 10
    }
}

class ParameterizedView(val param1: String = "ZZZZ", val param2: Int = 999999) : Fragment() {
    val param3: String by param(defaultValue = "abcZZZZ")
    override val root = vbox {
        label(param1)
        label(param2.toString())
        label(param3)
        button("Open Main view") {
            action {
                replaceWith(ViewParamsMainView())
            }
        }
        paddingAll = 10
    }
}