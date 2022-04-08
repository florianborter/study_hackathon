package ch.bfh.kotlin.experiments.and.mergesort

import ch.bfh.kotlin.experiments.and.DivideAndConquerable
import ch.bfh.kotlin.experiments.and.DivideAndConquerableConcurrent
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import kotlinx.coroutines.asCoroutineDispatcher
import org.jfree.chart3d.Chart3D
import org.jfree.chart3d.Chart3DFactory
import org.jfree.chart3d.Orientation
import org.jfree.chart3d.data.Range
import org.jfree.chart3d.data.xyz.XYZSeries
import org.jfree.chart3d.data.xyz.XYZSeriesCollection
import org.jfree.chart3d.fx.Chart3DViewer
import org.jfree.chart3d.graphics3d.Dimension3D
import org.jfree.chart3d.legend.LegendAnchor
import org.jfree.chart3d.plot.XYZPlot
import org.jfree.chart3d.renderer.RainbowScale
import org.jfree.chart3d.renderer.xyz.SurfaceRenderer
import tornadofx.App
import tornadofx.launch
import java.awt.Color
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

class Chart : App() {
    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 500.0
        super.start(stage)

        val sp = StackPane()
        sp.children.add(Companion.createDemoNode())
        val scene = Scene(sp, 768.0, 512.0)
        stage.scene = scene
        stage.show()
    }

    companion object {
        /**
         * Creates and returns a node for the demo chart.
         *
         * @return A node for the demo chart.
         */
        fun createDemoNode(): Node {
            val chart = createChart()
            return Chart3DViewer(chart)
        }

        /**
         * Creates a surface chart for the demo.
         *
         * @return A surface chart.
         */
        private fun createChart(): Chart3D {
            val inputSizeStart = 10000000
            val inputSizeEnd = 100000000
            val inputSizeStep = 20000000
            val nThreadsStart = 4
            val nThreadsEnd = 32
            val nThreadsStep = 4

            val concurrentMergeSortMetrics = XYZSeriesCollection<String>()

            val metricsForNoThreads = XYZSeries("0")
            for (inputSize in inputSizeStart .. inputSizeEnd step inputSizeStep) {
                val input = Array<Int>(inputSize) { (0..Int.MAX_VALUE).random() }

                System.gc()
                val startTime = System.nanoTime()

                MergeSortConcurrent(input).divideAndConquer()

                val endTime = System.nanoTime()
                metricsForNoThreads.add(0.0, inputSize.toDouble(), ((endTime - startTime) / 1000000).toDouble())
                println("Concurrent mergesort done (0, $inputSize)")
            }
            concurrentMergeSortMetrics.add(metricsForNoThreads)

            for (nThreads in nThreadsStart .. nThreadsEnd step nThreadsStep) {
                val metricsForThreads = XYZSeries(nThreads.toString())

                for (inputSize in inputSizeStart .. inputSizeEnd step inputSizeStep) {
                    val input = Array<Int>(inputSize) { (0..Int.MAX_VALUE).random() }

                    System.gc()
                    val startTime = System.nanoTime()

                    val dispatcher = Executors.newFixedThreadPool(nThreads)
                    MergeSortConcurrent(input).divideAndConquer(dispatcher, nThreads)
                    val endTime = System.nanoTime()
                    dispatcher.shutdown()
                    metricsForThreads.add(nThreads.toDouble(), inputSize.toDouble(), ((endTime - startTime) / 1000000).toDouble())
                    println("Concurrent mergesort done ($nThreads, $inputSize)")
                }
                concurrentMergeSortMetrics.add(metricsForThreads)
            }


            val chart = Chart3DFactory.createXYZLineChart(
                "XYZ Line Chart Demo",
                "Orson Charts", concurrentMergeSortMetrics, "Threads", "InputSize", "Time"
            )
            chart.chartBoxColor = Color(255, 255, 255, 128)
            val plot = chart.plot as XYZPlot
            plot.dimensions = Dimension3D(10.0, 10.0, 10.0)

            return chart
        }
    }
}

