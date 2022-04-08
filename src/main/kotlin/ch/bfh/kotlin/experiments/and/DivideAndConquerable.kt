package ch.bfh.kotlin.experiments.and

import kotlinx.coroutines.*

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

interface DivideAndConquerableConcurrent<OutputType>: DivideAndConquerable<OutputType> {
    fun divideAndConquer(dispatcher: ExecutorCoroutineDispatcher, nThreads: Int): OutputType {
        if (this.isBasic) return baseFun()
        val subcomponents: List<DivideAndConquerableConcurrent<OutputType>> = decompose()
        val intermediateResults: MutableList<OutputType> = ArrayList(
            subcomponents.size
        )

        if(Thread.activeCount() > nThreads) {
            for (x in subcomponents) {
                intermediateResults.add(x.divideAndConquer(dispatcher, nThreads))
            }
        } else {
            runBlocking {
                val waitList = Array<Deferred<Boolean>>(subcomponents.size){ CompletableDeferred(false) }

                for (x in subcomponents.indices) {
                    waitList[x] = async(dispatcher) {
                        intermediateResults.add(subcomponents[x].divideAndConquer(dispatcher, nThreads)) }
                }
                waitList.forEach { it -> it.await() }
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