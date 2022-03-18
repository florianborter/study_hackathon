package ch.bfh.kotlin.experiments.and.linear

fun main() {
    val toBeSorted = intArrayOf(3,6,4,9,77,3,5,1,2,3,7,2,5,47,87,44,34,23,82,28,9,2,35,60,777,31)

    println(insertionSort(toBeSorted).joinToString { it.toString() })
}


fun insertionSort(input: IntArray): IntArray {
    return if (input.size < 2) {input} else {
        for (i in 1 until input.size) {
            val currentValue = input[i]
            var currentPrefixPosition = i - 1
            while (currentPrefixPosition >= 0 && currentValue < input[currentPrefixPosition]) {
                input[currentPrefixPosition + 1] = input[currentPrefixPosition--]
            }
            input[currentPrefixPosition + 1] = currentValue
        }
        input
    }
}
