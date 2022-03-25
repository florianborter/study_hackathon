package ch.bfh.kotlin.experiments.and.linear

fun main() {
    val toBeSorted = intArrayOf(3,6,4,9,77,3,5,1,2,3,7,2,5,47,87,44,34,23,82,28,9,2,35,60,777,31)

    println(insertionSort(toBeSorted).joinToString { it.toString() })
}

// θ( (n^2 - 3n + 2 ) / 2)
fun insertionSort(input: IntArray): IntArray {
    return if (input.size < 2) {input} else {
        // θ(n)
        for (i in 1 until input.size) {
            // θ(1)
            val currentValue = input[i]
            // θ(1)
            var currentPrefixPosition = i - 1
            // worst case θ(n-1)
            while (currentPrefixPosition >= 0 && currentValue < input[currentPrefixPosition]) {
                // θ(1)
                input[currentPrefixPosition + 1] = input[currentPrefixPosition--]
            }
            // θ(1)
            input[currentPrefixPosition + 1] = currentValue
        }
        input
    }
}
