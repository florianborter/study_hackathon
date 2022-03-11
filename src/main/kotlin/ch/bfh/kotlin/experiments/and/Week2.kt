package ch.bfh.kotlin.experiments.and

import kotlinx.coroutines.*
import tornadofx.runAsync
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

interface DivideAndConquerable<OutputType> {
    val isBasic: Boolean

    fun baseFun(): OutputType
    fun decompose(): List<DivideAndConquerable<Int>?>?
    fun recombine(intermediateResults: MutableList<Int?>): OutputType

    fun stump(): List<DivideAndConquerable<OutputType>?>? {
        return ArrayList(0)
    }
    fun divideAndConquer(): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerable<Int>?>? = decompose()
        val intermediateResults: MutableList<Int?> = ArrayList(
            subcomponents!!.size
        )
        subcomponents.forEach { subcomponent: DivideAndConquerable<Int>? ->
            intermediateResults.add(
                subcomponent!!.divideAndConquer()
            )
        }
        return recombine(intermediateResults)
    }
}

interface DivideAndConquerableConcurrent<OutputType>: DivideAndConquerable<OutputType> {
    fun divideAndConquer(dispatcher: ExecutorCoroutineDispatcher): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerableConcurrent<Int>?>? = decompose()
        val intermediateResults: MutableList<Int?> = ArrayList(
            subcomponents!!.size
        )


        if(Thread.activeCount() > 4) {
            intermediateResults.add(subcomponents[0]!!.divideAndConquer(dispatcher))
            intermediateResults.add(subcomponents[1]!!.divideAndConquer(dispatcher))
        } else {
            runBlocking {
                val one = async(dispatcher) {
                    intermediateResults.add(subcomponents[0]!!.divideAndConquer(dispatcher))
                }
                val two = async(dispatcher) {
                    intermediateResults.add(subcomponents[1]!!.divideAndConquer(dispatcher))
                }

                one.await()
                two.await()
            }

        }
        return recombine(intermediateResults)
    }

    override fun decompose(): List<DivideAndConquerableConcurrent<Int>?>?
}

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





fun main(args: Array<String>) {
    val fib = 45

    val dispatcher = Executors.newFixedThreadPool(6).asCoroutineDispatcher()
    val start2 = System.nanoTime()
    println(FibonacciConquerConcurrent(fib).divideAndConquer(dispatcher))
    val end2 = System.nanoTime()

    val start1 = System.nanoTime()
    println(FibonacciConquer(fib).divideAndConquer())
    val end1 = System.nanoTime()



    println("Non-Optimised fibonacci $fib:")
    println("passed Time: ${(end1 - start1) / 1000000000 }")
    println("Threaded fibonacci $fib:")
    println("passed Time: ${(end2 - start2) / 1000000000 }")
}



