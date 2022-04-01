package ch.bfh.kotlin.experiments.and.mergesort

import ch.bfh.kotlin.experiments.and.DivideAndConquerableConcurrent

class MergeSort(private val partialList: Array<Int>) : DivideAndConquerableConcurrent<Array<Int>> {
    override val isBasic: Boolean
        get() {
            return partialList.size < 3
        }

    override fun baseFun(): Array<Int> {
        return partialList
    }

    override fun decompose(): List<MergeSort> {
        TODO("Not yet implemented")
    }

    override fun recombine(intermediateResults: MutableList<Array<Int>>): Array<Int> {
        TODO("Not yet implemented")
    }

}