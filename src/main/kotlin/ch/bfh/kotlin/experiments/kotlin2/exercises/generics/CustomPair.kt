package ch.bfh.kotlin.experiments.kotlin2.exercises.generics

open class CustomPair<S, T>(_first: S, _second: T) {
    var first = _first
        protected set
    var second = _second
        protected set

    fun swapedPair() = CustomPair(second, first)

    override fun toString(): String {
        return "${this.javaClass}: First: $first, Second: $second"
    }
}

class Couple<T>(_first: T, _second: T) : CustomPair<T, T>(_first, _second) {
    fun swap() {
        val temp = first
        first = second
        second = temp
    }
}

fun <E : Comparable<E>> ArrayList<out E>.minmax(): Couple<E> {
    var min = this[0]
    var max = this[0]

    for (e in this) {
        if (e < min) {
            min = e
        }
        if (e > max) {
            max = e
        }
    }

    return Couple(min, max)
}

fun main() {
    val coupleOfInts = Couple(4, 5)
    println(coupleOfInts)
    coupleOfInts.swap()
    println(coupleOfInts)

    val listOfInts = arrayListOf(3, 46, 7, 9, 987)
    println(listOfInts.minmax())

    val listOfStrings = arrayListOf("ha", "hi", "hoho", "hihi")
    println(listOfStrings.minmax())
}