package ch.bfh.kotlin.experiments.kotlin2.exercises.gui

import javafx.collections.FXCollections
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.stage.Stage
import tornadofx.*
import java.nio.file.Paths
import java.text.MessageFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun createEaster(): List<Easter> {
    val result = mutableListOf<Easter>()
    var previous = 0.0
    (2005..2022).map {
        val holyDayCal = LocalDateTime.ofInstant(findHolyDay(it).toInstant(), ZoneId.systemDefault())
        val daytime = holyDayCal.plusHours(Random().nextLong(23)).plusMinutes(Random().nextLong(59))
        val price = Random().nextDouble(100.0)

        val easter = Easter(
            daytime = daytime,
            number = Random().nextInt(1000),
            price = price,
            priceLastYear = previous
        )

        previous = price
        result.add(easter)
    }
    return result.toList()
}


/*
* Compute the day of the year that Easter falls on. Step names E1 E2 etc.,
* are direct references to Knuth, Vol 1, p 155. @exception
* IllegalArgumentexception If the year is before 1582 (since the algorithm
* only works on the Gregorian calendar).
*/
fun findHolyDay(year: Int): Calendar {
    require(year > 1582) { "Algorithm invalid before April 1583" }
    val golden: Int
    val century: Int
    val x: Int
    val z: Int
    val d: Int
    var epact: Int
    var n: Int
    golden = year % 19 + 1 /* E1: metonic cycle */
    century = year / 100 + 1 /* E2: e.g. 1984 was in 20th C */
    x = 3 * century / 4 - 12 /* E3: leap year correction */
    z = (8 * century + 5) / 25 - 5 /* E3: sync with moon's orbit */
    d = 5 * year / 4 - x - 10
    epact = (11 * golden + 20 + z - x) % 30 /* E5: epact */
    if (epact == 25 && golden > 11 || epact == 24) epact++
    n = 44 - epact
    n += 30 * if (n < 21) 1 else 0 /* E6: */
    n += 7 - (d + n) % 7
    return if (n > 31) /* E7: */ GregorianCalendar(year, 4 - 1, n - 31) /* April */ else GregorianCalendar(
        year,
        3 - 1,
        n
    ) /* March */
}

class EasterApp : App(EasterAppView::class) {
    init {
        importStylesheet(Paths.get("./src/main/resources/easterapp.css"))
        FX.locale = Locale.ENGLISH
        FX.messages = ResourceBundle.getBundle("i18n", FX.locale)
    }

    override fun start(stage: Stage) {
        stage.width = 700.0
        stage.height = 700.0
        super.start(stage)
    }
}

class EasterAppView : View("Easter App") {
    val elements = FXCollections.observableList(createEaster())

    override val root: HBox by fxml("EasterAppView.fxml")

    private val textLabel: Label by fxid()
    private val table: TableView<Easter> by fxid()

    init {
        table.addEventHandler(MouseEvent.MOUSE_CLICKED, TableClickedHandler())
        table.items = elements
    }

    inner class TableClickedHandler : javafx.event.EventHandler<MouseEvent> {

        override fun handle(p0: MouseEvent?) {
            val easter = table.selectionModel.selectedItem
            rowSelected(easter)
        }
    }

    fun rowSelected(easter: Easter) {
        textLabel.text = MessageFormat.format(
            FX.messages["description"],
            easter.daytime.year,
            easter.daytime.toLocalTime(),
            easter.diff
        )
    }
}

fun main(args: Array<String>) {
    launch<EasterApp>(args)
}

data class Easter(val daytime: LocalDateTime, val number: Int, val price: Double, val priceLastYear: Double = price) {
    val diff
        get() = (100.0 / priceLastYear) * price

    val day
        get() = DateTimeFormatter.ISO_DATE.format(daytime)

    val arrival
        get() = DateTimeFormatter.ISO_TIME.format(daytime)
}