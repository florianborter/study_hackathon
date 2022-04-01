package ch.bfh.kotlin.experiments.and.fibonacci

import ch.bfh.kotlin.experiments.and.DivideAndConquerable
import ch.bfh.kotlin.experiments.and.DivideAndConquerableConcurrent
import ch.bfh.kotlin.experiments.and.DivideAndConquerableMemory
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.stage.Stage
import kotlinx.coroutines.*
import tornadofx.*
import java.util.concurrent.Executors

class FibonacciConquer(private val fib: Int) : DivideAndConquerable<Int> {
    override val isBasic: Boolean
        get() = fib <= 1

    override fun baseFun(): Int {
        return fib
    }

    override fun decompose(): List<DivideAndConquerable<Int>?> {
        return listOf(FibonacciConquer(fib-1), FibonacciConquer(fib-2))
    }

    override fun recombine(intermediateResults: MutableList<Int?>): Int {
        return intermediateResults[0]!! + intermediateResults[1]!!
    }

}

class FibonacciConquerConcurrent(private val fib: Int) : DivideAndConquerableConcurrent<Int> {
    override val isBasic: Boolean
        get() = fib <= 1

    override fun baseFun(): Int {
        return fib
    }

    override fun decompose(): List<DivideAndConquerableConcurrent<Int>?>? {
        return listOf(FibonacciConquerConcurrent(fib-1), FibonacciConquerConcurrent(fib-2))
    }

    override fun recombine(intermediateResults: MutableList<Int?>): Int {
        return intermediateResults[0]!! + intermediateResults[1]!!
    }

}

class FibonacciConquerMemory(private val fib: Int) : DivideAndConquerableMemory<Int> {
    override val isBasic: Boolean
        get() = fib <= 1

    override fun baseFun(): Int {
        return fib
    }

    override fun decompose(): List<DivideAndConquerableMemory<Int>?>? {
        return listOf(FibonacciConquerMemory(fib-2), FibonacciConquerMemory(fib-1))
    }

    override fun recombine(intermediateResults: MutableList<Int?>): Int {
        return intermediateResults[0]!! + intermediateResults[1]!!
    }

    override fun memoryOrDefault(d: DivideAndConquerableMemory<Int>?, memory: IntArray): Int {
        if (memory[fib] != 0) {
            return memory[fib]
        }
        memory[fib] = d!!.divideAndConquer(memory)
        return memory[fib]
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

class ChartView : View("Fibonacci Chart") {
    override val root = vbox(20, alignment = Pos.CENTER)
    init {
        val linechart = linechart("Chart for fib methods", CategoryAxis(), NumberAxis())

        val fibonacciMetricsStart = 5
        val fibonacciMetricsEnd = 40
        val fibonacciMetricsStep = 5

        val memoryFibonacciMetrics = XYChart.Series<String, Number>()
        memoryFibonacciMetrics.name = "Fibonacci with memory"
        for (i in fibonacciMetricsStart..fibonacciMetricsEnd step fibonacciMetricsStep) {
            val memory = IntArray(i + 1){0}
            val startTime = System.nanoTime()
            FibonacciConquerMemory(i).divideAndConquer(memory)
            val endTime = System.nanoTime()
            memoryFibonacciMetrics.data.add(XYChart.Data(i.toString(), endTime-startTime))
            println("Memory Fibonacci ($i) done")
        }
        linechart.data.add(memoryFibonacciMetrics)

        val threadedFibonacciMetrics = XYChart.Series<String, Number>()
        threadedFibonacciMetrics.name = "Fibonacci with threads"
        for (i in fibonacciMetricsStart..fibonacciMetricsEnd step fibonacciMetricsStep) {
            val dispatcher = Executors.newFixedThreadPool(12).asCoroutineDispatcher()
            val startTime = System.nanoTime()
            FibonacciConquerConcurrent(i).divideAndConquer(dispatcher)
            val endTime = System.nanoTime()
            threadedFibonacciMetrics.data.add(XYChart.Data(i.toString(), endTime-startTime))
            println("Threaded Fibonacci ($i) done")
        }
        linechart.data.add(threadedFibonacciMetrics)

        val regularFibonacciMetrics = XYChart.Series<String, Number>()
        regularFibonacciMetrics.name = "Fibonacci without optimization"
        for (i in fibonacciMetricsStart..fibonacciMetricsEnd step fibonacciMetricsStep) {
            val startTime = System.nanoTime()
            FibonacciConquer(i).divideAndConquer()
            val endTime = System.nanoTime()
            regularFibonacciMetrics.data.add(XYChart.Data(i.toString(), endTime-startTime))
            println("Regular Fibonacci ($i) done")
        }
        linechart.data.add(regularFibonacciMetrics)

        this.root.add(linechart)
    }
}



