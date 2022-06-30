package ch.bfh.kotlin.experiments.kotlin2.exercises.recursion

fun binominal_rec(n: Int, k: Int): Int {
    if (k == n || k == 0) return 1
    if (0 <= k && k < n) {
        return binominal_rec(n - 1, k) + binominal_rec(n - 1, k - 1)
    }
    return 0
}

tailrec fun binomial_real(n: Double, k: Double, acc: Double = 1.0): Double {
    if (k == 0.0) return acc
    return binomial_real(n - 1, k - 1, acc * n / k)
}

fun main() {
    println(binominal_rec(5, 2))
    println(binomial_real(5.0, 2.0))
}