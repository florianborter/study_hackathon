package ch.bfh.kotlin.experiments.and

import kotlinx.coroutines.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger

// θ (2^n)
interface DivideAndConquerable<OutputType> {
    val isBasic: Boolean

    fun baseFun(): OutputType
    fun decompose(): List<DivideAndConquerable<OutputType>>
    fun recombine(intermediateResults: MutableList<OutputType>): OutputType

    fun stump(): List<DivideAndConquerable<OutputType>> {
        return ArrayList(0)
    }
    fun divideAndConquer(): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerable<OutputType>> = decompose()
        val intermediateResults: MutableList<OutputType> = ArrayList(
            subcomponents.size
        )
        subcomponents.forEach { subcomponent: DivideAndConquerable<OutputType> ->
            intermediateResults.add(
                subcomponent.divideAndConquer()
            )
        }
        return recombine(intermediateResults)
    }
}

var nThreadslol: AtomicInteger = AtomicInteger(0)

interface DivideAndConquerableConcurrent<OutputType>: DivideAndConquerable<OutputType> {
    fun divideAndConquer(dispatcher: ExecutorService, nThreads: Int): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerableConcurrent<OutputType>> = decompose()
        val intermediateResults: MutableList<OutputType> = ArrayList(
            subcomponents.size
        )

        for (x in subcomponents) {
            if(nThreads == 0 || nThreadslol.get() >= nThreads) {
                intermediateResults.add(x.divideAndConquer(dispatcher, nThreads))
            } else {
                val futures = ArrayList<Future<OutputType>>()
                for (x in subcomponents.indices) {
                    val worker = Callable {
                        nThreadslol.getAndIncrement()
                        val res = subcomponents[x].divideAndConquer(dispatcher, nThreads)
                        nThreadslol.getAndIncrement()
                        res
                    }
                    futures.add(dispatcher.submit(worker))
                }

                for (x in subcomponents.indices) {
                    intermediateResults.add(futures[x].get())
                }
            }
        }
        return recombine(intermediateResults)
    }

    override fun decompose(): List<DivideAndConquerableConcurrent<OutputType>>
}

interface DivideAndConquerableMemory<OutputType>: DivideAndConquerable<OutputType> {
    fun divideAndConquer(memorized: IntArray): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerableMemory<OutputType>> = decompose()
        val intermediateResults: MutableList<OutputType> = ArrayList(
            subcomponents.size
        )

        subcomponents.forEach { subcomponent: DivideAndConquerableMemory<OutputType> ->
            intermediateResults.add(
                memoryOrDefault(subcomponent, memorized)
            )
        }

        return recombine(intermediateResults)
    }

    fun memoryOrDefault(d: DivideAndConquerableMemory<OutputType>, memory: IntArray): OutputType

    override fun decompose(): List<DivideAndConquerableMemory<OutputType>>
}