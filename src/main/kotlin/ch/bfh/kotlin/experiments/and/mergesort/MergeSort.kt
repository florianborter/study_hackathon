package ch.bfh.kotlin.experiments.and.mergesort

import ch.bfh.kotlin.experiments.and.DivideAndConquerable
import ch.bfh.kotlin.experiments.and.DivideAndConquerableConcurrent
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.stage.Stage
import kotlinx.coroutines.asCoroutineDispatcher
import tornadofx.*
import java.util.concurrent.Executors

class MergeSort(private val partialList: Array<Int>) : DivideAndConquerable<Array<Int>> {
    override val isBasic: Boolean
        get() {
            return partialList.size < 3
        }

    override fun baseFun(): Array<Int> {
        return if(partialList.size == 1 || partialList[0] <= partialList[1]) {
            partialList
        } else {
            Array(2) {partialList[1];partialList[0]}
        }

    }

    override fun decompose(): List<MergeSort> {
        val halfPoint = partialList.size / 2
        val firstHalf = partialList.sliceArray(0..halfPoint)
        val secondHalf = partialList.sliceArray(halfPoint + 1 until partialList.size)
        return listOf(MergeSort(firstHalf), MergeSort(secondHalf))
    }

    override fun recombine(intermediateResults: MutableList<Array<Int>>): Array<Int> {
        val nElements = intermediateResults[0].size + intermediateResults[1].size
        var x = 0
        var y = 0
        val sortedIntermediateResult = Array(nElements){0}
        for (i in 0 until nElements) {
            if( intermediateResults[0].size == x) {
                sortedIntermediateResult[i] = intermediateResults[1][y]
                y++
                continue
            }

            if( intermediateResults[1].size == y) {
                sortedIntermediateResult[i] = intermediateResults[0][x]
                x++
                continue
            }

            if (intermediateResults[0][x] < intermediateResults[1][y]) {
                sortedIntermediateResult[i] = intermediateResults[0][x]
                x++
            } else {
                sortedIntermediateResult[i] = intermediateResults[1][y]
                y++
            }
        }
        return sortedIntermediateResult
    }

}

class MergeSortConcurrent(private val partialList: Array<Int>) : DivideAndConquerableConcurrent<Array<Int>> {
    override val isBasic: Boolean
        get() {
            return partialList.size < 3
        }

    override fun baseFun(): Array<Int> {
        return if(partialList.size == 1 || partialList[0] <= partialList[1]) {
            partialList
        } else {
            Array(2) {partialList[1];partialList[0]}
        }

    }

    override fun decompose(): List<MergeSortConcurrent> {
        val halfPoint = partialList.size / 2
        val firstHalf = partialList.sliceArray(0..halfPoint)
        val secondHalf = partialList.sliceArray(halfPoint + 1 until partialList.size)
        return listOf(MergeSortConcurrent(firstHalf), MergeSortConcurrent(secondHalf))
    }

    override fun recombine(intermediateResults: MutableList<Array<Int>>): Array<Int> {
        val nElements = intermediateResults[0].size + intermediateResults[1].size
        var x = 0
        var y = 0
        val sortedIntermediateResult = Array(nElements){0}
        for (i in 0 until nElements) {
            if( intermediateResults[0].size == x) {
                sortedIntermediateResult[i] = intermediateResults[1][y]
                y++
                continue
            }

            if( intermediateResults[1].size == y) {
                sortedIntermediateResult[i] = intermediateResults[0][x]
                x++
                continue
            }

            if (intermediateResults[0][x] < intermediateResults[1][y]) {
                sortedIntermediateResult[i] = intermediateResults[0][x]
                x++
            } else {
                sortedIntermediateResult[i] = intermediateResults[1][y]
                y++
            }
        }
        return sortedIntermediateResult
    }

}


fun main(args: Array<String>) {
    launch<Chart>(args)
}

class Chart : App(ChartView::class) {
    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 500.0
        super.start(stage)
    }
}

class ChartView : View("Mergesort Chart") {
    override val root = vbox(20, alignment = Pos.CENTER)
    init {
        val linechart = linechart("Chart for mergesort methods", CategoryAxis(), NumberAxis())

        val metricsStart = 1000000
        val metricsEnd = 10000000
        val step = 1000000


        val regularMergeSortMetrics = XYChart.Series<String, Number>()
        val concurrentMergeSortMetrics = XYChart.Series<String, Number>()
        regularMergeSortMetrics.name = "Mergesort"
        concurrentMergeSortMetrics.name = "Concurrent Mergesort"
        for (i in metricsStart..metricsEnd step step) {
            val input = Array<Int>(i){(0..Int.MAX_VALUE).random()}

            var startTime = System.nanoTime()
            val dispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
            MergeSortConcurrent(input).divideAndConquer(dispatcher)
            var endTime = System.nanoTime()
            concurrentMergeSortMetrics.data.add(XYChart.Data(i.toString(), endTime-startTime))
            println("Concurrent mergesort done ($i)")

            startTime = System.nanoTime()
            MergeSort(input).divideAndConquer()
            endTime = System.nanoTime()
            regularMergeSortMetrics.data.add(XYChart.Data(i.toString(), endTime-startTime))
            println("Mergesort done ($i)")
        }
        linechart.data.add(regularMergeSortMetrics)
        linechart.data.add(concurrentMergeSortMetrics)

        this.root.add(linechart)
    }
}

