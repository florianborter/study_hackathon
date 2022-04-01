package ch.bfh.kotlin.experiments.and.mergesort

import ch.bfh.kotlin.experiments.and.DivideAndConquerable
import ch.bfh.kotlin.experiments.and.DivideAndConquerableConcurrent

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

fun main() {
    val input = arrayOf(2,5,6,3,3,7,2,1,8,6,4,4,4,6,7)
    val x = MergeSort(input).divideAndConquer()
    for(i in x) {
        print(i)
    }
}