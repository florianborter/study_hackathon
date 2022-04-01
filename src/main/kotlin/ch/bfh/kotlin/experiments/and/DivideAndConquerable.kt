package ch.bfh.kotlin.experiments.and

import kotlinx.coroutines.*

// θ (2^n)
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

        if(Thread.activeCount() > 10) {
            for (x in subcomponents) {
                intermediateResults.add(x!!.divideAndConquer(dispatcher))
            }
        } else {
            runBlocking {
                val waitList = Array<Deferred<Boolean>>(subcomponents.size){ CompletableDeferred(false) }

                for (x in subcomponents.indices) {
                    waitList[x] = async(dispatcher) {
                        intermediateResults.add(subcomponents[x]!!.divideAndConquer(dispatcher)) }
                }
                waitList.forEach { it -> it.await() }
            }
        }
        return recombine(intermediateResults)
    }

    override fun decompose(): List<DivideAndConquerableConcurrent<Int>?>?
}

interface DivideAndConquerableMemory<OutputType>: DivideAndConquerable<OutputType> {
    fun divideAndConquer(memorized: IntArray): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerableMemory<Int>?>? = decompose()
        val intermediateResults: MutableList<Int?> = ArrayList(
            subcomponents!!.size
        )

        subcomponents.forEach { subcomponent: DivideAndConquerableMemory<Int>? ->
            intermediateResults.add(
                memoryOrDefault(subcomponent, memorized)
            )
        }

        return recombine(intermediateResults)
    }

    fun memoryOrDefault(d: DivideAndConquerableMemory<Int>?, memory: IntArray): Int

    override fun decompose(): List<DivideAndConquerableMemory<Int>?>?
}